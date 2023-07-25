package spring.restproject.restfeeinstitutebilling.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("ACCOUNTANT")
public class Accountant extends Usercollection {

	@EqualsAndHashCode.Exclude
	private String branch;

	public Accountant(String firstname, String lastname, String email, String password, String branch) {
		super(firstname, lastname, email, password);
		this.branch = branch;
	}

	public Accountant(String id, String firstname, String lastname, String email, String password, String branch) {
		super(id, firstname, lastname, email, password);
		this.branch = branch;
	}

}
