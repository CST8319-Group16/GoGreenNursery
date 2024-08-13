package org.cst8319.gogreen.DAO;

import org.cst8319.gogreen.DTO.Item;
import org.cst8319.gogreen.DTO.Product;

import java.util.List;

import java.sql.*;
import java.util.ArrayList;


public class ItemDAO {
    private Connection connection;

    public ItemDAO() {
        try {
            this.connection = DBConnection.getConnection();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addItem(Item item) {
        String sql = "INSERT INTO Item (orderId, productId, quantity, price, totalprice) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, item.getOrderId());
            stmt.setInt(2, item.getProductId());
            stmt.setBigDecimal(3, item.getPrice());
            stmt.setInt(4, item.getQuantity());
            stmt.setBigDecimal(5, item.getTotalPrice());
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM Item";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Item item = new Item();
                item.setItemId(rs.getInt("itemId"));
                item.setOrderId(rs.getInt("orderId"));
                item.setProductId(rs.getInt("productId"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getBigDecimal("price"));
                item.setTotalPrice(rs.getBigDecimal("totalPrice"));
                items.add(item);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }

    public Item getItemById(int itemId){
        Item item = null;
        String sql = "SELECT * FROM Item WHERE itemId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, itemId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    item = new Item();
                    item.setItemId(rs.getInt("itemId"));
                    item.setOrderId(rs.getInt("orderId"));
                    item.setProductId(rs.getInt("productId"));
                    item.setQuantity(rs.getInt("quantity"));
                    item.setPrice(rs.getBigDecimal("price"));
                    item.setTotalPrice(rs.getBigDecimal("totalPrice"));
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }

    public void updateItem(Item item){
        String sql = "UPDATE Item SET orderId= ?, productId= ?, quantity = ?, price = ?, totalprice = ? WHERE itemId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, item.getItemId());
            stmt.setInt(2, item.getProductId());
            stmt.setInt(3, item.getQuantity());
            stmt.setBigDecimal(4, item.getPrice());
            stmt.setBigDecimal(5, item.getTotalPrice());
            stmt.setInt(6, item.getItemId());
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteItem(int itemId){
        String sql = "DELETE FROM Item WHERE itemId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, itemId);
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
