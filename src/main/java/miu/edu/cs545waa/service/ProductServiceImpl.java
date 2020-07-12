package miu.edu.cs545waa.service;

import miu.edu.cs545waa.domain.Product;
import miu.edu.cs545waa.domain.Seller;
import miu.edu.cs545waa.exception.NoProductFoundException;
import miu.edu.cs545waa.repository.ProductRepo;
import miu.edu.cs545waa.repository.ProductReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{


    @Autowired
    ProductRepo productRepo;


    @Override
    public List<Product> getAll() {
        return (List<Product>) productRepo.findAll();
    }

    @Override
    public void save(Product product) {
        productRepo.save(product);

    }

    @Override
    public List<Product> getByCategory(Integer categoryId) {
        return productRepo.findProductsByProductCategory(categoryId);
    }

    @Override
    public List<Product> getBySeller(Seller seller) {
        return productRepo.findProductsBySeller(seller);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepo.delete(product);

    }

    @Override
    public Product findById(Long id) {
        return productRepo.findById(id).orElseThrow(()->new NoProductFoundException(id.toString()));
    }
}
