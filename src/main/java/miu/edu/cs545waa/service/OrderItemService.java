package miu.edu.cs545waa.service;

import miu.edu.cs545waa.domain.OrderItem;
import miu.edu.cs545waa.domain.Product;

public interface OrderItemService {

//    OrderItem findTopByProduct(Product product);

    void deleteItemById(Long itemId);

}
