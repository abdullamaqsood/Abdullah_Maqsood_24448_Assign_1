package com.mycompany.hr;

import java.util.Date;

// This refactored code follows the Open/Closed Principle (OCP) by ensuring that each responsibility 
// is handled separately, making the system extensible without modifying existing code. 
// Employee_OCP only contains employee attributes, while salary computation, tax calculation, 
// time tracking, and leave management are handled by dedicated classes/interfaces. 
// Dependency Injection is implemented in SalaryCalculator to allow dynamic tax policies, 
// and an interface for leave management enables flexible leave policies.

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

interface TaxCalculator {
    double calculateTax(double salary);
}

class StandardTaxCalculator implements TaxCalculator {
    private double taxRate;

    public StandardTaxCalculator(double taxRate) {
        this.taxRate = taxRate;
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

class TimeTracker {
    private boolean isClockedIn;
    private Date lastClockIn;

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
}

interface LeavePolicy {
    boolean requestLeave(String employeeName, int days);
    int getLeaveBalance();
}

class StandardLeaveManager implements LeavePolicy {
    private int leaveBalance;

    public StandardLeaveManager(int initialLeaveBalance) {
        this.leaveBalance = initialLeaveBalance;
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

    @Override
    public int getLeaveBalance() {
        return leaveBalance;
    }
}

class SickLeaveManager implements LeavePolicy {
    private int sickLeaveBalance;

    public SickLeaveManager(int initialSickLeave) {
        this.sickLeaveBalance = initialSickLeave;
    }

    @Override
    public boolean requestLeave(String employeeName, int days) {
        if (sickLeaveBalance >= days) {
            sickLeaveBalance -= days;
            System.out.println(employeeName + " took " + days + " days of sick leave. Remaining: " + sickLeaveBalance);
            return true;
        } else {
            System.out.println("Insufficient sick leave balance.");
            return false;
        }
    }

    @Override
    public int getLeaveBalance() {
        return sickLeaveBalance;
    }
}
