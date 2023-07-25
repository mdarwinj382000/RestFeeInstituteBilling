package spring.restproject.restfeeinstitutebilling.exceptionhandling;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import spring.restproject.restfeeinstitutebilling.modeldto.Exceptionbody;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = BadCredentialsException.class)
	public ResponseEntity<Exceptionbody> handleException(BadCredentialsException exception) {
		return new ResponseEntity<>(new Exceptionbody(exception.getMessage(), 601), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = UsernameNotFoundException.class)
	public ResponseEntity<Exceptionbody> handleException(UsernameNotFoundException exception) {
		return new ResponseEntity<>(new Exceptionbody(exception.getMessage(), HttpStatus.FORBIDDEN.value()),
				HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(value = AccessDeniedException.class)
	public ResponseEntity<Exceptionbody> handleException(AccessDeniedException exception) {
		return new ResponseEntity<>(new Exceptionbody(exception.getMessage(), HttpStatus.UNAUTHORIZED.value()),
				HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(value = IllegalStateException.class)
	public ResponseEntity<Exceptionbody> handleException(IllegalStateException exception) {
		return new ResponseEntity<>(new Exceptionbody(exception.getMessage(), HttpStatus.UNAUTHORIZED.value()),
				HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(value = NoSuchElementException.class)
	public ResponseEntity<Exceptionbody> handleException(NoSuchElementException exception) {
		return new ResponseEntity<>(new Exceptionbody(exception.getMessage(), 602), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = NullPointerException.class)
	public ResponseEntity<Exceptionbody> handleException(NullPointerException exception) {
		return new ResponseEntity<>(new Exceptionbody(exception.getMessage(), 603), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Exceptionbody> handleException(Exception exception) {
		return new ResponseEntity<>(new Exceptionbody(exception.getMessage(), 600), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
