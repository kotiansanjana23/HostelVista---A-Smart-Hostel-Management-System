# 🏨 HostelVista

### Smart Hostel Management System

**HostelVista** is a Java-based desktop application designed to simplify and digitize hostel operations for students and administrators.

The system provides a centralized platform for managing **student admissions, room bookings, fees, mess services, gym facilities, laundry, complaints, study rooms, events, emergency assistance, feedback, and student profiles**.

Built using **Java, JavaFX, Firebase, Maven, ZXing, and webcam integration**, HostelVista demonstrates the development of a feature-rich desktop application with cloud-backed authentication and data management.

---

## 🌟 Why HostelVista?

Traditional hostel management often involves disconnected processes for room allocation, fee management, complaints, mess services, and student records.

HostelVista brings these workflows together into a single application, reducing manual processes and providing students with convenient access to essential hostel services.

---

## 🚀 Key Features

### 👨‍🎓 Student Management

* Student registration and sign-in
* Student admission workflow
* Student profile management
* Centralized student dashboard
* Student information management

### 🛏️ Room Management

* Room booking
* Room-related requests
* Room information management
* Hostel accommodation workflow

### 💳 Fee Management

* Hostel fee dashboard
* Fee payment workflow
* Fee receipt generation
* Payment-related information
* Fee status management

### 🍽️ Mess Management

* Mess dashboard
* Mess information
* Mess change requests
* Food/service management workflow

### 🏋️ Gym Management

* Gym dashboard
* Gym facility information
* Gym-related services and management

### 🧺 Laundry Management

* Laundry service interface
* Laundry-related requests and management

### 📚 Study Room

* Study room access
* Study room management
* Dedicated study-space workflow

### 🚑 Emergency Assistance

* Ambulance assistance interface
* Emergency contact/access functionality
* Dedicated emergency-service workflow

### 📝 Complaints & Feedback

* Complaint submission
* Feedback forms
* Feedback management workflow
* Student issue reporting

### 🎉 Events & Activities

* Hostel event/fest interface
* Event-related forms
* Student participation workflow

### 🔐 Authentication

* User registration
* User sign-in
* Firebase Authentication integration
* Authentication service layer
* Firebase-backed user management

### 📷 QR & Webcam Integration

The application includes webcam and QR-related functionality using:

* Webcam Capture
* ZXing
* QR decoding utilities

This provides the foundation for QR-based hostel workflows.

---

## 🏗️ Application Architecture

HostelVista follows a structured **Model–View–Controller (MVC)** style architecture.

```text
                    ┌──────────────────┐
                    │   JavaFX UI      │
                    │      Views       │
                    └────────┬─────────┘
                             │
                             ▼
                    ┌──────────────────┐
                    │   Controllers    │
                    │ Business Logic   │
                    └────────┬─────────┘
                             │
                             ▼
                    ┌──────────────────┐
                    │     Models       │
                    │   User / Data    │
                    └────────┬─────────┘
                             │
                             ▼
                    ┌──────────────────┐
                    │     Firebase     │
                    │ Cloud Services   │
                    └──────────────────┘
```

---

## 📂 Project Structure

```text
HostelVista/
│
└── app/
    │
    ├── pom.xml
    │
    └── src/
        └── main/
            │
            ├── java/
            │   └── com/
            │       └── hostelvista/
            │
            │           ├── Authentication/
            │           │   ├── FirebaseAuthService.java
            │           │   ├── FirebaseAuthService1.java
            │           │   └── FirebaseInitializer.java
            │           │
            │           ├── controller/
            │           │   ├── AdmissionFormCont.java
            │           │   ├── AmbulanceController.java
            │           │   ├── ComplaintFormController.java
            │           │   ├── DashboardFeesController.java
            │           │   ├── DashboardGymController.java
            │           │   ├── DashboardMessController.java
            │           │   ├── FeeReceiptController.java
            │           │   ├── FeedbackFormCont.java
            │           │   ├── FestFormController.java
            │           │   ├── GymController.java
            │           │   ├── LaundryController.java
            │           │   ├── MessChangeController.java
            │           │   ├── RoomBookingController.java
            │           │   ├── SigninController.java
            │           │   ├── SignupController.java
            │           │   ├── StudentAdmissionCont.java
            │           │   ├── StudentMenuController.java
            │           │   ├── StudentdashboardCont.java
            │           │   └── StudyRoomController.java
            │           │
            │           ├── model/
            │           │   └── User.java
            │           │
            │           ├── view/
            │           │   ├── AdmissionFormView.java
            │           │   ├── AmbulanceView.java
            │           │   ├── ComplaintFormView.java
            │           │   ├── DashboardFeesView.java
            │           │   ├── DashboardGymView.java
            │           │   ├── DashboardmessView.java
            │           │   ├── FeeReceiptView.java
            │           │   ├── FeedbackFormView.java
            │           │   ├── FestFormView.java
            │           │   ├── GymView.java
            │           │   ├── LaundryView.java
            │           │   ├── MessChangeView.java
            │           │   ├── RoomBookingView.java
            │           │   ├── SigninView.java
            │           │   ├── SignupView.java
            │           │   ├── StudentAdmission.java
            │           │   ├── StudentMenuView.java
            │           │   ├── Studentdashboard.java
            │           │   └── StudyRoomView.java
            │           │
            │           ├── Application.java
            │           ├── Main.java
            │           └── Stage.java
            │
            └── resources/
                └── Assets/
                    └── Images/
```

