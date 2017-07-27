package fr.simplon.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * entity Equipe
 */


@Entity
@Table(name = "equipe")
public class Equipe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nom")
	@NotBlank(message = "nom obligatoire")
	@Length(min = 4, message = "La chaîne doit avoir au moins 2 caractères")
	private String nom;

	@OneToMany(mappedBy = "equipe")
	@JsonBackReference
	private List<Employe> employe;

	@ManyToOne
	@JoinColumn(name="id_responsable")
	private Employe responsable;


	public Equipe() {
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


	public List<Employe> getEmploye() {
		return employe;
	}


	public void setEmploye(List<Employe> employe) {
		this.employe = employe;
	}


	public Employe getResponsable() {
		return responsable;
	}


	public void setResponsable(Employe responsable) {
		this.responsable = responsable;
	}
	
	


}
