package fr.simplon.domain.dto;

import fr.simplon.domain.Employe;

public class EquipeDto {
	public String nom;
	public int idResponsable;
	public int idHierarchie;
	private Employe responsable;
	
	public EquipeDto() {
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getIdResponsable() {
		return idResponsable;
	}

	public void setIdResponsable(int idResponsable) {
		this.idResponsable = idResponsable;
	}

	public int getIdHierarchie() {
		return idHierarchie;
	}

	public void setIdHierarchie(int idHierarchie) {
		this.idHierarchie = idHierarchie;
	}

	public Employe getResponsable() {
		return responsable;
	}

	public void setResponsable(Employe responsable) {
		this.responsable = responsable;
	}
	
}
