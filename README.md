# **README - Employee and Library System Refactoring**  

## **1. Employee Management System (`Employee_OCP`)**  
### **Problem**  
The original `Employee` class was trying to do too much at once—handling employee details, tax calculations, salary processing, time tracking, and leave management, all in a single place. This made it difficult to introduce changes, like adding a new tax policy or a different leave structure, without modifying the core code. Over time, this would make the system harder to maintain and scale, breaking the Open/Closed Principle (OCP).  

### **Solution**  
To fix this, I broke the class into smaller, dedicated components:  
- **`Employee_OCP`** → Stores only employee-related details.  
- **`TaxCalculator` Interface** → Allows for different tax policies to be applied dynamically.  
- **`SalaryCalculator`** → Manages salary and bonuses separately.  
- **`TimeTracker`** → Handles clock-in and clock-out operations.  
- **`LeavePolicy` Interface** → Supports different types of leave policies, such as sick leave and vacation leave.  

### **Why This is Better**  
Now, the system is much easier to extend. If a new tax rule or leave policy needs to be introduced, it can be done without modifying the core employee class. Each component focuses on a single responsibility, making the code cleaner and easier to manage. On top of that, salary and leave rules can be changed independently, allowing businesses to adapt quickly without disrupting the entire system.  

---  

## **2. Library Management System (`Library_OCP`)**  
### **Problem**  
The original `Library` class had everything thrown into one place—managing books, users, and borrowing transactions. This made it messy and difficult to scale. If we wanted to introduce new features like digital books, premium memberships, or late return penalties, we would have had to modify the entire class. This not only made the code fragile but also violated OCP by making future changes more complex than they needed to be.  

### **Solution**  
The new design embraces **interfaces and dependency injection** to keep things modular:  
- **`Library_OCP`** → Acts as a controller, handling dependencies instead of logic.  
- **`BookManager` Interface** → Keeps book-related operations separate.  
- **`UserManager` Interface** → Manages user accounts independently.  
- **`BorrowingService` Interface** → Handles different borrowing policies.  
- **`DefaultBookManager`, `DefaultUserManager`, `DefaultBorrowingService`** → Provide basic implementations, which can be replaced as needed.  

### **Why This is Better**  
With this new approach, the system is scalable and modular. If we want to integrate a database-backed book manager or introduce new borrowing rules, we can do so without breaking existing functionality. The system also allows flexible borrowing policies, meaning we can easily introduce premium memberships, late fees, or digital book lending.

---  

## **Final Thoughts**  
Both systems are now clean, modular, and ready for the future. By following OCP and using dependency injection, I have made it so that new features can be added without rewriting existing code.