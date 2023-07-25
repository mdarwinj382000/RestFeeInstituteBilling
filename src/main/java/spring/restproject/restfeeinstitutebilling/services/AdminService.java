package spring.restproject.restfeeinstitutebilling.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.restproject.restfeeinstitutebilling.entities.Admin;
import spring.restproject.restfeeinstitutebilling.exceptionhandling.AdminException;
import spring.restproject.restfeeinstitutebilling.repositories.Adminrepository;

@Service
public class AdminService {

	@Autowired
	private Adminrepository adminrepository;

	public List<Admin> getAllAdmin() {
		return adminrepository.findAll().parallelStream().filter(Admin.class::isInstance).map(Admin.class::cast)
				.collect(Collectors.toList());
	}

	public Admin getAdminById(String adminid) {
		return (Admin) adminrepository.findById(adminid)
				.orElseThrow(() -> new AdminException("Given Admin user id is not Found"));
	}

	public Admin saveAdmin(Admin a, String operation) {
		return adminrepository.save(a)
				.orElseThrow(() -> new AdminException("Failed to " + operation + " Admin user ,Check the data given"));
	}

	public void deleteAdminById(String adminid) {
		adminrepository.deleteById(adminid);
	}

}
