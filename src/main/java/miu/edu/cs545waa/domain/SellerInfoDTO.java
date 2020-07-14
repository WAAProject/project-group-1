package miu.edu.cs545waa.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class SellerInfoDTO {
    @NotBlank
    private String sellerName;
    @NotNull
    private Long sellerId;
    @NotBlank
    private double total;
    private List<OrderItem> items = new ArrayList<>();

    public SellerInfoDTO(String sellerName, Long sellerId){
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.total = 0;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    public void addItem(OrderItem item){
        items.add(item);
        total += item.getPrice();
    }
    public void removeItem(OrderItem item){
        items.remove(item);
        total -= item.getPrice();
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
