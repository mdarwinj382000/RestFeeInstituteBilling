package spring.restproject.restfeeinstitutebilling.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.restproject.restfeeinstitutebilling.entities.BranchSemesterCourse;
import spring.restproject.restfeeinstitutebilling.entities.Fees;
import spring.restproject.restfeeinstitutebilling.entities.Payhistory;
import spring.restproject.restfeeinstitutebilling.entities.Payment;
import spring.restproject.restfeeinstitutebilling.entities.Student;
import spring.restproject.restfeeinstitutebilling.exceptionhandling.FeesException;
import spring.restproject.restfeeinstitutebilling.exceptionhandling.PayhistoryException;
import spring.restproject.restfeeinstitutebilling.exceptionhandling.PaymentException;
import spring.restproject.restfeeinstitutebilling.exceptionhandling.StudentException;
import spring.restproject.restfeeinstitutebilling.repositories.Payhistoryrepository;
import spring.restproject.restfeeinstitutebilling.repositories.Paymentrepository;
import spring.restproject.restfeeinstitutebilling.repositories.Studentrepository;

@Service
public class StudentService {

	@Autowired
	private Studentrepository studentrepository;

	@Autowired
	private Paymentrepository paymentrepository;

	@Autowired
	private Payhistoryrepository payhistoryrepository;

	@Autowired
	private AccountantService accountantService;

	public List<Student> getAllStudent() {
		return studentrepository.findAll().parallelStream().filter(Student.class::isInstance).map(Student.class::cast)
				.collect(Collectors.toList());
	}

	public Student getStudentById(String studentid) {
		return (Student) studentrepository.findById(studentid)
				.orElseThrow(() -> new StudentException("Given student user id is not Found"));
	}

	@Transactional(rollbackFor = FeesException.class)
	public Student saveStudent(Student a) {
		a = studentrepository.save(a)
				.orElseThrow(() -> new StudentException("Failed to add student user ,Check the data given"));
		Fees f = accountantService
				.getFeeByBranchSem(new BranchSemesterCourse(a.getBsc().getBranch(), a.getBsc().getSemester()));
		Payment p = new Payment(f.getAmount(), f.getAmount(), a);
		paymentrepository.save(p);
		return a;
	}

	public Student updateStudent(Student a) {
		return studentrepository.save(a)
				.orElseThrow(() -> new StudentException("Failed to update student user ,Check the data given"));
	}

	public void deleteStudentById(String studentid) {
		studentrepository.deleteById(studentid);
	}

	public Payment getPaymentById(Long paymentid) {
		return paymentrepository.findById(paymentid)
				.orElseThrow(() -> new PaymentException("Given payment id is not Found"));
	}

	public Payment getPaymentByStudent(String studentid) {
		Student s = getStudentById(studentid);
		return s.getPayment().parallelStream().filter(p -> p.getSemester().equals(s.getBsc().getSemester())).findFirst()
				.orElseThrow(() -> new PaymentException("Payment for student id is not Found"));
	}

	public Payment getPaymentByStudentwithsemester(String studentid, String semester) {
		Student s = getStudentById(studentid);
		return s.getPayment().parallelStream().filter(p -> p.getSemester().equals(semester)).findFirst()
				.orElseThrow(() -> new PaymentException("Payment for student id with semester is not Found"));
	}

	public void deletePaymentById(Long paymentid) {
		paymentrepository.deleteById(paymentid);
	}

	public Payhistory getPaymentHistoryById(Long payhistoryid) {
		return payhistoryrepository.findById(payhistoryid)
				.orElseThrow(() -> new PayhistoryException("Given payment history id is not Found"));
	}

	public List<Payhistory> getAllPaymentHistory(Payment payment) {
		List<Payhistory> payhistory = payhistoryrepository.findAllByPayment(payment)
				.orElseThrow(() -> new NullPointerException("payment history list is not intialized"));
		if (payhistory.isEmpty())
			throw new PayhistoryException("Payment history for given payment is not found");
		return payhistory;

	}

	public Payment updatePayment(String studentid, int amt) {
		Payment pay = getPaymentByStudent(studentid);
		int a = pay.getCrntremamnt() - amt;
		pay.setCrntremamnt(a);
		pay = paymentrepository.save(pay);
		Payhistory his = new Payhistory(amt, pay.getCrntremamnt(), LocalDate.now(), pay);
		payhistoryrepository.save(his);
		pay.setPayhistory(null);
		return pay;
	}
}
