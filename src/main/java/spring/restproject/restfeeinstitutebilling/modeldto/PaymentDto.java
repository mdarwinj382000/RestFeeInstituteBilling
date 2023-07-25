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
public class PaymentDto implements Serializable {

	private static final long serialVersionUID = -8318684750311580154L;
	int totamnt;
	int crntremamnt;
	String semester;
}
