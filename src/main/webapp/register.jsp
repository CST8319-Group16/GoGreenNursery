<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error = (String) request.getAttribute("error");
    String info = (String) request.getAttribute("info");
%>

<% request.setAttribute("headerTitle", "Register - FWRP"); %>
<%@ include file="includes/header.jsp" %>
<script>
    // Function to validate the form
    function validateForm() {
        let error = '';
        const email = document.getElementById('email');
        const password = document.getElementById('password');
        const password2 = document.getElementById('password2');
        const errorDiv = document.getElementById('error');

        if (!/.+@.+\..+/.test(email.value)) {
            error = 'Please enter a valid email address.';
        }
        // Check password length
        else if (password.value.length < 8) {
            error = 'Password must be at least 8 characters long.';
        }
        // Check if passwords match
        else if (password.value !== password2.value) {
            error = 'Passwords do not match. Please enter the same password in both fields.';
        }

        if (error !== '') {
            errorDiv.classList.remove('hidden');
            errorDiv.innerHTML = error;
            return false;
        }

        // If everything's okay, hide the error div and return true
        errorDiv.classList.add('hidden');
        return true;
    }
</script>

<%-- https://tailwindui.com/components/marketing/sections/contact-sections --%>
<main class="flex-1">
    <%--title--%>
    <div class="mx-auto max-w-2xl text-center">
        <h2 class="mt-10 mb-5 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Welcome
            Aboard!</h2>
    </div>

    <%--error--%>
    <div id="error" class="<%= error == null ? "hidden" : "" %> p-4 text-sm text-red-800 rounded-lg bg-red-50 max-w-sm w-full mx-auto">
        <span class="font-medium"><%= error %></span>
    </div>

    <%--info--%>
    <div class="<%= info == null ? "hidden" : "" %> p-4 text-sm text-green-800 rounded-lg bg-green-50 max-w-sm w-full mx-auto">
        <span class="font-medium"><%= info %></span>
    </div>

    <%--register form--%>
    <form action="UserServlet" method="POST"
          onsubmit="return validateForm()"
          class="mx-auto mt-5 mb-10 max-w-sm grid grid-cols-1 gap-x-8 gap-y-6 sm:grid-cols-2">
        <input type="hidden" name="action" value="register">
        <div class="sm:col-span-2">
            <label for="email" class="block text-sm font-semibold leading-6 text-gray-900">Email</label>
            <div class="mt-2.5">
                <input type="email" name="email" id="email" autocomplete="email"
                       class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6">
            </div>
        </div>
        <div class="sm:col-span-2">
            <label for="password" class="block text-sm font-semibold leading-6 text-gray-900">Password</label>
            <div class="mt-2.5">
                <input type="password" name="password" id="password" autocomplete="password"
                       class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6">
            </div>
        </div>
        <div class="sm:col-span-2">
            <label for="password2" class="block text-sm font-semibold leading-6 text-gray-900">Repeat
                Password</label>
            <div class="mt-2.5">
                <input type="password" name="password2" id="password2" autocomplete="password"
                       class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6">
            </div>
        </div>
        <div class="">
            <label for="name" class="block text-sm font-semibold leading-6 text-gray-900">Name</label>
            <div class="mt-2.5">
                <input type="text" name="userName" id="name" autocomplete="given-name"
                       class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6">
            </div>
        </div>
        <div class="">
            <label for="userType" class="block text-sm font-semibold leading-6 text-gray-900">User Type</label>
            <div class="relative mt-2.5">
                <select id="userType" name="userType"
                        class="block w-full rounded-md border-0 px-3.5 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-blue-600 sm:text-sm sm:leading-6">
                    <option value="retailer">Retailer</option>
                    <option value="consumer">Consumer</option>
                    <option value="charity">Charitable Organization</option>
                </select>
            </div>
        </div>
        <div class="flex gap-x-4 sm:col-span-2">
            <label class="text-sm leading-6 text-gray-600" id="switch-1-label">
                By clicking Register, you agree to our
                <a href="#" class="font-semibold text-blue-600">privacy&nbsp;policy</a>.
            </label>
        </div>
        <button type="submit"
                class="col-span-2 block w-full rounded-md bg-blue-600 px-3.5 py-2.5 text-center text-sm font-semibold text-white shadow-sm hover:bg-blue-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-blue-600">
            Register
        </button>
    </form>
</main>
<%@ include file="includes/footer.jsp" %>