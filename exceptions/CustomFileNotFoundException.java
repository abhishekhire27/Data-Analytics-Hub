package exceptions;

public class CustomFileNotFoundException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomFileNotFoundException() {
		super();
	}
	
	public CustomFileNotFoundException(String message) {
		super(message);
	}

}
