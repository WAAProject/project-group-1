package miu.edu.cs545waa;

import miu.edu.cs545waa.domain.*;
import miu.edu.cs545waa.repository.ProductCategoryRepo;
import miu.edu.cs545waa.repository.ProductRepo;
import miu.edu.cs545waa.repository.UserRepository;
import miu.edu.cs545waa.service.ProductCategoryService;
import miu.edu.cs545waa.service.ProductReviewService;
import miu.edu.cs545waa.service.ProductService;
import miu.edu.cs545waa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ProductCategoryService productCategoryService;
    private final ProductService productService;
    private final ProductReviewService productReviewService;

    @Autowired
    public DataLoader(UserService userService, ProductCategoryService productCategoryService,
                      ProductService productService, ProductReviewService productReviewService) {
        this.userService = userService;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.productCategoryService = productCategoryService;
        this.productService = productService;
        this.productReviewService = productReviewService;
        }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // User
        Admin admin = new Admin("Admin", "Admin", "admin@admin.com", passwordEncoder.encode("admin"), true);
        Buyer buyer1 = new Buyer("Buyer1", "Buyer1", "buyer1@buyer1.com", passwordEncoder.encode("buyer1"), true);
        Buyer buyer2 = new Buyer("Buyer2", "Buyer2", "buyer2@buyer2.com", passwordEncoder.encode("buyer2"), true);
        Buyer buyer3 = new Buyer("Buyer3", "Buyer3", "buyer3@buyer3.com", passwordEncoder.encode("buyer3"), false);
        Seller seller1 = new Seller("Seller1", "Seller1", "seller1@seller1.com", passwordEncoder.encode("seller1"), true);
        Seller seller2 = new Seller("Seller2", "Seller2", "seller2@seller2.com", passwordEncoder.encode("seller2"), true);
        Seller seller3 = new Seller("Seller3", "Seller3", "seller3@seller3.com", passwordEncoder.encode("seller3"), false);
        seller1.setApproved(true);
        seller2.setApproved(true);
        userService.save(admin);
        userService.save(buyer1);
        userService.save(buyer2);
        userService.save(buyer3);
        userService.save(seller1);
        userService.save(seller2);
        userService.save(seller3);

        // Categories
        ProductCategory category1 = new ProductCategory("Laptop");
        ProductCategory category2 = new ProductCategory("Mobile");
        ProductCategory category3 = new ProductCategory("Camera");
        ProductCategory category4 = new ProductCategory("Watch");
        ProductCategory category5 = new ProductCategory("Bluetooth Speaker");
        productCategoryService.save(category1);
        productCategoryService.save(category2);
        productCategoryService.save(category3);
        productCategoryService.save(category4);
        productCategoryService.save(category5);

        // Products
        Product pro1=new Product("beats123B","This product is not eligible for promotional offers and coupons. However, you are able to earn and redeem Kohl's Cash and Kohl's Rewards on this product."
       , 123.04,"/images/beats123B.jpg",5,category5, seller1);
        Product pro2=new Product("digitalWatch", "Digital Waterproof Sports Watch Electronic Military LED Sport Running Watch Multifunction Wrist Stopwatch", 20.00,"/images/digitalWatch.jpg",
                6,category4,seller2);
        Product pro3=new Product("deskLaptop", "nvisible Lightweight Laptop Stand Portable, Adhesive Laptop Stand Foldable, Compatible with MacBook, Air, Pro, Tablets and Laptops up to 15.6” (Gray)"
        , 500.00,"/images/deskLaptop.jpg",7,category1,seller1);
        Product pro4=new Product("htc1","The next generation of Edge Sense offers more useful features and now even knows which hand you are using, giving you true one-handed freedom for either hand.",
                700.00,"/images/htc.jpg", 2, category2,seller2);
        Product pro5=new Product("drone","Comes with SanDisk Extreme microSD Card 128GB",
                900.00, "/images/drone.jpg",1,category3,seller2);
        Product pro6=new Product("Dell Monitor","An accessible 19.5\" monitor made for your daily workflow. Featuring an elegant design, 1600x900 resolution and Dell Display Manager.",
                89.0,"/images/monitor.jpg",3,category1,seller1);
        Product pro7=new Product("macGadget","All-New Apple Mac Pro 2020 Desktop", 1500.00,"/images/macGadget.png",10,category1,seller2);
        pro1.setEnabled(true);
        pro2.setEnabled(true);
        pro3.setEnabled(true);
        pro4.setEnabled(true);
        pro5.setEnabled(true);
        pro7.setEnabled(true);
        pro6.setEnabled(true);
        productService.save(pro1);
        productService.save(pro2);
        productService.save(pro3);
        productService.save(pro4);
        productService.save(pro5);
        productService.save(pro6);
        productService.save(pro7);

        // Reviews
        ProductReview review1 = new ProductReview(5, "Good product!", buyer1);
        ProductReview review2 = new ProductReview(4, "Bawd product!", buyer2);
        ProductReview review3 = new ProductReview(4, "awdawd product!", buyer3);
        ProductReview review4 = new ProductReview(2, "3 rawoduct!", buyer1);
        ProductReview review5 = new ProductReview(5, "Ad 13 roduct!", buyer2);
        ProductReview review6 = new ProductReview(4, "adz product!", buyer3);
        ProductReview review7 = new ProductReview(1, "1  11 product!", buyer1);
        productReviewService.addReviewToProduct(review1, 6L);
        productReviewService.addReviewToProduct(review2, 7L);
        productReviewService.addReviewToProduct(review3, 8L);
        productReviewService.addReviewToProduct(review4, 9L);
        productReviewService.addReviewToProduct(review5, 10L);
        productReviewService.addReviewToProduct(review6, 11L);
        productReviewService.addReviewToProduct(review7, 8L);

    }
}
