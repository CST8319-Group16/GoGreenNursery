package org.cst8319.gogreen.business;

import org.cst8319.gogreen.DAO.CategoryDAO;
import org.cst8319.gogreen.DTO.Category;

import java.sql.SQLException;
import java.util.List;

public class CategoryService {
    private CategoryDAO categoryDAO;

    public CategoryService() {
        this.categoryDAO = new CategoryDAO();
    }

    public void addCategory(Category category){
        categoryDAO.addCategory(category);
    }

    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    public Category getCategoryById(int categoryId){
        return categoryDAO.getCategoryById(categoryId);
    }

    public void updateCategory(Category category) {
        categoryDAO.updateCategory(category);
    }

    public void deleteCategory(int categoryId) {
        categoryDAO.deleteCategory(categoryId);
    }
}

