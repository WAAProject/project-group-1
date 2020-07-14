package miu.edu.cs545waa.controller;

import miu.edu.cs545waa.Cs545WaaApplication;
import miu.edu.cs545waa.domain.*;
import miu.edu.cs545waa.exception.ImageNotValidException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@SessionAttributes({"user"})
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping(value = "/products")
    public String productList(Model model, String category) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Seller seller = (Seller) userService.findByEmail(authentication.getName());

        List<Product> products = productService.getBySeller(seller);
        model.addAttribute("products", products);

        return "seller/listOfProducts";//display list with CRUD
    }

    @GetMapping(value = "/addProduct")
    public String addProduct(Model model) {
        Product product = new Product();
        List<Product> products = productService.getAll();
        List<ProductCategory> categories = productCategoryService.getAll();
        model.addAttribute("products", products);
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "seller/addProduct";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        System.out.println("im in add controller!!");
        if (result.hasErrors()) {
            return "seller/addProduct";
        }
        MultipartFile productImage = product.getProductImage();

        if (productImage != null && !productImage.isEmpty()) {
            // Checking for image only
            if (productImage.getContentType().contains("image/")) {
                System.out.println("Image is not null, content type: " + productImage.getContentType());
                try {
                    String fileNameAndPath = new ApplicationHome(Cs545WaaApplication.class).getDir() + "/static/images/" + productImage.getOriginalFilename();
                    productImage.transferTo(new File(fileNameAndPath));
                    product.setImageUrl("/images/" + productImage.getOriginalFilename());
                    System.out.println("Saved image!!!");
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("Product image can't be saved!!", e);
                }
            } else {
                throw new ImageNotValidException();
            }
        } else {
            System.out.println("Select Image");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Seller seller = (Seller) userService.findByEmail(authentication.getName());
        product.setSeller(seller);

        productService.save(product);
        redirectAttributes.addFlashAttribute(product);
        return "redirect:/seller/products";

    }

    @PostMapping(value = {"/updateProduct/{id}"})
    public String editProduct(@Valid @PathVariable(value = "id", required = false) Long id, @ModelAttribute("product") Product product, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        System.out.println(id);
//        ProductCategory cat=productService.getCategoryById(id);
        System.out.println(product.getProductCategory().getId());

        ProductCategory cat = productCategoryService.getCategoryById(product.getProductCategory().getId());
        System.out.println("=====================================" + cat);

        if (result.hasErrors()) {
            return "seller/updateProduct";
        }

        MultipartFile productImage = product.getProductImage();
        String url = new ApplicationHome(Cs545WaaApplication.class).getDir() + "\\static\\images\\";
        String imgName = "";
        if (productImage != null && !productImage.isEmpty()) {
            if (productImage.getContentType().contains("image/")) {
                try {
                    imgName = UUID.randomUUID().toString() + "." + productImage.getOriginalFilename();
                    System.out.println("imgName");
                    productImage.transferTo(new File(url + imgName));
                } catch (Exception e) {
                    throw new RuntimeException("Product image cant be saved!!", e);
                }
            } else {
                throw new ImageNotValidException();
            }
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Seller seller = (Seller) userService.findByEmail(authentication.getName());
        product.setSeller(seller);

        ProductCategory productCategory = productCategoryService.getCategoryById(product.getProductCategory().getId());
        if (id == null) {
            product.setImageUrl("images\\" + imgName);
            product.setSeller(seller);
            productService.save(product);
        } else {
            Product newProduct = productService.findById(id);
            newProduct.setName(product.getName());
            newProduct.setPrice(product.getPrice());
            newProduct.setDescription(product.getDescription());
            newProduct.setQuantity(product.getQuantity());
            if (!productImage.isEmpty() && !imgName.isEmpty()) {
                newProduct.setImageUrl("images\\" + imgName);
                newProduct.setProductImage(product.getProductImage());
            }
            newProduct.setImageUrl(newProduct.getImageUrl());
            newProduct.setProductCategory(productCategory);
            productService.save(newProduct);
        }
        redirectAttributes.addFlashAttribute(product);

        return "redirect:/seller/products";

    }

    @GetMapping(value = {"/editProduct/{id}"})
    public String findById(@PathVariable(value = "id") Long id, Model model) {
        Product product = productService.findById(id);
        List<Product> products = productService.getAll();
        List<ProductCategory> categories = productCategoryService.getAll();
        model.addAttribute("products", products);
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);

        return "seller/updateProduct";
    }

    @RequestMapping(value = "/product/{id}")
    public String update(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "seller/updateProduct";
    }

    @GetMapping(value = {"/productDetails"})
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

    @RequestMapping(value = "/deleteProduct/{id}")
    public String removeProduct(@PathVariable(value = "id") Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            throw new RuntimeException();
        }
        productService.deleteProduct(product);
        return "redirect:/seller/products";
    }

    @GetMapping("/orders")
    public String getOrder(Order order,Model model){
      Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
      Seller seller=(Seller) userService.findByEmail(authentication.getName());
      List<OrderStatus> orderStatusList= Arrays.asList(OrderStatus.values());
      List<Order>orderList=userService.getOrdersBySeller(seller);
        model.addAttribute("orderStatus",orderStatusList);
        model.addAttribute("orders",orderList);

        return "orders";

    }

    @RequestMapping(value = "/orders/{id}")
    public String getOrderById(Order order){
        orderService.updateOrderStatusById(order.getId(),order.getStatus());
        return "redirect:/seller/orders";

    }

    @GetMapping(value = {"/orderList"})
    public String orderDetails(Model model){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        Seller seller=(Seller) userService.findByEmail(authentication.getName());
        model.addAttribute("orders",userService.getOrdersBySeller(seller));
        return "seller/orderList";
    }
}