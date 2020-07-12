package miu.edu.cs545waa.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Size(min=2,max=100)
    private String street;

    @NotEmpty
    @Size(min=2,max=50)
    private String city;

    @NotEmpty
    @Size(min=2,max=50)
    private String state;

    @NotEmpty
    @Size(min=2,max=50)
    private String country;

    @NotEmpty
    @Size(min=2,max=50)
    private String zipCode;

    public Address() {
    }

    public Address(String street, String city, String state, String country, String zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }
}
