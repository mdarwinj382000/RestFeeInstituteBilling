package spring.restproject.restfeeinstitutebilling.exceptionhandling;

import org.springframework.stereotype.Component;

@Component
public class FeesException extends RuntimeException {

	private static final long serialVersionUID = -4181303322063456695L;

	public FeesException(String message) {
		super(message);
	}

	public FeesException() {
		super();
	}

}
