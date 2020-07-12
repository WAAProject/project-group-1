package miu.edu.cs545waa.service;

import miu.edu.cs545waa.domain.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory getCategoryById(Long id);
    List<ProductCategory> getAll();
}