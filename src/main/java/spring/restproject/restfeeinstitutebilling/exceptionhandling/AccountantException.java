package spring.restproject.restfeeinstitutebilling.exceptionhandling;

import org.springframework.stereotype.Component;

@Component
public class AccountantException extends RuntimeException {

	private static final long serialVersionUID = -3389534406813906758L;

	public AccountantException(String message) {
		super(message);
	}

	public AccountantException() {
		super();
	}

}
