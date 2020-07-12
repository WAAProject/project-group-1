package miu.edu.cs545waa.service;

import miu.edu.cs545waa.domain.Review;

import java.util.List;

public interface ProductReviewService {
    public Review save(Review review);
    public Review findById(Long id);
    public List<Review> getAll();
    public Review addReviewToProduct(Review review, Long id);
}
