<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/view.css">
<script>
    function confirmDelete(blogId) {
        const confirmation = confirm("Are you sure you want to delete this blog post?");
        if (confirmation) {
            // If confirmed, redirect to delete URL
            window.location.href = "delete_blog?id=" + blogId;
        }
        // If not confirmed, do nothing
        return false;
    }
</script>
</head>
<body>
	<header>
		<h1>Blog Application</h1>
		<a href="${pageContext.request.contextPath}/index.jsp"
			class="home-button">Home</a>
	</header>
	<div class="container">
		<h2>
			<a class="box" href="create_Blog.jsp">Create New Blog Post</a>
		</h2>
		<h3 class="message">Your Blog Posts</h3>
		<c:if test="${not empty blogs}">
			<table>
				<tr>
					<th>Title</th>
					<th>Content</th>
					<th>Image</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
				<c:forEach var="blog" items="${blogs}">
					<tr>
						<td>${blog.title}</td>
						<td>${blog.content}</td>
						<td><c:if test="${not empty blog.imagePath}">
								<img
									src="${pageContext.request.contextPath}/uploads/${blog.imagePath}"
									alt="Blog Image">
							</c:if> <c:if test="${empty blog.imagePath}">
                                No image available
                            </c:if></td>
						<td><a href="update_blog?id=${blog.id}">Edit</a></td>
						<td><a href="#" onclick="return confirmDelete(${blog.id})">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${empty blogs}">
			<p>No blog posts found.</p>
		</c:if>
	</div>
	<footer>
		<p>&copy; 2024 Blog Application. All rights reserved.</p>
	</footer>
	</body>
</html>
