package org.cst8319.gogreen.business;

import org.cst8319.gogreen.DAO.ItemDAO;
import org.cst8319.gogreen.DAO.ProductDAO;
import org.cst8319.gogreen.DTO.Item;
import org.cst8319.gogreen.DTO.Product;

import java.util.List;

public class ItemService {
    private ItemDAO itemDAO;

    public ItemService() {
        this.itemDAO = new ItemDAO();
    }

    public void addItem(Item item) {
        itemDAO.addItem(item);
    }

    public List<Item> getAllItems(){
        return itemDAO.getAllItems();
    }

    public Item getItemById(int itemId){

        return itemDAO.getItemById(itemId);

    }

    public void updateItem(Item item) {

        itemDAO.updateItem(item);

    }

    public void deleteItem(int itemId){
        itemDAO.deleteItem(itemId);
    }
}


