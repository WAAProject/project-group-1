package miu.edu.cs545waa.repository;

import miu.edu.cs545waa.domain.Product;
import miu.edu.cs545waa.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

    List<Product> findProductsBySeller(Seller seller);

    @Query(value="SELECT prod from Product prod where prod.productCategory.id=:catId")
    List<Product>findProductsByProductCategory(Integer catId);

    @Query(nativeQuery=true, value="SELECT *  FROM Product ORDER BY random() LIMIT 1")
    Product getRandomOne();
}
