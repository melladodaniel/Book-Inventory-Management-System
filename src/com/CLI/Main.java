package com.CLI;
import com.BIMS.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static BookInventory inventory = new BookInventory();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Book Inventory Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Update Book");
            System.out.println("4. Search Book");
            System.out.println("5. Print Inventory");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
        try {    
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    searchBook();
                    break;
                case 5:
                    inventory.printInventory();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        	} catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // Consume invalid input
            }
        	}
    }

    private static void addBook() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String ISBN = scanner.nextLine();
        System.out.print("Enter genre (FICTION, NON_FICTION, MYSTERY, ROMANCE): ");
        try {
        Genre genre = Genre.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Book book = new Book(title, author, ISBN, genre, price, quantity);
        if (inventory.addBook(book)) {
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Book with this ISBN already exists.");
        }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid genre! Please enter one of FICTION, NON_FICTION, MYSTERY, ROMANCE.");
            scanner.nextLine(); // Consume invalid input
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a valid number.");
            scanner.nextLine(); // Consume invalid input
        }
    }

    private static void removeBook() {
        System.out.print("Enter ISBN of the book to remove: ");
        String ISBN = scanner.nextLine();
        if (inventory.removeBook(ISBN)) {
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void updateBook() {
        System.out.print("Enter ISBN of the book to update: ");
        String ISBN = scanner.nextLine();
        Book existingBook = inventory.getBook(ISBN);
        if (existingBook == null) {
            System.out.println("Book not found.");
            return;
        }

        System.out.print("Enter new title (leave empty to keep current): ");
        String title = scanner.nextLine();
        if (!title.isEmpty()) {
            existingBook.setTitle(title);
        }

        System.out.print("Enter new author (leave empty to keep current): ");
        String author = scanner.nextLine();
        if (!author.isEmpty()) {
            existingBook.setAuthor(author);
        }

        System.out.print("Enter new genre (leave empty to keep current): ");
        String genre = scanner.nextLine();
        if (!genre.isEmpty()) {
        	 try {
            existingBook.setGenre(Genre.valueOf(genre.toUpperCase()));
             } catch (IllegalArgumentException e) {
                 System.out.println("Invalid genre! Please enter one of FICTION, NON_FICTION, MYSTERY, ROMANCE.");
             }
        }

        System.out.print("Enter new price (leave empty to keep current): ");
        String price = scanner.nextLine();
        if (!price.isEmpty()) {
        	try {
            existingBook.setPrice(Double.parseDouble(price));
            } catch (NumberFormatException e) {
                System.out.println("Invalid price! Please enter a valid number.");
            }
        }

        System.out.print("Enter new quantity (leave empty to keep current): ");
        String quantity = scanner.nextLine();
        if (!quantity.isEmpty()) {
        	try {
            existingBook.setQuantity(Integer.parseInt(quantity));
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity! Please enter a valid number.");
            }
        }

        inventory.updateBook(ISBN, existingBook);
        System.out.println("Book updated successfully.");
    }

    private static void searchBook() {
        System.out.println("Search by: 1. Title 2. Author 3. Genre 4. ISBN");
        int searchChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (searchChoice) {
            case 1:
                System.out.print("Enter title: ");
                String title = scanner.nextLine();
                inventory.searchByTitle(title).forEach(System.out::println);
                break;
            case 2:
                System.out.print("Enter author: ");
                String author = scanner.nextLine();
                inventory.searchByAuthor(author).forEach(System.out::println);
                break;
            case 3:
                System.out.print("Enter genre: ");
                try {
                Genre genre = Genre.valueOf(scanner.nextLine().toUpperCase());
                inventory.searchByGenre(genre).forEach(System.out::println);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid genre! Please enter one of FICTION, NON_FICTION, MYSTERY, ROMANCE.");
                }
                break;
            case 4:
                System.out.print("Enter ISBN: ");
                String ISBN = scanner.nextLine();
                System.out.println(inventory.searchByISBN(ISBN));
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
}

