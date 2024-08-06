package org.cst8319.gogreen.controller;

import org.cst8319.gogreen.DAO.CategoryDAO;
import org.cst8319.gogreen.DTO.Category;
import org.cst8319.gogreen.business.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * CategoryServlet class does not require session control
 */
@WebServlet("/Category")
public class CategoryServlet extends HttpServlet {
    private CategoryService categoryService = new CategoryService();

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            List<Category> categories = categoryService.getAllCategories();
            req.setAttribute("categories", categories);
            req.getRequestDispatcher("/WEB-INF/jsp/categories.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName = req.getParameter("categoryName");

        Category category = new Category();
        category.setCategoryName(categoryName);


            categoryService.addCategory(category);
            resp.sendRedirect("categories");

    }
}
