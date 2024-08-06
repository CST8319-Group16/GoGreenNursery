package org.cst8319.gogreen.controller;

import org.cst8319.gogreen.DTO.Order;
import org.cst8319.gogreen.business.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/addOrderServlet")
public class addOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        Date orderTime = new Date();

        int userId = Integer.parseInt(request.getParameter("userId"));
        int plantId = Integer.parseInt(request.getParameter("plantId"));
        String plantName = request.getParameter("plantName");
        Double price = Double.valueOf(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Double totalPrice = (price * quantity);

        Order order = new Order();
        order.setUserId(userId);
        order.setPlantId(plantId);
        order.setPrice(price);
        order.setTotalPrice(totalPrice);
        order.setOrderTime(orderTime);
        order.setOrderStatus(1);

        // add order
        OrderService service = new OrderService();
        service.insertOrder(order);

        request.setAttribute("order", order);

        request.getRequestDispatcher("/Order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
