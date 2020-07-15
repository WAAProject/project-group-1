package miu.edu.cs545waa.domain;

public class ReviewDTO {
    private String message;
    private String productName;

    public ReviewDTO() {
    }

    public ReviewDTO(String message, String productName) {
        this.message = message;
        this.productName = productName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
