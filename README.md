# 🎓 Connect Backend

The **Connect Backend** is a REST API service built using **Spring Boot** and **PostgreSQL**.  
It powers the **Connect Platform**, which helps alumni and students stay connected.

This backend is deployed on **Render** and connects with the React frontend hosted on **Vercel**.

---

## 🚀 Features
- 🔐 **JWT-based Authentication** (Login & Register)  
- 👨‍🎓 **Alumni Management**  
- 👮 **Role-based Access Control**  
- 🐳 **Docker Support** for easy deployment  
- 🌍 **Cloud Deployment** with Render + PostgreSQL  

---

## 🛠 Tech Stack
- **Java 17**
- **Spring Boot 3**
- **Spring Security (JWT Authentication)**
- **Hibernate / JPA**
- **PostgreSQL**
- **Docker**

---

## ⚙️ Setup & Run Locally

### 1️⃣ Clone the repository
git clone https://github.com/Rishabhj9888/connect_backend.git
cd connect_backend/demo

 ---

 
2️⃣ Configure PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
spring.datasource.username=your_username
spring.datasource.password=your_password

----

3️⃣ Build & Run (without Docker)
./mvnw clean install
./mvnw spring-boot:run
---

🐳 Run with Docker
1️⃣ Build the image
docker build -t connect-backend .

2️⃣ Run the container
docker run -p 8080:8080 connect-backend
