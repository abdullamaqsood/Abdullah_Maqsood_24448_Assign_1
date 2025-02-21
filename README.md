# **README - Employee and Library System Refactoring**  

## **1. Employee Management System (`Employee_OCP`)**  
### **Problem**  
The original `Employee` class was overloaded with multiple responsibilities—it managed employee details, tax calculations, salary processing, time tracking, and leave management, all within a single class. This made it difficult to introduce changes, such as implementing a new tax policy or modifying leave structures, without directly modifying the core class. Over time, this led to a **rigid and difficult-to-maintain system**, violating the **Open/Closed Principle (OCP)** by requiring modifications to accommodate new features.  

### **Solution**  
To address this issue, the class was broken down into dedicated components that handle specific responsibilities:  
- **`Employee_OCP`** → Stores only essential employee attributes.  
- **`TaxCalculator` Abstract Class** → Allows for different tax policies to be implemented dynamically.  
- **`SalaryCalculator`** → Manages salary computations, including tax deductions and bonuses.  
- **`TimeTracker` Abstract Class** → Handles employee clock-in and clock-out functionality.  
- **`LeavePolicy` Abstract Class** → Supports different types of leave management, such as sick leave and vacation leave.  

### **Why This is Better**  
Now, the system is **highly extensible and adaptable**. New tax rules, salary structures, or leave policies can be introduced **without modifying existing code**, ensuring long-term maintainability. Each component has a **single responsibility**, making the system **more organized, modular, and easier to test**. Additionally, salary and leave policies can be adjusted independently, allowing businesses to **customize the system to their needs without introducing unnecessary complexity**.  

---

## **2. Library Management System (`Library_OCP`)**  
### **Problem**  
The original `Library` class was an **all-in-one** implementation, handling book management, user management, and borrowing transactions within a single class. This made the system **difficult to scale**, as adding new features—such as **digital books, premium memberships, or late return penalties**—required modifying the core class, violating **OCP** and making the system **prone to breaking existing functionality**.  

### **Solution**  
To create a **scalable and modular system**, the code was restructured into separate abstract classes:  
- **`Library_OCP`** → Serves as a central controller, managing dependencies without handling logic directly.  
- **`BookManager` Abstract Class** → Manages book-related operations, allowing different implementations for physical and digital books.  
- **`UserManager` Abstract Class** → Handles user account management separately.  
- **`BorrowingService` Abstract Class** → Supports different borrowing policies, ensuring future extensibility.    

### **Why This is Better**  
The new structure **ensures modularity**, making it easy to introduce new features **without modifying core components**. If we need to integrate a **database-backed book manager** or introduce **custom borrowing rules**, these can be implemented as new subclasses without affecting the rest of the system. This approach also allows **flexible borrowing policies**, making it simple to add **premium memberships, late return fees, or digital lending options** in the future.  

---

## **Final Thoughts**  
Both systems have been **cleanly refactored**, making them **modular, scalable, and future-proof**. By following the **Open/Closed Principle (OCP)** and using **dependency injection**, the code is now designed to **support new features without requiring modifications to existing logic**.