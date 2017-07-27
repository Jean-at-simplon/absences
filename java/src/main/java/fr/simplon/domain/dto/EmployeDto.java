package fr.simplon.domain.dto;

public class EmployeDto {
	
	private String nom;
	private String prenom;
	private String matricule;
	private String email;
	private String role;
	private int nbCa;
	private int nbRtt;
	private int nbRc;
	private String nomRh;
	private String emailRh;
	private String nomEquipe;
	private String nomResponsable;
	private String prenomResponsable;
	private String emailReponsable;
	
	public EmployeDto() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public String getNomRh() {
		return nomRh;
	}

	public void setNomRh(String nomRh) {
		this.nomRh = nomRh;
	}

	public String getEmailRh() {
		return emailRh;
	}

	public void setEmailRh(String emailRh) {
		this.emailRh = emailRh;
	}

	public String getNomEquipe() {
		return nomEquipe;
	}

	public void setNomEquipe(String nomEquipe) {
		this.nomEquipe = nomEquipe;
	}

	public String getNomResponsable() {
		return nomResponsable;
	}

	public void setNomResponsable(String nomResponsable) {
		this.nomResponsable = nomResponsable;
	}

	public String getEmailReponsable() {
		return emailReponsable;
	}

	public void setEmailReponsable(String emailReponsable) {
		this.emailReponsable = emailReponsable;
	}

	public String getPrenomResponsable() {
		return prenomResponsable;
	}

	public void setPrenomResponsable(String prenomResponsable) {
		this.prenomResponsable = prenomResponsable;
	}	

}
