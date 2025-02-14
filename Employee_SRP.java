package com.mycompany.hr;

import java.util.Date;

// The refactored version follows SRP by splitting concerns into separate classes: 
// 'EmployeeDetails' for personal information, 'PayrollSystem' for salary and tax calculations, 
// and 'AttendanceSystem' for tracking clock-ins and leave. This improves modularity, 
// maintainability, and scalability by ensuring that each class has a single responsibility.

class EmployeeDetails {
    private String name;
    private int id;
    private String department;
    private String designation;

    public EmployeeDetails(String name, int id, String department, String designation) {
        this.name = name;
        this.id = id;
        this.department = department;
        this.designation = designation;
    }


    public String getName() { return name; }
    public int getId() { return id; }
    public String getDepartment() { return department; }
    public String getDesignation() { return designation; }


    @Override
    public String toString() {
        return "Employee ID: " + id + "\n" +
               "Name: " + name + "\n" +
               "Department: " + department + "\n" +
               "Designation: " + designation;
    }
}


class PayrollSystem {
    private double salary;
    private double taxRate;
    private double bonus;

    public PayrollSystem(double salary, double taxRate) {
        this.salary = salary;
        this.taxRate = taxRate;
        this.bonus = 0.0;
    }

    public double calculateTax() {
        return salary * taxRate;
    }

    public double calculateNetSalary() {
        return salary - calculateTax() + bonus;
    }

    public void applyBonus(double amount) {
        this.bonus += amount;
    }


    public double getSalary() { return salary; }
    public double getTaxRate() { return taxRate; }
    public double getBonus() { return bonus; }

    @Override
    public String toString() {
        return "Salary: $" + salary + "\n" +
               "Net Salary: $" + calculateNetSalary() + "\n" +
               "Bonus: $" + bonus;
    }
}


class AttendanceSystem {
    private boolean isClockedIn;
    private Date lastClockIn;
    private int leaveBalance;

    public AttendanceSystem() {
        this.isClockedIn = false;
        this.leaveBalance = 20; 
    }

    public void clockIn(String employeeName) {
        isClockedIn = true;
        lastClockIn = new Date();
        System.out.println(employeeName + " clocked in at " + lastClockIn);
    }

    public void clockOut(String employeeName) {
        if (isClockedIn) {
            isClockedIn = false;
            System.out.println(employeeName + " clocked out.");
        } else {
            System.out.println(employeeName + " was not clocked in.");
        }
    }

    public void takeLeave(String employeeName, int days) {
        if (leaveBalance >= days) {
            leaveBalance -= days;
            System.out.println(employeeName + " took " + days + " days leave. Remaining: " + leaveBalance);
        } else {
            System.out.println("Insufficient leave balance.");
        }
    }


    public int getLeaveBalance() { return leaveBalance; }
    public boolean isClockedIn() { return isClockedIn; }

    @Override
    public String toString() {
        return "Leave Balance: " + leaveBalance + " days\n" +
               "Clocked In: " + (isClockedIn ? "Yes" : "No");
    }
}
