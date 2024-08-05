<%@ page import="org.cst8319.gogreen.DTO.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="org.cst8319.gogreen.DTO.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error = (String) request.getAttribute("error");
    String info = (String) request.getAttribute("info");
%>

<% request.setAttribute("headerTitle", "Admin Page - GoGreen"); %>
<%@ include file="resources/includes/header.jsp" %>
<main class="flex-1 bg-cover bg-center" style="background-image: url('resources/image/background.png');">

    <div class="flex flex-col px-8 py-12">
        <%--title--%>
        <h2 class="mt-10 mb-5 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Admin Page</h2>
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
                    <th class="col-1">Quantities</th>
                    <th class="col-1">Category ID</th>
                    <th class="col-1">Image URL</th>
                    <th class="col-2">Action</th>
                </tr>
                </thead>
                <tbody>
                <% List<Product> products = (List<Product>) request.getAttribute("products");
                    for (Product product : products) {%>
                <tr>
                    <td>
                    <form action="Product" method="post">

                        <%= product.getProductId()%></td>
                    <td><input type="text" class="col-6" name="productName" value="<%= product.getProductName()%>"></td>
                    <td><input type="text" class="col-6" name="productDesc" value="<%= product.getProductDesc()%>"></td>
                    <td><input type="text" class="col-6" name="price" value="<%= product.getPrice()%>"></td>
                    <td><input type="text" class="col-6" name="stock" value="<%= product.getStock()%>"></td>
                        <td>
                    <select name="categoryId" class="col-md-2" id="categoryId" >
                        <%   List<Category> categories = (List<Category>) request.getAttribute("categories");
                            for (Category category : categories) {
                        if(category.getCategoryId()==product.getCategoryId()) { %>
                        <option value="<%= category.getCategoryId()%>" selected><%= category.getCategoryName()%></option>
                        <% } else { %>
                        <option value="<%= category.getCategoryId()%>" ><%= category.getCategoryName()%></option>
                        <% } } %>
                    </select>
                        </td>
                    <td><input type="text" class="col-6" name="imageURL" value="<%= product.getImageURL()%>"></td>
                    <td>
                        <input hidden type="text" name="productId" value="<%= product.getProductId()%>">
                        <input type="submit" name="action" class="btn btn-outline-success btn-sm" value="Update">
                        <input type="submit" name="action" class="btn btn-outline-success btn-sm" value="Delete">
                        </form>
                    </td>
                </tr>
                <% }%>
                <%--empty row for add--%>
                <tr>

                        <form action="Product" method="post">
                            <td> </td>
                    <td><input type="text" class="col-12" name="productName" value=""></td>
                    <td><input type="text" class="col-6" name="productDesc" value=""></td>
                    <td><input type="text" class="col-6" name="price" value=""></td>
                    <td><input type="text" class="col-6" name="stock" value=""></td>
                            <td>
                    <select name="categoryId" class="col-md-2" id="categoryId" >
                        <% List<Category> categories = (List<Category>) request.getAttribute("categories");
                            for (Category category : categories) { %>
                        <option value="<%= category.getCategoryId()%>" ><%= category.getCategoryName()%></option>
                        <% } %>
                    </select>
                            </td>
                    <td><input type="text" class="col-6" name="imageURL" value=""></td>
                    <td>
                        <input hidden type="text" name="productId" value="-1">
                        <input type="submit" name="action" class="btn btn-outline-success btn-sm" id="showToastBtn" value="Add">
                        </form>
                    </td>
                </tr>
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
