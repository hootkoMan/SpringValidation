package validation.demo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Information {
    @NotNull
    private String personCode;
    @NotNull
    private Address address;

}

@Data
class Address {
    @NotNull
    private String street;
    @NotNull
    private String building;
}
