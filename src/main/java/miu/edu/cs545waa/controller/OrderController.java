package miu.edu.cs545waa.controller;

import miu.edu.cs545waa.domain.Order;
import miu.edu.cs545waa.domain.OrderStatus;
import miu.edu.cs545waa.service.CartService;
import miu.edu.cs545waa.service.OrderService;
import miu.edu.cs545waa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/buyer/order")
public class OrderController {

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @GetMapping("")
    public String getOrdersBySeller(Model model, Order order) {
        List<Order> orders = userService.getOrdersByBuyer();
//        OrderStatus status = orders.get(0).getStatus();
        model.addAttribute("orders", orders);
        return "buyer/buyerOrders";
    }

    @GetMapping("/checkout")
    public String checkOut(@ModelAttribute("order") Order order, Model model,
                           @RequestParam Long sellerId, @RequestParam(required = false) String coupon) {
        model = cartService.getCheckOutSummary(model, sellerId, coupon);
        return "buyer/checkoutForm";
    }

    @PostMapping("/save")
    public String saveOrder(@Valid @ModelAttribute("order") Order order, BindingResult bindingResult,
                            Model model, @RequestParam(required = false) String coupon, @RequestParam String sellerId,
                            RedirectAttributes redAttr) {
        if (bindingResult.hasErrors()) {
            model = cartService.getCheckOutSummary(model, Long.parseLong(sellerId), coupon);
            return "buyer/checkout";
        }

        order = orderService.saveOrder(order, sellerId, coupon);
        if (order != null) {
            redAttr.addFlashAttribute("message", "Order has been successfully added to your account. Thank you for Shopping with us!");
            return "redirect:/buyer/order";
        } else {
            return "order/checkout";
        }
    }

    @PostMapping("/cancel")
    public String cancelOrder(@RequestParam Long orderId, RedirectAttributes redAttr) {
        redAttr.addFlashAttribute("message", orderService.cancelOrder(orderId));
        return "redirect:/buyer/order";
    }
}