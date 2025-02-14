package com.mycompany.library;

import java.util.ArrayList;

// The refactored version follows SRP by splitting responsibilities into distinct classes: 
// 'LibraryCatalog' for book management, 'UserManagement' for handling users, 
// and 'BorrowingSystem' for managing book lending. This modular design improves maintainability, 
// flexibility, and ensures each class has a single well-defined responsibility.

class LibraryCatalog {
    private ArrayList<Book> books;

    public LibraryCatalog() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public boolean isBookAvailable(Book book) {
        return books.contains(book);
    }
}

class UserManagement {
    private ArrayList<User> users;

    public UserManagement() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }
}

class BorrowingSystem {
    private LibraryCatalog catalog;
    private ArrayList<String> borrowedBooks;

    public BorrowingSystem(LibraryCatalog catalog) {
        this.catalog = catalog;
        borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(User user, Book book) {
        if (catalog.isBookAvailable(book)) {
            borrowedBooks.add(book.getTitle());
            catalog.removeBook(book);
            System.out.println(user.getName() + " borrowed " + book.getTitle());
        } else {
            System.out.println("Book not available.");
        }
    }

    public void returnBook(User user, Book book) {
        if (borrowedBooks.contains(book.getTitle())) {
            catalog.addBook(book);
            borrowedBooks.remove(book.getTitle());
            System.out.println(user.getName() + " returned " + book.getTitle());
        } else {
            System.out.println("This book was not borrowed.");
        }
    }
}
