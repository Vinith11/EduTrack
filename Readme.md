
# 🎓 EduTrack - Streamlining Student-Faculty Collaboration 🚀

**EduTrack** is a comprehensive platform that bridges the gap between students and faculty, ensuring a smooth process for mentor selection, project approval, and internship tracking. With robust features and seamless email integration, it eliminates conflicts and enhances productivity for final-year project management.

---

## 📋 Project Description

EduTrack enables students to select their project guides and team members. Faculty receive requests for approval via email and can respond accordingly. Upon action, students are notified through emails. Additionally, students can log their internship details, including organization, duration, and domain. Faculty benefit from insightful batch-wise analytics on project status and domains.

---

## 🖥️ Repositories

- **Frontend Repository**: [EduTrack-Frontend](https://github.com/Vinith11/EduTrack-frontend)  
- **Backend Repository**: [EduTrack-Backend](https://github.com/Vinith11/EduTrack-backend)  

---

## 🛠 Tech Stack

### Frontend
- **React**: Dynamic and responsive UI.
- **Redux Toolkit**: Efficient state management.
- **Material UI**: Modern and accessible design components.

### Backend
- **Spring Boot**: Scalable backend API development.
- **PostgreSQL**: Reliable and robust database management.
- **Docker**: For containerizing the backend and database.
- **GitHub Actions**: For CI/CD pipeline.

### Deployment Links: [Link](https://mentor-front-roan.vercel.app)
- **Frontend**: Vercel
- **Backend**: Render


---

## 🌟 Features

### Student Features
- **Guide Selection**: Choose mentors and form project teams.
- **Internship Logging**: Record details like company, domain, and duration.
- **Email Notifications**: Receive updates on guide approval status.

### Faculty Features
- **Approval Management**: Approve or decline student requests for guidance.
- **Batch Analytics**: View insights on project completion rates and domains.

---

## 📦 Prerequisites

- **Node.js**: Required for frontend development.
- **Java 17**: To run the Spring Boot backend.
- **Docker**: To containerize the application and database.

---

## 🚀 Getting Started

### Clone the Repositories

```bash
git clone https://github.com/Vinith11/EduTrack-frontend.git
git clone https://github.com/Vinith11/EduTrack-backend.git
```

---

### 🖥️ Frontend Setup

1. Navigate to the frontend directory:
    ```bash
    cd EduTrack-frontend
    ```

2. Install dependencies:
    ```bash
    npm install
    ```

3. Configure environment variables in `.env`:
    ```env
    VITE_DEPLOYED_URL=http://localhost:5454
    ```

4. Start the development server:
    ```bash
    npm start
    ```

---

### ⚙️ Backend Setup

1. Navigate to the backend directory:
    ```bash
    cd EduTrack-backend
    ```

2. Configure environment variables in `.env`:
    ```env
    DATABASE_URL=
    DATABASE_USER=
    DATABASE_PASSWORD=
    EMAIL_USER=
    EMAIL_PASSWORD=
    ```

3. Build and run the Spring Boot application:
    ```bash
    ./mvnw spring-boot:run
    ```

---

### 📑 API Documentation

Access the full API documentation using [Postman](https://documenter.getpostman.com/view/29960479/2sAXqy2JYb).

---

## 🌐 Deployment Details

### CI/CD Pipeline
- **GitHub Actions** is used for automated builds, and deployments.
- The backend and database are containerized using Docker to ensure consistent environments across development and production.

![CI/CD Pipeline Demo](https://miro.medium.com/v2/resize:fit:1400/format:webp/1*NscA2HUJm8ED-vuXyayO-g.gif)

---

## 🛠 Troubleshooting

### Frontend
- Ensure `.env` contains the correct `VITE_DEPLOYED_URL`.
- Run `npm install` to resolve any missing dependencies.

### Backend
- Verify `.env` for accurate database and email credentials.
- Ensure Docker is running before starting the backend.
