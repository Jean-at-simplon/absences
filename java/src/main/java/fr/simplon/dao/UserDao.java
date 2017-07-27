package fr.simplon.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.simplon.domain.User;

/**
 * Interface du CRUD
 * pour la classe User
 * 
 * @author JGL
 *
 */

@Repository
public interface UserDao extends JpaRepository<User, Long>{
	

	/**
	 * lecture personnalisee dans la base de donnees
	 * @param email
	 * @return User
	 */

	User findByEmail(String email);

}
