package com.mycompany.hr;

import java.util.Date;

// The original 'Employee' class violates SRP because it combines multiple responsibilities: 
// employee information management, payroll processing, and attendance tracking. 
// Changes in salary calculations or leave policies would require modifying the same class, 
// making it harder to maintain and extend.

public class Employee {
    private String name;
    private int id;
    private String department;
    private String designation;
    private double salary;
    private double taxRate;
    private double bonus;
    private int leaveBalance;
    private boolean isClockedIn;
    private Date lastClockIn;

    public Employee(String name, int id, String department, String designation, double salary, double taxRate) {
        this.name = name;
        this.id = id;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
        this.taxRate = taxRate;
        this.bonus = 0.0;
        this.leaveBalance = 20; 
        this.isClockedIn = false;
    }


    public String getName() { return name; }
    public int getId() { return id; }
    public String getDepartment() { return department; }
    public String getDesignation() { return designation; }
    public double getSalary() { return salary; }


    public double calculateTax() {
        return salary * taxRate;
    }

    public double calculateNetSalary() {
        return salary - calculateTax() + bonus;
    }

    public void applyBonus(double amount) {
        this.bonus += amount;
    }


    public void clockIn() {
        isClockedIn = true;
        lastClockIn = new Date();
        System.out.println(name + " clocked in at " + lastClockIn);
    }

    public void clockOut() {
        if (isClockedIn) {
            isClockedIn = false;
            System.out.println(name + " clocked out.");
        } else {
            System.out.println(name + " was not clocked in.");
        }
    }

    public void takeLeave(int days) {
        if (leaveBalance >= days) {
            leaveBalance -= days;
            System.out.println(name + " took " + days + " days leave. Remaining: " + leaveBalance);
        } else {
            System.out.println("Insufficient leave balance.");
        }
    }


    @Override
    public String toString() {
        return "Employee ID: " + id + "\n" +
               "Name: " + name + "\n" +
               "Department: " + department + "\n" +
               "Designation: " + designation + "\n" +
               "Salary: $" + salary + "\n" +
               "Net Salary: $" + calculateNetSalary() + "\n" +
               "Leave Balance: " + leaveBalance + " days\n" +
               "Clocked In: " + (isClockedIn ? "Yes" : "No");
    }
}
