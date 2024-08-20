package org.cst8319.gogreen.controller;

import org.cst8319.gogreen.DTO.Item;
import org.cst8319.gogreen.DTO.Product;
import org.cst8319.gogreen.DTO.User;
import org.cst8319.gogreen.DTO.UserOrder;
import org.cst8319.gogreen.business.ItemService;
import org.cst8319.gogreen.business.ProductService;
import org.cst8319.gogreen.business.UserOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@WebServlet("/userOrder")
public class UserOrderServlet extends HttpServlet {

    private UserOrderService userOrderService;
    private ItemService itemService;
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        userOrderService = new UserOrderService();
        itemService = new ItemService();
        productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
//            case "create":
//                showCreateForm(request, response);
//                break;
            case "detail":
                showDetailByOrderId(request, response);
                break;
            case "delete":
                deleteUserOrder(request, response);
                break;
            case "list":
            default:
                listUserOrders(request, response);
                break;
        }
    }

    private void listUserOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user= (User)session.getAttribute("currentUser");
        int userId = user.getUserId();

        List<UserOrder> listUserOrders = userOrderService.getAllOrdersByUserId(userId);
        request.setAttribute("listUserOrders", listUserOrders);
        request.getRequestDispatcher("userOrder-list.jsp").forward(request, response);
    }

//    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("userOrder-form.jsp").forward(request, response);
//    }

    private void showDetailByOrderId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        UserOrder userOrder = userOrderService.getUserOrderById(orderId);
        // Set the UserOrder object in the request scope
        request.setAttribute("userOrder", userOrder);

        List<Item> orderItems =  itemService.findByOrderId(orderId);
        request.setAttribute("orderItems", orderItems);
        request.getRequestDispatcher("userOrder-detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";



        switch (action) {
            case "Buy":
                saveUserOrder(request, response);
                break;
            case "update":
                updateUserOrder(request, response);
                break;
            default:
                listUserOrders(request, response);
                break;
        }
    }

    private void saveUserOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user= (User)session.getAttribute("currentUser");
        int userId = user.getUserId();
        double sum = 0;

        String[] selectedItemIds = request.getParameterValues("selectedItems");
        if (selectedItemIds != null) {
            // Convert selected item IDs to integers (if needed)
            int[] selectedIds = Arrays.stream(selectedItemIds).mapToInt(Integer::parseInt).toArray();

            // Process the selected items
            // task1: sum all itemtotalPrice
            for (int itemId: selectedIds) {
                Item item = itemService.getItemById(itemId);
                sum+= item.getItemTotalPrice().doubleValue();
            }

            //task2: create a new order
            UserOrder newUserOrder = new UserOrder();
            newUserOrder.setUserId(userId);
            newUserOrder.setTotalPrice(BigDecimal.valueOf(sum));
            userOrderService.createUserOrder(newUserOrder);

            // task3: update item with new orderId, orderStatus
            for (int itemId: selectedIds) {
                Item item = itemService.getItemById(itemId);
                item.setOrderId(newUserOrder.getOrderId());
                item.setOrderStatus(1);
                itemService.updateItem(item);
            }

        } else {
            // Handle the case where no items were selected
            response.getWriter().println("No items were selected.");
        }
        response.sendRedirect("userOrder?action=list");
    }

    private void updateUserOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        BigDecimal totalPrice = new BigDecimal(request.getParameter("totalPrice"));

        UserOrder userOrder = new UserOrder();
        userOrder.setOrderId(orderId);
        userOrder.setUserId(userId);
        userOrder.setTotalPrice(totalPrice);

        userOrderService.updateUserOrder(userOrder);
        response.sendRedirect("userOrder?action=list");
    }

    private void deleteUserOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        itemService.deleteItemByOrderId(orderId);
        userOrderService.deleteUserOrder(orderId);
        response.sendRedirect("userOrder?action=list");
    }
}

