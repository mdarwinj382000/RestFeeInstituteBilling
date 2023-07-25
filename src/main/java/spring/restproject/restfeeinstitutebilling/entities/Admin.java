package spring.restproject.restfeeinstitutebilling.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@DiscriminatorValue("ADMIN")
public class Admin extends Usercollection {

	public Admin(String id, String firstname, String lastname, String email, String password) {
		super(id, firstname, lastname, email, password);
	}

	public Admin(String firstname, String lastname, String email, String password) {
		super(firstname, lastname, email, password);
	}

}
