<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Blogs</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view.css">
    <style>
        .blog-container {
            width: 70%;
            margin: auto;
        }
        .search-form {
        	margin:80px 0;
            margin-bottom: 20px;
        }
        .search-input {
            padding: 10px;
            font-size: 1em;
            width: 80%;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .search-button {
            padding: 10px 20px;
            font-size: 1em;
            border: none;
            background-color: #007bff;
            color: white;
            cursor: pointer;
            border-radius: 5px;
        }
        .search-button:hover {
            background-color: #0056b3;
        }
        .slideshow-container {
        padding:100px;
    background-color: rgba(0, 0, 0, 0.5);
            max-width: 1000px;
            position: relative;
            margin: auto;
        }
        .slide {
            display: none;
            text-align: center;
        }
        .active-slide {
            display: block;
        }
        .prev, .next {
            cursor: pointer;
            position: absolute;
            top: 50%;
            width: auto;
            margin-top: -22px;
            padding: 16px;
            color: white;
            font-weight: bold;
            font-size: 18px;
            transition: 0.6s ease;
            border-radius: 0 3px 3px 0;
            user-select: none;
        }
        .next {
            right: 0;
            border-radius: 3px 0 0 3px;
        }
        .prev:hover, .next:hover {
            background-color: rgba(0,0,0,0.8);
        }
        .pagination {
            text-align: center;
            margin-top: 20px;
        }
        .pagination a {
            margin: 0 5px;
            padding: 8px 16px;
            text-decoration: none;
            color: black;
            background-color: #f4f4f4;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .pagination a.active {
            background-color: #007bff;
            color: white;
            border: 1px solid #007bff;
        }
        .pagination a:hover {
            background-color: #ddd;
            color: black;
        }
    </style>
</head>
<body>
    <header>
        <h1>Search Blogs</h1>
        <a href="${pageContext.request.contextPath}/index.jsp" class="home-button">Home</a>
    </header>
    <div class="blog-container">
        <!-- Search form -->
        <div class="search-form">
            <form action="${pageContext.request.contextPath}/search" method="get">
                <input type="text" name="searchTitle" class="search-input" placeholder="Enter blog title to search" value="${param.searchTitle}">
                <input type="submit" value="Search" class="search-button">
            </form>
        </div>

        <!-- Slideshow container -->
        <div class="slideshow-container">
            <c:forEach var="blog" items="${blogs}">
                <div class="slide">
                    <div class="blog-title"><h1>${blog.title}</h1></div>
                    <c:if test="${not empty blog.imagePath}">
                        <img src="${pageContext.request.contextPath}/uploads/${blog.imagePath}" alt="Blog Image">
                    </c:if>
                    <div class="blog-content">${blog.content}</div>
                </div>
            </c:forEach>

            <!-- Next and previous buttons -->
            <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
            <a class="next" onclick="plusSlides(1)">&#10095;</a>
        </div>

        <!-- Pagination controls -->
        <div class="pagination">
            <c:forEach var="blog" items="${blogs}" varStatus="status">
                <a href="#" class="${status.index == 0 ? 'active' : ''}" onclick="currentSlide(${status.index + 1})">${status.index + 1}</a>
            </c:forEach>
        </div>
    </div>

    <script>
        let slideIndex = 1;
        showSlides(slideIndex);

        function plusSlides(n) {
            showSlides(slideIndex += n);
        }

        function currentSlide(n) {
            showSlides(slideIndex = n);
        }

        function showSlides(n) {
            let i;
            let slides = document.getElementsByClassName("slide");
            let pagination = document.getElementsByClassName("pagination")[0].getElementsByTagName("a");
            if (n > slides.length) {slideIndex = 1}
            if (n < 1) {slideIndex = slides.length}
            for (i = 0; i < slides.length; i++) {
                slides[i].style.display = "none";
            }
            for (i = 0; i < pagination.length; i++) {
                pagination[i].className = pagination[i].className.replace(" active", "");
            }
            slides[slideIndex-1].style.display = "block";
            pagination[slideIndex-1].className += " active";
        }
    </script>
</body>
</html>
