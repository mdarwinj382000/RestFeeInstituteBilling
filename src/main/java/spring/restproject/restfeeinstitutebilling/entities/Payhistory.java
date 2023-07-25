package spring.restproject.restfeeinstitutebilling.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.SelectBeforeUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@SelectBeforeUpdate
public class Payhistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@EqualsAndHashCode.Exclude
	private int paid;
	@EqualsAndHashCode.Exclude
	private int remamt;
	@EqualsAndHashCode.Exclude
	private LocalDate paiddate;
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(referencedColumnName = "id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonBackReference
	private Payment payment;

	public Payhistory(Long id, int paid, int remamt, LocalDate paiddate, Payment payment) {
		this.id = id;
		this.paid = paid;
		this.remamt = remamt;
		this.paiddate = paiddate;
		this.payment = payment;
	}

	public Payhistory(int paid, int remamt, LocalDate paiddate, Payment payment) {
		this.paid = paid;
		this.remamt = remamt;
		this.paiddate = paiddate;
		this.payment = payment;
	}

}
