package validation.demo.annot;

import validation.demo.IndividualCustomerValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = IndividualCustomerValidator.class)
@Documented
public @interface CheckCustomer {
    String message() default "default IndividualCustomerValidator.class message validation";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}