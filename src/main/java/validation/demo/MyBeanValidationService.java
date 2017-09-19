package validation.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service("myBeanValidationService")
public class MyBeanValidationService {
    @Autowired
    private Validator validator;

    public <T> Set<ConstraintViolation<T>> validateCustomer(T customer) {
        return validator.validate(customer);
    }
}
