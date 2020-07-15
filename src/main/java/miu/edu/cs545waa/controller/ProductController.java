package miu.edu.cs545waa.controller;


import miu.edu.cs545waa.domain.ProductReview;
import miu.edu.cs545waa.exception.AlreadyOrderedProduct;
import miu.edu.cs545waa.exception.ImageNotValidException;
import miu.edu.cs545waa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;


@Controller
@SessionAttributes("userName")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductReviewService productReviewService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    private StorageService storageService;

    public void FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    public void forAllAttributes(Model model) {
        model.addAttribute("categories", productCategoryService.getAll());
    }

    @GetMapping("/product/{id}")
    public String getProductById(@PathVariable(value = "id") Long id, Model model, @ModelAttribute("review") ProductReview productReview) {
        model.addAttribute("product", productService.findById(id));
        return "product/productDetails";
    }

    @ExceptionHandler(AlreadyOrderedProduct.class)
    public ModelAndView excepHandler(HttpServletRequest request, ImageNotValidException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", e.getInvlaidMsg());
        modelAndView.setViewName("exception");
        return modelAndView;
    }

    @ExceptionHandler(ImageNotValidException.class)
    public ModelAndView errorHandler(HttpServletRequest request, ImageNotValidException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", e.getInvlaidMsg());
        modelAndView.setViewName("exception");
        return modelAndView;
    }
}
