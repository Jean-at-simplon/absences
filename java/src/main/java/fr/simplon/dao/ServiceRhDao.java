package fr.simplon.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.simplon.domain.ServiceRh;

@Repository
public interface ServiceRhDao extends JpaRepository<ServiceRh, Long>{
	
	/**
	 * lecture personnalisee dans la base de donnees
	 * @param name 
	 * @return nom service RH
	 */
	 List<ServiceRh> findByNomContaining(String name);
	
	ServiceRh findByEmail(String email);
	
}
