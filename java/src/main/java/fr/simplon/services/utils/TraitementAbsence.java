package fr.simplon.services.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.simplon.dao.AbsenceDao;
import fr.simplon.dao.EmployeDao;
import fr.simplon.dao.StatutDao;
import fr.simplon.dao.TypeDao;
import fr.simplon.domain.Absence;
import fr.simplon.domain.Employe;
import fr.simplon.domain.dto.AbsenceDto;
import fr.simplon.exception.ServiceException;
import freemarker.template.TemplateException;

/**
 * Classe de service pour la verification des droits à congés
 * 
 * le calcul des reliquats, et la sauvegarde des données
 * 
 * 
 * @author JGL
 *
 */
//indique que c'esu un service, pas besoin d'être instancier
@Service
//indique que la classe assure les transactions avec la base de données
@Transactional
public class TraitementAbsence {

	@Autowired
	AbsenceDao absenceDao;

	@Autowired
	TypeDao typeDao;

	@Autowired
	StatutDao statutDao;

	@Autowired
	EmployeDao employeDao;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	MapperDto mapper;

	public AbsenceDto demanderAbsence(AbsenceDto absenceDto) throws ServiceException {
		Absence nouvelleAbs;
		AbsenceDto creationAbs = null;
		Employe employe = employeDao.findByMatricule(absenceDto.getMatricule());
		int resultatAbs = 0;

		try {
			//Création d'un objet absence à partir du Dto reçu du front
			nouvelleAbs = mapper.convertDtoToAbs(absenceDto);
			//Vérification des droits à congés et calcul du nombre de jours pris
			resultatAbs = decompterJours(nouvelleAbs);
			// Vérification du nombre de jours d'absences pas supérieur au reliquat
			if (resultatAbs < 0) {
				throw new ServiceException("Vous avez pris trop de " + nouvelleAbs.getType().getNom());
			}
			
			//changement du statut de la demande de "nouvelle demande" à "en attente de validation"
			nouvelleAbs.setStatut(statutDao.findByCode(1));
			//Mise à jour des reliquats de l'employé
			if (nouvelleAbs.getType().getNom().equals("Congé payé")){
				employe.setNbCa(resultatAbs);
			} else if (nouvelleAbs.getType().getNom().equals("RTT")){
				employe.setNbRtt(resultatAbs);
			} else if (nouvelleAbs.getType().getNom().equals("Repos compensateur")){
				employe.setNbRc(resultatAbs);
			}
			//recupère le résultat de la demande pour mis à jour du front, au format absenceDto
			creationAbs = mapper.convertAbsToDto(nouvelleAbs);
			//mise à jour de la BDD absence
			absenceDao.save(nouvelleAbs);
			//mise à jour de la BDD employé
			employeDao.save(employe);
			
			//envoi d'un email au N+1
			emailService.sendEmail(nouvelleAbs, employe.getEquipe().getResponsable().getUser().getEmail(), "demande de congé",1);

		} catch (ServiceException e) {
			
			throw new ServiceException(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return creationAbs;
	}


	// Décompte des jours d'absences, utilisation de la librairie Joda-Time

	public int decompterJours(Absence absence) throws ServiceException {
		DateTime dateDebut = new DateTime(absence.getDebut());
		DateTime dateFin = new DateTime(absence.getFin());
		//calcul de la durée des congés, il faut retirer un jour à la date de début, sinon elle sort du calcul
		Duration duree = new Duration(dateDebut.minusDays(1), dateFin);
		//qui est convertie en jour, le resultat retourné étant un long, on le caste en int
		int joursAbs = Math.toIntExact(duree.getStandardDays());
		//initialisation du reliquat
		int reliquat = 0;

		try {
			// Vérifie que l'employé ne prend pas plus de 4 semaines de congés
			if (joursAbs > 28) {
				throw new ServiceException("Vous ne pouvez pas prendre plus de 4 semaines de congés");
			}

			//appelle une methose qui calcule les jours fériés de l'année et retourne une liste de ceux-ci
			List<DateTime> ferie = new ArrayList<DateTime>(JourFerie.calculerFerie(dateDebut.getYear()));

			// Retire les jours fériés se situant pendant les congés
			if (absence.getStatut().getCode() == 0) {
				for (DateTime dateFerie : ferie) {
					if (dateDebut.isBefore(dateFerie) && dateFin.isAfter(dateFerie)) {
						joursAbs--;
					}
				}

			}
			
			//On retire les samedi et dimanche qui ne sont pas travaillés
			joursAbs -= (joursAbs/7)*2;

			//calcul du reliquat de congé en fonction du type d'absence
			reliquat = calculerReliquat(joursAbs, absence);

		} catch (ServiceException e) {
			throw new ServiceException(e.getMessage());
		}
		return reliquat;
	}

	// Méthode décomptant les jours d'absence en fonction du type de congés
	private int calculerReliquat(int joursAbs, Absence absence) throws RuntimeException {

		switch (absence.getType().getNom()) {
		case "Congé payé":
			if (joursAbs < absence.getEmploye().getNbCa()) {
				int reliquat = absence.getEmploye().getNbCa() - joursAbs;
				return reliquat;
			}
		case "RTT":
			if (joursAbs < absence.getEmploye().getNbRtt()) {
				int reliquat = absence.getEmploye().getNbRtt() - joursAbs;
				return reliquat;
			}
		case "Repos compensateur":
			if (joursAbs < absence.getEmploye().getNbRc()) {
				int reliquat = absence.getEmploye().getNbRc() - joursAbs;
				return reliquat;
			}
		default:
			throw new ServiceException("Vous avez pris trop de " + absence.getType().getNom());
		}
	}
}
