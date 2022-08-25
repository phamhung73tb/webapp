package hp.com.exception;

/**
 * exception for request invalid
 *
 * @author Pham Van Hung
 */
public class RequestParamInvalidException extends RuntimeException{
    public RequestParamInvalidException(String message) {
        super(message);
    }
}
