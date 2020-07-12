package miu.edu.cs545waa.repository;

import miu.edu.cs545waa.domain.Product;
import miu.edu.cs545waa.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product,Long> {

    List<Product> findProductsBySeller(Seller seller);

    @Query(value="SELECT prod from Product prod where prod.productCategory.id=:catId")
    List<Product>findProductsByProductCategory(Integer catId);
}
