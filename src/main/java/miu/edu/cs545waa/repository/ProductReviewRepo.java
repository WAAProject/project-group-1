package miu.edu.cs545waa.repository;

import miu.edu.cs545waa.domain.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepo extends JpaRepository<ProductReview,Long> {
}
