package org.cst8319.gogreen.controller;
import org.cst8319.gogreen.DTO.Item;
import org.cst8319.gogreen.business.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ItemsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        ItemService itemService = new ItemService();
        List<Item> items = itemService.findItemByUserId(userId);

        request.setAttribute("items", items);
        request.getRequestDispatcher("/Item.jsp").forward(request, response);
    }
}