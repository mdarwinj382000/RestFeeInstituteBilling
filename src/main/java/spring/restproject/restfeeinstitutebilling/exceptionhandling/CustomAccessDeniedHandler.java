package spring.restproject.restfeeinstitutebilling.exceptionhandling;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.apachecommons.CommonsLog;
import spring.restproject.restfeeinstitutebilling.modeldto.Exceptionbody;

@Component
@CommonsLog
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		log.warn("Un-authorized access of Url");
		Exceptionbody resp = new Exceptionbody(accessDeniedException.getMessage(), HttpStatus.FORBIDDEN.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		String body = new ObjectMapper().writeValueAsString(resp);
		response.getWriter().write(body);
		response.getWriter().flush();

	}

}