---

## 🛠️ Technology Stack

### Programming Language

* **Java 17**

### Desktop UI

* **JavaFX**

### Backend & Cloud

* **Firebase**
* **Firebase Authentication**
* **Firebase Admin SDK**

### Build & Dependency Management

* **Apache Maven**

### Data & Utilities

* **GSON**
* **JSON**

### QR & Camera

* **ZXing**
* **Webcam Capture**

---

## 🔄 Application Workflow

A typical student workflow is:

```text
                    ┌───────────────┐
                    │     Sign Up   │
                    └───────┬───────┘
                            │
                            ▼
                    ┌───────────────┐
                    │    Sign In    │
                    └───────┬───────┘
                            │
                            ▼
                    ┌────────────────┐
                    │ Student        │
                    │ Dashboard      │
                    └───────┬────────┘
                            │
       ┌────────────┬───────┼────────┬─────────────┐
       ▼            ▼       ▼        ▼             ▼
    Booking       Fees     Mess     Gym        Laundry
       │            │       │        │             │
       └────────────┴───────┼────────┴─────────────┘
                            │
                            ▼
                 ┌────────────────────┐
                 │ Hostel Services    │
                 │ & Support          │
                 └────────────────────┘
```

Additional services include:

* Complaints
* Feedback
* Study rooms
* Events
* Emergency assistance
* Student admission
* Fee receipts

---

## 🔥 Firebase Integration

HostelVista integrates Firebase for application services such as authentication and cloud-backed functionality.

The project contains dedicated Firebase service classes:

```text
Authentication/
├── FirebaseAuthService.java
├── FirebaseAuthService1.java
└── FirebaseInitializer.java
```

This separates authentication-related functionality from the JavaFX presentation layer.

---

## 🖥️ User Interface

The application uses JavaFX to provide a desktop-based graphical interface.

The UI includes dedicated screens for:

* Authentication
* Student dashboard
* Admissions
* Room booking
* Fees
* Mess
* Gym
* Laundry
* Study rooms
* Complaints
* Feedback
* Events
* Emergency assistance

The application also includes custom visual assets and dashboard-oriented interfaces.

---

## ⚙️ Getting Started

### Prerequisites

Make sure you have installed:

* **JDK 17**
* **Apache Maven**
* **JavaFX**
* A Firebase project
* Git

Verify Java:

```bash
java -version
```

Verify Maven:

```bash
mvn -version
```

---

## 📥 Clone the Repository

```bash
git clone <YOUR-GITHUB-REPOSITORY-URL>
cd HostelVista
```

Navigate to the Maven application:

```bash
cd app
```

---

## 📦 Install Dependencies

```bash
mvn clean install
```

---

## ▶️ Run the Application

The application starts from:

```text
com.hostelvista.Main
```

The JavaFX application opens with the HostelVista sign-in interface.

> **Note:** JavaFX runtime configuration may be required depending on your operating system and IDE setup.

---

## 🔐 Firebase Configuration

Before running the application, configure your Firebase project and authentication services.

### Important Security Notice

**Do not commit Firebase service-account credentials or private configuration files to GitHub.**

For example, files such as:

```text
serviceAccountKey.json
```

should **not** be publicly exposed.

Instead:

1. Remove sensitive credentials from the repository.
2. Add credential files to `.gitignore`.
3. Rotate/revoke any credentials that have already been exposed.
4. Load configuration securely through environment variables or local configuration.

Example `.gitignore` entry:

```gitignore
serviceAccountKey.json
*.json
.env
```

> Only ignore JSON files globally if your project does not require other JSON assets. Prefer ignoring the specific credential file.

---

## 🧠 Technical Concepts Demonstrated

HostelVista demonstrates practical implementation of:

* Object-oriented programming
* Java desktop application development
* JavaFX UI development
* MVC-style application architecture
* Event-driven programming
* Firebase integration
* Authentication
* Cloud service integration
* REST/API communication
* Maven dependency management
* QR code processing
* Webcam integration
* Form validation
* Modular controller design
* Reusable view components
* Student service workflows

---

## 🎯 What This Project Demonstrates to Recruiters

HostelVista showcases experience beyond basic CRUD development.

The project demonstrates the ability to design a **multi-feature desktop application** that combines:

**UI + Business Logic + Authentication + Cloud Services + External Libraries + Real-World Workflows**

It also demonstrates experience working with a larger Java codebase organized into separate:

* Models
* Views
* Controllers
* Authentication services
* Application entry points

---

## 📌 Project Status

**✅ Completed**

HostelVista is a completed desktop hostel-management application designed to centralize and simplify student hostel services.

---

## 👩‍💻 Project

**HostelVista — Smart Hostel Management System**

A Java desktop application focused on improving hostel administration and providing students with convenient access to essential hostel services.

---

## 📄 License

This project was developed as an academic/portfolio project.
