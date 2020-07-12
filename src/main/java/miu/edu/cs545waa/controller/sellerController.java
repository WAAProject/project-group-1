package miu.edu.cs545waa.controller;

import miu.edu.cs545waa.domain.Order;
import miu.edu.cs545waa.domain.OrderStatus;
import miu.edu.cs545waa.domain.Seller;
import miu.edu.cs545waa.service.OrderItemService;
import miu.edu.cs545waa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@SessionAttributes({"user"})
public class sellerController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private OrderItemService orderItemService;

    @GetMapping(value = "/sellerIndex")
    public String sellerPage() {
        return "sellerIndex";
    }

    @RequestMapping(value = "/orders",method = RequestMethod.GET)
    public String getOrder(Order order,Model model){
      Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
      Seller seller=(Seller) userService.findByEmail(authentication.getName());
      List<OrderStatus> orderStatusList= Arrays.asList(OrderStatus.values());
//      List<Order>orderList=userService.getOrdersBySeller(seller);
        model.addAttribute("orderStatus",orderStatusList);
//        model.addAttribute("orders",orderList);

        return "orders";

    }

    @RequestMapping(value = "/orders/{id}")
    public String getOrderById(Order order){
        //
      return "not yet";
    }

    public String orderDetails(Model model){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        Seller seller=(Seller) userService.findByEmail(authentication.getName());
//        model.addAttribute("orders",userService.getOrdersBySeller());
        return "orderList";
    }
}