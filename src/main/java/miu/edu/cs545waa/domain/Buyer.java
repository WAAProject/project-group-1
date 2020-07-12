package miu.edu.cs545waa.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("BUYER")
public class Buyer extends User {

    public Buyer() {
        super();
    }

    public Buyer(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }
}
