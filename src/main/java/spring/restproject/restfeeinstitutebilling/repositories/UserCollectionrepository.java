package spring.restproject.restfeeinstitutebilling.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.restproject.restfeeinstitutebilling.entities.Usercollection;

@Repository
@Primary
@Transactional
public interface UserCollectionrepository extends JpaRepository<Usercollection, String> {

	Optional<Usercollection> findByEmail(String username);

}
