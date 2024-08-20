package org.cst8319.gogreen.controller;

import org.cst8319.gogreen.DTO.Item;
import org.cst8319.gogreen.DTO.Product;
import org.cst8319.gogreen.DTO.User;
import org.cst8319.gogreen.business.ItemService;
import org.cst8319.gogreen.business.ProductService;

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

@WebServlet("/item")
public class ItemServlet extends HttpServlet {

    private ItemService itemService;
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        itemService = new ItemService();
        productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteItem(request, response);
                break;
            case "list":
            default:
                listItems(request, response);
                break;
        }
    }

    private void listItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        int userId = user.getUserId();


        //List<Item> listItems = itemService.getAllItems();

        //orderStatus = 0, in cart
        //orderStatus = 1, in order.
        List<Item> cartItems = itemService.findByOrderStatusAndUserId(0, userId);
        request.setAttribute("cartItems", cartItems);

        List<Item> payedItems = itemService.findByOrderStatusAndUserId(1, userId);
        request.setAttribute("payedItems", payedItems);
        request.getRequestDispatcher("item-list.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("item-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        Item existingItem = itemService.getItemById(itemId);
        request.setAttribute("item", existingItem);
        request.getRequestDispatcher("item-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {

            case "Add To Cart":
                saveItem(request, response);
                break;
            case "update":
                updateItem(request, response);
                break;

            default:
                listItems(request, response);
                break;
        }
    }

    private void saveItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        int userId = user.getUserId();
        //System.out.println("ItemServlet " + user.toString());


        int productId = Integer.parseInt(request.getParameter("productId"));
        //int orderId = Integer.parseInt(request.getParameter("orderId"));
        int orderId = -1;
        int quantities = Integer.parseInt(request.getParameter("quantities"));
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        BigDecimal itemTotalPrice = price.multiply(BigDecimal.valueOf(quantities));
        int orderStatus = 0;


        Product product = productService.getProductById(productId);
        if (quantities <= product.getStock()) {
            //task1: update stock
            product.setStock(product.getStock() - quantities);
            productService.updateProduct(product);

            //task2: create a new Item in cart
            Item newItem = new Item();
            newItem.setUserId(userId);
            newItem.setProductId(productId);
            newItem.setOrderId(orderId);
            newItem.setQuantity(quantities);
            newItem.setPrice(price);
            newItem.setItemTotalPrice(itemTotalPrice);
            newItem.setOrderStatus(orderStatus);

            System.out.println(newItem.toString());

            itemService.createItem(newItem);
        } else {
            //don't have enough item in stock
        }
        response.sendRedirect("Product");
    }

    private void updateItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        int userId = user.getUserId();

        int itemId = Integer.parseInt(request.getParameter("itemId"));
        Item item = itemService.getItemById(itemId);
        if(item.getOrderStatus()==1){
            //already paid item in an order, don't support update quantities
            response.sendRedirect("item?action=list");
            return;
        }
        Product product = productService.getProductById(item.getProductId());
        int oldQuantity = item.getQuantity();

        int newQuantity = Integer.parseInt(request.getParameter("quantity"));

        if(newQuantity  >  0 && newQuantity!=oldQuantity && newQuantity - oldQuantity  <= product.getStock()){
            //valid operation, update product and item.
            product.setStock(product.getStock() -(newQuantity - oldQuantity));
            productService.updateProduct(product);

            BigDecimal itemTotalPrice = item.getPrice().multiply(BigDecimal.valueOf(newQuantity));
            item.setQuantity(newQuantity);
            item.setItemTotalPrice(itemTotalPrice);
            itemService.updateItem(item);

        }else{
            // newQuantity == oldQuantity or don't have enough item in stock.
        }

        response.sendRedirect("item?action=list");
    }

    private void deleteItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int itemId = Integer.parseInt(request.getParameter("itemId"));

        Item item= itemService.getItemById(itemId);

        // task 1:delete item in cart, increase stock of product
        if(item.getOrderStatus() == 0){
            Product product= productService.getProductById(item.getProductId());
            product.setStock(product.getStock() + item.getQuantity());
            productService.updateProduct(product);
            // task 2:delete current item
            itemService.deleteItem(itemId);
        }
        else{
            //item in order, should delete whole order.
        }

        response.sendRedirect("item?action=list");
    }
}

