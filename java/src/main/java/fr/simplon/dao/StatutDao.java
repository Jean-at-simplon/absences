package fr.simplon.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.simplon.domain.Statut;

public interface StatutDao extends JpaRepository<Statut, Long>{
	
	 
	Statut findByCode(int code);
	
	Statut findByNom(String name);

}


