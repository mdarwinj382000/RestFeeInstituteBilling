package spring.restproject.restfeeinstitutebilling.modeldto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenAuth implements Serializable {

	private static final long serialVersionUID = 7894640227487389617L;

	private String token;
}
