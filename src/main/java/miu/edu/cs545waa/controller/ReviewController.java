package miu.edu.cs545waa.controller;

//import miu.edu.cs545waa.domain.ProductReview;
import miu.edu.cs545waa.domain.Buyer;
import miu.edu.cs545waa.domain.ProductReview;
import miu.edu.cs545waa.domain.User;
import miu.edu.cs545waa.service.ProductReviewService;
import miu.edu.cs545waa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@SessionAttributes({"userName"})
public class ReviewController {
    @Autowired
    ProductReviewService productReviewService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/product/review/{id}")
    //@ModelAttribute("review")ProductReview review,
    public String reviewForm( @PathVariable(value = "id")Long id){
        return "product/review";
    }

    @PostMapping("/product/review/add")
    public String addReview(@ModelAttribute("productReview") ProductReview productReview, Principal principal,
                            @RequestParam("productId") Long productId, Model model) {
        Buyer buyer = (Buyer) userService.findByEmail(principal.getName());
        productReview.setBuyer(buyer);
        productReviewService.addReviewToProduct(productReview, productId);
        model.addAttribute("Your review has been added.");
        return "redirect:/product/" + productId;
    }
}