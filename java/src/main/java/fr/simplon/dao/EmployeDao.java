package fr.simplon.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.simplon.domain.Employe;
import fr.simplon.domain.Equipe;
import fr.simplon.domain.ServiceRh;

/**
 * Interface du CRUD
 * 
 * @author JGL
 *
 */
@Repository
public interface EmployeDao extends JpaRepository<Employe, Long> {
	
	/**
	 * lecture personnalisee dans la base de donnees par le nom
	 * @param name 
	 * @return liste des employés
	 */
	
	List<Employe> findByNomContaining(String nom);
	
	/**
	 * Recherche d'un employe par le matricule
	 * @param matricule
	 * @return employe
	 */
	
	Employe findByMatricule(String matricule);

	
	List<Employe> findByEquipe(Equipe id);

	List<Employe> findByServiceRh(ServiceRh id);
}
