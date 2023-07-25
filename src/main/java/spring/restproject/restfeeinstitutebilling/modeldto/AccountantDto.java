package spring.restproject.restfeeinstitutebilling.modeldto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountantDto extends UsercollectionDto {

	private static final long serialVersionUID = 7192876565618193532L;
	private String branch;
}
