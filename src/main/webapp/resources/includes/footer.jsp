<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<footer class="bottom-0 left-0 z-20 w-full p-4 bg-white border-t border-gray-200 shadow md:flex md:items-center md:justify-between md:p-6 dark:bg-gray-800 dark:border-gray-600">
    <span class="text-sm text-gray-500 sm:text-center dark:text-gray-400">© 2024
        <a href="#" class="hover:underline">GoGreen™</a>. <span
              onclick="setTestPassword('retailer1@example.com','password')">All</span> <span
              onclick="setTestPassword('consumer1@example.com','password')">Rights</span> <span
              onclick="setTestPassword('foodbank1@example.com','password')">Reserved</span>.
    </span>
  <ul class="flex flex-wrap items-center mt-3 text-sm font-medium text-gray-500 dark:text-gray-400 sm:mt-0">
    <li>
      <a href="${pageContext.request.contextPath}/" class="hover:underline me-4 md:me-6">About</a>
    </li>
    <li>
      <a href="#" class="hover:underline me-4 md:me-6">Privacy Policy</a>
    </li>
    <li>
      <a href="#" class="hover:underline me-4 md:me-6">Licensing</a>
    </li>
    <li>
      <a href="#" class="hover:underline">Contact</a>
    </li>
  </ul>
  <script>
    function setTestPassword(email, password) {
      document.getElementById("email").value = email;
      document.getElementById("password").value = password;
    }
  </script>
</footer>
</body>
</html>
