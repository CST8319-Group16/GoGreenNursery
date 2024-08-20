package org.cst8319.gogreen.business;

import org.cst8319.gogreen.DAO.ItemDAO;
import org.cst8319.gogreen.DTO.Item;

import java.util.List;

public class ItemService {

    private ItemDAO itemDAO;

    public ItemService() {
        this.itemDAO = new ItemDAO();
    }

    public void createItem(Item item) {
        itemDAO.saveItem(item);
    }

    public Item getItemById(int itemId) {
        return itemDAO.getItemById(itemId);
    }

    public List<Item> getAllItems() {
        return itemDAO.getAllItems();
    }

    public void updateItem(Item item) {
        itemDAO.updateItem(item);
    }

    public void deleteItem(int itemId) {
        itemDAO.deleteItemById(itemId);
    }

    public List<Item> findByOrderId(int orderId) {
        return itemDAO.findByOrderId(orderId);
    }

    public List<Item> findByOrderStatusAndUserId(int orderStatus, int userId){
        return itemDAO.findByOrderStatusAndUserId(orderStatus, userId);
    }

    public void deleteItemByOrderId(int orderId) {
        itemDAO.deleteItemByOrderId(orderId);
    }
}
