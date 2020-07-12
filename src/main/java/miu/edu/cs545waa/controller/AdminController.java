package miu.edu.cs545waa.controller;

import miu.edu.cs545waa.domain.ProductReview;
import miu.edu.cs545waa.domain.Seller;
import miu.edu.cs545waa.domain.User;
import miu.edu.cs545waa.service.ProductReviewService;
import miu.edu.cs545waa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    ProductReviewService productReviewService;

    @GetMapping
    public String getAdminIndex() {
        return "admin/index";
    }

    @GetMapping("/accounts")
    public String getAccounts(Model model) {
        List<User> users = userService.getAll();
        System.out.println(users);
        model.addAttribute("users", users);
        return "admin/accounts";
    }

    @GetMapping("/sellers")
    public String getSellerAccounts(Model model) {
        List<User> sellers = userService.findByType("Seller");
        System.out.println(sellers);
        model.addAttribute("sellers", sellers);
        return "admin/sellers";
    }

    @GetMapping("/reviews")
    public String getReviews(Model model) {
        List<ProductReview> productReviews = productReviewService.getAll();
        System.out.println(productReviews);
        System.out.println(productReviews);
        model.addAttribute("productReviews", productReviews);
        return "admin/reviews";
    }
}
