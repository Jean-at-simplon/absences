package fr.simplon.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * entity Absence
 * 
 * @author Simplon
 *
 */

@Entity //Spécifie que cette classe est une entité, un javabean
@Table(name = "absence") // Assure la correlation entre la base de données et le javabean
public class Absence {

	@Id  //Indique qu'il s'agit de la clé primaire de la table
	@GeneratedValue(strategy = GenerationType.AUTO) // Specifie que la génération de la clé primaire sera gérer automatiquement
	private Long id;
	

	@Column(name = "debut") // Indique le nom de la colonne de la table
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-jj") //Converti le format de la date dans la base de données au format désiré
	@Temporal(TemporalType.DATE) //Indique qu'il s'agit d'un élément à traiter avec java.util.Date
	@Future(message = "La date doit être postérieure à la date du jour") //Vérifie que la date soit supérieur à la date du jour
	private Date debut;


	@Column(name = "fin")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-jj")
	@Temporal(TemporalType.DATE)
	private Date fin;
	
 	@Column(name= "num_demande")
	private String numDemande;
	
	@Column(name= "commentaire")
	private String commentaire;
	

	@ManyToOne // Liaison avec une autre entité incorporé dans cette classe, la liaison est multiple dans cette classe, unique dans la classe liée
	@JoinColumn(name = "id_type") // Specifie la colonne sur laquelle se fera la jointure des 2 entités, et recupère la valeur correspondant a la FK
	private TypeAbsence type;

	
	@ManyToOne
	@JoinColumn(name = "id_statut")
	private Statut statut;
	
	@ManyToOne
	@JoinColumn(name = "id_employe")
	private Employe employe;
	
	@Column(name = "date_relance")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-jj")
	@Temporal(TemporalType.DATE)
	private Date relance;


	// Constructeur vide conforme aux spécifications java
	public Absence() {
		
	}

	//Retourne la valeur de la variable
	public Long getId() {
		return id;
	}

	//Affecte une valeur à la variable
	public void setId(Long id) {
		this.id = id;
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
	
	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public TypeAbsence getType() {
		return type;
	}

	public void setType(TypeAbsence type) {
		this.type = type;
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

	public Date getRelance() {
		return relance;
	}

	public void setRelance(Date relance) {
		this.relance = relance;
	}
	
	

	
}
