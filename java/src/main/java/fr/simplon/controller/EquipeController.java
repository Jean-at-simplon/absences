package fr.simplon.controller;

import java.sql.SQLException;
import java.util.ArrayList;
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

import fr.simplon.domain.Equipe;
import fr.simplon.services.EquipeService;

/**
 * Controleur REST de la classe Equipe
 * @author simplon
 *
 */

@RestController
@RequestMapping("/equipe")
@CrossOrigin(origins="*")
public class EquipeController {

	@Autowired
	EquipeService equipeService;

	/**
	 * Liste des equipes
	 * @param search : critère de recherche
	 * @param searchnew : 2eme critere de recherche 
	 * @return liste des equipes
	 * @throws Exception 
	 */
	@GetMapping("/listeEquipe")
	public ResponseEntity<?> listEquipes(){
		List<Equipe> listEquipe = new ArrayList<Equipe>();
		try {
			listEquipe = equipeService.listEquipes();
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(listEquipe);
	}

	/**
	 * 
	 * Recherche d'une equipe par le nom
	 * 
	 * @param String nom
	 * 
	 * @return 1 entité equipe
	 * @throws Exception 
	 * 
	 */

	/*
	 * Cette methode recherche une equipe par le nom
	 * 
	 */

	@GetMapping("/getEquipe")
	public ResponseEntity<?> findById(@RequestParam(value = "nom", defaultValue = "") String nom){
		Equipe equipe;
		try {
			equipe = equipeService.getEquipe(nom);
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(equipe);
	}

	/**
	 * Création d'une nouvelle equipe
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
	@PostMapping(value = "/creerEquipe", consumes = "application/json")
	public ResponseEntity<?> save(@RequestBody @Valid Equipe equipe, BindingResult result) {
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
				equipe = equipeService.insertEquipe(equipe);
			}
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(equipe.getNom() + " créée.");
	}
	
	/**
	 * Mise à jour d'une equipe
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
	@PutMapping(value = "/updateEquipe")
	public ResponseEntity<?> update(@RequestBody @Valid Equipe equipe, BindingResult result) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (result.hasErrors()) {
				for (FieldError error : result.getFieldErrors()) {
					map.put(error.getField(), String.format("message:%s", error.getDefaultMessage()));
					return ResponseEntity.badRequest().body(map);
				}
			} else {
				equipe = equipeService.updateEquipe(equipe);
			}
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(equipe.getNom() + " modifié.");
	}




	/**
	 * Suppression d'une equipe
	 * @param nom
	 * @return message de suppression
	 */
	/*
	 * La suppression se fait par le matricule
	 * Le reste de l'action est dans la classe Service
	 */
	@DeleteMapping("deleteEquipe")
	public ResponseEntity<?> delete(@RequestBody Equipe equipe) {	
		try {
			equipeService.deleteEquipe(equipe);
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok("Equipe supprimée");
	}

}
