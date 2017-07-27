package fr.simplon.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import fr.simplon.domain.dto.AbsenceDto;
import fr.simplon.services.ValidationService;

/**
 * Traitement pour la validation des absences
 * N+1 et RH
 * 
 * @author JGL
 *
 */

@RestController 
@RequestMapping("validation") 
@CrossOrigin(origins="*") 
public class ValidationController {
	
	@Autowired
	ValidationService validationService;
	
	/**
	 * Liste des absences pour le responsable
	 * @param equipe
	 * @return
	 */
	@GetMapping("/listeAbsences") 
	public ResponseEntity<?> findAll(@RequestParam(value = "equipe", defaultValue = "") String equipe, @RequestParam(value="statut", defaultValue="") int statut) {
		List<AbsenceDto> absenceDto;
		try {
			absenceDto =validationService.listeValidation(equipe, statut);
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(absenceDto);
	}
	
	/**
	 * Liste des absences pour le service RH
	 * @param equipe
	 * @return
	 */
	@GetMapping("/listeAbsencesRh") 
	public ResponseEntity<?> findAllRh(@RequestParam(value = "email", defaultValue = "") String email, @RequestParam(value="statut", defaultValue="") int statut) {
		List<AbsenceDto> absenceDto;
		try {
			absenceDto =validationService.listeValidationRh(email,statut);
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(absenceDto);
	}

	
	/**
	 * Trairement des absences
	 * @param json
	 * @return
	 * @throws Exception
	 */
	
	//Validation des congés
	@PutMapping(value = "/validationAbsence")
	public ResponseEntity<?> update(@RequestBody ObjectNode json) throws Exception {
		String numDemande = json.get("numDemande").asText();
		String commentaire = json.get("commentaire").asText();
		String validation = json.get("validation").asText();
		AbsenceDto absenceDto;
		try {
			absenceDto = validationService.updateValidation(numDemande, commentaire, validation);
			
		} catch (SQLException sqle) {
			return ResponseEntity.badRequest().body(sqle);
		}
		return ResponseEntity.ok(absenceDto);
	}

}
