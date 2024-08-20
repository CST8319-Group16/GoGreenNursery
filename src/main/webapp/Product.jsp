<%@ page import="org.cst8319.gogreen.DTO.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="org.cst8319.gogreen.DTO.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error = (String) request.getAttribute("error");
    String info = (String) request.getAttribute("info");
%>

<% request.setAttribute("headerTitle", "Plants page - GoGreen"); %>
<%@ include file="resources/includes/header.jsp" %>
<main class="flex-1 bg-cover bg-center" >

    <div>
        <%--title--%>
        <h2 class="mt-3 mb-5 text-center text-2xl font-bold leading-3 tracking-tight text-gray-900">Plants</h2>
    </div>

    <div id="main-content" >
        <div class="container">
            <table class="table table-striped table-hover table-bordered">
                <thead class="thead-dark">
                <tr>
                    <th class="col-1">ID</th>
                    <th class="col-1">Name</th>
                    <th class="col-1">Description</th>
                    <th class="col-1">Price</th>
                    <th class="col-1">In Store</th>
                    <th class="col-1">Category</th>
                    <th class="col-1">Image URL</th>
                    <th class="col-1">Quantities</th>
                    <th class="col-1">Action</th>
                </tr>
                </thead>
                <tbody>
                <% List<Product> products = (List<Product>) request.getAttribute("products");
                User user = (User) request.getAttribute("user");
                    for (Product product : products) {%>
                <tr>
                    <td>
                    <form action="item" method="post">

                        <%= product.getProductId()%></td>
                    <td><input type="text" class="col-12" name="productName" value="<%= product.getProductName()%>" readonly></td>
                    <td><input type="text" class="col-12" name="productDesc" value="<%= product.getProductDesc()%>" readonly></td>
                    <td><input type="text" class="col-12" name="price" value="<%= product.getPrice()%>" readonly></td>
                    <td><input type="text" class="col-12" name="stock" value="<%= product.getStock()%>" readonly></td>
                    <td>
                        <%   List<Category> categories = (List<Category>) request.getAttribute("categories");
                            for (Category category : categories) {
                        if(category.getCategoryId()==product.getCategoryId()) { %>
                        <input type="text" class="col-12" name="category" value="<%= category.getCategoryName()%>" readonly>
                        <% }
                            } %>
                    </td>
<%--                    <select name="categoryId" class="col-12" id="categoryId" readonly>--%>
<%--                        <%   List<Category> categories = (List<Category>) request.getAttribute("categories");--%>
<%--                            for (Category category : categories) {--%>
<%--                        if(category.getCategoryId()==product.getCategoryId()) { %>--%>
<%--                        <option value="<%= category.getCategoryId()%>" selected><%= category.getCategoryName()%></option>--%>
<%--                        <% } else { %>--%>
<%--                        <option value="<%= category.getCategoryId()%>" ><%= category.getCategoryName()%></option>--%>
<%--                        <% } } %>--%>
<%--                    </select>--%>

                    <!--<td><input type="text" class="col-6" name="imageURL" value="<%= product.getImageURL()%>"></td> -->
                    <td><img src="<%= product.getImageURL()%>" alt="some_text" readonly></td>
                    <td><input type="text" class="col-12" name="quantities" value="0"></td>
                    <td>
<%--                        <input hidden type="text" name="userId" value="<%= user.getUserId()%>">--%>
                        <input hidden type="text" name="productId" value="<%= product.getProductId()%>">
                        <input type="submit" name="action" class="btn btn-outline-success btn-sm" value="Add To Cart">
                        </form>
                    </td>
                </tr>
                <% }%>
                </tbody>
                <hr>

            </table>
        </div>


        <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
            <div id="liveToast" class="toast hide" role="alert" aria-live="assertive" aria-atomic="true">
                <div class="toast-header">
                    <!--<img src="..." class="rounded me-2" alt="...">-->
                    <strong class="me-auto">Your favorite Plant</strong>
                    <small>Add Result</small>
                    <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
                <div class="toast-body">
                    <!-- Subscribe Result will be show here  -->
                </div>
            </div>
        </div>

    </div>
</main>

<%@ include file="resources/includes/footer.jsp" %>
