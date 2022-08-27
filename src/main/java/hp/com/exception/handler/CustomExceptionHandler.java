package hp.com.exception.handler;

import hp.com.dto.ErrorCode;
import hp.com.dto.response.ErrorResponse;
import hp.com.exception.RequestParamInvalidException;
import hp.com.exception.ResourceExistedException;
import hp.com.exception.ResourceNotFoundException;
import hp.com.exception.TokenInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Pham Van Hung
 */
@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({TokenInvalidException.class})
    public ResponseEntity<ErrorResponse> handleTokenInvalidException(TokenInvalidException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getCode(), e.getMessage()), null,
            HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({RequestParamInvalidException.class})
    public ResponseEntity<ErrorResponse> handleTokenInvalidException(RequestParamInvalidException e) {

        return new ResponseEntity<>(new ErrorResponse(ErrorCode.E20.toString(), e.getMessage()), null,
            HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.E00.toString(), e.getMessage()),
            null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ResourceExistedException.class})
    public ResponseEntity<ErrorResponse> handleResourceExistedException(ResourceExistedException e) {
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.E01.toString(), e.getMessage()), null,
                HttpStatus.SEE_OTHER);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleCommonException(Exception e) {

        e.printStackTrace();
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.E30.toString(), e.getMessage()), null,
            HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
