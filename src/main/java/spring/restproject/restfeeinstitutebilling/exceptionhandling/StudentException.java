package spring.restproject.restfeeinstitutebilling.exceptionhandling;

import org.springframework.stereotype.Component;

@Component
public class StudentException extends RuntimeException {

	private static final long serialVersionUID = 143206359411961692L;

	public StudentException(String message) {
		super(message);
	}

	public StudentException() {
		super();
	}

}
