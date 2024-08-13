package org.cst8319.gogreen.DAO;

import org.cst8319.gogreen.DTO.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;




public class OrderDAO {
    private Connection connection;

    public OrderDAO() {
        try {
            this.connection = DBConnection.getConnection();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }



    public void insertOrder(Order order) {
        String sql = "INSERT INTO `Order` (userId, orderStatus) (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order.getUserId());
            stmt.setInt(2, order.getOrderStatus());
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }




    public List<Order> getAllOrders() {
        List<Order> products = new ArrayList<>();
        String sql = "SELECT * FROM `Order`";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("orderId"));
                order.setOrderStatus(rs.getInt("OrderStatus"));
                products.add(order);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }



    public Order getOrderById(int orderId){
        Order order = null;
        String sql = "SELECT * FROM `Order` WHERE orderId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    order = new Order();
                    order.setUserId(rs.getInt("userId"));
                    order.setOrderStatus(rs.getInt("orderStatus"));
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return order;
    }


    public void updateOrder(Order order){
        String sql = "UPDATE `Order` SET userId = ?, orderStatus = ? WHERE orderId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order.getUserId());
            stmt.setInt(2, order.getOrderStatus());
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteOrder(int orderId){
        String sql = "DELETE FROM `Order` WHERE orderId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId );
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }




}
