package miu.edu.cs545waa.controller;


import miu.edu.cs545waa.Cs545WaaApplication;
import miu.edu.cs545waa.domain.Product;
import miu.edu.cs545waa.domain.ProductCategory;
import miu.edu.cs545waa.domain.ProductReview;
import miu.edu.cs545waa.domain.Seller;
import miu.edu.cs545waa.exception.AlreadyOrderedProduct;
import miu.edu.cs545waa.exception.ImageNotValidException;
import miu.edu.cs545waa.exception.InvalidImageException;
import miu.edu.cs545waa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.catalog.CatalogException;
import java.io.File;
import java.util.List;
import java.util.UUID;


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
    public String getProductById(@ModelAttribute("review") ProductReview review, @PathVariable(value = "id") Long id,
                                 Model model, @RequestParam(required = false) String reviewMsg) {
        model.addAttribute("product", productService.findById(id));
        if(reviewMsg!=null) {
            model.addAttribute("reviewMsg", reviewMsg);
        }
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
