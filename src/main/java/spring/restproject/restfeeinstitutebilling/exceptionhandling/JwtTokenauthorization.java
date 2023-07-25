package spring.restproject.restfeeinstitutebilling.exceptionhandling;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenauthorization extends RuntimeException {

	private static final long serialVersionUID = -2694928070691497385L;

	public JwtTokenauthorization() {
		super();
	}

	public JwtTokenauthorization(String message) {
		super(message);

	}
}
