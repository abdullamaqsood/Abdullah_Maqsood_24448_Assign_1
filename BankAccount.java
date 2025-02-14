package com.mycompany.banking;

// The 'BankAccount' class follows the Single Responsibility Principle (SRP) because 
// it focuses solely on managing bank account details and transactions. 
// It does not handle unrelated concerns such as user authentication, notifications, 
// or external payment processing.

public class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private String accountType;
    private boolean isActive;

    public BankAccount(String accountNumber, String accountHolder, double initialBalance, String accountType) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.accountType = accountType;
        this.isActive = true; 
    }


    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolder() { return accountHolder; }
    public double getBalance() { return balance; }
    public String getAccountType() { return accountType; }
    public boolean isActive() { return isActive; }


    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("$" + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }


    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("$" + amount + " withdrawn successfully.");
            return true;
        } else {
            System.out.println("Insufficient balance or invalid amount.");
            return false;
        }
    }


    public void closeAccount() {
        isActive = false;
        System.out.println("Account " + accountNumber + " has been closed.");
    }


    @Override
    public String toString() {
        return "Account Number: " + accountNumber + "\n" +
               "Account Holder: " + accountHolder + "\n" +
               "Balance: $" + balance + "\n" +
               "Account Type: " + accountType + "\n" +
               "Status: " + (isActive ? "Active" : "Closed");
    }
}
