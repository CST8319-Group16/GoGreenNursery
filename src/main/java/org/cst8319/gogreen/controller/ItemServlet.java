package org.cst8319.gogreen.controller;

import org.cst8319.gogreen.DAO.ItemDAO;
import org.cst8319.gogreen.DTO.Category;
import org.cst8319.gogreen.DTO.Item;
import org.cst8319.gogreen.DTO.Product;
import org.cst8319.gogreen.DTO.User;
import org.cst8319.gogreen.business.CategoryService;
import org.cst8319.gogreen.business.ItemService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ItemServlet class require session control.
 */

@WebServlet("/Item")
public class ItemServlet extends HttpServlet {
    private ItemService ItemService = new ItemService();
    private CategoryService categoryService = new CategoryService();


    private List<String> categories = new ArrayList<>();


    private List<Item> Items = new ArrayList<>();


    private User user = null;
    // online user session control, only online user will be stored in  userManager
    private UserManager userManager = UserManager.getInstance();

    /**
     * Verify that the user is logged in before accessing the page
     *
     * @param req
     * @param resp
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        super.service(req, resp);
    }

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //all category in store.
        List<Item> Items = ItemService.getAllItems();
        //all Item in store.
        List<Category> categories = categoryService.getAllCategories();
        req.setAttribute("Items", Items);
        req.setAttribute("categories", categories);

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currentUser");

        String userType = user.getUserType();
        switch (userType) {
            case "admin":
                //resp.sendRedirect("adminPage.jsp");
                req.getRequestDispatcher("/adminPage.jsp").forward(req, resp);
                break;
            case "registered user":
                req.getRequestDispatcher("/Item.jsp").forward(req, resp);
                break;
            default:
                resp.sendRedirect("login.jsp");
                break;
        }

        //  req.getRequestDispatcher("/adminPage.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        // if action equals addFood
        if ("Add".equals(action)) {

            //task1: get Item info from req
            Item Item = getItemInfo(req);


            //task2: call foodService.addRecord method
            ItemService.addItem(Item);
            req.setAttribute("operateResult", "add new food successful.");

        }

        // if action equals deleteFood
        else if ("Delete".equals(action)) {
            //task1: get ID from req
            int ItemId = Integer.parseInt(req.getParameter("ItemId"));

            //task2: deleteItem method
            ItemService.deleteItem(ItemId);

        } // if action equals modify Food
        else if ("Update".equals(action)) {
            //task1: get food info from req
            Item Item = getItemInfo(req);

            //task2: call updateItem method
            ItemService.updateItem(Item);

        }

        //task3: doGet method
        doGet(req, resp);

    }

    private Item getItemInfo(HttpServletRequest req) {
        int ItemId = Integer.parseInt(req.getParameter("ItemId"));
        int OrderId = Integer.parseInt(req.getParameter("OrderId"));
        int productId = Integer.parseInt(req.getParameter("ProductId"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        BigDecimal totalprice = new BigDecimal(req.getParameter("totalprice"));

        Item item = new Item();
        item.setItemId(ItemId);
        item.setOrderId(OrderId);
        item.setProductId(productId);
        item.setQuantity(quantity);
        item.setPrice(price);
        item.setTotalPrice(totalprice);
        return  item;
    }
}



