package spring.restproject.restfeeinstitutebilling.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import spring.restproject.restfeeinstitutebilling.modeldto.Exceptionbody;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			if (!request.getServletPath().equals("/signin")) {
				String jwt = parseJwt(request);
				if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
					String username = jwtUtils.getUserNameFromJwtToken(jwt);

					UserDetails userDetails = userDetailsService.loadUserByUsername(username);
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				} else {
					logger.warn("jwt token is not Found!! Trying To Access the Non-Secured API");
				}
			}
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			logger.warn("Token found but user not Found");
			Exceptionbody resp = new Exceptionbody(e.getMessage(), HttpStatus.FORBIDDEN.value());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			String body = new ObjectMapper().writeValueAsString(resp);
			response.getWriter().write(body);
			response.getWriter().flush();
		}

	}

	private String parseJwt(HttpServletRequest request) {
		String sutho = "Authorization";
		String headerAuth = request.getHeader(sutho);
		if (headerAuth != null) {
			logger.info(request.getHeader(sutho));
			if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
				return headerAuth.substring(7, headerAuth.length());
			}
		}
		return null;
	}

}
