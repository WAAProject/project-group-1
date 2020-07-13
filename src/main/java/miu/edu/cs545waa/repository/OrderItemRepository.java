package miu.edu.cs545waa.repository;

import miu.edu.cs545waa.domain.OrderItem;
import miu.edu.cs545waa.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    OrderItem findTopByProduct(Product product);

    @Query(value ="SELECT DISTINCT i FROM Buyer b INNER JOIN b.orderItems i WHERE b.id =:buyerId and i.product.seller.id= :sellerId")
    List<OrderItem> getItemsBySellerId(Long buyerId, Long sellerId);

}
