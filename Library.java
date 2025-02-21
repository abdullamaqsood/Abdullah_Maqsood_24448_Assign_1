package com.mycompany.library;

import java.util.ArrayList;

// This class violates the Open/Closed Principle (OCP) because it combines multiple responsibilities: 
// book management, user management, and borrowing transactions. Any modifications, such as adding 
// new borrowing rules or different types of users, would require changing this class, making it harder 
// to extend and maintain.

public class Library {
    private ArrayList<Book> books;
    private ArrayList<User> users;
    private ArrayList<String> borrowedBooks;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        borrowedBooks = new ArrayList<>();
    }


    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }


    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }


    public void borrowBook(User user, Book book) {
        if (books.contains(book)) {
            borrowedBooks.add(book.getTitle());
            books.remove(book);
            System.out.println(user.getName() + " borrowed " + book.getTitle());
        } else {
            System.out.println("Book not available.");
        }
    }

    public void returnBook(User user, Book book) {
        if (borrowedBooks.contains(book.getTitle())) {
            books.add(book);
            borrowedBooks.remove(book.getTitle());
            System.out.println(user.getName() + " returned " + book.getTitle());
        } else {
            System.out.println("This book was not borrowed.");
        }
    }
}
