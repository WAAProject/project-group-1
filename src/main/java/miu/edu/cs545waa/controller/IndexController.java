package miu.edu.cs545waa.controller;

import miu.edu.cs545waa.domain.Product;
import miu.edu.cs545waa.domain.ProductCategory;
import miu.edu.cs545waa.domain.Seller;
import miu.edu.cs545waa.domain.User;
import miu.edu.cs545waa.service.CartService;
import miu.edu.cs545waa.service.ProductCategoryService;
import miu.edu.cs545waa.service.ProductService;
import miu.edu.cs545waa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @GetMapping({"/" , "/product"})
    public String index(Model model, @RequestParam(required = false) String category) {

        if (category != null) {
            model.addAttribute("products", productService.getByCategory(Integer.parseInt(category)));
        } else {
            model.addAttribute("products", productService.getAll());
        }

        List<ProductCategory> categories = productCategoryService.getAll();
        model.addAttribute("categories", categories);

        Product randomOne = productService.getRandomOne();
        model.addAttribute("randomOne", randomOne);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByEmail(authentication.getName());

        if (user != null && user.getType().equalsIgnoreCase("Buyer")) {
            model.addAttribute("size", cartService.getCartSize());
        }

        return "index";
    }

    @GetMapping("/buyer")
    public String buyer() {
        return "buyer/index";
    }

    @GetMapping("/seller")
    public String seller() {
        return "seller/index";
    }
}
