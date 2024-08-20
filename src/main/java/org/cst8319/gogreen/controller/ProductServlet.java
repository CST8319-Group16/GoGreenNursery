package org.cst8319.gogreen.controller;

import org.cst8319.gogreen.DAO.ProductDAO;
import org.cst8319.gogreen.DTO.Category;
import org.cst8319.gogreen.DTO.Product;
import org.cst8319.gogreen.DTO.User;
import org.cst8319.gogreen.business.CategoryService;
import org.cst8319.gogreen.business.ProductService;
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
 * ProductServlet class require session control.
 */

@WebServlet("/Product")
public class ProductServlet extends HttpServlet {
    private ProductService productService = new ProductService();
    private CategoryService categoryService = new CategoryService();


    private List<String> categories = new ArrayList<>();


    private List<Product> Products = new ArrayList<>();


    private User user = null;
    // online user session control, only online user will be stored in  userManager
    private UserManager userManager= UserManager.getInstance();

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
//        if (session.isNew()) {
//            // non login user
//            String sessionid = session.getId();
//            resp.sendRedirect("register");
//        } else {
//            // online user
//            String sessionid = session.getId();
//            user = userManager.getUser(sessionid);
//            if (user != null) {
//                req.getSession().setAttribute("user", user);
//                super.service(req, resp);
//            } else {
//                //super.service(req, resp);   // temp handle
//                resp.sendRedirect("register");
//            }
//        }
    }

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            //all category in store.
            List<Product> products = productService.getAllProducts();
            //all product in store.
            List<Category> categories = categoryService.getAllCategories();
            req.setAttribute("products", products);
            req.setAttribute("categories", categories);

        HttpSession session = req.getSession();
        User user= (User)session.getAttribute("currentUser");

        System.out.println("ProductServlet " + user.toString());


        String userType = user.getUserType();
        switch (userType) {
            case "admin":
                //resp.sendRedirect("adminPage.jsp");
                req.getRequestDispatcher("/adminPage.jsp").forward(req, resp);
                break;
            case "registered user":
                req.getRequestDispatcher("/Product.jsp").forward(req, resp);
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

            //task1: get product info from req
            Product product = getProductInfo(req);


                //task2: call foodService.addRecord method
                productService.addProduct(product);
                req.setAttribute("operateResult", "add new food successful.");

        }

        // if action equals deleteFood
        else if ("Delete".equals(action)) {
            //task1: get ID from req
            int productId = Integer.parseInt(req.getParameter("productId"));

            //task2: deleteProduct method
            productService.deleteProduct(productId);

        } // if action equals modify Food
        else if ("Update".equals(action)) {
            //task1: get food info from req
            Product product = getProductInfo(req);

            //task2: call updateProduct method
            productService.updateProduct(product);

        }

        //task3: doGet method
        doGet(req, resp);

    }

    private Product getProductInfo(HttpServletRequest req) {
        int productId = Integer.parseInt(req.getParameter("productId"));
        String productName = req.getParameter("productName");
        String productDesc = req.getParameter("productDesc");
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        int stock = Integer.parseInt(req.getParameter("stock"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        String imageURL = req.getParameter("imageURL");

        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductDesc(productDesc);
        product.setPrice(price);
        product.setStock(stock);
        product.setCategoryId(categoryId);
        product.setImageURL(imageURL);
        return  product;
    }
}
