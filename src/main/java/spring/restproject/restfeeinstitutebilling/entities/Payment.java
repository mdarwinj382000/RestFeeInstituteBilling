package spring.restproject.restfeeinstitutebilling.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.SelectBeforeUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@SelectBeforeUpdate
@JsonInclude(Include.NON_NULL)
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@EqualsAndHashCode.Exclude
	int totamnt;
	@EqualsAndHashCode.Exclude
	int crntremamnt;
	String semester;
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(referencedColumnName = "id")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonBackReference
	Student student;
	@OneToMany(mappedBy = "payment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@EqualsAndHashCode.Exclude
	Set<Payhistory> payhistory;

	public Payment(int totalamount, int remainamount, Student student) {
		this.totamnt = totalamount;
		this.crntremamnt = remainamount;
		this.student = student;
		this.semester = student.getBsc().getSemester();
	}

}
