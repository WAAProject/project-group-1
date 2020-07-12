package miu.edu.cs545waa;

import miu.edu.cs545waa.domain.*;
import miu.edu.cs545waa.repository.ProductCategoryRepo;
import miu.edu.cs545waa.repository.ProductRepo;
import miu.edu.cs545waa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductCategoryRepo categoryRepo;
private final ProductRepo productRepo;
    @Autowired
    public DataLoader(UserRepository userRepository, ProductCategoryRepo categoryRepo, ProductRepo productRepo) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.categoryRepo=categoryRepo;
        this.productRepo=productRepo;

        }


    @Override
    public void run(ApplicationArguments args) throws Exception {

        Admin admin = new Admin("Admin", "Admin", "admin@admin.com", passwordEncoder.encode("admin"));
        Buyer buyer1 = new Buyer("Buyer1", "Buyer1", "buyer1@buyer1.com", passwordEncoder.encode("buyer1"));
        Buyer buyer2 = new Buyer("Buyer2", "Buyer2", "buyer2@buyer2.com", passwordEncoder.encode("buyer2"));
        Seller seller1 = new Seller("Seller1", "Seller1", "seller1@seller1.com", passwordEncoder.encode("seller1"));
        Seller seller2 = new Seller("Seller2", "Seller2", "seller2@seller2.com", passwordEncoder.encode("seller2"));
        userRepository.save(admin);
        userRepository.save(buyer1);
        userRepository.save(buyer2);
        userRepository.save(seller1);
        userRepository.save(seller2);
        ProductCategory category1= new ProductCategory("Laptop");
        ProductCategory category2=new ProductCategory("Mobile");
        ProductCategory category3=new ProductCategory("Camera");
        ProductCategory catagory4=new ProductCategory("Watch");
        ProductCategory catagory5=new ProductCategory("Bluetooth Speaker");
        categoryRepo.save(catagory4);
        categoryRepo.save(category3);
        categoryRepo.save(category1);
        categoryRepo.save(category2);
        categoryRepo.save(catagory5);
        // public Product(String name, String description, double price,
        //                   String imageUrl, int quantity, ProductCategory category, Seller seller){
       Product pro1=new Product("beats123B","This product is not eligible for promotional offers and coupons. However, you are able to earn and redeem Kohl's Cash and Kohl's Rewards on this product."
       , 123.04,"/images/beats123B.jpg",5,catagory5, seller1);
        Product pro2=new Product("digitalWatch", "Digital Waterproof Sports Watch Electronic Military LED Sport Running Watch Multifunction Wrist Stopwatch", 20.00,"/images/digitalWatch.jpg",
                6,catagory4,seller2);
        Product pro3=new Product("deskLaptop", "nvisible Lightweight Laptop Stand Portable, Adhesive Laptop Stand Foldable, Compatible with MacBook, Air, Pro, Tablets and Laptops up to 15.6” (Gray)"
        , 500.00,"/images/deskLaptop.jpg",7,category1,seller1);
        Product pro4=new Product("htc","The next generation of Edge Sense offers more useful features and now even knows which hand you are using, giving you true one-handed freedom for either hand.",
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
        productRepo.save(pro1);
        productRepo.save(pro2);
        productRepo.save(pro3);
        productRepo.save(pro4);
        productRepo.save(pro5);
        productRepo.save(pro6);
        productRepo.save(pro7);

    }
}
