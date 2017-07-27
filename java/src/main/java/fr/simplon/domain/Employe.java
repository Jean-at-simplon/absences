package fr.simplon.domain;

import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Entity Employé
 * 
 * @author JGL
 *
 */

@Entity
@Table(name = "employe")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Employe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nom")
	@NotBlank(message = "Nom obligatoire")
	@Length(min = 2, message = "La chaîne doit avoir au moins 2 caractères")
	private String nom;

	@Column(name = "prenom")
	@NotBlank(message = "Prenom obligatoire")
	@Length(min = 2, message = "La chaîne doit avoir au moins 2 caractères")
	private String prenom;

	@Column(name = " matricule")
	@NotBlank(message = "Matricule obligatoire")
	@Length(min = 2, message = "La chaîne doit avoir au moins 2 caractères")
	private String matricule;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "employe")
	@Cascade(CascadeType.ALL)
	private User user;

	@Column(name = "nb_ca")
	@NotNull(message = "Le nombre de C.A. est obligatoire")
	@Max(value = 30, message = " Le nombre de congés ne peut être supérieur à 30")
	private int nbCa;

	@Column(name = "nb_rtt")
	private int nbRtt;

	@Column(name = "nb_rc")
	private int nbRc;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employe")
	private List<Absence> absence;

	@ManyToOne
	@JoinColumn(name = "id_service_rh")
	private ServiceRh serviceRh;

	@ManyToOne
	@JoinColumn(name = "id_equipe")
	private Equipe equipe;



	public Employe() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public int getNbCa() {
		return nbCa;
	}

	public void setNbCa(int nbCa) {
		this.nbCa = nbCa;
	}

	public int getNbRtt() {
		return nbRtt;
	}

	public void setNbRtt(int nbRtt) {
		this.nbRtt = nbRtt;
	}

	public int getNbRc() {
		return nbRc;
	}

	public void setNbRc(int nbRc) {
		this.nbRc = nbRc;
	}
	
	public List<Absence> getAbsence() {
		Collections.reverse(absence);
		return absence;
	}

	public void setAbsence(List<Absence> absence) {
		this.absence = absence;
	}

	public ServiceRh getServiceRh() {
		return serviceRh;
	}

	public void setServiceRh(ServiceRh serviceRh) {
		this.serviceRh = serviceRh;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
