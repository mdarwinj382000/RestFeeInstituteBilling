package spring.restproject.restfeeinstitutebilling.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import spring.restproject.restfeeinstitutebilling.entities.BranchSemesterCourse;
import spring.restproject.restfeeinstitutebilling.entities.Fees;
import spring.restproject.restfeeinstitutebilling.entities.Payhistory;
import spring.restproject.restfeeinstitutebilling.entities.Payment;
import spring.restproject.restfeeinstitutebilling.entities.Student;
import spring.restproject.restfeeinstitutebilling.modeldto.Exceptionbody;
import spring.restproject.restfeeinstitutebilling.modeldto.FeesDto;
import spring.restproject.restfeeinstitutebilling.modeldto.StudentDto;
import spring.restproject.restfeeinstitutebilling.services.AccountantService;
import spring.restproject.restfeeinstitutebilling.services.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	private String sdelete = " was deleted successfully";

	@Autowired
	AccountantService accountantService;

	@Autowired
	StudentService studentService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostMapping("/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Student addstudent(@RequestBody StudentDto studentDto) {
		studentDto.setPassword(bCryptPasswordEncoder.encode(studentDto.getPassword()));
		Student a = new Student();
		BeanUtils.copyProperties(studentDto, a);
		return studentService.saveStudent(a);
	}

	@PostMapping("/update")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Student updatestudent(@RequestParam String id, @RequestBody StudentDto studentDto) {
		studentDto.setPassword(bCryptPasswordEncoder.encode(studentDto.getPassword()));
		Student a = new Student();
		BeanUtils.copyProperties(studentDto, a);
		a.setId(id);
		return studentService.updateStudent(a);
	}

	@Secured(value = { "STUDENT", "ACCOUNTANT" })
	@GetMapping("/get")
	@ResponseStatus(code = HttpStatus.FOUND)
	public Student getstudent(@RequestParam(required = true) String id) {
		return studentService.getStudentById(id);
	}

	@DeleteMapping("/delete")
	@ResponseStatus(code = HttpStatus.OK)
	public Exceptionbody deletestudent(@RequestParam(required = true) String id) {
		studentService.deleteStudentById(id);
		return new Exceptionbody("Student with user id " + id + sdelete, HttpStatus.OK.value());
	}

	@GetMapping("/all")
	@ResponseStatus(code = HttpStatus.FOUND)
	public List<Student> getallstudent() {
		return studentService.getAllStudent();
	}

	@GetMapping("/getpaymenthistory")
	@ResponseStatus(code = HttpStatus.FOUND)
	public List<Payhistory> getpaymenthistory(@RequestParam(required = true) String id) {
		Payment p = studentService.getPaymentByStudent(id);
		return studentService.getAllPaymentHistory(p);
	}

	@GetMapping("/getpayment")
	@ResponseStatus(code = HttpStatus.FOUND)
	public Payment getpayment(@RequestParam(required = true) String id) {
		return studentService.getPaymentByStudent(id);
	}

	@GetMapping("/getpaymentwithsemester")
	@ResponseStatus(code = HttpStatus.FOUND)
	public Payment getpayment(@RequestParam(required = true) String id,
			@RequestParam(required = true) String semester) {
		return studentService.getPaymentByStudentwithsemester(id, semester);
	}

	@PostMapping("/updatepayment")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Payment updatepayment(@RequestParam(required = true) String id, @RequestParam(required = true) int amt) {
		return studentService.updatePayment(id, amt);
	}

	@DeleteMapping("/deletepayment")
	@ResponseStatus(code = HttpStatus.OK)
	public Exceptionbody deletepayment(@RequestParam(required = true) Long id) {
		studentService.deletePaymentById(id);
		return new Exceptionbody("Payment with id " + id + sdelete, HttpStatus.OK.value());
	}

	@PostMapping("/addfees")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Fees addfees(@RequestBody FeesDto feesDto) {
		Fees a = new Fees();
		BeanUtils.copyProperties(feesDto, a);
		return accountantService.saveFees(a, "Saving");
	}

	@PostMapping("/updatefees")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Fees updatefees(@RequestBody FeesDto feesDto) {
		Fees a = new Fees();
		BeanUtils.copyProperties(feesDto, a);
		return accountantService.saveFees(a, "Updating");
	}

	@GetMapping("/allfees")
	@ResponseStatus(code = HttpStatus.FOUND)
	public List<Fees> getallfees() {
		return accountantService.getAllFees();
	}

	@GetMapping("/getfeesbybranchsemester")
	@ResponseStatus(code = HttpStatus.FOUND)
	public Fees getfeesbybranchsem(@RequestBody(required = true) BranchSemesterCourse branchSemesterCourse) {
		return accountantService.getFeeByBranchSem(branchSemesterCourse);
	}

	@GetMapping("/getfeesbybranch")
	@ResponseStatus(code = HttpStatus.FOUND)
	public List<Fees> getfeesbybranch(@RequestParam(required = true) String branch) {
		return accountantService.getFeesByBranch(branch);
	}

	@DeleteMapping("/deletefeesbybranchsemester")
	@ResponseStatus(code = HttpStatus.OK)
	public Exceptionbody deletefeesbybranchsem(
			@RequestBody(required = true) BranchSemesterCourse branchSemesterCourse) {
		accountantService.deleteFeeByBranchSem(branchSemesterCourse);
		return new Exceptionbody(
				"Fees with " + branchSemesterCourse.getBranch() + " " + branchSemesterCourse.getSemester() + sdelete,
				HttpStatus.OK.value());
	}

	@DeleteMapping("/deletefeesbybranch")
	@ResponseStatus(code = HttpStatus.OK)
	public Exceptionbody deletefeesbybranch(@RequestParam(required = true) String branch) {
		accountantService.deleteFeeByBranch(branch);
		return new Exceptionbody("All Fees with " + branch + sdelete, HttpStatus.OK.value());
	}
}
