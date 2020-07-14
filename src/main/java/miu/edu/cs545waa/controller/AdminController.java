package miu.edu.cs545waa.controller;

import miu.edu.cs545waa.domain.ProductReview;
import miu.edu.cs545waa.domain.Seller;
import miu.edu.cs545waa.domain.User;
import miu.edu.cs545waa.service.ProductReviewService;
import miu.edu.cs545waa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    ProductReviewService productReviewService;

    @GetMapping("/accounts")
    public String getAccounts(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "admin/accounts";
    }

    @PostMapping("/accounts/activate/{action}")
    public String updateAccount(@PathVariable("action") boolean action, @RequestParam("userId") Long userId) {

        User user = userService.findById(userId);
        if (user != null) {
            user.setEnabled(action);
            userService.save(user);
        }

        return "redirect:/admin/accounts";
    }

    @GetMapping("/sellers")
    public String getSellerAccounts(Model model) {
        List<User> sellers = userService.findByType("seller");
        model.addAttribute("sellers", sellers);
        System.out.println(sellers);
        return "admin/sellers";
    }

    @PostMapping("/sellers/approve/{action}")
    public String updateSeller(@PathVariable("action") boolean action, @RequestParam("sellerId") Long sellerId) {

        User user = userService.findById(sellerId);
        if (user != null && user.getType().equalsIgnoreCase("Seller")) {
            user.setApproved(action);
            userService.save(user);
        }

        return "redirect:/admin/sellers";
    }

    @GetMapping("/reviews")
    public String getReviews(Model model) {
        List<ProductReview> productReviews = productReviewService.getAll();
        model.addAttribute("productReviews", productReviews);
        return "admin/reviews";
    }

    @PostMapping("/reviews/approve/{action}")
    public String updateReview(@PathVariable("action") boolean action, @RequestParam("reviewId") Long reviewId, Model model) {
        ProductReview productReview = productReviewService.findById(reviewId);

        if (productReview != null) {
            productReview.setApproved(action);
            productReviewService.save(productReview);
        }
        return "redirect:/admin/reviews";
    }
}
