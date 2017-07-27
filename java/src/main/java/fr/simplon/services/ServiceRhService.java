package fr.simplon.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.simplon.dao.ServiceRhDao;
import fr.simplon.domain.ServiceRh;
import fr.simplon.exception.EmailException;
import fr.simplon.exception.ServiceException;

/**
 * Classe métier du service Rh
 * 
 * @author JGL
 *
 */
@Service
@Transactional
public class ServiceRhService {

	@Autowired
	private ServiceRhDao rhDao;
	
	/**
	 * Liste des services Rh
	 * @return une liste
	 * @throws SQLException
	 */
	/*
	 * La methode [findAll] retourne une iteration de la table
	 * Le méthode "iterationToList(iteration)" permet de retourner
	 * le résultat sous forme de liste 
	 */
	public List<ServiceRh> listeServicesRh() throws SQLException {
		List<ServiceRh> resultat = new ArrayList<>();
		try {
			resultat = rhDao.findAll();
		} catch (Exception e) {
			throw new ServiceException("Hibernate Error !: listeServiceRh" + e);
		}
		return resultat;
	}

	/**
	 * Recherche d'un service Rh
	 * @param nom
	 * @return une liste des services rh en fonction du nom
	 * @throws SQLException
	 */
	/*
	 * Meme principe que ci-dessus : une iteration qu'on transforme en liste
	 * car une liste sera retourner, peu importe le nombre de lettres du mot
	 */
	public List<ServiceRh> getServiceRh(String nom) throws SQLException {
		List<ServiceRh> resultat = new ArrayList<>();
		try {
			resultat = rhDao.findByNomContaining(nom);
		} catch (Exception e) {
			throw new ServiceException("Hibernate Error !: listeServiceRh" + e);
		}
		return resultat;
	}

	/**
	 * Creation nouveau service Rh
	 * @param serviceRh
	 * @return objet
	 * @throws SQLException
	 */
	/*
	 * Simple methode hibernate pour la creation d'un nouveau service Rh
	 * J'ai crée un objet ServiceRh pour avoir le resultat de la creation en retour
	 */
	public ServiceRh insertServiceRh(ServiceRh serviceRh) throws SQLException {
		ServiceRh creationRh = new ServiceRh();
		try {
			if(rhDao.findByEmail(serviceRh.getEmail()).equals(serviceRh)){
				throw new EmailException();
			} else {
				creationRh = rhDao.save(serviceRh);
			}
		} catch (Exception e) {
			throw new EmailException();
		}
		return creationRh;
	}

	/**
	 * Modification service Rh
	 * @param serviceRh
	 * @return Objet
	 * @throws SQLException
	 */
	/*
	 * Même principe que creation
	 */
	public ServiceRh updateServiceRh(ServiceRh serviceRh) throws SQLException {
		ServiceRh modifRh = new ServiceRh();
		try {
			modifRh = rhDao.save(serviceRh);
		} catch (Exception e) {
			throw new ServiceException("Hibernate Error !: updateServiceRh" + e);
		}
		return (ServiceRh) modifRh;
	}

	/**
	 * Suppression Service Rh
	 * @param serviceRh
	 * @throws SQLException
	 */
	/*
	 * On commence par faire une recherche d'un service rh
	 * avec la methode [findByName()]
	 * Et on supprime l'objet par la methode delete
	 * d'hibernate qui supprime une entité complete.
	 * Cette methode peut etre appelé à evoluer
	 */
	public void deleteServiceRh(ServiceRh supprRh) throws SQLException {
		try{
			rhDao.delete(supprRh);
		} catch (Exception e) {
			throw new ServiceException("Hibernate Error !: deleteServiceRh" + e);
		}
	}
}
