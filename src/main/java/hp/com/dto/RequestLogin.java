package hp.com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author Pham Van Hung
 */
@Data
@AllArgsConstructor
public class RequestLogin {
    private String username;
    private String password;
}
