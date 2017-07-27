package fr.simplon.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.simplon.dao.TypeDao;
import fr.simplon.domain.TypeAbsence;
import fr.simplon.exception.ServiceException;

/**
 * service gérant le type
 * C'est la couche métier.
 * 
 * @author simplon
 *
 */
@Service
@Transactional
public class TypeService {
	
	
	@Autowired
	private TypeDao typeDao;

	/**
	 * Liste des type d'absence
	 * 
	 * @return une liste
	 * @throws SQLException
	 */
	/*
	 * La methode [findAll] retourne une liste de la table 
	 * La methode [findAll] est overrride.
	 */
	public List<TypeAbsence> listeTypeAbsence() throws SQLException {
		List<TypeAbsence> resultat;
		try {
			resultat = typeDao.findAll();
		} catch (ServiceException e) {
			throw new ServiceException("Hibernate Error !: listeTypeAbsence" + e);
		}
		return resultat;
	}

	/**
	 * Recherche d'un type d'absence
	 * 
	 * @param nom
	 * @return une liste de type absence en fonction du nom
	 * @throws SQLException
	 */
	/*
	 * Meme principe que ci-dessus une iteration qu'on transforme en liste
	 */
	public TypeAbsence getTypeByName(String nom) throws SQLException {
		TypeAbsence resultat;
		try {
			resultat = typeDao.findTypeByNom(nom);
		} catch (ServiceException e) {
			throw new ServiceException("Hibernate Error !: getTypeByName" + e);
		}
		return resultat;
	}

	/**
	 * Creation nouveau type d'absence
	 * 
	 * @param type d'absence
	 * @return objet
	 * @throws SQLException
	 */
	/*
	 * Simple methode hibernate pour la creation d'un nouveau type d'absence
	 * J'ai crée un bojet Absence pour avoir le resultat de la creation en
	 * retour
	 */
	public TypeAbsence insertTypeAbsence(TypeAbsence typeAbsence) throws SQLException {
		TypeAbsence creationTypeAbs;
		try {
			creationTypeAbs = typeDao.save(typeAbsence);
		} catch (ServiceException e) {
			throw new ServiceException("Hibernate Error !: insertTypeAbsence" + e);
		}
		return creationTypeAbs;
	}

	/**
	 * Modification type Absence
	 * 
	 * @param type absence
	 * @return Objet
	 * @throws SQLException
	 */
	/*
	 * Même principe que creation
	 */
	public TypeAbsence updateTypeAbsence(TypeAbsence typeAbsence) throws SQLException {
		System.out.println(typeAbsence.getId()+" "+typeAbsence.getNom());
		TypeAbsence modifTypeAbs;
		try {
			modifTypeAbs = typeDao.save(typeAbsence);
		} catch (ServiceException e) {
			throw new ServiceException("Hibernate Error !: updateAbsence" + e);
		}
		return modifTypeAbs;
	}

	/**
	 * Suppression Type Absence
	 * 
	 * @param type absence
	 * @throws SQLException
	 */
	/*
	 * On commence par faire une recherche d'un service absence avec la methode
	 * [findByName()] Et on supprime l'objet par la methode delete d'hibernate
	 * qui supprime une entité complete. Cette methode peut etre appelé à
	 * evoluer
	 */
	public void deleteTypeAbsence(TypeAbsence typeAbsence) throws SQLException {
		try {
			typeDao.delete(typeAbsence);
		} catch (ServiceException e) {
			throw new ServiceException("Hibernate Error !: deleteTypeAbsence" + e);
		}
	}

}
