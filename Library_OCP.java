package com.mycompany.library;

import java.util.ArrayList;

// This refactored version follows the Open/Closed Principle (OCP) by structuring the system to be easily extendable 
// without modifying existing code. Interfaces for BookManager, UserManager, and BorrowingService allow new implementations 
// to be added seamlessly. Library_OCP uses dependency injection to improve flexibility, making it easier to replace or update 
// components as needed. This ensures the system remains modular, scalable, and adaptable to future changes.


public class Library_OCP {
    private BookManager bookManager;
    private UserManager userManager;
    private BorrowingService borrowingService;

    public Library_OCP(BookManager bookManager, UserManager userManager, BorrowingService borrowingService) {
        this.bookManager = bookManager;
        this.userManager = userManager;
        this.borrowingService = borrowingService;
    }

    public BookManager getBookManager() {
        return bookManager;
    }

    public void setBookManager(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public BorrowingService getBorrowingService() {
        return borrowingService;
    }

    public void setBorrowingService(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }
}


interface BookManager {
    void addBook(Book book);
    void removeBook(Book book);
    boolean isBookAvailable(Book book);
    void borrowBook(Book book);
    void returnBook(Book book);
}


class DefaultBookManager implements BookManager {
    private ArrayList<Book> books;

    public DefaultBookManager() {
        books = new ArrayList<>();
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public void removeBook(Book book) {
        books.remove(book);
    }

    @Override
    public boolean isBookAvailable(Book book) {
        return books.contains(book);
    }

    @Override
    public void borrowBook(Book book) {
        books.remove(book);
    }

    @Override
    public void returnBook(Book book) {
        books.add(book);
    }
}


interface UserManager {
    void addUser(User user);
    void removeUser(User user);
    boolean isUserRegistered(User user);
}


class DefaultUserManager implements UserManager {
    private ArrayList<User> users;

    public DefaultUserManager() {
        users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void removeUser(User user) {
        users.remove(user);
    }

    @Override
    public boolean isUserRegistered(User user) {
        return users.contains(user);
    }
}


interface BorrowingService {
    void borrowBook(User user, Book book);
    void returnBook(User user, Book book);
}


class DefaultBorrowingService implements BorrowingService {
    private ArrayList<String> borrowedBooks;
    private BookManager bookManager;

    public DefaultBorrowingService(BookManager bookManager) {
        this.bookManager = bookManager;
        borrowedBooks = new ArrayList<>();
    }

    public void setBookManager(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    @Override
    public void borrowBook(User user, Book book) {
        if (bookManager.isBookAvailable(book)) {
            borrowedBooks.add(book.getTitle());
            bookManager.borrowBook(book);
            System.out.println(user.getName() + " borrowed " + book.getTitle());
        } else {
            System.out.println("Book not available.");
        }
    }

    @Override
    public void returnBook(User user, Book book) {
        if (borrowedBooks.contains(book.getTitle())) {
            bookManager.returnBook(book);
            borrowedBooks.remove(book.getTitle());
            System.out.println(user.getName() + " returned " + book.getTitle());
        } else {
            System.out.println("This book was not borrowed.");
        }
    }
}
