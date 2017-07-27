package fr.simplon.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.simplon.domain.Equipe;

@Repository
public interface EquipeDao extends JpaRepository<Equipe, Long> {
	
	/**
	 * exemple de lecture personnalisee dans la base de donnees
	 * @param name nom
	 * @return liste de equipes
	 */
	
	Equipe findEquipeByNom(String nom);

}
