<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
 <header>
        <h1>Blog Application</h1>
        <a href="${pageContext.request.contextPath}/index.jsp" class="home-button">Home</a>
    </header>
    <div class="container">
        <h1>Welcome</h1>
        <div class="links">
            <button class="box" onclick="window.location.href='blog_list'">List of all blogs</button>
            <button class="box" onclick="window.location.href='search_Blog.jsp'">Search for blog</button>
        </div>
    </div>
     <footer>
        <p>&copy; 2024 Blog Application. All rights reserved.</p>
    </footer>
    
</body>
</html>
