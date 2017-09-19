package validation.demo;

import org.springframework.stereotype.Service;
import validation.demo.annot.CheckCustomer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Service
public class IndividualCustomerValidator implements ConstraintValidator<CheckCustomer, Customer> {
    @Override
    public void initialize(CheckCustomer constraintAnnotation) {
    }

    @Override
    public boolean isValid(Customer value, ConstraintValidatorContext context) {
        boolean result = true;
        if (value.getCustomerType() == null) {
            result = false;
        }

        return result;
    }
}
