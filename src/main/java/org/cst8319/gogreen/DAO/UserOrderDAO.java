package org.cst8319.gogreen.DAO;

import org.cst8319.gogreen.DTO.UserOrder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserOrderDAO {

    private Connection connection;

    public UserOrderDAO() {
        try {
            this.connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUserOrder(UserOrder userOrder) {
        String sql = "INSERT INTO userOrder (userId, totalPrice) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, userOrder.getUserId());
            preparedStatement.setBigDecimal(2, userOrder.getTotalPrice());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        userOrder.setOrderId(generatedKeys.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public UserOrder getUserOrderById(int orderId) {
        String sql = "SELECT * FROM userOrder WHERE orderId = ?";
        UserOrder userOrder = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, orderId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userOrder = buildUserOrder(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userOrder;
    }



    public List<UserOrder> getAllUserOrders() {
        String sql = "SELECT * FROM userOrder";
        List<UserOrder> userOrders = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                UserOrder userOrder = buildUserOrder(resultSet);
                userOrders.add(userOrder);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userOrders;
    }

    public List<UserOrder> getAllOrdersByUserId(int userId) {
        String sql = "SELECT * FROM userOrder where userId = ? ";
        List<UserOrder> userOrders = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    UserOrder userOrder = buildUserOrder(resultSet);
                    userOrders.add(userOrder);
                }
            }} catch (SQLException e) {
            e.printStackTrace();
        }
        return userOrders;
    }


    public void updateUserOrder(UserOrder userOrder) {
        String sql = "UPDATE userOrder SET userId = ?, totalPrice = ? WHERE orderId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, userOrder.getUserId());
            preparedStatement.setBigDecimal(2, userOrder.getTotalPrice());
            preparedStatement.setInt(3, userOrder.getOrderId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteUserOrderById(int orderId) {
        String sql = "DELETE FROM userOrder WHERE orderId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, orderId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private UserOrder buildUserOrder(ResultSet resultSet) throws SQLException{
        UserOrder userOrder = new UserOrder();
        userOrder.setOrderId(resultSet.getInt("orderId"));
        userOrder.setUserId(resultSet.getInt("userId"));
        userOrder.setOrderTime(resultSet.getTimestamp("orderTime"));
        userOrder.setTotalPrice(resultSet.getBigDecimal("totalPrice"));
        return userOrder;
    }


}
