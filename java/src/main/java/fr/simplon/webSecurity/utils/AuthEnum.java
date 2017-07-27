package fr.simplon.webSecurity.utils;

/**
 * Securité de l'application
 */

public enum AuthEnum {
	
	NOAUTH("0"),
	EMPLOYE("1"),
	RESPONSABLE("2"),
	SERVICERH("3"),
	ADMIN("4");
	
	private String auth;
	
	private AuthEnum(String auth) {
		this.auth= auth;
	}

	public String getAuth() {
		return auth;
	}
	
	
}
