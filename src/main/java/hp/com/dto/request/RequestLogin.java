package hp.com.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *
 * @author Pham Van Hung
 */
@Data
@AllArgsConstructor
public class RequestLogin {
    @NotNull(message = "username.required")
    private String username;

    @NotNull(message = "password.required")
    private String password;
}
