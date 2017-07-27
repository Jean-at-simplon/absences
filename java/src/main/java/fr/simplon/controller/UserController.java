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

import fr.simplon.domain.User;
import fr.simplon.domain.dto.UserDto;
import fr.simplon.services.UserService;

/**
 * CRUD des user
 * 
 * @author JGL
 * 
 */

/*
 * Controlleur pour la gestion des employes
 * J'ai utilisé le verbe correspondant à l'action 
 * (get : lecture, post : création, put : mise à jour et delete: suppression
 * url àsaisir dans le navigateur : localhost:8080/emp/nomMethode
 */
@RestController
@RequestMapping("/user")
 public class UserController {
	
	@Autowired
	UserService userService;
	
	/**
	 * Liste des employes
	 * 
	 * @return liste des employes
	 */
	
	/*
	 * Affiche la liste des employes
	 * ResponseEntity permet gérer la réponse envoyée au front
	 */
	@GetMapping("listeUsers")
	public ResponseEntity<?> findAll() {	
		List<UserDto> users;
		try {
		users = userService.listeUsers();
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(users);
	}
	
	/**
	 * 
	 * Recherche des users par l'email
	 * 
	 * @param String email
	 * 
	 * @return 1 user
	 * 
	 */
	
	/*
	 * Cette methode recherche un user par l'email
	 * 
	 */
	@GetMapping("getUser")
	public ResponseEntity<?> findByName(@RequestParam(value="email", defaultValue="") String email) {	
		UserDto user;
		try {
			user = userService.getUser(email);
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(user);
}
	
	/**
	 * Création de nouveaux users
	 * 
	 * @param données concernant le user
	 * 
	 * @return enregistrement ou erreur de saisie
	 * 
	 */
	/*
	 * La 2° ligne permet d'enregistrer les données dans le bean [Employe employe]
	 * et de capter le résultat  [BindingResult result]
	 */
	@PostMapping(value="/creerUser")	
	public ResponseEntity<?> save(@RequestBody @Valid User user, BindingResult result) {			
	/*
	 * On capture les éventuelles erreurs dans une map 
	 * sous forme : key, value
	 * et formatée pour l'affichage
	 */
		System.out.println(user.getEmail()+" - "+user.getPasswd());
		try{
			Map<String,Object> map = new HashMap<String,Object>();
			if(result.hasErrors()){
				for(FieldError error : result.getFieldErrors()){
					map.put(error.getField(), String.format("message:%s", error.getDefaultMessage()));
					return ResponseEntity.badRequest().body(map);
				}
			} else {
		user =  userService.insertUser(user);
		}
		} catch( SQLException sqle){
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(user.getEmail()+" crée");
	}
	
	/**
	 * Mise à jour d'un user
	 * 
	 * @param données à modifier du user
	 * 
	 * @return enregistrement ou erreur de saisie
	 * 
	 */
	
	/*
	 * La mise à jour suis le même principe que la création
	 */
	@PutMapping(value="/updateUser")	
	public ResponseEntity<?> update(@RequestBody @Valid User user, BindingResult result) {			
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			if(result.hasErrors()){
				for(FieldError error : result.getFieldErrors()){
					map.put(error.getField(), String.format("message:%s", error.getDefaultMessage()));
					return ResponseEntity.badRequest().body(map);
				}
			} else {
		user =  userService.updateUser(user);
		}
		} catch( SQLException sqle){
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(user.getEmail()+" modifié.");
	}
	
	/**
	 * Suppression d'un user
	 * @param email
	 * @return message de suppression
	 */
	/*
	 * La suppression se fait par le matricule
	 * Le reste de l'action est dans la classe Service
	 */
	@DeleteMapping("/deleteUser")
	public ResponseEntity<?> delete(@RequestBody User user) {	
		try {
			userService.deleteUser(user);
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok("Suppression effectuée");
	}
	
	@GetMapping("/authUser")
	public ResponseEntity<?> authenticate() {	
		UserDto userDto;
		try {
			userDto = userService.authUser();
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(userDto);
}

}
