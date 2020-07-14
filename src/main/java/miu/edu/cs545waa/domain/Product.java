package miu.edu.cs545waa.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 4, max = 50, message = "{Size.validation}")
    private String name;

    @NotEmpty(message = "{Prod.Desc.Valid}")
    @Lob
    private String description;

    @NotEmpty
    @Min(value = 0, message = "{Product.Price.validation}")
    private double price;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "{Product.Category.NotNull}")
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name="seller_id")
    private Seller seller;

    @NotNull(message = "{Product.quantity.NotEmpty}")
    private int quantity;
    private boolean enabled = false;

    @Transient
    @JsonIgnore
    private MultipartFile productImage;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="product_id")
    private List<ProductReview> prodProductReviewList =new ArrayList<>();

    public Product(){}
    public Product(String name, String description, double price,
                   String imageUrl, int quantity, ProductCategory category, Seller seller){
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.enabled = true;
        this.productCategory = category;
        this.seller = seller;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public MultipartFile getProductImage() {
        return productImage;
    }

    public void setProductImage(MultipartFile productImage) {
        this.productImage = productImage;
    }

    public List<ProductReview> getProdProductReviewList() {
        return prodProductReviewList;
    }

    public void setProdProductReviewList(List<ProductReview> prodProductReviewList) {
        this.prodProductReviewList = prodProductReviewList;
    }

    public void addReview(ProductReview productReview) {
        this.prodProductReviewList.add(productReview);
    }
}
