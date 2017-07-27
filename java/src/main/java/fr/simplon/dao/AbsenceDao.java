package fr.simplon.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.simplon.domain.Absence;
import fr.simplon.domain.Statut;

/**
 * lecture personnalisée de la BDD
 * 
 * @author JGL
 *
 */
public interface AbsenceDao extends JpaRepository<Absence, Long> {
	
	/**
	 * recherche d'une absence
	 * @param id 
	 * @param numDemande
	 * @return une absence
	 */
	
	List<Absence> findById(Long id);
	
	List<Absence> findByStatut(Statut code);
	
	Absence findByNumDemande(String numDemande);
	
	

}
