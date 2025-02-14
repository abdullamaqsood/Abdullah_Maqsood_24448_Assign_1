# **README - SRP Analysis and Refactored Classes**

This repository contains five Java classes that demonstrate the **Single Responsibility Principle (SRP)**. Some of the original classes **violated SRP** by handling multiple concerns, and they have been **refactored** to follow SRP by separating responsibilities into distinct classes.

## **1. BankAccount.java**
- The `BankAccount` class follows SRP by managing **only bank account details and transactions**.
- It provides functionalities like deposits, withdrawals, and account closure.
- Since it does not handle authentication, notifications, or external payment processing, **no refactoring was needed**.

## **2. Employee.java**
- The original `Employee` class **violated SRP** by combining:
  - Employee personal details
  - Payroll processing (salary, tax, bonus)
  - Attendance tracking (clock-in, leave balance)
- The refactored version splits responsibilities into:
  - `EmployeeDetails` → Stores personal employee information.
  - `PayrollSystem` → Handles salary calculations and bonuses.
  - `AttendanceSystem` → Manages employee attendance and leave tracking.

## **3. HashGraph.java**
- The original `HashGraph` class **violated SRP** by managing:
  - Graph structure (adding/removing vertices and edges)
  - Graph traversal (searching and retrieving connections)
  - Pathfinding (finding the shortest path using BFS)
- The refactored version separates these concerns:
  - `Graph` → Manages the structure of the graph.
  - `GraphTraversal` → Handles searching for vertices and displaying adjacency lists.
  - `PathFinder` → Implements pathfinding algorithms like BFS.

## **4. Library.java**
- The original `Library` class **violated SRP** by mixing:
  - Book management (adding/removing books)
  - User management (registering/removing users)
  - Borrowing system (handling book loans and returns)
- The refactored version splits these concerns into:
  - `LibraryCatalog` → Manages book storage and availability.
  - `UserManagement` → Handles user registration and removal.
  - `BorrowingSystem` → Manages book borrowing and returns.

## **5. Patient.java**
- The original `Patient` class **violated SRP** by handling:
  - Patient personal information
  - Billing (managing fees)
  - Medical records (storing patient history)
- The refactored version splits these concerns into:
  - `Patient` → Stores personal patient information.
  - `Billing` → Manages financial transactions.
  - `MedicalRecords` → Handles patient history and records.

## **Conclusion**
- The **original versions** of the `Employee`, `HashGraph`, `Library`, and `Patient` classes **violated SRP** by combining multiple concerns into a single class.
- The **refactored versions** improve maintainability, readability, and scalability by ensuring **each class has a single, well-defined responsibility**.
- The `BankAccount` class already followed SRP and did not require refactoring.

Each refactored file now adheres to **SOLID design principles**, making the codebase **more modular and easier to extend**. 🚀
