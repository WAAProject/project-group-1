package miu.edu.cs545waa.service;

import miu.edu.cs545waa.domain.Buyer;
import miu.edu.cs545waa.domain.Product;
import miu.edu.cs545waa.domain.ProductReview;
import miu.edu.cs545waa.repository.ProductReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ProductReviewServiceImpl implements ProductReviewService{
    @Autowired
    ProductReviewRepo productReviewRepo;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Override
    public ProductReview save(ProductReview productReview) {
        return productReviewRepo.save(productReview);
    }

    @Override
    public ProductReview findById(Long id) {
        return productReviewRepo.findById(id).get();
    }

    @Override
    public List<ProductReview> getAll() {
        return (List<ProductReview>) productReviewRepo.findAll();
    }

    @Override
    public void addReviewToProduct(ProductReview productReview, Long productId) {
        Product product = productService.findById(productId);

        if(product!=null){
            productReview.setReviewDate(LocalDate.now());
            product.addReview(productReview);
            productService.save(product);
        }
        else{
            throw new NullPointerException("No product Found!");
        }

    }
}
