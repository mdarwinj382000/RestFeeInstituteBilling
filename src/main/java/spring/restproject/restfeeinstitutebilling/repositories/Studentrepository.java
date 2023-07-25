package spring.restproject.restfeeinstitutebilling.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import spring.restproject.restfeeinstitutebilling.entities.Student;

@Repository
@Transactional
public interface Studentrepository extends UserCollectionrepository {

	@Modifying
	void deleteById(String string);

	Optional<Student> save(Student student);
}
