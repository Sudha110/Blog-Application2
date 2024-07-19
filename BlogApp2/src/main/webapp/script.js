document.addEventListener('DOMContentLoaded', () => {
	const form = document.getElementById('registrationForm');

	if (form) {
		form.addEventListener('submit', (event) => {
			// Clear previous error messages
			const errorElements = document.querySelectorAll('.error');
			errorElements.forEach(el => el.remove());

			// Validate form fields
			let isValid = true;
			const name = document.getElementById('name').value.trim();
			const email = document.getElementById('email').value.trim();
			const password = document.getElementById('password').value.trim();
			const role = document.getElementById('role').value;

			if (name.length < 3) {
				isValid = false;
				showError('name', 'Name must be at least 3 characters long');
			}

			if (!validateEmail(email)) {
				isValid = false;
				showError('email', 'Invalid email format');
			}

			if (password.length < 6) {
				isValid = false;
				showError('password', 'Password must be at least 6 characters long');
			}

			if (role !== 'Admin' && role !== 'Viewer') {
				isValid = false;
				showError('role', 'Please select a valid role');
			}

			if (!isValid) {
				event.preventDefault();
			}
		});
	}

	function showError(fieldId, message) {
		const field = document.getElementById(fieldId);
		const error = document.createElement('p');
		error.className = 'error';
		error.textContent = message;
		field.parentElement.appendChild(error);
	}

	function validateEmail(email) {
		const re = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
		return re.test(String(email).toLowerCase());
	}

	const deleteButtons = document.querySelectorAll('.delete-button');
	deleteButtons.forEach(button => {
		button.addEventListener('click', (event) => {
			const blogId = event.target.dataset.blogId;
			confirmDelete(blogId);
		});
	});

	const fileInput = document.getElementById('imageUpload');
	if (fileInput) {
		fileInput.addEventListener('change', previewImage);
	}

	const loginForm = document.getElementById('loginForm');
	if (loginForm) {
		loginForm.addEventListener('submit', (event) => {
			if (!validateLoginForm()) {
				event.preventDefault();
			}
		});
	}

	function confirmDelete(blogId) {
		const confirmation = confirm("Are you sure you want to delete this blog post?");
		if (confirmation) {
			// If confirmed, redirect to delete URL
			window.location.href = "delete_blog?id=" + blogId;
		}
		// If not confirmed, do nothing
		return false;
	}

	function validateLoginForm() {
		const email = document.getElementById("email").value;
		const password = document.getElementById("password").value;
		const errorMessage = document.getElementById("clientErrorMessage");

		if (email === "" || password === "") {
			errorMessage.textContent = "Both fields are required.";
			return false;
		}

		// Simple email format validation
		const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		if (!emailPattern.test(email)) {
			errorMessage.textContent = "Please enter a valid email address.";
			return false;
		}

		return true;
	}

	// Slideshow logic
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
		if (n > slides.length) { slideIndex = 1 }
		if (n < 1) { slideIndex = slides.length }
		for (i = 0; i < slides.length; i++) {
			slides[i].style.display = "none";
		}
		for (i = 0; i < pagination.length; i++) {
			pagination[i].className = pagination[i].className.replace(" active", "");
		}
		slides[slideIndex - 1].style.display = "block";
		pagination[slideIndex - 1].className += " active";
	}
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
	
});
