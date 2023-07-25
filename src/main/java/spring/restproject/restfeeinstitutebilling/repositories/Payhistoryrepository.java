package spring.restproject.restfeeinstitutebilling.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.restproject.restfeeinstitutebilling.entities.Payhistory;
import spring.restproject.restfeeinstitutebilling.entities.Payment;

@Repository
@Transactional
public interface Payhistoryrepository extends JpaRepository<Payhistory, Long> {

	Optional<List<Payhistory>> findAllByPayment(Payment payment);

}
