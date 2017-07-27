package fr.simplon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity Numéro de demande
 * 
 * @author JGL
 *
 */


@Entity
@Table(name = "num_demande")
public class NumDemande {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "numero")
	private String numDemande;

	public NumDemande() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumDemande() {
		return numDemande;
	}

	public void setNumDemande(String numDemande) {
		this.numDemande = numDemande;
	}
	
	

}

