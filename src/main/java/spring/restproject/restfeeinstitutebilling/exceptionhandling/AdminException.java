package spring.restproject.restfeeinstitutebilling.exceptionhandling;

import org.springframework.stereotype.Component;

@Component
public class AdminException extends RuntimeException {

	private static final long serialVersionUID = -1024153128877497027L;

	public AdminException(String message) {
		super(message);
	}

	public AdminException() {
		super();
	}

}
