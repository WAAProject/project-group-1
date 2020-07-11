package miu.edu.cs545waa.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @GeneratedValue
    public Long id;

    private int quantity;

    private double price;

    @OneToOne
    private Product product;

}
