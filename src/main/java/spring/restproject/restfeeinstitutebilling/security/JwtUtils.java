package spring.restproject.restfeeinstitutebilling.security;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
	@Value("${jwt_secret}")
	private String jwtSecret;
	@Value("${jwt_expiration_ms}")
	private int jwtExpirationMs;
	protected final Log logger = LogFactory.getLog(getClass());

	public String generateJwtToken(UserDetails userPrincipal) {
		return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {} " + e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {} " + e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {} " + e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {} " + e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {} " + e.getMessage());
		}
		return false;
	}
}