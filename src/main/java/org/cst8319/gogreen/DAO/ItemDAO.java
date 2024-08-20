package org.cst8319.gogreen.DAO;

import org.cst8319.gogreen.DTO.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ItemDAO {

    private Connection connection;

    public ItemDAO() {
        try {
            this.connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveItem(Item item) {
        String sql = "INSERT INTO Item (userId, productId, orderId, quantity, price, itemTotalPrice, orderStatus) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, item.getUserId());
            preparedStatement.setInt(2, item.getProductId());
            if (item.getOrderId() != null) {
                preparedStatement.setInt(3, item.getOrderId());
            } else {
                preparedStatement.setNull(3, Types.INTEGER);
            }
            preparedStatement.setInt(4, item.getQuantity());
            preparedStatement.setBigDecimal(5, item.getPrice());
            preparedStatement.setBigDecimal(6, item.getItemTotalPrice());
            preparedStatement.setInt(7, item.getOrderStatus());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Item getItemById(int itemId) {
        String sql = "SELECT * FROM Item WHERE itemId = ?";
        Item item = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, itemId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                item = buildItem(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }


    public List<Item> getAllItems() {
        String sql = "SELECT * FROM Item";
        List<Item> items = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Item item = buildItem(resultSet);
                items.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }


    public void updateItem(Item item) {
        String sql = "UPDATE Item SET userId = ?, productId = ?, orderId = ?, quantity = ?, price = ?, itemTotalPrice = ?, orderStatus = ? WHERE itemId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, item.getUserId());
            preparedStatement.setInt(2, item.getProductId());
            if (item.getOrderId() != null) {
                preparedStatement.setInt(3, item.getOrderId());
            } else {
                preparedStatement.setNull(3, Types.INTEGER);
            }
            preparedStatement.setInt(4, item.getQuantity());
            preparedStatement.setBigDecimal(5, item.getPrice());
            preparedStatement.setBigDecimal(6, item.getItemTotalPrice());
            preparedStatement.setInt(7, item.getOrderStatus());
            preparedStatement.setInt(8, item.getItemId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteItemById(int itemId) {
        String sql = "DELETE FROM Item WHERE itemId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, itemId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Item> findByOrderStatusAndUserId(int orderStatus, int userId) {
        String sql = "SELECT * FROM Item WHERE userId = ? and orderStatus = ?";
        List<Item> items = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, orderStatus);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Item item = buildItem(resultSet);
                items.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public List<Item> findByOrderId(int orderId) {
        String sql = "SELECT * FROM Item WHERE orderId = ?";
        List<Item> items = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, orderId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Item item = buildItem(resultSet);
                items.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    private Item buildItem(ResultSet resultSet) throws SQLException {
        Item item = new Item();
        item.setItemId(resultSet.getInt("itemId"));
        item.setUserId(resultSet.getInt("userId"));
        item.setProductId(resultSet.getInt("productId"));
        item.setOrderId(resultSet.getInt("orderId"));
        item.setQuantity(resultSet.getInt("quantity"));
        item.setPrice(resultSet.getBigDecimal("price"));
        item.setItemTotalPrice(resultSet.getBigDecimal("itemTotalPrice"));
        item.setOrderStatus(resultSet.getInt("orderStatus"));

        return item;
    }

    public void deleteItemByOrderId(int orderId) {

        String sql = "DELETE FROM Item WHERE orderId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, orderId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
