package spring.restproject.restfeeinstitutebilling.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchSemesterCourse implements Serializable {

	private static final long serialVersionUID = 1331938189333879155L;
	private String branch;
	private String semester;
}
