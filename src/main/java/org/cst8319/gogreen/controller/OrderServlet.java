package org.cst8319.gogreen.controller;

import org.cst8319.gogreen.business.OrderService;
import org.cst8319.gogreen.DTO.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class OrderServlet extends HttpServlet {

    private OrderService OrderService;

    public OrderServlet() {
        super();
        OrderService = new OrderService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle GET requests, e.g., to fetch all orders
        List<Order> orders = OrderService.getAllOrders();
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/WEB-INF/order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST requests, e.g., to create or update an order
        String action = request.getParameter("action");
        if ("create".equals(action)) {
            // Create a new order
            Order order = new Order(
//                    Integer.parseInt(request.getParameter("userId")),
//                    Integer.parseInt(request.getParameter("productId")),
//                    Double.parse(request.getParameter("totalPrice"))
                    // ... other fields
            );
            OrderService.insertOrder(order);
            response.sendRedirect("/orders");
        } else if ("update".equals(action)) {
            // Update an existing order
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            Order order = OrderService.getOrderById(orderId);
            // Update order fields here
            OrderService.updateOrder(order);
            response.sendRedirect("/orders");
        }
    }
}