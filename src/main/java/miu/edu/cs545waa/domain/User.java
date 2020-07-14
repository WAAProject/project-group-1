package miu.edu.cs545waa.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
// Renaming DTYPE column to USER_TYPE
@DiscriminatorColumn(name="USER_TYPE", discriminatorType = DiscriminatorType.STRING)
public class User {

    @Column(name = "USER_TYPE", insertable = false, updatable = false)
    private String type;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name="First_Name")
    @NotEmpty
    @NotBlank
    private String firstName;

    @Column(name="Last_Name")
    @NotEmpty
    @NotBlank
    private String lastName;

    @Email(message = "{Email.validation}")
    @NotBlank(message = "{Email.validation}")
    @Column(unique = true)
    private String email;

    @Size(min=5,message = "{Size.validation}")
    @NotBlank(message = "{Password.validation}")
    private String password;

    private boolean enabled;

    private boolean isApproved;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, boolean enabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public String getType() {
        return type;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
