# 📚 Attendance Management System

A full-stack Attendance Management System built using **Spring Boot**, **Spring MVC**, **Thymeleaf**, **Spring Data JPA**, **Hibernate**, and **MySQL Database**.

The application allows users to manage students, mark attendance, view attendance records, generate student reports, and monitor attendance through a simple and responsive web interface.

---

## 📸 Screenshots

> Add screenshots of your application here.

- Dashboard
- Student Management Page
- Add Student
- Attendance Page
- Attendance Records
- Student Report

---

# ✨ Features

- 👨‍🎓 Add Students
- ✏️ Edit Student Details
- 🗑 Delete Students
- 🔍 Search Students
- 📝 Mark Attendance
- ✅ Present / Absent Status
- 📅 Date-wise Attendance
- 📋 View Attendance Records
- 📊 Attendance Dashboard
- 📈 Student Attendance Reports
- 🎯 Attendance Percentage
- 📱 Responsive User Interface

---

# 🛠 Tech Stack

| Layer | Technology |
|--------|------------|
| Backend | Java 17 |
| Framework | Spring Boot |
| MVC | Spring MVC |
| ORM | Hibernate |
| Database | MySQL |
| Template Engine | Thymeleaf |
| Build Tool | Maven |
| Frontend | HTML, CSS, JavaScript |
| Version Control | Git & GitHub |
| IDE | IntelliJ IDEA |

---

# 📁 Project Structure

```text
attendance-management
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.am.attendancemanagement
│   │   │       ├── controller
│   │   │       ├── entity
│   │   │       ├── repository
│   │   │       ├── service
│   │   │       └── AttendanceManagementApplication.java
│   │   │
│   │   ├── resources
│   │   │   ├── templates
│   │   │   ├── static
│   │   │   │   ├── css
│   │   │   │   └── js
│   │   │   └── application.properties
│   │
│   └── test
│
├── pom.xml
└── README.md
```

---

# 🚀 Installation

Clone the repository:

```bash
git clone https://github.com/harikka4905-collab/attendance-management-system.git
```

Go to the project folder:

```bash
cd attendance-management-system
```

---

# ⚙ Configure Database

Create the MySQL database:

```sql
CREATE DATABASE attendance_management;
```

Configure the database in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/attendance_management
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

# ▶ Run the Project

Run using Maven:

```bash
mvn spring-boot:run
```

Or run:

```text
AttendanceManagementApplication.java
```

---

# 🌐 Open in Browser

```text
http://localhost:8080
```

---

# 📊 Dashboard

The dashboard displays:

- Total Students
- Attendance Records
- Present Students
- Absent Students
- Attendance Statistics
- Student Reports

---

# 📈 Attendance Reports

The Attendance Management System provides student attendance reports including:

- Total Attendance Days
- Present Days
- Absent Days
- Attendance Percentage
- Individual Student Report

---

# 🎯 Future Enhancements

- 🔐 Spring Security
- 👤 User Login & Registration
- 🛡 Admin Login
- 🎓 Student Login
- 👨‍🏫 Faculty Login
- 🔑 JWT Authentication
- 📧 Email Notifications
- 📱 SMS Attendance Alerts
- 📊 Advanced Attendance Analytics
- 📄 PDF Report Generation
- 📥 Excel Report Export
- ☁ Cloud Deployment
- 🔗 REST APIs

---

# 👩‍💻 Developed By

**Harikka**

GitHub:

https://github.com/harikka4905-collab

---

# ⭐ If you like this project

Please give this repository a ⭐ on GitHub.
