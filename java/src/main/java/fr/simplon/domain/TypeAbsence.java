package fr.simplon.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * entity Type
 */
@Entity
@Table(name = "type")
public class TypeAbsence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "nom")
	@NotBlank(message = "Nom obligatoire")
	@Length(min = 2, message = "La chaîne doit avoir au moins 2 caractères")
	private String nom;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
	@JsonBackReference
	private List<Absence> absence;


	public TypeAbsence() {
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


	public List<Absence> getAbsence() {
		return absence;
	}


	public void setAbsences(List<Absence> absence) {
		this.absence = absence;
	}


}
