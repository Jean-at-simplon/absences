package fr.simplon.webSecurity.utils;

/**
 * Securité de l'application
 */

public class AuthConstantes {
	public static final String EMPLOYE = "hasRole('ROLE_1')";
	public static final String RESPONSABLE = "hasRole('ROLE_2')";
	public static final String SERVICERH = "hasRole('ROLE_3')";
	public static final String ADMIN = "hasRole('ROLE_4')";
	public static final String ROLE = "ROLE_0";
	
	private AuthConstantes() {
	}

	public static String getEmploye() {
		return EMPLOYE;
	}

	public static String getResponsable() {
		return RESPONSABLE;
	}

	public static String getServicerh() {
		return SERVICERH;
	}

	public static String getAdmin() {
		return ADMIN;
	}

	public static String getRole() {
		return ROLE;
	}
	
	

}
