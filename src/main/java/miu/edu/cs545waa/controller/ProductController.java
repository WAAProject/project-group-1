package miu.edu.cs545waa.controller;


import miu.edu.cs545waa.Cs545WaaApplication;
import miu.edu.cs545waa.domain.Product;
import miu.edu.cs545waa.domain.ProductCategory;
import miu.edu.cs545waa.domain.Seller;
import miu.edu.cs545waa.exception.ImageNotValidException;
import miu.edu.cs545waa.service.*;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.Valid;
import java.io.File;
import java.util.UUID;

@RequestMapping(value = "/seller")
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

    private  StorageService storageService;

    public void FileUploadController(StorageService storageService){
        this.storageService=storageService;
    }

    public void forAllAttributes(Model model){
        model.addAttribute("categories",productCategoryService.getAll());
    }

    @GetMapping("/products")
    public String productList(Model model, String category){
        if(category==null){
            model.addAttribute("products",productService.getAll());
        }
        else{
            model.addAttribute("products",productService.getByCategory(Integer.parseInt(category)));
        }
        return "listOfProducts";
    }

    @GetMapping("/addProduct")
    public String addProduct(Model model){
        Product product=new Product();
        model.addAttribute("product",product);
//        model.addAttribute("files",)
        return "addProduct";
    }
    @RequestMapping(value = "/addProduct",method = RequestMethod.POST)
    public String saveProduct(@Valid @ModelAttribute("product")Product product, BindingResult result,Model model){
     if(result.hasErrors()){
         return "addProduct";
     }
        MultipartFile productImage=product.getProductImage();
     String url=new ApplicationHome(Cs545WaaApplication.class).getDir()+"\\static\\images\\";
     String imgName="";
     if(productImage!=null&&!productImage.isEmpty()){
         if(productImage.getContentType().contains("image/")){
//             System.out.println(productImage.getContentType());
             try{
                 imgName= UUID.randomUUID().toString()+"."+productImage.getOriginalFilename();
                 System.out.println("imgName");
                 productImage.transferTo(new File(url+imgName));
             }
             catch (Exception e){
                 throw new RuntimeException("Product image cant be saved!!",e);
             }

         }
         else {
             throw new ImageNotValidException();
         }
     }
//     else {
//         System.out.println("Image is not selected!!");
//     }


//        product.setSeller(seller);
     product.setImageUrl("images\\"+imgName);
     productService.save(product);
     return "redirect:/product";
    }

    @RequestMapping(value = "/deleteProduct/{id}")
    public String removeProduct(@PathVariable(value = "id")Long id){
        Product product=productService.findById(id);
        if(product==null){

        }
        return "redirect:/productDetails";
    }






}
