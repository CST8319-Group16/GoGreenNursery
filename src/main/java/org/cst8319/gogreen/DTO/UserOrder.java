package org.cst8319.gogreen.DTO;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class UserOrder {
    private int orderId;
    private int userId;
    private Timestamp orderTime;
    private BigDecimal totalPrice;

    public UserOrder() {
    }

    public UserOrder(int orderId, int userId, Timestamp orderTime, BigDecimal totalPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderTime = orderTime;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
