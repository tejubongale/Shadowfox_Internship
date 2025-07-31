#  Inventory Management System with Login & Signup (Java Swing)

A clean and colorful **Java Swing-based Inventory Management System** that allows users to manage stock after securely logging in. Built for students and mini-projects with a focus on UI, CRUD operations, and authentication.

---

## Features

- **Login and Signup System**  
  Simple authentication using a text file (`users.txt`) to store usernames and passwords.

- **Inventory Management**  
  Add, Update, Delete items with name, quantity, and price.

-**Search Functionality**  
  Filter inventory in real-time using a search bar.

-**Attractive User Interface**  
  Built using Java Swing with a colorful and structured layout.

---

## 📁 Project Structure

InventoryProject/
├── InventoryApp.java # Main inventory GUI
├── InventoryManager.java # Inventory logic (add, update, delete, search)
├── InventoryItem.java # Model class for inventory items
├── LoginSignupSystem.java # Login & signup UI
└── users.txt # Auto-generated file to store user credentials



---

## How to Run

### 1️.Compile all Java files
Open terminal / command prompt in the folder and run:
```bash
javac *.java


2.Start the application-->

java LoginSignupSystem


How to Use
Sign Up with a new username and password.

Login using the same credentials.

The inventory interface opens:

-Add new inventory items

-Update existing items

-Delete unwanted items

-Search by item name

All inventory data is stored temporarily (in-memory), and user credentials are saved in users.txt.

🛠️ Tech Stack
Language: Java

UI Library: Swing (javax.swing, java.awt)

Data Storage: In-memory for inventory; text file for users

Ideal For
Academic mini-projects

Beginners learning Java Swing

GUI + File handling practice

Resume/portfolio showcase

Author
Tejaswini Bongale
B.Tech, Computer Science & Engineering
Presidency University, Bangalore

Last Updated: July 2025
Project Type: Java Desktop Application