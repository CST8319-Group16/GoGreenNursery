package org.cst8319.gogreen.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import org.cst8319.gogreen.business.UserService;
import org.cst8319.gogreen.DTO.User;
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    UserService userService;

    @Override
    public void init() {
        try {
            userService = new UserService();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "register":
                register(request, response);
                break;
            case "login":
                login(request, response);
                break;
            case "logout":
                logout(request, response);
                break;
            default:
                response.sendRedirect("error.jsp");
                break;
        }
    }


    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String userType = request.getParameter("userType");

        try {
            userService.register(username, password, email);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        request.setAttribute("email", email);
        request.setAttribute("info", "Registered successfully. Please login.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }


    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = null;
        try {
            user = userService.login(email, password);
        } catch (Exception e) {
            request.setAttribute("email", email);
            request.setAttribute("error", "Login failed. " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);

            String userType = user.getUserType();
            switch (userType) {
                case "admin":
                   // response.sendRedirect("adminPage.jsp");
                    response.sendRedirect("Product");
                    break;
                case "registered user":
                    response.sendRedirect("Product");
                    break;
                default:
                    response.sendRedirect("index.jsp");
                    break;
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        request.setAttribute("info", "Logout successful");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

}
