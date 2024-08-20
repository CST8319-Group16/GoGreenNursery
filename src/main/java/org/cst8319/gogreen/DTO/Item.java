package org.cst8319.gogreen.DTO;

import java.math.BigDecimal;

public class Item {
    private int itemId;
    private int userId;
    private int productId;
    private Integer orderId; // allow null
    private int quantity;
    private BigDecimal price;
    private BigDecimal itemTotalPrice;
    private int orderStatus;

    public Item(){}

    public Item(int itemId, int userId, int productId, Integer orderId, int quantity, BigDecimal price, BigDecimal itemTotalPrice, int orderStatus) {
        this.itemId = itemId;
        this.userId = userId;
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
        this.itemTotalPrice = itemTotalPrice;
        this.orderStatus = orderStatus;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getItemTotalPrice() {
        return itemTotalPrice;
    }

    public void setItemTotalPrice(BigDecimal itemTotalPrice) {
        this.itemTotalPrice = itemTotalPrice;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", userId=" + userId +
                ", productId=" + productId +
                ", orderId=" + orderId +
                ", quantity=" + quantity +
                ", price=" + price +
                ", itemTotalPrice=" + itemTotalPrice +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
