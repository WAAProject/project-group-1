package miu.edu.cs545waa.domain;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Buyer extends User {

    public Buyer() {
    }

    public Buyer(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }
}
