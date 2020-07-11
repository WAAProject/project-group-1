package miu.edu.cs545waa.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "productCategory")
    private List<Product> productList=new ArrayList<>();

    public ProductCategory(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return name;
    }
}
