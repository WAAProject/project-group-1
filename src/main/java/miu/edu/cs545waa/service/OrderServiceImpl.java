package miu.edu.cs545waa.service;

import miu.edu.cs545waa.domain.*;
import miu.edu.cs545waa.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserService userService;
    @Autowired
    CartService cartService;

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public void updateOrderStatusById(Long Id, OrderStatus status) {
        Order order = findById(Id);
        Date date = new Date();
        order.setStatus(status);
        order.setOrderDate(date);
        orderRepository.save(order);
    }

    @Override
    public Order saveOrder(Order order, String sellerId, String coupon) {
        Buyer buyer = userService.getAuthenticatedBuyer();

        Seller seller = (Seller) userService.findById(Long.parseLong(sellerId));
        if(seller != null){
            List<OrderItem> items = cartService.getCartItemsBySeller(seller.getId());
            double result = 0.0;
            for(OrderItem i:items){
                result += i.getPrice();
                buyer.removeOrderItem(i);
            }
            if(coupon != null && buyer.getCoupon() > 0){
                result = result * 0.95;
                buyer.setCoupon(buyer.getCoupon() - 1);
            }else{
                buyer.setCoupon(buyer.getCoupon() + 1);
            }
            order.setSeller(seller);
            order.setBuyer(buyer);
            order.setSum(result);
            order.setItems(items);
            order.setOrderDate(new Date());
            order.setStatus(OrderStatus.New);

            userService.save(buyer);
            userService.save(seller);
            orderRepository.save(order);
            return order;
        }
        return null;
    }

    @Override
    public String cancelOrder(Long orderId) {
        Order order = this.findById(orderId);
        if(order !=null && order.getStatus() == OrderStatus.New){
            order.setStatus(OrderStatus.Cancelled);
            orderRepository.save(order);
            return "Order has been cancelled!";
        }else{
            return "Order can not be cancelled!";
        }
    }
}
