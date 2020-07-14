package miu.edu.cs545waa.service;

import miu.edu.cs545waa.domain.ProductCategory;
import miu.edu.cs545waa.repository.ProductCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService{

    @Autowired
    ProductCategoryRepo productCategoryRepo;

    @Override
    public ProductCategory getCategoryById(int id) {
        return productCategoryRepo.findById(id).get();
    }

    @Override
    public List<ProductCategory> getAll() {
        return (List<ProductCategory>) productCategoryRepo.findAll();
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepo.save(productCategory);
    }
}
