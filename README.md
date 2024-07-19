## BlogApp2

## Project Overview
BlogApp2 is a Java-based web application designed for managing blog posts with user roles and authentication. The application supports two types of users: Admin and Viewer. Admins can create, update, and delete blog posts, while Viewers can browse and search for blog posts.

## Technologies Used
- Backend: Java Servlets
- Frontend: JSP (JavaServer Pages)
- Database: MySQL

## Prerequisites
1. IDE with support for Java EE (e.g., Eclipse)
2. `jstl.jar` and `standard.jar` for JSTL support
3. Java Development Kit (JDK)
4. Apache Tomcat or any other Java web server
5. MySQL Database
6. Maven

## Project Phases

### Phase 1: User Registration and Login (Frontend & Backend)
- User Registration Form: Captures user details (name, email, password) and role (Admin/Viewer).
- Registration Logic: Validates user data, stores it securely in the MySQL database with hashed passwords, and assigns a role.
- Login Page: Includes username/email and password fields.
- Login Logic: Authenticates users against the database and establishes user sessions using cookies or sessions for role identification.

### Phase 2: Admin Dashboard (Frontend & Backend)
- Admin Dashboard: Accessible only after successful login with the "Admin" role.
- Admin Functionalities:
  - Create new blog posts with title, content, and an option to upload images/videos.
  - Update existing blog posts (title, content, image/video).
  - Delete blog posts.
- Servlets Implementation: Handles the functionalities and stores uploaded images/videos securely in the database. Ensures data validation and sanitization to prevent security vulnerabilities.

### Phase 3: User Interface for Viewers (Frontend)
- User Interface: User-friendly interface for viewers to browse blog posts.
- Viewer Functionalities:
  - Search blog posts by title or date.
  - Implement pagination and sorting for search results.
  - View individual blog posts in detail.

### Phase 4: Security Enhancements (Backend)
- Password Hashing: Secure password hashing and storage using algorithms like BCrypt.
- Input Validation: Prevent SQL injection attacks using prepared statements.

## Deliverables
1. Functional Java web application with JSP and Servlets.
2. MySQL database schema populated with sample data.
3. Unit tests for critical functionalities (optional but highly recommended).
4. Documentation outlining the implemented features and technologies used.

## Database Setup
1. Create a database named `blog_app`:
```sql
CREATE DATABASE blog_app;
```

2. Create the `users` table:
```sql
CREATE TABLE users (
   id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
   name VARCHAR(100) NOT NULL,
   email VARCHAR(100) UNIQUE NOT NULL,
   password VARCHAR(255) NOT NULL,
   role ENUM('Admin', 'Viewer') NOT NULL
);
```

