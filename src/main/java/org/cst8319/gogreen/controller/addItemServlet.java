package org.cst8319.gogreen.controller;

import org.cst8319.gogreen.business.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addItemServlet")
public class addItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        int userId = Integer.parseInt(request.getParameter("userId"));
        int plantId = Integer.parseInt(request.getParameter("plantId"));
        String plantName =request.getParameter("plantName");
        Double price = Double.valueOf(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));


        ItemService service = new ItemService();
        service.addIntoItem(userId, plantId, plantName,quantity,price);

        request.setAttribute("userId",userId);
        request.setAttribute("plantId",plantId);
        request.setAttribute("plantName",plantName);
        request.setAttribute("quantity",quantity);
        request.setAttribute("price",price);

        request.getRequestDispatcher("ItemsServlet").forward(request,response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
