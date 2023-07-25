package spring.restproject.restfeeinstitutebilling.modeldto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.restproject.restfeeinstitutebilling.entities.BranchSemesterCourse;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeesDto implements Serializable {

	private static final long serialVersionUID = 3567467477843624307L;
	@JsonUnwrapped
	private BranchSemesterCourse id;
	private int amount;
}
