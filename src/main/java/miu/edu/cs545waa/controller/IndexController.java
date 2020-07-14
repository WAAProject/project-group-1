package miu.edu.cs545waa.controller;

import miu.edu.cs545waa.domain.Product;
import miu.edu.cs545waa.domain.ProductCategory;
import miu.edu.cs545waa.service.CartService;
import miu.edu.cs545waa.service.ProductCategoryService;
import miu.edu.cs545waa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping
    public String index( Model model) {

        List<Product> products = productService.getAll();
        List<ProductCategory> categories = productCategoryService.getAll();

        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("size", cartService.getCartSize());

        Product randomOne = productService.getRandomOne();
        model.addAttribute("randomOne", randomOne);

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
