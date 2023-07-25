package spring.restproject.restfeeinstitutebilling.exceptionhandling;

import org.springframework.stereotype.Component;

@Component
public class PaymentException extends RuntimeException {

	private static final long serialVersionUID = 6739224826302807207L;

	public PaymentException(String message) {
		super(message);
	}

	public PaymentException() {
		super();
	}

}
