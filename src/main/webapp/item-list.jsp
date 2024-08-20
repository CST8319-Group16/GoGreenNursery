<%--
  Created by IntelliJ IDEA.
  User: Jianbo Tan
  Date: 2024/8/17
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String error = (String) request.getAttribute("error");
    String info = (String) request.getAttribute("info");
%>

<% request.setAttribute("headerTitle", "Plants page - GoGreen"); %>
<%@ include file="resources/includes/header.jsp" %>
<main class="flex-1 bg-cover bg-center">

    <div >
        <%--title--%>
        <h2 class="mt-10 mb-5 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Plants in Cart</h2>
    </div>
    <div id="main-content">
        <div class="container">

            <form action="userOrder" method="post" class="html-editor-align-center">
                <table class="table table-striped table-hover table-bordered">
                    <thead class="thead-dark">
                    <tr>
                        <th class="col-1">Select</th>
                        <th class="col-1">Item ID</th>
                        <th class="col-1">User ID</th>
                        <th class="col-1">Product ID</th>
                        <th class="col-1">Order ID</th>
                        <th class="col-1">Quantity</th>
                        <th class="col-1">Price</th>
                        <th class="col-1">Total Price</th>
                        <th class="col-1">Order Status</th>
                        <th class="col-1">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${cartItems}">
                        <tr>
                            <td><input type="checkbox" name="selectedItems" value="${item.itemId}"/></td>
                            <td>${item.itemId}</td>
                            <td>${item.userId}</td>
                            <td>${item.productId}</td>
                            <td>${item.orderId}</td>
                            <td>${item.quantity}</td>
                            <td>${item.price}</td>
                            <td>${item.itemTotalPrice}</td>
                            <td>${item.orderStatus}</td>
                            <td>
                                <a href="item?action=edit&itemId=${item.itemId}" class="btn btn-outline-success btn-sm">Edit</a>
                                <a href="item?action=delete&itemId=${item.itemId}"
                                   class="btn btn-outline-success btn-sm">Delete</a>
<%--                                <input type="submit" name="action" class="btn btn-outline-success btn-sm" value="Delete">--%>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
                <input type="submit" name="action" value="Buy" class="btn btn-outline-success btn-sm"/>
            </form>

        </div>
    </div>
</main>


<%@ include file="resources/includes/footer.jsp" %>
