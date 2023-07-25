package spring.restproject.restfeeinstitutebilling.exceptionhandling;

import org.springframework.stereotype.Component;

@Component
public class PayhistoryException extends RuntimeException {

	private static final long serialVersionUID = -7617062336687287565L;

	public PayhistoryException(String message) {
		super(message);
	}

	public PayhistoryException() {
		super();
	}

}
