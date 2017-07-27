package fr.simplon.exception;

/**
 * @category Gestion des exceptions
 * 
 * @author JGL
 */ 

public class ServiceException extends RuntimeException {
	
	/**
	 * @category Personnalisation des exceptions
	 * 
	 * @author JGL
	 */ 
	private static final long serialVersionUID = 1L;

	public ServiceException(String message){
		super(message);
	}

}
