<%@ page import="org.cst8319.gogreen.DTO.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="org.cst8319.gogreen.DTO.Category" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error = (String) request.getAttribute("error");
    String info = (String) request.getAttribute("info");

    // Assuming `orders` is a List of Order objects passed to the JSP via request.
    List orders = (List) request.getAttribute("orders");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order page - GoGreen</title>
    <%@ include file="resources/includes/header.jsp" %>
</head>
<body>
<div class="flex flex-col px-8 py-12">
    <%--title--%>
    <h2 class="mt-10 mb-5 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Order</h2>
</div>
<table border="1">
    <thead>
    <tr>
        <th>orderId</th>
        <th>userId</th>
        <th>productId</th>
        <th>totalPrice</th>
        <th>orderTime</th>
        <th>orderStatus</th>
    </tr>
    </thead>
    <tbody>
    <% if (orders != null) { %>
    <% for (Object order : orders) { %>
    <tr>
        <td><%= ((Map)order).get("orderId") %></td>
        <td><%= ((Map)order).get("userId") %></td>
        <td><%= ((Map)order).get("productId") %></td>
        <td><%= ((Map)order).get("totalPrice") %></td>
        <td><%= ((Map)order).get("orderTime") %></td>
        <td><%= ((Map)order).get("orderStatus") %></td>
    </tr>
    <% } %>
    <% } else { %>
    <tr>
        <td colspan="6">No orders found.</td>
    </tr>
    <% } %>
    </tbody>
</table>
<a href="Item.jsp">Return Item</a>
</body>
</html>