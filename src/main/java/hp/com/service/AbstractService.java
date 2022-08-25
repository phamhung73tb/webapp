package hp.com.service;

import hp.com.utility.ObjectValidator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Pham Van Hung
 */
public abstract class AbstractService {

    @Autowired
    protected ObjectValidator validator;
}
