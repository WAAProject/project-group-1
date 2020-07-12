package miu.edu.cs545waa.exception;

public class NoProductFoundException extends RuntimeException {
    private String productId;

    public NoProductFoundException(String productId){
        this.productId=productId;
    }
    public String getProductId(){
        return productId;
    }
}
