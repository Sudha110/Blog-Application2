<!DOCTYPE html>
<html>
<head>
    <title>Error - Blog Application</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
    <header>
        <h1>Blog Application</h1>
        <a href="${pageContext.request.contextPath}/index.jsp" class="home-button">Home</a>
    </header>
    <div class="container">
        <h2>Oops! Something went wrong.</h2>
        <p class="message">We encountered an unexpected error. Please try again later.</p>
        <c:if test="${not empty errorMessage}">
            <p class="message" style="color:red;">Error details: ${errorMessage}</p>
        </c:if>
        <p class="message">If the problem persists, please contact support.</p>
        <a href="${pageContext.request.contextPath}/index.jsp" class="box">Go to Home</a>
    </div>
    <footer>
        <p>&copy; 2024 Blog Application. All rights reserved.</p>
    </footer>
</body>
</html>
