package exceptions;

public class CustomNumberFormatException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomNumberFormatException() {
		super();
	}
	
	public CustomNumberFormatException(String message) {
		super(message);
	}

}
