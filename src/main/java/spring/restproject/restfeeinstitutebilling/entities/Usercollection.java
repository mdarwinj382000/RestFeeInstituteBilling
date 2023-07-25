package spring.restproject.restfeeinstitutebilling.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SelectBeforeUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SelectBeforeUpdate
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public class Usercollection {

	@Id
	@GeneratedValue(generator = "userid-generator")
	@GenericGenerator(name = "userid-generator", strategy = "spring.restproject.restfeeinstitutebilling.config.UserIDGenerator")
	private String id;
	@Column(nullable = false)
	@EqualsAndHashCode.Exclude
	private String firstname;
	@Column(nullable = false)
	@EqualsAndHashCode.Exclude
	private String lastname;
	@EqualsAndHashCode.Exclude
	@Column(unique = true, nullable = false)
	private String email;
	@EqualsAndHashCode.Exclude
	@Column(nullable = false)
	private String password;

	public Usercollection(String firstname, String lastname, String email, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}

	public String getUsername() {
		return this.getEmail();
	}

}
