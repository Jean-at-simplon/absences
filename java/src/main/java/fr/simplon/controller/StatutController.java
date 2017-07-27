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

import fr.simplon.domain.Statut;
import fr.simplon.services.StatutService;

/**
 * CRUD des Statuts de demande de congé
 * 
 * @author JGL
 * 
 */

/*
 * Controlleur pour la gestion des services Absence J'ai utilisé le verbe
 * correspondant à l'action (get : lecture, post : création, put : mise à jour
 * et delete: supression url àsaisir dans le navigateur :
 * localhost:8080/absence/nomMethode
 */

@RestController
@RequestMapping("/statut")
@CrossOrigin(origins="*")
public class StatutController {
	
	@Autowired
	StatutService statutService;

	/**
	 * Liste des statuts
	 * 
	 * @return liste des statuts
	 */

	/*
	 * Affiche la liste des statut 
	 * ResponseEntity permet gérer la réponse envoyée au front
	 * 
	 */
	@GetMapping("/listeStatut")
	public ResponseEntity<?> findAll() {
		List<Statut> statut;
		try {
			statut =statutService.listeStatut();
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(statut);
	}

	/**
	 * 
	 * Recherche des absences par le nom
	 * 
	 * @param String
	 *            nom
	 * 
	 * @return 1 entité absence
	 * 
	 */

	/*
	 * Cette methode recherche une absence par l'id
	 * 
	 */

	@GetMapping("/getStatutByCode")
	public ResponseEntity<?> findById(@RequestParam(value = "code", defaultValue = "") int code) {
		Statut statut;
		try {
			statut = statutService.getStatutByCode(code);
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(statut);
	}

	/**
	 * Création d'unnouveau statut
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
	@PostMapping(value = "/creerStatut", consumes = "application/json")
	public ResponseEntity<?> save(@RequestBody @Valid Statut statut, BindingResult result) {
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
				statut = statutService.insertStatut(statut);
			}
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(statut.getNom() + " créée.");
	}

	/**
	 * Mise à jour d'un statut
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
	@PutMapping(value = "/updateStatut")
	public ResponseEntity<?> update(@RequestBody @Valid Statut statut, BindingResult result) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (result.hasErrors()) {
				for (FieldError error : result.getFieldErrors()) {
					map.put(error.getField(), String.format("message:%s", error.getDefaultMessage()));
					return ResponseEntity.badRequest().body(map);
				}
			} else {
				statut = statutService.updateStatut(statut);
			}
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(statut.getNom() + " modifié.");
	}

	/**
	 * Supression d'un statut
	 * 
	 * @param id
	 * @return message de suppression
	 */
	/*
	 * La suppression se fait par l'id Le reste de l'action est dans la classe
	 * Service
	 */
	@DeleteMapping("/deleteStatut")
	public ResponseEntity<?> delete(@RequestBody Statut statut) {
		try {
			statutService.deleteStatut(statut);
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(statut.getNom() + " supprimé.");
	}
}

