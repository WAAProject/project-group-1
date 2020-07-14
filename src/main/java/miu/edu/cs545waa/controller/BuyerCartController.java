package miu.edu.cs545waa.controller;


import miu.edu.cs545waa.domain.CartRequestDTO;
import miu.edu.cs545waa.domain.CartResponseDTO;
import miu.edu.cs545waa.service.CartService;
import miu.edu.cs545waa.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class BuyerCartController {

    @Autowired
    CartService cartService;

    @Autowired
    OrderItemService orderItemService;

    @GetMapping
    public String showCartItems(Model model) {
        model = cartService.getCartItems(model);
        model.addAttribute("size", cartService.getCartSize());
        return "buyer/cartDetails";
    }

    @PostMapping
    public @ResponseBody CartResponseDTO addCartItem(@RequestBody CartRequestDTO productInfo) {
        System.out.println(productInfo);
        System.out.println(productInfo.getProductId());
        Long productId = Long.valueOf(productInfo.getProductId());
        int quantity = Integer.valueOf(productInfo.getQuantity());

        return cartService.addItem(productId, quantity);
    }

    @GetMapping("/remove")
    public String removeItem(@RequestParam Long id) {
        orderItemService.deleteItemById(id);
        return "redirect:/cart";
    }
}
