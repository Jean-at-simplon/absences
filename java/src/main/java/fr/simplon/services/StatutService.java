package fr.simplon.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.simplon.dao.StatutDao;
import fr.simplon.domain.Statut;
import fr.simplon.exception.ServiceException;

@Service
@Transactional
public class StatutService {

		
	@Autowired
	private StatutDao statutDao;
	

	/**
	 * Liste des statuts
	 * 
	 * @return une liste
	 * @throws SQLException
	 */
	/*
	 * La methode [findAll] retourne une liste de la table 
	 * La methode [findAll] est overrride.
	 */
	public List<Statut> listeStatut() throws SQLException {
		List<Statut> resultat;
		try {
			resultat = statutDao.findAll();
		} catch (ServiceException e) {
			throw new ServiceException("Hibernate Error !: listeAbsence" + e);
		}
		return resultat;
	}

	/**
	 * Recherche d'un statut
	 * 
	 * @param nom
	 * @return une liste des services absence en fonction du code
	 * @throws SQLException
	 */
	/*
	 * Meme principe que ci-dessus une iteration qu'on transforme en liste
	 */
	public Statut getStatutByCode(int code) throws SQLException {
		Statut resultat;
		try {
			resultat = statutDao.findByCode(code);
		} catch (ServiceException e) {
			throw new ServiceException("Hibernate Error !: listeAbsence" + e);
		}
		return resultat;
	}

	/**
	 * Creation nouveau statut
	 * 
	 * @param statut
	 * @return objet
	 * @throws SQLException
	 */
	/*
	 * Simple methode hibernate pour la creation d'un nouveau statut
	 * J'ai crée un bojet Absence pour avoir le resultat de la creation en
	 * retour
	 */
	public Statut insertStatut(Statut statut) throws SQLException {
		Statut creationStatut;
		try {
			creationStatut = statutDao.save(statut);
		} catch (ServiceException e) {
			throw new ServiceException("Hibernate Error !: insertAbsence" + e);
		}
		return creationStatut;
	}

	/**
	 * Modification statut
	 * 
	 * @param statut
	 * @return Objet
	 * @throws SQLException
	 */
	/*
	 * Même principe que creation
	 */
	public Statut updateStatut(Statut statut) throws SQLException {
		Statut modifStatut;
		try {
			modifStatut = statutDao.save(statut);
		} catch (ServiceException e) {
			throw new ServiceException("Hibernate Error !: updateAbsence" + e);
		}
		return modifStatut;
	}

	/**
	 * Suppression statut
	 * 
	 * @param statut
	 * @throws SQLException
	 */
	/*
	 * On commence par faire une recherche d'un service absence avec la methode
	 * [findByName()] Et on supprime l'objet par la methode delete d'hibernate
	 * qui supprime une entité complete. Cette methode peut etre appelé à
	 * evoluer
	 */
	public void deleteStatut(Statut statut) throws SQLException {
		try {
			statutDao.delete(statut);
		} catch (ServiceException e) {
			throw new ServiceException("Hibernate Error !: deleteAbsence" + e);
		}
	}
}