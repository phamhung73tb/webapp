package hp.com.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * exception for JWT token invalid.
 *
 * @author Pham Van Hung
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TokenInvalidException extends RuntimeException{
    private String code;

    public TokenInvalidException(String code, String message) {
        super(message);
        this.code = code;
    }
}
