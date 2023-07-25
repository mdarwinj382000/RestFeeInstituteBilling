package spring.restproject.restfeeinstitutebilling.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fees {

	@EmbeddedId
	private BranchSemesterCourse id;
	@EqualsAndHashCode.Exclude
	private int amount;
}
