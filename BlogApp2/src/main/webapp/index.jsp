<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <header>
        <h1>Blog Application</h1>
        <a href="${pageContext.request.contextPath}/index.jsp" class="home-button">Home</a>
    </header>
    <div class="container">
        <h1>Welcome to Blog Application</h1>
        <div class="links">
            <button class="box" onclick="window.location.href='${pageContext.request.contextPath}/admin_Login.jsp'">Admin Login</button>
            <button class="box" onclick="window.location.href='${pageContext.request.contextPath}/viewer_Login.jsp'">Viewer Login</button>
        </div>
    </div>
    <footer>
        <p>&copy; 2024 Blog Application. All rights reserved.</p>
    </footer>
</body>
</html>
