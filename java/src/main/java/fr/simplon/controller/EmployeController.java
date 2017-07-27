package fr.simplon.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.simplon.domain.Employe;
import fr.simplon.domain.dto.EmployeDto;
import fr.simplon.services.EmployeService;

/**
 * CRUD des employes
 * 
 * @author JGL
 * 
 */

/*
 * Controlleur pour la gestion des employes
 * J'ai utilisé le verbe correspondant à l'action 
 * (get : lecture, post : création, put : mise à jour et delete: suppression
 * url à saisir dans le navigateur : localhost:8080/emp/nomMethode
 */
@RestController
@RequestMapping("emp")
@CrossOrigin(origins="*")
public class EmployeController {
	
	Logger logger;
	
	@Autowired
	EmployeService empService;
	
	/**
	 * Liste des employes
	 * 
	 * @return liste des employes
	 */
	
	/*
	 * Affiche la liste des employes
	 * ResponseEntity permet gérer la réponse envoyée au front
	 */
	@GetMapping("listeEmployes")
	public ResponseEntity<?> findAll() {	
		List<EmployeDto> employes;
		try {
		employes = empService.listeEmployes();
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(employes);
	}
	
	/**
	 * 
	 * Recherche des employe rh par le nom
	 * 
	 * @param String nom
	 * 
	 * @return 1 ou  plusieurs entités employes
	 * 
	 */
	
	/*
	 * Cette methode recherche un employe par le nom
	 * Il suffit de rentrer une lettre et la liste des employes
	 * contenant cette lettre sera affichée
	 */
	
	@GetMapping("getEmploye")
	public ResponseEntity<?> findByName(@RequestParam(value="nom", defaultValue="") String nom) {	
		List<EmployeDto> employe;
		try {
			employe = empService.getEmploye(nom);
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(employe);
	}
	
	/**
	 * Création de nouveaux employes
	 * 
	 * @param données concernant l'employe
	 * 
	 * @return enregistrement ou erreur de saisie
	 * 
	 */
	/*
	 * La 2° ligne permet d'enregistrer les données dans le bean [Employe employe]
	 * et de capter le résultat  [BindingResult result] (la création ou les erreurs)
	 */
	@PostMapping(value="/creerEmploye")	
	public ResponseEntity<?> save(@RequestBody @Valid Employe employe, BindingResult result) {			
	/*
	 * On capture les éventuelles erreurs dans une map 
	 * sous forme : key, value
	 * et formatée pour l'affichage
	 */
		try{
			Map<String,Object> map = new HashMap<String,Object>();
			if(result.hasErrors()){
				for(FieldError error : result.getFieldErrors()){
					map.put(error.getField(), String.format("message:%s", error.getDefaultMessage()));
					return ResponseEntity.badRequest().body(map);
				}
			} else {
		employe =  empService.insertEmploye(employe);
		}
		} catch( SQLException sqle){
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(employe.getPrenom()+" "+employe.getNom()+" créée.");
	}
	
	/**
	 * Mise à jour d'un employe
	 * 
	 * @param données à modifier de l'employé
	 * 
	 * @return enregistrement ou erreur de saisie
	 * 
	 */
	
	/*
	 * La mise à jour suis le même principe que la création.
	 * Si l'id est présent dans le bean, Hibernate sait qu'il s'agit d'un update
	 */
	@PutMapping(value="/updateEmploye")	
	public ResponseEntity<?> update(@RequestBody @Valid Employe employe, BindingResult result) {			
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if(result.hasErrors()){
				for(FieldError error : result.getFieldErrors()){
					map.put(error.getField(), String.format("message:%s", error.getDefaultMessage()));
					return ResponseEntity.badRequest().body(map);
				}
			} else {
		employe =  empService.updateEmploye(employe);
		}
		} catch( SQLException sqle){
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(employe.getNom()+" modifié.");
	}
	
	/**
	 * Suppression d'un employé
	 * @param nom
	 * @return message de suppression
	 */
	/*
	 * La suppression se fait par le matricule
	 * Le reste de l'action est dans la classe Service
	 */
	@DeleteMapping("deleteEmploye")
	public ResponseEntity<?> delete(@RequestBody Employe employe) {	
		try {
			empService.deleteEmploye(employe);
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok("Suppression effectuée");
	}

}
