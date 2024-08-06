package org.cst8319.gogreen.controller;

import org.cst8319.gogreen.DAO.ItemDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteItemServlet")
public class deleteItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int itemId = Integer.parseInt(request.getParameter("itemId"));
        int userId = Integer.parseInt(request.getParameter("userId"));

        ItemDAO dao = new ItemDAO();
        dao.deleteItem(itemId);

        request.setAttribute("userId",userId);

        request.getRequestDispatcher("/ItemsServlet").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
