package spring.restproject.restfeeinstitutebilling.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.SelectBeforeUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SelectBeforeUpdate
@DiscriminatorValue("STUDENT")
@JsonInclude(Include.NON_NULL)
public class Student extends Usercollection {

	@EqualsAndHashCode.Exclude
	private BranchSemesterCourse bsc;

	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@EqualsAndHashCode.Exclude
	Set<Payment> payment;

	public Student(String id, String firstname, String lastname, String email, String password,
			BranchSemesterCourse branchSemesterCourse) {
		super(id, firstname, lastname, email, password);
		this.bsc = branchSemesterCourse;
	}

	public Student(String firstname, String lastname, String email, String password,
			BranchSemesterCourse branchSemesterCourse) {
		super(firstname, lastname, email, password);
		this.bsc = branchSemesterCourse;
	}

}
