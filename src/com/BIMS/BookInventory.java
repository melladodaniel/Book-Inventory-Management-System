package com.BIMS;
import java.util.*;

public class BookInventory {
    private Map<String, Book> inventory;
    private Map<String, Double> pricing;

    public BookInventory() {
        inventory = new HashMap<>();
        pricing = new HashMap<>();
    }

    // Add a book to the inventory
    public boolean addBook(Book book) {
        if (inventory.containsKey(book.getISBN())) {
            return false; // Book with the same ISBN already exists
        }
        inventory.put(book.getISBN(), book);
        pricing.put(book.getISBN(), book.getPrice());
        return true;
    }

    // Remove a book from the inventory
    public boolean removeBook(String ISBN) {
        if (!inventory.containsKey(ISBN)) {
            return false;
        }
        inventory.remove(ISBN);
        pricing.remove(ISBN);
        return true;
    }

    // Update book information
    public boolean updateBook(String ISBN, Book updatedBook) {
        if (!inventory.containsKey(ISBN)) {
            return false;
        }
        inventory.put(ISBN, updatedBook);
        pricing.put(ISBN, updatedBook.getPrice());
        return true;
    }

    // Retrieve book details
    public Book getBook(String ISBN) {
        return inventory.get(ISBN);
    }

    // Search books by title
    public List<Book> searchByTitle(String title) {
        List<Book> results = new ArrayList<>();
        for (Book book : inventory.values()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                results.add(book);
            }
        }
        return results;
    }

    // Search books by author
    public List<Book> searchByAuthor(String author) {
        List<Book> results = new ArrayList<>();
        for (Book book : inventory.values()) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                results.add(book);
            }
        }
        return results;
    }

    // Search books by genre
    public List<Book> searchByGenre(Genre genre) {
        List<Book> results = new ArrayList<>();
        for (Book book : inventory.values()) {
            if (book.getGenre() == genre) {
                results.add(book);
            }
        }
        return results;
    }

    // Search books by ISBN
    public Book searchByISBN(String ISBN) {
        return inventory.get(ISBN);
    }

    // Print all books
    public void printInventory() {
        for (Book book : inventory.values()) {
            System.out.println(book);
        }
    }
}

