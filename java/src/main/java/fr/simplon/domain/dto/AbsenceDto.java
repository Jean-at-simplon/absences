package fr.simplon.domain.dto;


import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @Entity AbsenceDto
 * 
 * Classe pour l'envoi et la reception
 * 
 * de données au front
 * 
 * @author JGL
 *
 */

public class AbsenceDto {
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Future(message = "La date doit être postérieure à la date du jour")
	private Date debut;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Future(message = "La date doit être postérieure à la date du jour")
	private Date fin;
	
	private String type;
	
	private String statut;
	
	private String numDemande;
	
	private String commentaire;
	
	private String matricule;
	
	private String nom;
	
	private String prenom;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date relance;
	
	public AbsenceDto() {
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

 	public String getNumDemande() {
		return numDemande;
	}

	public void setNumDemande(String numDemande) {
		this.numDemande = numDemande;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getRelance() {
		return relance;
	}

	public void setRelance(Date relance) {
		this.relance = relance;
	}

	

}
