<%@ page import="org.cst8319.gogreen.DTO.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="org.cst8319.gogreen.DTO.Category" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error = (String) request.getAttribute("error");
    String info = (String) request.getAttribute("info");

    // Assuming `items` is a List of Item objects passed to the JSP via request.
    List items = (List) request.getAttribute("items");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Item page - GoGreen</title>
    <%@ include file="resources/includes/header.jsp" %>
</head>
<body>
<div class="flex flex-col px-8 py-12">
    <%--title--%>
    <h2 class="mt-10 mb-5 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Item List</h2>
</div>
<table border="1">
    <thead>
    <tr>
        <th>Item ID</th>
        <th>User ID</th>
        <th>Plant ID</th>
        <th>Plant Name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Total Price</th>
    </tr>
    </thead>
    <tbody>
    <% if (items != null) { %>
    <% for (Object item : items) { %>
    <tr>
        <td><%= ((Map)item).get("itemId") %></td>
        <td><%= ((Map)item).get("userId") %></td>
        <td><%= ((Map)item).get("productId") %></td>
        <td><%= ((Map)item).get("productName") %></td>
        <td><%= ((Map)item).get("quantity") %></td>
        <td><%= ((Map)item).get("price") %></td>
        <td><%= ((Map)item).get("totalPrice") %></td>
    </tr>
    <% } %>
    <% } else { %>
    <tr>
        <td colspan="7">No items found.</td>
    </tr>
    <% } %>
    </tbody>
</table>
<a href="order.jsp">Return to Order</a>
</body>
</html>