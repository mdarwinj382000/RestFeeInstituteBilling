package spring.restproject.restfeeinstitutebilling.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import spring.restproject.restfeeinstitutebilling.entities.BranchSemesterCourse;
import spring.restproject.restfeeinstitutebilling.entities.Fees;

@Repository
@Transactional
public interface Feesrepository extends JpaRepository<Fees, BranchSemesterCourse> {

	Optional<List<Fees>> findAllByIdBranch(String branch);

	@Modifying
	void deleteByIdBranch(String branch);

}
