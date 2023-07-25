package spring.restproject.restfeeinstitutebilling.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import spring.restproject.restfeeinstitutebilling.entities.Admin;

@Repository
@Transactional
public interface Adminrepository extends UserCollectionrepository {
	Optional<Admin> save(Admin admin);
}
