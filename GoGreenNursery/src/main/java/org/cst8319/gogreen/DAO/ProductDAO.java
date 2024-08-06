package org.cst8319.gogreen.DAO;

import org.cst8319.gogreen.DTO.Product;

import java.util.List;

import java.sql.*;
import java.util.ArrayList;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// By Joshua Below:
public class ProductDAO {
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Product";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setProductId(resultSet.getInt("productId"));
                    product.setProductName(resultSet.getString("productName"));
                    product.setProductDesc(resultSet.getString("productDesc"));
                    product.setPrice(resultSet.getBigDecimal("price"));
                    product.setStock(resultSet.getInt("stock"));
                    product.setCategoryId(resultSet.getInt("categoryId"));
                    product.setImageURL(resultSet.getString("imageURL"));
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}

    public void addProduct(Product product) {
        String sql = "INSERT INTO Product (productName, productDesc, price, stock, categoryId, imageURL) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getProductDesc());
            stmt.setBigDecimal(3, product.getPrice());
            stmt.setInt(4, product.getStock());
            stmt.setInt(5, product.getCategoryId());
            stmt.setString(6, product.getImageURL());
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("productId"));
                product.setProductName(rs.getString("productName"));
                product.setProductDesc(rs.getString("productDesc"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setStock(rs.getInt("stock"));
                product.setCategoryId(rs.getInt("categoryId"));
                product.setImageURL(rs.getString("imageURL"));
                products.add(product);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }

    public Product getProductById(int productId){
        Product product = null;
        String sql = "SELECT * FROM Product WHERE productId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    product = new Product();
                    product.setProductId(rs.getInt("productId"));
                    product.setProductName(rs.getString("productName"));
                    product.setProductDesc(rs.getString("productDesc"));
                    product.setPrice(rs.getBigDecimal("price"));
                    product.setStock(rs.getInt("stock"));
                    product.setCategoryId(rs.getInt("categoryId"));
                    product.setImageURL(rs.getString("imageURL"));
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return product;
    }

    public void updateProduct(Product product){
        String sql = "UPDATE Product SET productName = ?, productDesc = ?, price = ?, stock = ?, categoryId = ?, imageURL = ? WHERE productId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getProductDesc());
            stmt.setBigDecimal(3, product.getPrice());
            stmt.setInt(4, product.getStock());
            stmt.setInt(5, product.getCategoryId());
            stmt.setString(6, product.getImageURL());
            stmt.setInt(7, product.getProductId());
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteProduct(int productId){
        String sql = "DELETE FROM Product WHERE productId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
