package org.cst8319.gogreen.DAO;

import org.cst8319.gogreen.DTO.Order;
import org.cst8319.gogreen.util.dbUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;




public class OrderDAO {
    private JdbcTemplate template = new JdbcTemplate(dbUtil.getDataSource());

    public void insertOrder(Order order) {
        String sql = "insert into orders (userId, plantId, plantName, price, totalPrice, orderTime, orderStatus) values (?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, order.getUserId(), order.getPlantId(), order.getPlantName(), order.getPrice(), order.getTotalPrice(), order.getOrderTime(), order.getOrderStatus());
    }


    public Order findLastOrder() {

        String sql = "select * from orders order by orderId DESC limit 1";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(Order.class));
    }

    public void updateStatusById(int orderId, int newStatus) {

        String sql = "UPDATE orders SET orderStatus = ? WHERE orderId = ?";
        template.update(sql, newStatus, orderId);
    }

    public List<Order> findOrderById(int orderId) {

        String sql = "select * from orders where orderId = ?";
        return template.query(sql, new BeanPropertyRowMapper<Order>(Order.class), orderId);
    }
}
