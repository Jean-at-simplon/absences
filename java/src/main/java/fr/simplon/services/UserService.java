package fr.simplon.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.simplon.dao.UserDao;
import fr.simplon.domain.User;
import fr.simplon.domain.dto.UserDto;
import fr.simplon.exception.EmailException;
import fr.simplon.exception.ServiceException;
import fr.simplon.services.utils.MapperDto;

/**
 * Classe metier de la gestion des user
 * 
 * @author JGL
 *
 */
@Service
@Transactional
public class UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	MapperDto mapper;
	
	/**
	 * Liste des employes
	 * @return une liste
	 * @throws SQLException
	 */
	/*
	 * La methode [findAll] retourne une iteration de la table
	 * Avec la boucle [for], on la parcours et on retourne une
	 * liste de la table
	 */
	public List<UserDto> listeUsers() throws SQLException {
		List<User> listeUsers;
		List<UserDto> listeUsersDto;
		try {
			listeUsers = userDao.findAll();
			listeUsersDto = mapper.convertListUserToDto(listeUsers);
		} catch (ServiceException e) {
			throw new ServiceException("Hibernate Error !: listeEmployes" + e);
		}
		return listeUsersDto;
	}

	/**
	 * Recherche d'un user
	 * @param email
	 * @return un profil employé
	 * @throws SQLException
	 */
	/*
	 * Meme principe que ci-dessus
	 * une iteration qu'on transforme en liste
	 */
	public UserDto getUser(String email) throws SQLException {
		User getUser;
		UserDto getUserDto;
		try {
			getUser = userDao.findByEmail(email);
			getUserDto = mapper.convertUserToDto(getUser);
		} catch (ServiceException e) {
			throw new ServiceException("Hibernate Error !: getUser" + e);
		}
		return getUserDto;
}

	/**
	 * Creation nouveau user
	 * @param user
	 * @return objet
	 * @throws SQLException
	 */
	/*
	 * Simple methode hibernate pour la creation d'un nouveau user
	 * J'ai crée un objet User pour avoir le resultat de la creation en retour
	 */
	public User insertUser(User user) throws SQLException {
		try {
			if(!userDao.findByEmail(user.getEmail()).equals(null)) {
				throw new EmailException();
			} else {
			user = userDao.save(user);
			}
		} catch (EmailException e) {
			throw new EmailException();

		}
		return user;
	}

	/**
	 * Modification user
	 * @param user
	 * @return Objet
	 * @throws SQLException
	 */
	/*
	 * Même principe que creation
	 */
	public User updateUser(User user) throws SQLException {
		User modifUser;
		try {
			modifUser = userDao.save(user);
		} catch (ServiceException e) {
			throw new ServiceException("Hibernate Error !: updateUser" + e);
		}
		return modifUser;
	}

	/**
	 * Suppression User
	 * @param user
	 * @throws SQLException
	 */
	/*
	 * On commence par faire une recherche d'un user par son adresse email
	 * avec la methode [findByEmail()]
	 * Et on supprime l'objet par la methode delete
	 * d'hibernate qui supprime une entité complete.
	 * Cette methode peut etre appelé à evoluer
	 */
	public void deleteUser(User supprUser) throws SQLException {
		try {
			userDao.delete(supprUser);
		} catch (ServiceException e) {
			throw new ServiceException("Hibernate Error !: deleteUser" + e);
		}
	}


	public UserDto authUser() throws SQLException{
		User user;
		UserDto userDto;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication);
		String email = authentication.getName();
		try {
			user = userDao.findByEmail(email);
			userDto = mapper.convertUserToDto(user);
			
			
		} catch (ServiceException e) {
			throw new ServiceException("Erreur d'authentification");
		}
		return userDto;
	}
}
