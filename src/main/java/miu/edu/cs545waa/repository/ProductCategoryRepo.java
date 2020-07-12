package miu.edu.cs545waa.repository;

import miu.edu.cs545waa.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory,Long> {
}
