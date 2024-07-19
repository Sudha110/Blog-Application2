<!DOCTYPE html>
<html>
<head>
<title>Create Blog</title>
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
		<h2>Create Blog Post</h2>
		<c:if test="${not empty errorMessage}">
			<p class="message" style="color: red;">${errorMessage}</p>
		</c:if>
		<form id="editBlogForm" action="${pageContext.request.contextPath}/create_blog"
			method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="title">Title:</label> <input type="text" id="title"
					name="title" placeholder="blog title" required>
			</div>
			<div class="form-group">
				<label for="content">Content:</label>
				<textarea id="content" name="content" placeholder="blog description"
					required></textarea>
			</div>
			<div class="form-group">
				<label for="image">Image:</label> <input type="file" id="image"
					name="image" onchange="previewImage(event)" required>
			</div>
			<p class="note">Note: Ensure the image is saved in
				`webapp/upload` before uploading. Using the same image again might
				cause display issues upon relogin. To reuse the same image, create
				the blog with the image and then update the blog with the same
				image.</p>
			<img id="preview" style="display: none; height: 50px; width: 50px;"
				alt="Image Preview"><br> <br> <input type="submit"
				value="Create Blog">
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
			};
			reader.readAsDataURL(event.target.files[0]);
		}
	</script>
</body>
</html>
