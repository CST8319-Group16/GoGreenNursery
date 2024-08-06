package org.cst8319.gogreen.business;

import org.cst8319.gogreen.DAO.ItemDAO;
import org.cst8319.gogreen.DTO.Item;

import java.util.List;

public class ItemService {
    ItemDAO dao = new ItemDAO();

    public void addIntoItem(int userId, int plantId, String plantName, int quantity, double price) {
        int itemId = dao.confirmItem(userId, plantId);
        if (itemId == 0) {
            dao.insertItem(userId, plantId, plantName, quantity, price);
        } else {
            dao.updateItem(itemId, quantity, price);
        }
    }

    public List<Item> findItemByUserId(int userId) {
        return dao.findItemByUserId(userId);
    }

    public void deleteItem(int itemId) {
        dao.deleteItem(itemId);
    }
}
