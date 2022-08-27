package hp.com.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Validate object then return missing param
 *
 * @author Pham Van Hung
 * @since 2022
 */
@Component
public class ObjectValidator {

    @Autowired
    LocalValidatorFactoryBean validatorFactory;

    /**
     * Validate an object then return message, Object must using ConstraintViolation to validate
     *
     * @param t   object
     * @param <T> Type
     * @return an blank string if object is valid, a detail message if object is invalid
     */
    public <T> String validateRequestThenReturnMessage(T t) {
        Set<ConstraintViolation<T>> violations = validatorFactory.getValidator().validate(t);
        List<String> messages = new ArrayList<>();
        for (ConstraintViolation<T> violation : violations) {
            messages.add(violation.getMessage());
        }
        String message = String.join(",", messages);
        if (message.contains(",")) {
            message = "[" + message + "]";
        }
        return message;
    }
}
