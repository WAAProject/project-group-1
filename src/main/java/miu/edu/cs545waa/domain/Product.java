package miu.edu.cs545waa.domain;

import org.springframework.web.multipart.MultipartFile;

public class Product {
    private Long id;
    private String productName;
    private double price;
    private String description;
    private ProductCategory category;
    private int quantity;
    private MultipartFile productImage;
}