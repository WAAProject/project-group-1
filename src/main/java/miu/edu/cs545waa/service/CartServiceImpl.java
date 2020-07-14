package miu.edu.cs545waa.service;

import miu.edu.cs545waa.domain.*;
import miu.edu.cs545waa.repository.OrderItemRepository;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.Order;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    ProductService productService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    UserService userService;

    @Override
    public CartResponseDTO addItem(Long productId, int quantity) {

        CartResponseDTO response = new CartResponseDTO();
        Buyer buyer = userService.getAuthenticatedBuyer();
        Product product = productService.findById(productId);

        if(product == null || product.getQuantity() < quantity){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This Product is Not Available");
        }else{

            if (buyer == null) {
                response.setMessage("Please login first");
                return response;
            }

            List<OrderItem> items = buyer.getOrderItems();
            OrderItem item = items.stream()
                    .filter(p -> p.getProduct().getId() == productId)
                    .findAny()
                    .orElse(null);
            if(item == null) {
                buyer.addOrderItem(new OrderItem(product,quantity));
            }else {
                item.setQuantity(item.getQuantity() + quantity);
                item.setPrice(item.getPrice() + quantity * product.getPrice());
            }

            product.setQuantity(product.getQuantity() - quantity);
            response.setMessage(product.getName() + "has been successfully added to the cart.");
            response.setSize(buyer.getOrderItems().size());

            System.out.println(product);

            productService.save(product);
            userService.save(buyer);
            return response;
        }
    }

    @Override
    public Model getCartItems(Model model) {
        Buyer buyer = userService.getAuthenticatedBuyer();
        if(buyer.getCoupon() > 0){
            model.addAttribute("hasCoupon", true);
        }
        List<OrderItem> items = buyer.getOrderItems();
        List<SellerInfoDTO> cartDTOS = new ArrayList<>();
        for(OrderItem i:items){
            Seller seller = i.getProduct().getSeller();
            if(seller != null){
                SellerInfoDTO cartSellerDTO = cartDTOS.stream()
                        .filter(dto -> dto.getSellerId() == seller.getId())
                        .findAny()
                        .orElse(null);
                if(cartSellerDTO != null){
                    cartSellerDTO.addItem(i);
                }else{
                    cartSellerDTO = new SellerInfoDTO(seller.getFirstName(), seller.getId());
                    cartSellerDTO.addItem(i);
                    cartDTOS.add(cartSellerDTO);
                }
            }
        }
        model.addAttribute("itemBySellers",cartDTOS);
        model.addAttribute("grandTotal",0);
        return model;
    }

    @Override
    public Model getCheckOutSummary(Model model, Long sellerId, String coupon) {
        List<OrderItem> items = this.getCartItemsBySeller(sellerId);
        double subTotal = 0.0;
        double grandTotal = 0.0;
        for(OrderItem i:items){
            subTotal += i.getPrice();
        }
        grandTotal = subTotal;
        if(coupon != null){
            if(userService.buyerHasCoupon()){
                double discount = subTotal * 0.05;
                grandTotal = subTotal - discount;
                model.addAttribute("discount",discount);
            }
        }
        model.addAttribute("subTotal",subTotal);
        model.addAttribute("grandTotal",grandTotal);
        model.addAttribute("items",items);
        model.addAttribute("sellerId",sellerId);
        return model;
    }

    @Override
    public List<OrderItem> getCartItemsBySeller(Long sellerId) {
        Buyer buyer = userService.getAuthenticatedBuyer();
        return orderItemRepository.getItemsBySellerId(buyer.getId(), sellerId);
    }

    @Override
    public int getCartSize() {
        Buyer buyer = userService.getAuthenticatedBuyer();
        if(buyer!=null) {
            return buyer.getOrderItems().size();
        } else {
            return 0;
        }
    }

}
