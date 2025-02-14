/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.frontend;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Abdullah Maqsood
 */

// The original 'Patient' class violates SRP as it combines multiple responsibilities: 
// managing personal details, handling billing, and storing medical records. 
// This makes the class harder to maintain and modify, as changes in financial 
// or medical record management would require modifying the patient data structure.

public class Patient implements Serializable {
    private String name;
    private String age;
    private String phone;
    private String email;
    private String gender;
    private String bgroup;
    private int id;
    private int pass;
    private ArrayList<String> record = new ArrayList<>();
    private double fees = 0.0;

    public Patient(String a, String b, String c, String d, String e, String f, int g, int z) {
        name = a;
        age = b;
        phone = c;
        email = d;
        gender = e;
        bgroup = f;
        id = g;
        pass = z;
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

    public double getFees() {
        return fees;
    }

    public int getPass() {
        return pass;
    }

    public void setFees(double a) {
        fees = fees + a;
    }

    public void addRecord(String docname, String day) {
        record.add(docname + "," + day + ",");
    }

    public String getRecord() {
        String z = "";
        for (int i = 0; i < record.size(); i++) {
            z += record.get(i);
        }
        return z;
    }

    public String toString() {
        return "ID: " + id + "\n" + "Name: " + name + "\n" + "Age: " + age + "\n" + "Phone: " + phone + "\n" + "Email: "
                + email + "\n" + "Gender: " + gender + "\n" + "Blood Group: " + bgroup;
    }

}
