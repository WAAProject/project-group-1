package miu.edu.cs545waa.domain;

public class CartResponseDTO {

    private String message;
    private int size;

    public CartResponseDTO(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
