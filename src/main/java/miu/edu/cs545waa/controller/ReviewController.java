package miu.edu.cs545waa.controller;

//import miu.edu.cs545waa.domain.ProductReview;
import miu.edu.cs545waa.service.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"userName"})
public class ReviewController {
    @Autowired
    ProductReviewService productReviewService;

    @GetMapping(value = "/product/review/{id}")
    //@ModelAttribute("review")ProductReview review,
    public String reviewForm( @PathVariable(value = "id")Long id){
        return "product/review";
    }
}
