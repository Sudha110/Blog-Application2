<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Edit Blog Post</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles.css">
</head>
<body>
	<header>
		<h1>Blog Application</h1>
		<a href="${pageContext.request.contextPath}/index.jsp"
			class="home-button">Home</a>
	</header>
	<div class="container">
		<h2>Edit Blog Post</h2>
		<form id="editBlogForm"
			action="${pageContext.request.contextPath}/update_blog" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="id" value="${blog.id}">
			<div class="form-group">
				<label for="title">Title:</label> <input type="text" id="title"
					name="title" placeholder="blog title" value="${blog.title}"
					required>
			</div>
			<div class="form-group">
				<label for="content">Content:</label>
				<textarea id="content" name="content" placeholder="blog description"
					required>${blog.content}</textarea>
			</div>
			<div class="form-group">
				<label for="image">Image:</label> <input type="file" id="image"
					name="image" onchange="previewImage(event)">
			</div>
			<c:if test="${not empty blog.imagePath}">
				<img id="currentImage"
					src="${pageContext.request.contextPath}/uploads/${blog.imagePath}"
					alt="Blog Image" style="width: 50px; height: 50px;">
			</c:if>
			<p class="note">Note: Ensure the image is saved in
				`webapp/upload` before uploading. Using the same image again might
				cause display issues upon relogin. To reuse the same image, create
				the blog with the image and then update the blog with the same
				image.</p>
			<img id="preview" style="display: none; width: 50px; height: 50px"
				alt="Image Preview"><br> <br> <input type="submit"
				value="Update">
		</form>
	</div>
	<footer>
		<p>&copy; 2024 Blog Application. All rights reserved.</p>
	</footer>
	<script>
		document
				.getElementById('editBlogForm')
				.addEventListener(
						'submit',
						function(event) {
							var confirmationMessage = "Ensure the image is saved in `webapp/upload` before uploading. Using the same image again might cause display issues upon relogin. To reuse the same image, create the blog with the image and then update the blog with the same image.If you want to continue updating the form, click Cancel";
							if (!confirm(confirmationMessage)) {
								event.preventDefault();
							}
						});

		function previewImage(event) {
			var reader = new FileReader();
			reader.onload = function() {
				var output = document.getElementById('preview');
				output.src = reader.result;
				output.style.display = 'block';
				var currentImage = document.getElementById('currentImage');
				if (currentImage) {
					currentImage.style.display = 'none';
				}
			};
			reader.readAsDataURL(event.target.files[0]);
		}
	</script>
</body>
</html>
