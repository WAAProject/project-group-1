package miu.edu.cs545waa.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Buyer buyer;

    @Min(0)@Max(5)
    private int rating;

    @NotEmpty(message = "{review.NotEmpty}")
    @Size(min=10,message = "{review.min.size}")
    private String comment;

    private boolean isApproved;

    private LocalDate reviewDate;

    public ProductReview(){

    }

    public ProductReview(int rating, String comment, Buyer buyer) {
        this.buyer = buyer;
        this.rating = rating;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    @Override
    public String toString() {
        return "ProductReview{" +
                "id=" + id +
                ", buyer=" + buyer +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", isApproved=" + isApproved +
                ", reviewDate=" + reviewDate +
                '}';
    }
}
