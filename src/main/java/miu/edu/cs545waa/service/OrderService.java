package miu.edu.cs545waa.service;

import miu.edu.cs545waa.domain.Order;
import miu.edu.cs545waa.domain.OrderStatus;

public interface OrderService {
    Order findById(Long id);

    void updateOrderStatusById(Long Id, OrderStatus status);

    Order saveOrder(Order order, String sellerId, String coupon);

    String cancelOrder(Long orderId);

}
