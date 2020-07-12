package miu.edu.cs545waa.repository;

import miu.edu.cs545waa.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}
