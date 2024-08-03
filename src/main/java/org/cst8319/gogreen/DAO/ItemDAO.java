package org.cst8319.gogreen.DAO;

import org.cst8319.gogreen.DTO.Item;
import org.cst8319.gogreen.util.dbUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ItemDAO {
    private JdbcTemplate template = new JdbcTemplate(dbUtil.getDataSource());

    public int confirmItem(int userId, int plantId) {
        String sql = "select count(*) from item where userId = ? and plantId = ?";
        Integer count = template.queryForObject(sql, Integer.class, userId, plantId);
        if (count > 0) {
            String getItemId = "select itemId from item where userId = ? and plantId = ?";
            Integer itemId = template.queryForObject(getItemId, Integer.class, userId, plantId);
            return itemId;
        } else {
            return 0;
        }
    }

    public void insertItem(int userId, int plantId, String plantName, int quantity, double price) {
        double totalPrice = quantity * price;
        String sql = "INSERT INTO item (userId, plantId, plantName, quantity, price, totalPrice) VALUES (?, ?, ?, ?, ?, ?)";
        template.update(sql, userId, plantId, plantName, quantity, price, totalPrice);
    }

    public void updateItem(int itemId, int quantity, Double price) {
        String sql = "UPDATE item SET quantity = quantity + ?, totalPrice = quantity * ? WHERE itemId = ?";
        template.update(sql, quantity, price, itemId);
    }

    public List<Item> findItemByUserId(int userId) {
        String sql = "SELECT * FROM item WHERE userId = ?";
        return template.query(sql, new BeanPropertyRowMapper<>(Item.class), userId);
    }

    public void deleteItem(int itemId) {
        String sql = "DELETE FROM item WHERE itemId = ?";
        template.update(sql, itemId);
    }
}