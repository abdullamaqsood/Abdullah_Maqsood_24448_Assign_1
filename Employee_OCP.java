package com.mycompany.hr;

import java.util.Date;

// This refactored version follows the Open/Closed Principle (OCP) by ensuring that each responsibility 
// is handled separately, making the system extensible without modifying existing code. 
// Abstract classes for tax calculation, salary computation, time tracking, and leave management provide 
// a structured foundation while allowing different implementations to be added seamlessly. 
// Dependency Injection is implemented in SalaryCalculator to allow dynamic tax policies, 
// and abstract classes for leave management enable flexible leave policies.

public class Employee_OCP {
    protected String name;
    protected int id;
    protected String department;
    protected String designation;
    protected double salary;

    public Employee_OCP(String name, int id, String department, String designation, double salary) {
        this.name = name;
        this.id = id;
        this.department = department;
        this.designation = designation;
        this.salary = salary;
    }

    public String getName() { return name; }
    public int getId() { return id; }
    public String getDepartment() { return department; }
    public String getDesignation() { return designation; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return "Employee ID: " + id + "\n" +
               "Name: " + name + "\n" +
               "Department: " + department + "\n" +
               "Designation: " + designation + "\n" +
               "Salary: $" + salary;
    }
}

abstract class TaxCalculator {
    protected double taxRate;

    public TaxCalculator(double taxRate) {
        this.taxRate = taxRate;
    }

    public abstract double calculateTax(double salary);
}

class StandardTaxCalculator extends TaxCalculator {
    public StandardTaxCalculator(double taxRate) {
        super(taxRate);
    }

    @Override
    public double calculateTax(double salary) {
        return salary * taxRate;
    }
}

class SalaryCalculator {
    private TaxCalculator taxCalculator;
    private double bonus;

    public SalaryCalculator(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
        this.bonus = 0.0;
    }

    public void setTaxCalculator(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    public void applyBonus(double amount) {
        this.bonus += amount;
    }

    public double calculateNetSalary(double baseSalary) {
        return baseSalary - taxCalculator.calculateTax(baseSalary) + bonus;
    }
}

abstract class TimeTracker {
    protected boolean isClockedIn;
    protected Date lastClockIn;

    public abstract void clockIn(String employeeName);
    public abstract void clockOut(String employeeName);
}

class DefaultTimeTracker extends TimeTracker {
    @Override
    public void clockIn(String employeeName) {
        isClockedIn = true;
        lastClockIn = new Date();
        System.out.println(employeeName + " clocked in at " + lastClockIn);
    }

    @Override
    public void clockOut(String employeeName) {
        if (isClockedIn) {
            isClockedIn = false;
            System.out.println(employeeName + " clocked out.");
        } else {
            System.out.println(employeeName + " was not clocked in.");
        }
    }
}

abstract class LeavePolicy {
    protected int leaveBalance;

    public LeavePolicy(int initialLeaveBalance) {
        this.leaveBalance = initialLeaveBalance;
    }

    public abstract boolean requestLeave(String employeeName, int days);
    public int getLeaveBalance() {
        return leaveBalance;
    }
}

class StandardLeaveManager extends LeavePolicy {
    public StandardLeaveManager(int initialLeaveBalance) {
        super(initialLeaveBalance);
    }

    @Override
    public boolean requestLeave(String employeeName, int days) {
        if (leaveBalance >= days) {
            leaveBalance -= days;
            System.out.println(employeeName + " took " + days + " days leave. Remaining: " + leaveBalance);
            return true;
        } else {
            System.out.println("Insufficient leave balance.");
            return false;
        }
    }
}

class SickLeaveManager extends LeavePolicy {
    public SickLeaveManager(int initialSickLeave) {
        super(initialSickLeave);
    }

    @Override
    public boolean requestLeave(String employeeName, int days) {
        if (leaveBalance >= days) {
            leaveBalance -= days;
            System.out.println(employeeName + " took " + days + " days of sick leave. Remaining: " + leaveBalance);
            return true;
        } else {
            System.out.println("Insufficient sick leave balance.");
            return false;
        }
    }
}
