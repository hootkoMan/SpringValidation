package validation.demo;

import lombok.Data;
import validation.demo.annot.CheckCustomer;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@CheckCustomer
public class Customer {
    @NotNull(message = "firstName must not be NULL")
    @Size(min = 2, max = 60)
    private String firstName;

    private String lastName;

    private Information information;

    @NotNull
    private CustomerType customerType;

    private Gender gender;

    @AssertTrue(message = "AssertTrue not pass")
    public boolean isIndividualCustomer() {
        return this.customerType.equals(CustomerType.INDIVIDUAL);
    }
}

enum CustomerType {
    INDIVIDUAL("I"), CORPORATE("C");

    private String code;

    private CustomerType(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CustomerType{" + "code='" + code + '\'' + '}';
    }
}

enum Gender {
    MALE("M"), FEMALE("F");

    private String code;

    Gender(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Gender{" + "code='" + code + '\'' + '}';
    }
}