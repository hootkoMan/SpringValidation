package validation.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@ImportResource("classpath:META-INF/spring/appcontex.xml")
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

		/*GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:META-INF/spring/appcontex.xml");
		ctx.refresh();*/

		MyBeanValidationService myBeanValidationService =
				context.getBean("myBeanValidationService", MyBeanValidationService.class);

		Customer customer = new Customer();
		customer.setFirstName("C");
		customer.setLastName("Folin");
		customer.setCustomerType(null);
		customer.setGender(null);

		validateCustomer(customer, myBeanValidationService);
	}

	private static void validateCustomer(Customer customer, MyBeanValidationService myBeanValidationService) {
		Set<ConstraintViolation<Customer>> violations = new HashSet<>();
		violations = myBeanValidationService.validateCustomer(customer);

		listViolations(violations);
	}

	private static void listViolations(Set<ConstraintViolation<Customer>> violations) {
		System.out.println(violations.size());

		for (ConstraintViolation<Customer> violation : violations) {
			System.out.println(violation.getPropertyPath());
		}
	}
}
