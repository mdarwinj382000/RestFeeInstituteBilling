package spring.restproject.restfeeinstitutebilling.modeldto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsercollectionDto implements Serializable {

	private static final long serialVersionUID = 4940740548410973893L;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
}
