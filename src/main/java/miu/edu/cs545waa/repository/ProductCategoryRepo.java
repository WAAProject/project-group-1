package miu.edu.cs545waa.repository;

import miu.edu.cs545waa.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProductCategoryRepo extends CrudRepository<ProductCategory,Integer> {
}
