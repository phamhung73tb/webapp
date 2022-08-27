package hp.com.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Pham Van Hung
 */
@Data
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;
}
