package spring.restproject.restfeeinstitutebilling;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.apachecommons.CommonsLog;

@SpringBootApplication
@CommonsLog
public class RestFeeInstituteBillingApplication {

	@Value("${applicationname}")
	String applicationname;

	public static void main(String[] args) {
		SpringApplication.run(RestFeeInstituteBillingApplication.class, args);
	}

	@Bean
	public CommandLineRunner preintializer() {

		return run -> log.info("cmd runner " + applicationname + " db intialize!!");
	}
}
