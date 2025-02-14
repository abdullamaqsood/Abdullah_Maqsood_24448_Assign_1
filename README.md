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

## **3. OrderProcessing.java**
- The `OrderProcessing` class follows SRP by managing **only order-related operations**.
- It provides functionalities like creating, updating, canceling, and displaying orders.
- Since it does not handle payments, inventory, or user authentication, it remains focused and maintainable.

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
- The **original versions** of the `Employee`, `Library`, and `Patient` classes **violated SRP** by combining multiple concerns into a single class.
- The **refactored versions** improve maintainability and readability by ensuring **each class has a single, well-defined responsibility**.
- The `BankAccount` and `OrderProcessing` classes already followed SRP and did not require refactoring.
