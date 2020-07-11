package miu.edu.cs545waa.service;

import miu.edu.cs545waa.domain.Product;
import miu.edu.cs545waa.domain.Seller;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    void save(Product product);
    List<Product>getByCategory(Integer categoryId);
    List<Product>getBySeller(Seller seller);
    void deleteByProduct(Product product);
    Product findById(Long id);
}
