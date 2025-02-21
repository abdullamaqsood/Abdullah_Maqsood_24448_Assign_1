package com.mycompany.library;

import java.util.ArrayList;

// This refactored version follows the Open/Closed Principle (OCP) by structuring the system to be easily extendable 
// without modifying existing code. Abstract classes for BookManager, UserManager, and BorrowingService provide 
// a flexible foundation for different implementations while maintaining common functionality. Library_OCP uses 
// dependency injection to improve flexibility, making it easier to replace or update components as needed. 
// This ensures the system remains modular, scalable, and adaptable to future changes.

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

abstract class BookManager {
    protected ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public boolean isBookAvailable(Book book) {
        return books.contains(book);
    }

    public abstract void borrowBook(Book book);
    public abstract void returnBook(Book book);
}

class DefaultBookManager extends BookManager {
    @Override
    public void borrowBook(Book book) {
        books.remove(book);
    }

    @Override
    public void returnBook(Book book) {
        books.add(book);
    }
}

abstract class UserManager {
    protected ArrayList<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public boolean isUserRegistered(User user) {
        return users.contains(user);
    }
}

class DefaultUserManager extends UserManager {}

abstract class BorrowingService {
    protected ArrayList<String> borrowedBooks = new ArrayList<>();
    protected BookManager bookManager;

    public BorrowingService(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    public abstract void borrowBook(User user, Book book);
    public abstract void returnBook(User user, Book book);
}

class DefaultBorrowingService extends BorrowingService {
    public DefaultBorrowingService(BookManager bookManager) {
        super(bookManager);
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
