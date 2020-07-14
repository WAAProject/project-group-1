package miu.edu.cs545waa.controller;

import miu.edu.cs545waa.domain.Buyer;
import miu.edu.cs545waa.domain.ProductReview;
import miu.edu.cs545waa.domain.ReviewDTO;
import miu.edu.cs545waa.domain.User;
import miu.edu.cs545waa.service.ProductReviewService;
import miu.edu.cs545waa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/product/review")
public class ReviewController {
    @Autowired
    ProductReviewService productReviewService;

    @Autowired
    UserService userService;

    @PostMapping("/{id}")
    public String saveReview(@Valid @ModelAttribute("review") ProductReview review, BindingResult bindingResult,
                             @PathVariable(value = "id") Long id, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "product/"+id;
        }
        productReviewService.saveReviewToProduct(review,id);
        redirectAttributes.addAttribute("reviewMsg", "Review successfully added. Will be visible after approval :)");
        return "redirect:/product/"+id;
    }
}