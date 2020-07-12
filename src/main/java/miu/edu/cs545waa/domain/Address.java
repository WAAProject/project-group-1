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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
