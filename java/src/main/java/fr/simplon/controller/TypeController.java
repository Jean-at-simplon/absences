package fr.simplon.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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

import fr.simplon.domain.TypeAbsence;
import fr.simplon.services.TypeService;

/**
 * Controleur REST de la classe TypeAbsence
 * @author simplon
 *
 */

@RestController
@RequestMapping("/type")
@CrossOrigin(origins="*")
public class TypeController {

	@Autowired
	TypeService typeService;

	/**
	 * Liste des types
	 * @param search : critère de recherche
	 * @param searchnew : 2eme critere de recherche 
	 * @return liste des types
	 */
	@GetMapping("/listeTypeAbsence")
	public ResponseEntity<?> findAll() {
		List<TypeAbsence> typeabsence;
		try {
			typeabsence =typeService.listeTypeAbsence();
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(typeabsence);
	}

	/**
	 * recherche d'un type. note : l'id est dans l'url et non en parametre
	 * 
	 * @param id : id du type
	 * @return : objet type
	 */
	@GetMapping("/getTypeByName")
	public ResponseEntity<?> findById(@RequestParam(value = "nom", defaultValue = "") String nom) {
		TypeAbsence typeAbsence;
		try {
			typeAbsence =typeService.getTypeByName(nom);
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(typeAbsence);
	}

	/**
	 * Création d'un nouveau type d'absence
	 * 
	 * @param 
	 * 
	 * @return enregistrement ou erreur de saisie
	 * 
	 */
	/*
	 * La 2° ligne permet d'enregistrer les données dans le bean [Absence
	 * absence] et de capter le résultat [BindingResult result]
	 */
	@PostMapping(value = "/creerTypeAbsence", consumes = "application/json")
	public ResponseEntity<?> save(@RequestBody @Valid TypeAbsence typeAbsence, BindingResult result) {
		/*
		 * On capture les éventuelles erreurs dans une map sous forme : key,
		 * value et formatée pour l'affichage
		 */
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if (result.hasErrors()) {
				for (FieldError error : result.getFieldErrors()) {
					map.put(error.getField(), String.format("message:%s", error.getDefaultMessage()));
					return ResponseEntity.badRequest().body(map);
				}
			} else {
				typeAbsence = typeService.insertTypeAbsence(typeAbsence);
			}
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(typeAbsence.getNom() + " créée.");
	}


	/**
	 * Mise à jour d'un type d'absence
	 * 
	 * @param d°
	 *            que création
	 * 
	 * @return enregistrement ou erreur de saisie
	 * 
	 */

	/*
	 * La mise à jour suis le même principe que la création
	 */
	@PutMapping(value = "/updateTypeAbsence", consumes = "application/json")
	public ResponseEntity<?> update(@RequestBody @Valid TypeAbsence typeAbsence, BindingResult result) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (result.hasErrors()) {
				for (FieldError error : result.getFieldErrors()) {
					map.put(error.getField(), String.format("message:%s", error.getDefaultMessage()));
					return ResponseEntity.badRequest().body(map);
				}
			} else {
				typeAbsence = typeService.updateTypeAbsence(typeAbsence);
			}
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(typeAbsence.getNom() + " modifié.");
	}
	
	/**
	 * Supression d'un type d'absence
	 * 
	 * @param id
	 * @return message de suppression
	 */
	/*
	 * La suppression se fait par l'id Le reste de l'action est dans la classe
	 * Service
	 */
	@DeleteMapping("/deleteTypeAbsence")
	public ResponseEntity<?> delete(@RequestBody TypeAbsence typeAbsence) {
		try {
			typeService.deleteTypeAbsence(typeAbsence);
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(typeAbsence.getNom() + " supprimé.");
	}

	
}