3. Create the `blogs` table:
```sql
CREATE TABLE blogs (
   id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
   title VARCHAR(255) NOT NULL,
   content TEXT NOT NULL,
   image_path VARCHAR(255),
   created_by INT,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## Installation
1. Clone the repository.
2. Open the project in your IDE.
3. Configure the project build path to include the necessary JAR files for JDBC, Servlets, and JSTL (`jstl.jar` and `standard.jar`).
4. Update the database connection details in java files:
```java
private String jdbcURL = "jdbc:mysql://localhost:3306/blog_app";
private String jdbcUsername = "root";
private String jdbcPassword = "yourpassword";
```

## Running the Application
1. Deploy the project to your Apache Tomcat server.
2. Access the application in your web browser:
http://localhost:8085/BlogApp2/

## Project Structure

```
MyBlogApp
│
├── Deployment Descriptor
│   └── BlogApp
│
├── JAX-WS Web Services
│
├── Java Resources
│   ├── src/main/java
│   │   ├── com.tap.admin
│   │   │   ├── AdminDashboardServlet.java
│   │   │   ├── AdminLoginServlet.java
│   │   │   ├── AdminRegisterServlet.java
│   │   ├── com.tap.blog
│   │   │   ├── BlogsDisplay.java
│   │   │   ├── BlogSearchServlet.java
│   │   │   ├── CreateBlogServlet.java
│   │   │   ├── DeleteBlogServlet.java
│   │   │   ├── UpdateBlogServlet.java
│   │   ├── com.tap.connection
│   │   │   ├── Connection.java
│   │   ├── com.tap.DAO
│   │   │   ├── BlogDaoImp.java
│   │   │   ├── BlogDAOInterface.java
│   │   │   ├── UserDaoImp.java
│   │   │   ├── UserDAOInterface.java
│   │   ├── com.tap.packages
│   │   │   ├── Blog.java
│   │   │   ├── User.java
│   │   └── com.tap.viewer
│   │       ├── ViewerDashboardServlet.java
│   │       ├── ViewerLoginServlet.java
│   │       └── ViewerRegisterServlet.java
│
├── src
│   └── main
│       └── webapp
│           ├── META-INF
│           ├── uploads
│           ├── WEB-INF
│           │   ├── lib
│           │   │   ├── jstl-1.2.jar
│           │   │   ├── mysql-connector-j-8.3.0.jar
│           │   │   └── standard.jar
│           │   └── web.xml
│           ├── admin_Dashboard.jsp
│           ├── admin_Login.jsp
│           ├── admin_Register.jsp
│           ├── create_Blog.jsp
│           ├── error.jsp
│           ├── image.jpg
│           ├── index.jsp
│           ├── script.js
│           ├── search_Blog.jsp
│           ├── styles.css
│           ├── update_Blog.jsp
│           ├── viewer_Dashboard.jsp
│           ├── viewer_Home.jsp
│           ├── viewer_Login.jsp
│           └── viewer_Register.jsp
│
├── target
├── pom.xml
└── README.md
```

## CRUD Operations
1. Create
   - Form: `create_Blog.jsp`
   - Servlet: `CreateBlogServlet.java`

2. Read
   - Pages: `viewer_dashboard.jsp`, `admin_dashboard.jsp`
   - Servlet: `BlogListServlet.java`

3. Update
   - Form: `update_Blog.jsp`
   - Servlet: `UpdateBlogServlet.java`

4. Delete
   - Page: `admin_dashboard.jsp` (contains delete links)
   - Servlet: `DeleteBlogServlet.java`

## Additional Information
Note: Before uploading the file, make sure that the image file is already saved or placed in the `webapp/upload` folder. Otherwise, sometimes it will not display the images correctly.

### Sample Data for Tables

`users` Table:
```sql
-- Insert sample data into users table
INSERT INTO users (name, email, password, role) VALUES
('Admin User', 'admin@example.com', '$2a$10$EIX/8FEoBpbn/Zd4B/UD7.PGZJwHV1FDhOd5LvQxYkKoZ0bhKGG2y', 'Admin'), -- password: admin123
('Viewer User', 'viewer@example.com', '$2a$10$XKdJgPf8y3e3LpEdZ6zleuOV4Eq8ufmMKYf7zUP6WZ76EZAxzOFUC', 'Viewer'); -- password: viewer123
```

`blogs` Table:
```sql
-- Insert sample data into blogs table
INSERT INTO blogs (title, content, image_path, created_by) VALUES
('First Blog Post', 'This is the content of the first blog post.', 'image5.jpg', 1),
('Second Blog Post', 'This is the content of the second blog post.', 'image6.jpg', 1),
('Third Blog Post', 'This is the content of the third blog post.', 'image7.jpg', 2);

-- Insert additional sample data into blogs table
INSERT INTO blogs (title, content, image_path, created_by) VALUES
('Understanding Java Streams', 
'Java Streams API is a powerful tool for processing sequences of elements. Introduced in Java 8, it allows for functional-style operations on streams of elements, such as map-reduce transformations. The Streams API can be used to perform a variety of operations, such as filtering, mapping, and reducing, in a declarative manner. This post explores the basics of Java Streams and provides examples on how to leverage its capabilities for more readable and maintainable code.', 
'image5.jpg', 1),

