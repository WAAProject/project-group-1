package miu.edu.cs545waa.controller;


import miu.edu.cs545waa.Cs545WaaApplication;
import miu.edu.cs545waa.domain.Product;
import miu.edu.cs545waa.domain.ProductCategory;
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


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.UUID;

//@RequestMapping(value = "/")
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductReviewService productReviewService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private UserService userService;

    private StorageService storageService;

    public void FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    public void forAllAttributes(Model model) {
        model.addAttribute("categories", productCategoryService.getAll());
    }

    //
    @GetMapping("/products")
    public String productList(Model model, String category) {
        if (category == null) {
            model.addAttribute("products", productService.getAll());
        } else {
            model.addAttribute("products", productService.getByCategory(Integer.parseInt(category)));
            return "index";
        }
        return "listOfProducts";//display list with CRUD
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
//        model.addAttribute("files",)
        return "addProduct";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addProduct";
        }
        MultipartFile productImage = product.getProductImage();
        String url = new ApplicationHome(Cs545WaaApplication.class).getDir() + "\\static\\images\\";
        String imgName = "";
        if (productImage != null && !productImage.isEmpty()) {
            if (productImage.getContentType().contains("images/")) {
                try {
                    imgName = UUID.randomUUID().toString() + "." + productImage.getOriginalFilename();
                    System.out.println("imgName");
                    productImage.transferTo(new File(url + imgName));
                } catch (Exception e) {
                    throw new RuntimeException("Product image cant be saved!!", e);
                }
            }
        }else {
                throw new ImageNotValidException();
            }
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        Seller seller=(Seller) userService.findByEmail(authentication.getName());
            product.setSeller(seller);
            product.setImageUrl("images\\" + imgName);
            productService.save(product);
            return "redirect:/productDetails";
        }

        @ExceptionHandler(ImageNotValidException.class)
        public ModelAndView errorHandler(HttpServletRequest request, ImageNotValidException e){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("message",e.getInvlaidMsg());
        modelAndView.setViewName("exception");
        return modelAndView;

        }

        @RequestMapping(value = "/deleteProduct/{id}")
        public String removeProduct (@PathVariable(value = "id") Long id){
            Product product = productService.findById(id);
            if (product == null) {
                throw new RuntimeException();
            }
            productService.deleteProduct(product);
            return "redirect:/productDetails";
        }

        public String update(@PathVariable(value = "id")Long id,Model model){
        model.addAttribute("product",productService.findById(id));
        return "updateProduct";
        }

        @GetMapping(value = "{/productDetails}")
        public String prodDetails (@RequestParam(value = "id") Long id, Model model){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Seller seller = (Seller) userService.findByEmail(authentication.getName());
            if (id != null) {
                model.addAttribute("product", productService.findById(id));
                return "productDetails";
            }
            model.addAttribute("products", productService.getBySeller(seller));
            return "productList";
        }


        @ExceptionHandler(AlreadyOrderedProduct.class)
            public ModelAndView excepHandler(HttpServletRequest request, ImageNotValidException e){
                ModelAndView modelAndView=new ModelAndView();
                modelAndView.addObject("message",e.getInvlaidMsg());
                modelAndView.setViewName("exception");
                return modelAndView;

            }


    }
