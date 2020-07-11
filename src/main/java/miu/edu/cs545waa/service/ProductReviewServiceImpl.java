package miu.edu.cs545waa.service;

import miu.edu.cs545waa.domain.Buyer;
import miu.edu.cs545waa.domain.Product;
import miu.edu.cs545waa.domain.ProductReview;
import miu.edu.cs545waa.repository.ProductReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductReviewServiceImpl implements ProductReviewService{
    @Autowired
    ProductReviewRepo productReviewRepo;

    @Autowired
    ProductService productService;

    @Override
    public ProductReview save(ProductReview review) {
        return productReviewRepo.save(review);
    }

    @Override
    public ProductReview findById(Long id) {
        return productReviewRepo.findById(id).get();
    }

    @Override
    public List<ProductReview> getAll() {
        return productReviewRepo.findAll();
    }

    @Override
    public ProductReview addReviewToProduct(ProductReview review, Long id) {
        Product product=productService.findById(id);
        if(product!=null){
           //need to work after buyer getAuthenticated
            return review;
        }
        else{
            throw new NullPointerException("No product Found!");
        }

    }
}
