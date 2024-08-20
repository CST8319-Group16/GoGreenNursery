package org.cst8319.gogreen.business;

import org.cst8319.gogreen.DAO.UserOrderDAO;
import org.cst8319.gogreen.DTO.UserOrder;

import java.util.List;

public class UserOrderService {

    private UserOrderDAO userOrderDAO;

    public UserOrderService() {
        this.userOrderDAO = new UserOrderDAO();
    }

    public void createUserOrder(UserOrder userOrder) {
         userOrderDAO.saveUserOrder(userOrder);
    }

    public UserOrder getUserOrderById(int orderId) {
        return userOrderDAO.getUserOrderById(orderId);
    }

    public List<UserOrder> getAllUserOrders() {
        return userOrderDAO.getAllUserOrders();
    }

    public void updateUserOrder(UserOrder userOrder) {
        userOrderDAO.updateUserOrder(userOrder);
    }

    public void deleteUserOrder(int orderId) {
        userOrderDAO.deleteUserOrderById(orderId);
    }

    public List<UserOrder> getAllOrdersByUserId(int userId) {return userOrderDAO.getAllOrdersByUserId(userId);
    }
}
