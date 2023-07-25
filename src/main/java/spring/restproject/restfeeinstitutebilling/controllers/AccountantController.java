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

import spring.restproject.restfeeinstitutebilling.entities.Accountant;
import spring.restproject.restfeeinstitutebilling.modeldto.AccountantDto;
import spring.restproject.restfeeinstitutebilling.modeldto.Exceptionbody;
import spring.restproject.restfeeinstitutebilling.services.AccountantService;
import spring.restproject.restfeeinstitutebilling.services.StudentService;

@RestController
@RequestMapping("/accountant")
public class AccountantController {

	@Autowired
	AccountantService accountantService;

	@Autowired
	StudentService studentService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostMapping("/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Accountant addAccountant(@RequestBody AccountantDto accountantDto) {
		accountantDto.setPassword(bCryptPasswordEncoder.encode(accountantDto.getPassword()));
		Accountant a = new Accountant();
		BeanUtils.copyProperties(accountantDto, a);
		return accountantService.saveAcc(a, "create");
	}

	@PostMapping("/update")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Accountant updateAccountant(@RequestParam String id, @RequestBody AccountantDto accountantDto) {
		accountantDto.setPassword(bCryptPasswordEncoder.encode(accountantDto.getPassword()));
		Accountant a = new Accountant();
		BeanUtils.copyProperties(accountantDto, a);
		a.setId(id);
		return accountantService.saveAcc(a, "update");
	}

	@GetMapping("/get")
	@ResponseStatus(code = HttpStatus.FOUND)
	public Accountant getAccountant(@RequestParam(required = true) String id) {
		return accountantService.getAccById(id);
	}

	@DeleteMapping("/delete")
	@ResponseStatus(code = HttpStatus.OK)
	public Exceptionbody deleteAccountant(@RequestParam(required = true) String id) {
		accountantService.deleteAccById(id);
		return new Exceptionbody("Accountant with user id " + id + " was deleted successfully", HttpStatus.OK.value());
	}

	@GetMapping("/all")
	@ResponseStatus(code = HttpStatus.FOUND)
	public List<Accountant> getallAccountant() {
		return accountantService.getAllAcc();
	}

}
