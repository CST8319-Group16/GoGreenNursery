package org.cst8319.gogreen.controller;

import org.cst8319.gogreen.DTO.Order;
import org.cst8319.gogreen.business.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ordersServlet")
public class ordersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        int orderId = Integer.parseInt(request.getParameter("orderId"));

        //select order
        OrderService service = new OrderService();
        List<Order> Orders = service.findOrderById(orderId);

        request.setAttribute("Orders",Orders);

        request.getRequestDispatcher("Order.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
