package fr.simplon.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.simplon.domain.Role;


@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
	
	/**
	 * exemple de lecture personnalisee dans la base de donnees
	 * @param name role
	 * @return liste de roles
	 */

	List<Role> findByValeur(String valeur);

}
