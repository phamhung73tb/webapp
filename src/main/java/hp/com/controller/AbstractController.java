package hp.com.controller;

import hp.com.exception.ResourceNotFoundException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractController {

    protected <T> ResponseEntity<T> response(Optional<T> response) {
        return new ResponseEntity<T>(response.orElseThrow(() -> {
            throw new ResourceNotFoundException();
        }), HttpStatus.OK);
    }
}
