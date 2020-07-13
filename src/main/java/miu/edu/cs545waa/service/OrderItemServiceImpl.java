package miu.edu.cs545waa.service;

import miu.edu.cs545waa.domain.OrderItem;
import miu.edu.cs545waa.domain.Product;
import miu.edu.cs545waa.repository.OrderItemRepository;
import miu.edu.cs545waa.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService{

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    ProductRepo productRepo;

//    @Override
//    public OrderItem findTopByProduct(Product product) {
//        return OrderItemRepository.findTopByProduct();
//    }

    @Override
    public void deleteItemById(Long itemId) {
        OrderItem item = orderItemRepository.findById(itemId).get();
        Product product = item.getProduct();
        product.setQuantity(product.getQuantity() + item.getQuantity());
        productRepo.save(product);
        orderItemRepository.deleteById(itemId);
    }
}
