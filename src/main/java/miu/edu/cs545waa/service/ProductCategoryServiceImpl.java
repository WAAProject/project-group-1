package miu.edu.cs545waa.service;

import miu.edu.cs545waa.domain.ProductCategory;
import miu.edu.cs545waa.repository.ProductCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{

    @Autowired
    ProductCategoryRepo productCategoryRepo;

    @Override
    public ProductCategory getCategoryById(Long id) {
        return productCategoryRepo.findById(id).get();
    }

    @Override
    public List<ProductCategory> getAll() {
        return (List<ProductCategory>) productCategoryRepo.findAll();
    }
}
