package spring.restproject.restfeeinstitutebilling.repositories;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.restproject.restfeeinstitutebilling.entities.Payment;
import spring.restproject.restfeeinstitutebilling.entities.Student;

@Repository
@Transactional
public interface Paymentrepository extends JpaRepository<Payment, Long> {

	Optional<Set<Payment>> findAllByStudent(Student student);

	Optional<Payment> findByStudentIdAndStudentBscSemester(String id, String semester);
}
