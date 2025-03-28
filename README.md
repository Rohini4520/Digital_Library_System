Digital Library System

📚 Project Overview

This is a Spring Boot-based Digital Library Management System. The application allows librarians to efficiently add, update, search, and remove books while maintaining their availability status.

🚀 Features

Add a Book

View All Books

Search Books by ID or Title

Update Book Details

Delete a Book Record

Exit System

🛠️ Tech Stack

Java

Spring Boot

Spring Web

Spring Actuator

Maven

🧑‍💻 Prerequisites

Java 8 or later

Maven

Git

🚦 Installation and Running Instructions

Clone the Repository:

git clone https://github.com/Rohini4520/Digital_Library_System.git
cd digital-library-system

Build the Project:

mvn clean install

Run the Application:

mvn spring-boot:run

Access the Application:

Open your browser and navigate to http://localhost:8080

🛎️ API Endpoints

Method         Endpoint                  Description

POST         /books/add                Add a new book

GET          /books/view-all           View all books

GET          /books/search             Search book by ID or Title

PUT          /books/update/{id}        Update book details by ID

DELETE       /books/delete/{id}        Delete a book by ID

POST        /books/exit                Exit the system


🚧 Challenges Faced

Graceful Shutdown: Implementing the exit functionality using System.exit() was challenging. However, with Spring Actuator, the system can now shut down properly.

Validation: Ensuring proper input validation for book data was essential to prevent errors.

🚀 Future Improvements

Implement a database using MySQL for persistent storage.

Add authentication and authorization using Spring Security.

Create a simple frontend using React or Angular.

📫 Contact

If you have any questions, feel free to reach out:

GitHub: Rohini4520

Email: rohine452001@gmail.com

