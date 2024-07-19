<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <script src="script.js" defer></script>
</head>
<body>
    <header>
        <h1>Blog Application</h1>
        <a href="${pageContext.request.contextPath}/index.jsp" class="home-button">Home</a>
    </header>
    <div class="container">
        <h2>User Registration</h2>
        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>
        <form id="registrationForm" action="${pageContext.request.contextPath}/register" method="post">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" placeholder="Enter your Name" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" placeholder="Enter your Email" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required>
            </div>
            <div class="form-group">
                <label for="role">Role:</label>
                <select id="role" name="role">
                    <option value="Admin">Admin</option>
                </select>
            </div>
            <input type="submit" value="Register">
        </form>
    </div>
    <footer>
        <p>&copy; 2024 Blog Application. All rights reserved.</p>
    </footer>
</body>
</html>