('Mastering CSS Grid Layout', 
'CSS Grid Layout is a two-dimensional layout system

 for the web. It lets you create complex layouts on the web more easily and consistently across browsers. Grid Layout excels at dividing a page into major regions or defining the relationship in terms of size, position, and layer between parts of a control built from HTML primitives. This article covers the fundamentals of CSS Grid Layout and provides practical examples to help you master this powerful layout system.', 
'image6.jpg', 1),

('Introduction to Machine Learning', 
'Machine Learning (ML) is a branch of artificial intelligence (AI) that focuses on building systems that learn from data, identify patterns, and make decisions with minimal human intervention. ML algorithms use historical data as input to predict new output values. This blog post delves into the basics of machine learning, its applications, and the different types of machine learning algorithms, providing a solid foundation for beginners.', 
'image7.jpg', 2),

('Building RESTful APIs with Spring Boot', 
'Spring Boot is a popular framework for building production-grade Spring-based applications with minimal effort. It provides a range of features that make it easy to create stand-alone, production-grade Spring-based applications. This post walks through the process of building RESTful APIs using Spring Boot, covering key concepts such as controllers, services, and repositories, and provides code examples to demonstrate the implementation.', 
'image10.jpg', 1),

('A Guide to Effective Code Reviews', 
'Code reviews are an essential part of the software development process. They help improve code quality, share knowledge among team members, and ensure that coding standards are followed. This article discusses best practices for conducting effective code reviews, including how to give constructive feedback, the importance of readability and maintainability, and tips for reviewers and authors to get the most out of the process.', 
'image9.jpg', 2);
```

Note: When inserting data into the `blogs` table, ensure that the `created_by` field corresponds to an existing `id` in the `users` table. Failure to do so will result in a foreign key constraint violation error.

---


## ScreenShots

Here are some screenshots of the project

## admin_Dashboard.jsp
![Screenshot 2024-07-19 161732](https://github.com/user-attachments/assets/d703008f-83ff-4e72-ada0-b2595c765fe9)

## admin_Login.jsp
![Screenshot 2024-07-19 161653](https://github.com/user-attachments/assets/5bb595ab-a7a1-4ebb-9a37-6da2b2c9bc43)

## admin_Register.jsp
![Screenshot 2024-07-19 161706](https://github.com/user-attachments/assets/c8f07f2b-ac7d-4952-99c8-7a493f057db0)

## create_Blog.jsp
![Screenshot 2024-07-19 161746](https://github.com/user-attachments/assets/2fe8cec8-c679-4baa-b0ab-aeb0f1f6bbe1)

## error.jsp
![Screenshot 2024-07-19 162244](https://github.com/user-attachments/assets/e91faa76-db78-43af-8b43-72456a39f4bd)

## index.jsp
![Screenshot 2024-07-19 161611](https://github.com/user-attachments/assets/d35b1d86-369c-4c75-8b82-f71cebac32eb)

## search_Blog.jsp
![Screenshot 2024-07-19 162113](https://github.com/user-attachments/assets/00c43b32-e421-40fc-a54a-87173e804cfa)

## update_Blog.jsp
![Screenshot 2024-07-19 161803](https://github.com/user-attachments/assets/596cd2f8-30d3-41d1-841a-d62c17767fc0)

## viewer_Dashboard.jsp
![Screenshot 2024-07-19 162904](https://github.com/user-attachments/assets/d3f1b861-2331-4434-9f17-bcbd775c2cba)

## viewer_Home.jsp
![Screenshot 2024-07-19 161923](https://github.com/user-attachments/assets/a550ce46-326a-49b8-9757-716330708be8)

## viewer_Login.jsp
![Screenshot 2024-07-19 161841](https://github.com/user-attachments/assets/df878d7e-f819-4b1d-ae8b-56884be9a55e)

## viewer_Register.jsp
![Screenshot 2024-07-19 161854](https://github.com/user-attachments/assets/d5c1653d-d209-4ce5-b3aa-b34db022d18b)

## Contact
For any questions or support, please contact [gunisudharani492@example.com](mailto:gunisudharani492@example.com).
