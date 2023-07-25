package spring.restproject.restfeeinstitutebilling.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import spring.restproject.restfeeinstitutebilling.entities.Accountant;

@Repository
@Transactional
public interface Accountantrepository extends UserCollectionrepository {

	Optional<Accountant> save(Accountant accountant);
}
