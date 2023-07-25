package spring.restproject.restfeeinstitutebilling.modeldto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exceptionbody {

	private String message;
	private int errorid;
}
