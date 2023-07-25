package spring.restproject.restfeeinstitutebilling.modeldto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser implements Serializable {

	private static final long serialVersionUID = -7866433040803939277L;

	private String username;
	private String password;
}