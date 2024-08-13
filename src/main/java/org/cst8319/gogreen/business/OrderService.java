package org.cst8319.gogreen.business;

import org.cst8319.gogreen.DAO.OrderDAO;
import org.cst8319.gogreen.DAO.ProductDAO;
import org.cst8319.gogreen.DTO.Order;
import org.cst8319.gogreen.DTO.Product;

import java.util.List;

public class OrderService {
    private OrderDAO orderDAO;

    public OrderService() {
        this.orderDAO = new OrderDAO();
    }

    public void insertOrder(Order order) {
        orderDAO.insertOrder(order);
    }

    public List<Order> getAllOrders(){
        return orderDAO.getAllOrders();
    }

    public Order getOrderById(int orderId){

        return orderDAO.getOrderById(orderId);

    }


    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }

}







