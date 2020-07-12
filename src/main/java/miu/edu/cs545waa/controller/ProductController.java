package miu.edu.cs545waa.controller;


import miu.edu.cs545waa.service.ProductCategoryService;
import miu.edu.cs545waa.service.ProductReviewService;
import miu.edu.cs545waa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductReviewService productReviewService;

    @Autowired
    ProductCategoryService productCategoryService;



}
