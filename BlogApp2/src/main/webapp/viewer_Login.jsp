<!DOCTYPE html>
<html>
<head>
    <title>Login - Blog Application</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
     <script src="${pageContext.request.contextPath}/script.js"></script>
</head>
<body>
    <header>
        <h1>Blog Application</h1>
        <a href="${pageContext.request.contextPath}/index.jsp" class="home-button">Home</a>
    </header>
    <div class="container">
        <div class="login-container">
            <h1>Viewer Login!</h1>
            <p class="message">Please login to access your account.</p>
            <p id="clientErrorMessage" class="message"></p>
            <c:if test="${not empty errorMessage}">
                <p class="message" style="color:red;">${errorMessage}</p>
            </c:if>
            <form action="${pageContext.request.contextPath}/viewer" method="post" onsubmit="return validateLoginForm()" class="login-form">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" placeholder="Enter your Email id" required>

                <label for="password">Password:</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required>

                <input type="submit" class="box" value="Login">
            </form>
            <p class="message">Don't have an account? <a href="viewer_Register.jsp">Sign up here</a></p>
        </div>
    </div>
    <footer>
        <p>&copy; 2024 Blog Application. All rights reserved.</p>
    </footer>
</body>
</html>
