
package fr.simplon.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.simplon.domain.NumDemande;

public interface NumDemandeDao extends JpaRepository<NumDemande, Long> {
	
	//cherche le dernier element de la la base
	
	NumDemande findById(Long id);

}

