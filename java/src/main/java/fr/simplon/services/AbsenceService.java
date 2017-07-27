package fr.simplon.services;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.simplon.dao.AbsenceDao;
import fr.simplon.domain.Absence;
import fr.simplon.domain.dto.AbsenceDto;
import fr.simplon.exception.ServiceException;
import fr.simplon.services.utils.EmailService;
import fr.simplon.services.utils.MapperDto;
import fr.simplon.services.utils.TraitementAbsence;
import fr.simplon.services.utils.UtilitairesService;

/**
 * Classe metier des absences
 * 
 * @author JGL
 *
 */

@Service
@Transactional
public class AbsenceService {

	//Il s'agit de l'injection de dépendance de Spring
	@Autowired
	private AbsenceDao absenceDao;
	
	@Autowired
	MapperDto mapper;
	
	@Autowired
	private TraitementAbsence traitementAbsence;
	
	@Autowired
	private UtilitairesService utilitaire;
	
	@Autowired
	private EmailService emailService;

	/**
	 * Liste des absences
	 * 
	 * @return une liste
	 * @throws SQLException
	 */
	/*
	 * La methode [findAll] retourne une liste de la table 
	 * La methode [findAll] est overide.
	 */
	public List<AbsenceDto> listeServicesAbsence() throws SQLException {
		List<AbsenceDto> resultat;
		List<Absence> absence;
		try {
			//recherche la liste complète des absences
			absence = absenceDao.findAll();
			//converti le resultat en dto
			resultat = mapper.convertListeAbsenceToDto(absence);
		} catch (Exception e) {
			throw new SQLException("Hibernate Error !: listeAbsence" + e);
		}
		return resultat;
	}

	/**
	 * Recherche d'une Absence
	 * 
	 * @param numero
	 * @return une absence en fonction du numero
	 * @throws SQLException
	 */
	/*
	 * Meme principe que ci-dessus une iteration qu'on transforme en liste
	 */
	public AbsenceDto getAbsenceByNumero(String numDemande) throws SQLException {
		AbsenceDto resultat;
		Absence absence;
		try {
			absence = absenceDao.findByNumDemande(numDemande);
			resultat=mapper.convertAbsToDto(absence);
		} catch (Exception e) {
			throw new SQLException("Hibernate Error !: getAbsenceById" + e);
		}
		return resultat;
	}
	
	/**
	 * Recherche d'une Absence
	 * 
	 * @param nom
	 * @return une liste des services absence en fonction du nom
	 * @throws SQLException
	 */
	/*
	 * Meme principe que ci-dessus une iteration qu'on transforme en liste
	 */
	public List<AbsenceDto> getAbsenceById(Long id) throws SQLException {
		List<AbsenceDto> resultat;
		List<Absence> absence;
		try {
			absence = absenceDao.findById(id);
			resultat=mapper.convertListeAbsenceToDto(absence);
		} catch (Exception e) {
			throw new SQLException("Hibernate Error !: getAbsenceById" + e);
		}
		return resultat;
	}

	/**
	 * Creation nouveau service Absence
	 * 
	 * @param absence
	 * @return objet
	 * @throws Exception 
	 */
	/*
	 * Appelle une classe spécifique afin de verifier la validité des congés
	 */
	public AbsenceDto insertAbsence(AbsenceDto absenceDto) throws Exception {
		
		//créer un objet absence Dto
		AbsenceDto resultatAbs = null;
		try {
			//vérifie que la date de fin de congé ne soit pas inférieur à la date de début
			if(absenceDto.getDebut().compareTo(absenceDto.getFin())>0){
				throw new ServiceException("La date de fin de congé doit être supèrieur à la date de début");
			} else
				try {
					{
						//envoie la demande vers le traitement
						//implemente le numéro de demande par un timeStamp
						Date now = new DateTime().toDate();
						absenceDto.setRelance(now);
						absenceDto.setNumDemande(utilitaire.findNumDemande());
						resultatAbs = traitementAbsence.demanderAbsence(absenceDto);
						
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} catch (ServiceException e) {
			throw new ServiceException("Hibernate Error !: insertAbsence");
		}
		return resultatAbs;
	}

	/**
	 * Modification service Absence
	 * 
	 * @param absenceDto
	 * @return Objet
	 * @throws SQLException
	 */
	/*
	 * Même principe que creation
	 */
	public AbsenceDto updateAbsence(AbsenceDto absenceDto) throws SQLException {
		Absence modifAbs = mapper.convertDtoToAbs(absenceDto);
		try {
			if(absenceDto.getDebut().compareTo(absenceDto.getFin())>0){
				throw new Exception("La date de fin de congé doit être supèrieur à la date de début");
			} else {
				modifAbs = absenceDao.save(modifAbs);
				absenceDto = mapper.convertAbsToDto(modifAbs);
			}
		} catch (Exception e) {
			throw new SQLException("Hibernate Error !: insertAbsence" + e);
		}
		return absenceDto;
	}
	
	public AbsenceDto reflateAbsence(String numDemande) throws SQLException {
		Absence absence = absenceDao.findByNumDemande(numDemande);
		AbsenceDto absenceDto;
		DateTime now = new DateTime();
		DateTime demande= new DateTime(absence.getRelance());
		try {
			if(demande.toLocalDate().compareTo(now.toLocalDate())==0){
				throw new ServiceException("Vous avez deja fait une relance aujourd'hui");
			} else {
				Date maintenant = now.toDate();
				absence.setRelance(maintenant);
				absenceDao.save(absence);
				absenceDto = mapper.convertAbsToDto(absence);
				emailService.sendEmail(absence, absence.getEmploye().getEquipe().getResponsable().getUser().getEmail(), "Relance de congé",2);
			}
		} catch (Exception e) {
			throw new ServiceException("Erreur de relance" + e);
		}
		
		return absenceDto;
	}

	/**
	 * Suppression Service Absence
	 * 
	 * @param absence
	 * @throws SQLException
	 */
	/*
	 * On commence par faire une recherche d'un service absence avec la methode
	 * [findByName()] Et on supprime l'objet par la methode delete d'hibernate
	 * qui supprime une entité complete. Cette methode peut etre appelé à
	 * evoluer
	 */
	public void deleteAbsence(AbsenceDto absenceDto) throws SQLException {
		try {
			Date now = new Date();
			if(now.compareTo(absenceDto.getDebut())>0){
				throw new ServiceException("Annulation impossible!");
			} else {
			Absence absence = mapper.convertDtoToAbs(absenceDto);
			absenceDao.delete(absence);
			}
		} catch (Exception e) {
			throw new SQLException("Hibernate Error !: deleteAbsence" + e);
		}
	}
}
