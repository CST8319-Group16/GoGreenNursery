<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.cst8319.gogreen.DTO.User" %>
<%@ page import="org.cst8288.fwrp.Utils" %>
<%@ page import="org.cst8288.fwrp.Constants" %>
<%@ page import="static org.cst8288.fwrp.Constants.RETAILER" %>
<%@ page import="static org.cst8288.fwrp.Constants.CONSUMER" %>
<%@ page import="static org.cst8288.fwrp.Constants.*" %>
<%
    //current user
    User u = Utils.getCurrentUser(session);
    //current user type
    String ut = u == null ? "" : u.getUserType();
    String headerTitle = (String) request.getAttribute("headerTitle");
    headerTitle = headerTitle == null ? "Food Waste Reduction Platform" : headerTitle;
%>


<%--from https://tailwindui.com/components/application-ui/navigation/navbars --%>
<html class="h-full bg-gray-100">
<head>
    <title><%= headerTitle %>
    </title>
    <%--tailwind css--%>
    <script src="${pageContext.request.contextPath}/resources/js/tailwind.js"></script>
    <%--favicon--%>
    <%-- https://realfavicongenerator.net/ --%>
    <link rel="apple-touch-icon" sizes="180x180"
          href="${pageContext.request.contextPath}/resources/img/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32"
          href="${pageContext.request.contextPath}/resources/img/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16"
          href="${pageContext.request.contextPath}/resources/img/favicon-16x16.png">
</head>
<body class="min-h-full flex flex-col">

<%--top nav--%>
<nav class="bg-blue-600 group">
    <div class="mx-auto max-w-6xl px-6 flex h-20 items-center justify-between">
        <div class="flex items-center">
            <%--logo--%>
            <a class="flex flex-col shrink-0 items-center" href="${pageContext.request.contextPath}/">
                <img class="h-12 w-12 shrink-0" src="${pageContext.request.contextPath}/resources/img/logo.png"
                     alt="Food Waste Reduction Platform">
                <span class="text-white text-xs content-center">FWRP</span>
            </a>
            <%--nav--%>
            <div class="ml-10 flex items-baseline space-x-4">
                <%--home--%>
                <a href="HomeServlet"
                   class="text-white hover:bg-blue-800 rounded-md px-3 py-2 font-medium">Home</a>
                <%--nav for retailer--%>
                <a href="InventoryServlet-URL?action=displayInventory"
                   class="<%= RETAILER.equals(ut) ? "":"hidden" %> text-white hover:bg-blue-800 rounded-md px-3 py-2 font-medium">
                    Inventory Management</a>
                <a href="ListingServlet-URL?action=displaySurplusItems"
                   class="<%= RETAILER.equals(ut) ? "":"hidden" %> text-white hover:bg-blue-800 rounded-md px-3 py-2 font-medium">
                    Surplus Food Listing</a>

                <%--Subscribe tab--%>
                <a href="SubscribeServlet"
                   class="<%= (CONSUMER.equals(ut) || CHARITABLE_ORGANIZATION.equals(ut)) ? "" : "hidden" %> text-white hover:bg-blue-800 rounded-md px-3 py-2 font-medium">
                    Subscribe
                </a>
                <%--about--%>
                <a href="${pageContext.request.contextPath}/"
                   class="text-white hover:bg-blue-800 rounded-md px-3 py-2 font-medium">About</a>
            </div>
        </div>

        <%--test login form--%>
        <%--        <div class="flex space-x-2 shrink-1">--%>
        <%--            <form action="UserServlet" method="POST" class="m-0">--%>
        <%--                <input type="hidden" name="action" value="login">--%>
        <%--                <input type="hidden" name="email" value="retailer1@example.com">--%>
        <%--                <input type="hidden" name="password" value="password">--%>
        <%--                <button type="submit" class="text-blue-600 group-hover:text-white text-xs">Retailer1</button>--%>
        <%--            </form>--%>

        <%--            <form action="UserServlet" method="POST" class="m-0">--%>
        <%--                <input type="hidden" name="action" value="login">--%>
        <%--                <input type="hidden" name="email" value="consumer1@example.com">--%>
        <%--                <input type="hidden" name="password" value="password">--%>
        <%--                <button type="submit" class="text-blue-600 group-hover:text-white text-xs">Consumer1</button>--%>
        <%--            </form>--%>

        <%--            <form action="UserServlet" method="POST" class="m-0">--%>
        <%--                <input type="hidden" name="action" value="login">--%>
        <%--                <input type="hidden" name="email" value="foodbank1@example.com">--%>
        <%--                <input type="hidden" name="password" value="password">--%>
        <%--                <button type="submit" class="text-blue-600 group-hover:text-white text-xs">Foodbank1</button>--%>
        <%--            </form>--%>
        <%--        </div>--%>

        <%--login/logout--%>
        <div class="flex space-x-1">

            <%--user name--%>
            <a href="UserInfoServlet"
               class="<%= u == null ? "hidden" : "" %> text-white hover:bg-blue-800 rounded-md px-3 py-2 font-medium flex items-center space-x-1">
                <svg class="h-6 w-6 text-gray-300" viewBox="0 0 24 24" fill="currentColor" aria-hidden="true">
                    <path fill-rule="evenodd"
                          d="M18.685 19.097A9.723 9.723 0 0021.75 12c0-5.385-4.365-9.75-9.75-9.75S2.25 6.615 2.25 12a9.723 9.723 0 003.065 7.097A9.716 9.716 0 0012 21.75a9.716 9.716 0 006.685-2.653zm-12.54-1.285A7.486 7.486 0 0112 15a7.486 7.486 0 015.855 2.812A8.224 8.224 0 0112 20.25a8.224 8.224 0 01-5.855-2.438zM15.75 9a3.75 3.75 0 11-7.5 0 3.75 3.75 0 017.5 0z"
                          clip-rule="evenodd"></path>
                </svg>
                <span>
                <%= u == null ? "" : u.getName() %>
                </span>
            </a>

            <%--login--%>
            <a href="login.jsp"
               class="<%= u == null ? "" : "hidden" %> text-white hover:bg-blue-800 rounded-md px-3 py-2 font-medium">Login</a>

            <%--logout--%>
            <form action="UserServlet" method="post" class="m-0 <%= u != null ? "" : "hidden" %>">
                <input type="hidden" name="action" value="logout">
                <input type="submit" value="Logout"
                       class="text-white hover:bg-blue-800 rounded-md px-3 py-2 font-medium">
            </form>
        </div>

    </div>
</nav>
