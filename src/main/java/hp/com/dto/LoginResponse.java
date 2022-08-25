package hp.com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Pham Van Hung
 */
@Data
@AllArgsConstructor
public class LoginResponse {
    private String username;
    private String emailAddress;
    private String token;
}
