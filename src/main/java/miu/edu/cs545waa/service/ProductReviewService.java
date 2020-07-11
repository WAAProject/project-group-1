package miu.edu.cs545waa.service;

import miu.edu.cs545waa.domain.ProductReview;

import java.util.List;

public interface ProductReviewService {
    public ProductReview save(ProductReview review);
    public ProductReview findById(Long id);
    public List<ProductReview> getAll();
    public ProductReview addReviewToProduct(ProductReview review,Long id);
}
