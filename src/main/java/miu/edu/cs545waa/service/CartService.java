package miu.edu.cs545waa.service;

import miu.edu.cs545waa.domain.CartResponseDTO;
import miu.edu.cs545waa.domain.OrderItem;
import org.springframework.ui.Model;

import java.util.List;

public interface CartService {

    CartResponseDTO addItem(Long productId, int quantity);
    Model getCartItems(Model model);

    Model getCheckOutSummary(Model model, Long sellerId, String coupon);
    List<OrderItem> getCartItemsBySeller(Long sellerId);

    int getCartSize();

}
