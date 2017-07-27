package fr.simplon.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.simplon.dao.EquipeDao;
import fr.simplon.domain.Equipe;
import fr.simplon.exception.EmailException;
import fr.simplon.exception.ServiceException;

/**
 * service gérant le equipe C'est la couche métier.
 * 
 * @author simplon
 *
 */
@Service
public class EquipeService {

	@Autowired
	private EquipeDao equipeDao;

	public List<Equipe> listEquipes() throws SQLException {
		List<Equipe> resultat = new ArrayList<Equipe>();
		try {
			resultat = equipeDao.findAll();
		} catch (EmailException e) {
			throw new ServiceException("Hibernate Error !: listEquipes" + e);
		}
		return resultat;
	}

	public Equipe getEquipe(String nom) throws SQLException {
		Equipe equipe;
		try {
			equipe = equipeDao.findEquipeByNom(nom);
		} catch (EmailException e) {
			throw new ServiceException("Hibrnate Error !: getEquipe" + e);
		}
		return equipe;
	}

	public Equipe insertEquipe(Equipe equipe) throws SQLException {
		Equipe creationEquipe = new Equipe();
		try {
			creationEquipe = equipeDao.save(equipe);
		} catch (EmailException e) {
			throw new ServiceException("Hibrnate Error !: insertEquipe" + e);
		}
		return creationEquipe;
	}

	public Equipe updateEquipe(Equipe equipe) throws SQLException {
		Equipe modifEquipe;
		try {
			modifEquipe = equipeDao.save(equipe);
		} catch (EmailException e) {
			throw new ServiceException("Hibrnate Error !: updateEquipe" + e);
		}
		return modifEquipe;
	}

	public void deleteEquipe(Equipe supprEquipe) throws SQLException {
		try {
			equipeDao.delete(supprEquipe);
		} catch (EmailException e) {
			throw new ServiceException("Hibrnate Error !: deleteEquipe" + e);
		}
	}

}
