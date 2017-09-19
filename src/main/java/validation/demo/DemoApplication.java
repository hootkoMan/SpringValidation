package validation.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

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
        customer.setFirstName(null);
        customer.setLastName("Folin");
        customer.setCustomerType(CustomerType.CORPORATE);
        customer.setGender(null);
        Information information = new Information();
        information.setPersonCode(null);
        Address address = new Address();
        address.setBuilding(null);
        customer.setInformation(information);
        information.setAddress(address);


        validateCustomer(customer, myBeanValidationService);
        validateCustomer(information, myBeanValidationService);
        validateCustomer(address, myBeanValidationService);
    }

    private static <T> void validateCustomer(T obj, MyBeanValidationService myBeanValidationService) {
        Set<ConstraintViolation<T>> violations = new HashSet<>();
        violations = myBeanValidationService.validateCustomer(obj);

        listViolations(violations);
    }

    private static <T> void listViolations(Set<ConstraintViolation<T>> violations) {
        System.out.println(violations.size());

        for (ConstraintViolation<T> violation : violations) {
            System.out.println(violation.getPropertyPath());
            System.out.println(violation.getMessage());
            System.out.println(violation.getInvalidValue());
            System.out.println(violation.getMessageTemplate());

            System.out.println("-------------");
        }
    }
}
