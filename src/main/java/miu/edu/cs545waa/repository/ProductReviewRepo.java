package miu.edu.cs545waa.repository;

import miu.edu.cs545waa.domain.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductReviewRepo extends JpaRepository<ProductReview,Long> {
}
