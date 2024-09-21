
# 🎓 EduTrack

**EduTrack** is a comprehensive platform built using Spring Boot and MySQL, designed to streamline interactions between students and faculty in an academic setting. It facilitates project collaborations, internship evaluations, and feedback management.

## 🌟 Features

### 👨‍🎓 Student Features
- **Create an Account**: Sign up as a student to access all features.
- **Add Internship Details**: Document your internship experience.
- **Company Feedback**: Share what you've learned and how the company has helped in your growth.
- **Project Group Creation**: 
  - Create a group for your final year project.
  - Add project details (description, document links, etc.).
  - Assign a faculty guide for the project.
  
### 👩‍🏫 Faculty Features
- **Create an Account**: Faculty can sign up to manage and evaluate student projects.
- **Approve Project Groups**: Review and approve final year project group requests.
- **Evaluate Internships**: Grade and provide feedback on student internships.
- **Evaluate Major Projects**: Grade student final year projects.
- **Feedback for Companies**: Provide insights on how well the company contributed to student development.

## 📧 Email Notification System
A core feature of EduTrack is the **automated email system**:
- When a student forms a project group, an email is sent to the assigned professor with all the relevant group details.
- The professor can then approve or reject the request.
- Upon decision, an email is automatically sent back to the students with the result of the approval process.

## 🛠️ Technologies Used
- **Backend**: Spring Boot
- **Database**: MySQL
- **Containerization**: Docker
- **Deployment**: Render

## 🚀 Deployment
The backend is deployed using **Render**. Docker was used to package the Spring Boot application into a container for easy deployment.

## 💡 Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/edutrack.git
   ```
2. Navigate into the project directory:
   ```bash
   cd edutrack
   ```
3. Build the Docker image:
   ```bash
   docker build -t edutrack-backend .
   ```
4. Run the container:
   ```bash
   docker run -p 5454:5454 edutrack-backend
   ```

5. Access the app at `http://localhost:5454`

6. You can view endpoints and test the API using Swagger UI at `http://localhost:5454/swagger-ui/index.html`

