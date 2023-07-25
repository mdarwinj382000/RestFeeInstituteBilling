package spring.restproject.restfeeinstitutebilling.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import spring.restproject.restfeeinstitutebilling.entities.Admin;
import spring.restproject.restfeeinstitutebilling.modeldto.AdminDto;
import spring.restproject.restfeeinstitutebilling.modeldto.Exceptionbody;
import spring.restproject.restfeeinstitutebilling.services.AccountantService;
import spring.restproject.restfeeinstitutebilling.services.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@Autowired
	AccountantService accountantService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/get")
	@ResponseStatus(code = HttpStatus.FOUND)
	public Admin getAdmin(@RequestParam(required = true) String id) {
		return adminService.getAdminById(id);
	}

	@PostMapping("/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Admin addAdmin(@RequestBody AdminDto admin) {
		admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
		Admin a = new Admin();
		BeanUtils.copyProperties(admin, a);
		return adminService.saveAdmin(a, "create");
	}

	@PostMapping("/update")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Admin updateAdmin(@RequestParam String id, @RequestBody AdminDto admin) {
		admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
		Admin a = new Admin();
		BeanUtils.copyProperties(admin, a);
		a.setId(id);
		return adminService.saveAdmin(a, "update");
	}

	@DeleteMapping("/delete")
	@ResponseStatus(code = HttpStatus.OK)
	public Exceptionbody deleteAdmin(@RequestParam(required = true) String id) {
		adminService.deleteAdminById(id);
		return new Exceptionbody("Admin with user id " + id + " was deleted successfully", HttpStatus.OK.value());
	}

	@GetMapping("/all")
	@ResponseStatus(code = HttpStatus.FOUND)
	public List<Admin> getallAdmin() {
		return adminService.getAllAdmin();
	}

}
