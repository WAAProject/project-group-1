package miu.edu.cs545waa.domain;

import javax.persistence.Entity;

@Entity
public class Admin extends User {

    public Admin() {
    }

    public Admin(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }
}
