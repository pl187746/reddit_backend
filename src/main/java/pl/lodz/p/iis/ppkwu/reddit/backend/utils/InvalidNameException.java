package pl.lodz.p.iis.ppkwu.reddit.backend.utils;

public class InvalidNameException extends Exception {

	private static final long serialVersionUID = -551774026460784970L;

	public InvalidNameException() {
		super();
	}

	public InvalidNameException(String message) {
		super(message);
	}

	public InvalidNameException(Throwable cause) {
		super(cause);
	}

	public InvalidNameException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidNameException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
