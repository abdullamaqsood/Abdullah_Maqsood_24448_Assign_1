package com.mycompany.frontend;

import java.io.Serializable;
import java.util.ArrayList;

// The refactored version follows SRP by splitting responsibilities into separate classes: 
// 'Patient' for personal information, 'Billing' for financial transactions, 
// and 'MedicalRecords' for handling patient history. This separation improves maintainability, 
// reusability, and ensures that each class focuses on a single, well-defined responsibility.

class Patient implements Serializable {
    private String name;
    private String age;
    private String phone;
    private String email;
    private String gender;
    private String bgroup;
    private int id;
    private int pass;

    public Patient(String name, String age, String phone, String email, String gender, String bgroup, int id,
            int pass) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.bgroup = bgroup;
        this.id = id;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getBgroup() {
        return bgroup;
    }

    public int getID() {
        return id;
    }

    public int getPass() {
        return pass;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" + "Name: " + name + "\n" +
                "Age: " + age + "\n" + "Phone: " + phone + "\n" +
                "Email: " + email + "\n" + "Gender: " + gender + "\n" +
                "Blood Group: " + bgroup;
    }
}

class Billing {
    private double fees;

    public Billing() {
        this.fees = 0.0;
    }

    public void addFees(double amount) {
        this.fees += amount;
    }

    public double getFees() {
        return fees;
    }
}

class MedicalRecords {
    private ArrayList<String> records;

    public MedicalRecords() {
        this.records = new ArrayList<>();
    }

    public void addRecord(String doctorName, String day) {
        records.add(doctorName + "," + day);
    }

    public String getRecords() {
        return String.join("\n", records);
    }
}
