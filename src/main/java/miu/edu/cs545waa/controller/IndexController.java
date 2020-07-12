package miu.edu.cs545waa.controller;

import miu.edu.cs545waa.domain.Product;
import miu.edu.cs545waa.domain.ProductCategory;
import miu.edu.cs545waa.service.ProductCategoryService;
import miu.edu.cs545waa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
 @Autowired
    ProductService productService;
 @Autowired
    ProductCategoryService productCategoryService;
    @GetMapping("/")
    public String index( Model model) {
      List<Product> allproducts=productService.getAll();
      List<ProductCategory> categories=productCategoryService.getAll();

      model.addAttribute("products", allproducts);
      model.addAttribute("catagories", categories);
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
