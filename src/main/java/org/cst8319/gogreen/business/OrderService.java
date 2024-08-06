package org.cst8319.gogreen.business;

import org.cst8319.gogreen.DAO.OrderDAO;
import org.cst8319.gogreen.DTO.Order;

import java.util.List;

public class OrderService {
    OrderDAO dao = new OrderDAO();

    public void insertOrder(Order order) {

        dao.insertOrder(order);
    }
    public Order findLastOrder() {
        return dao.findLastOrder();
    }

    public void updateStatusById(int orderId,int newStatus) {
        dao.updateStatusById(orderId,newStatus);
    }

    public List<Order> findOrderById(int orderId) {
        return dao.findOrderById(orderId);
    }


}







