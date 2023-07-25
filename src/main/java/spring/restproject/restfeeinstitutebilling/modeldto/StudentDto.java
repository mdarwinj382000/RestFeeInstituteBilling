package spring.restproject.restfeeinstitutebilling.modeldto;

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
public class StudentDto extends UsercollectionDto {

	private static final long serialVersionUID = -8449993639563474245L;
	@JsonUnwrapped
	private BranchSemesterCourse bsc;
}
