package miu.edu.cs545waa.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class payment {
    @Id
    @GeneratedValue
    private Long id;

    @CreditCardNumber
    @Size(min=16,max=16, message = "Card should have 16 numbers!")
    private String cardNumber;

    @NotEmpty
    private String nameOnCard;

    @NotNull
    private int expriyMonth;

    @NotNull
    private int expriryYear;
}
