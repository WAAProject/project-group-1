package miu.edu.cs545waa.repository;

import miu.edu.cs545waa.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
