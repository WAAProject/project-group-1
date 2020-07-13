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

    private StorageService storageService;

    public void FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    public void forAllAttributes(Model model) {
        model.addAttribute("categories", productCategoryService.getAll());
    }


    @GetMapping("seller/products")
    public String productList(Model model, String category) {
        if (category == null) {
            model.addAttribute("products", productService.getAll());
        } else {
            model.addAttribute("products", productService.getByCategory(Integer.parseInt(category)));
            return "index";
        }
        return "seller/listOfProducts";//display list with CRUD
    }

    @GetMapping("seller/addProduct")
    public String addProduct(Model model) {
        Product product = new Product();
        List<Product> products = productService.getAll();
        List<ProductCategory> categories = productCategoryService.getAll();
        model.addAttribute("products", products);
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "seller/addProduct";
    }


    @RequestMapping(value = "/seller/addProduct", method = RequestMethod.POST)
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model) {
        System.out.println("im in add controller!!");
        if (result.hasErrors()) {
            return "seller/addProduct";
        }
        MultipartFile productImage = product.getProductImage();
        String url = new ApplicationHome(Cs545WaaApplication.class).getDir() + "\\static\\images\\";
        String imgName = "";
        if (productImage != null && !productImage.isEmpty()) {
            if (productImage.getContentType().contains("images/")) {
                try {
                    imgName = UUID.randomUUID().toString() + "." + productImage.getOriginalFilename();
                    productImage.transferTo(new File(url + imgName));
                } catch (Exception e) {
                    throw new RuntimeException("Product image can't be saved!!", e);
                }
            }
        } else {
            throw new ImageNotValidException();
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Seller seller = (Seller) userService.findByEmail(authentication.getName());
        product.setSeller(seller);
        product.setImageUrl("images\\" + imgName);
        productService.save(product);
        return "redirect:/seller/products";

    }


    @ExceptionHandler(ImageNotValidException.class)
    public ModelAndView errorHandler(HttpServletRequest request, ImageNotValidException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", e.getInvlaidMsg());
        modelAndView.setViewName("exception");
        return modelAndView;

    }

    @RequestMapping(value = "/seller/deleteProduct/{id}")
    public String removeProduct(@PathVariable(value = "id") Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            throw new RuntimeException();
        }
        productService.deleteProduct(product);
        return "redirect:/seller/products";
    }

    @RequestMapping(value = "/seller/product/{id}")
    public String update(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "seller/updateProduct";
    }

    @GetMapping(value = "{/seller/productDetails}")
    public String prodDetails(@RequestParam(value = "id") Long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Seller seller = (Seller) userService.findByEmail(authentication.getName());
        if (id != null) {
            model.addAttribute("product", productService.findById(id));
            return "productDetails";
        }
        model.addAttribute("products", productService.getBySeller(seller));
        return "seller/productList";
    }

    @GetMapping("/seller/editProduct/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "seller/updateProduct";
    }

    @PostMapping(value = "{/seller/updateProduct/{id}}")
    public String editProduct(@Valid @PathVariable(value = "id", required = false) Long id, @ModelAttribute("product") Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "seller/updateProduct";
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
        } else {
            throw new ImageNotValidException();
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Seller seller = (Seller) userService.findByEmail(authentication.getName());
        product.setSeller(seller);
        product.setImageUrl("images\\" + imgName);

        ProductCategory productCategory = productCategoryService.getCategoryById(product.getProductCategory().getId());

        if (id == null) {
            product.setImageUrl("images\\" + imgName);
            product.setSeller(seller);
            productService.save(product);
        } else {
            Product newProduct = productService.findById(id);
            if (!productImage.isEmpty() && !imgName.isEmpty()) {
                newProduct.setImageUrl("images\\" + imgName);
                newProduct.setProductImage(product.getProductImage());
            }
            newProduct.setImageUrl(newProduct.getImageUrl());
            newProduct.setProductCategory(productCategory);
            productService.save(newProduct);
        }

        return "redirect:/seller/products";

    }

    @ExceptionHandler(AlreadyOrderedProduct.class)
    public ModelAndView excepHandler(HttpServletRequest request, ImageNotValidException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", e.getInvlaidMsg());
        modelAndView.setViewName("exception");
        return modelAndView;

    }


}
