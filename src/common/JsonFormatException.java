package common;

/**
 * JSON格式错误
 */
public class JsonFormatException extends RuntimeException {
	
	private static final long serialVersionUID = 3583566093089790852L;
	
	public JsonFormatException() {
		super("json报文格式不正确");
	}
	
	public JsonFormatException(String message) {
		super(message);
	}
	
	public JsonFormatException(Throwable cause) {
		super(cause);
	}
	
	public JsonFormatException(String message, Throwable cause) {
		super(message, cause);
	}
}
