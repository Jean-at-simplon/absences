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

import fr.simplon.domain.Role;
import fr.simplon.services.RoleService;

/**
 * Controleur REST de la classe Role
 * 
 * @author simplon
 *
 */

@RestController
@RequestMapping("/role")
@CrossOrigin(origins="*")
public class RoleController {

	@Autowired
	RoleService roleService;

	/**
	 * Liste des roles
	 * 
	 * @return liste des roles
	 */

	/*
	 * Affiche la liste des roles ResponseEntity permet gérer la réponse envoyée
	 * au front
	 * 
	 */
	@GetMapping("/listeRole")
	public ResponseEntity<?> findAll() {
		List<Role> role;
		try {
			role = roleService.listerRoles();
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(role);
	}

	/**
	 * 
	 * Recherche d'un role par la valeur
	 * 
	 * @param String
	 *            valeur
	 * 
	 * 
	 * @return 1 entité role
	 * 
	 */

	/*
	 * Cette methode recherche un role par sa valeur
	 * 
	 */

	@GetMapping("/getRoleByValue")
	public ResponseEntity<?> findByValue(@RequestParam(value = "valeur", defaultValue = "") String valeur) {
		List<Role> role;
		try {
			role = roleService.getRoleByValue(valeur);
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(role);
	}

	/**
	 * Création d'un nouveau role
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
	@PostMapping(value = "/creerRole")
	public ResponseEntity<?> save(@RequestBody @Valid Role role, BindingResult result) {
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
				role = roleService.insertRole(role);
			}
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(role.getId() + " créée.");
	}

	/**
	 * Mise à jour d'un role
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
	@PutMapping(value = "/updateRole")
	public ResponseEntity<?> update(@RequestBody @Valid Role role, BindingResult result) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (result.hasErrors()) {
				for (FieldError error : result.getFieldErrors()) {
					map.put(error.getField(), String.format("message:%s", error.getDefaultMessage()));
					return ResponseEntity.badRequest().body(map);
				}
			} else {
				role = roleService.updateRole(role);
			}
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(role.getId() + " modifié.");
	}

	/**
	 * Supression d'un role
	 * 
	 * @param id
	 * @return message de suppression
	 */
	/*
	 * La suppression se fait par l'id Le reste de l'action est dans la classe
	 * Service
	 */
	@DeleteMapping("/deleteRole")
	public ResponseEntity<?> delete(@RequestBody Role role) {
		try {
			roleService.deleteRole(role);
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(role.getId() + " supprimé.");
	}

}
