package spring.restproject.restfeeinstitutebilling.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.restproject.restfeeinstitutebilling.entities.Accountant;
import spring.restproject.restfeeinstitutebilling.entities.BranchSemesterCourse;
import spring.restproject.restfeeinstitutebilling.entities.Fees;
import spring.restproject.restfeeinstitutebilling.exceptionhandling.AccountantException;
import spring.restproject.restfeeinstitutebilling.exceptionhandling.FeesException;
import spring.restproject.restfeeinstitutebilling.repositories.Accountantrepository;
import spring.restproject.restfeeinstitutebilling.repositories.Feesrepository;

@Service
public class AccountantService {

	@Autowired
	private Accountantrepository accountantrepository;

	@Autowired
	private Feesrepository feesrepository;

	public List<Accountant> getAllAcc() {
		return accountantrepository.findAll().parallelStream().filter(Accountant.class::isInstance)
				.map(Accountant.class::cast).collect(Collectors.toList());
	}

	public Accountant getAccById(String accountantid) {
		return (Accountant) accountantrepository.findById(accountantid)
				.orElseThrow(() -> new AccountantException("Given Accountant user id is not Found"));
	}

	public Accountant saveAcc(Accountant a, String operation) {
		return accountantrepository.save(a).orElseThrow(
				() -> new AccountantException("Failed to " + operation + " Accountant user ,Check the data given"));
	}

	public void deleteAccById(String accountantid) {
		accountantrepository.deleteById(accountantid);
	}

	public Fees saveFees(Fees fee, String operation) {
		try {
			return feesrepository.save(fee);
		} catch (Exception e) {
			throw new FeesException(operation + " fees failed, check the fees data ");
		}
	}

	public List<Fees> getAllFees() {
		List<Fees> f = feesrepository.findAll();
		if (f.isEmpty())
			throw new FeesException("No fees is not Found");
		return f;

	}

	public Fees getFeeByBranchSem(BranchSemesterCourse branchSemesterCourse) {
		return feesrepository.findById(branchSemesterCourse)
				.orElseThrow(() -> new FeesException("Given branch/semester is not Found"));
	}

	public void deleteFeeByBranchSem(BranchSemesterCourse branchSemesterCourse) {
		feesrepository.deleteById(branchSemesterCourse);
	}

	public List<Fees> getFeesByBranch(String branch) {
		List<Fees> fees = feesrepository.findAllByIdBranch(branch)
				.orElseThrow(() -> new NullPointerException("fees list is not intialized"));
		if (fees.isEmpty())
			throw new FeesException("Given fees branch is not Found");
		return fees;
	}

	public void deleteFeeByBranch(String branch) {
		feesrepository.deleteByIdBranch(branch);
	}
}
