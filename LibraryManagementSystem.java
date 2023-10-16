import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

class Book {
    private String title;
    private String author;
    private int bookId;
    private boolean isAvailable;

    public Book(String title, String author, int bookId) {
        this.title = title;
        this.author = author;
        this.bookId = bookId;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBookId() {
        return bookId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void checkOut() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("Book checked out successfully.");
        } else {
            System.out.println("Book is already checked out.");
        }
    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Book is already available in the library.");
        }
    }

    // Other methods and getters

    @Override
    public String toString() {
        return "Book [Title=" + title + ", Author=" + author + ", BookID=" + bookId + ", Available=" + isAvailable + "]";
    }
}

class Library {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void listBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public Book findBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }
}

class User {
    public void browseBooks(Library library) {
        library.listBooks();
    }

    public Book searchBook(Library library, String query) {
        for (Book book : library.getBooks()) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase())) {
                return book;
            }
        }
        return null;
    }

    public void issueBook(Book book) {
        if (book != null) {
            if (book.isAvailable()) {
                book.checkOut();
            } else {
                System.out.println("Book is already checked out.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    public void returnBook(Book book) {
        if (book != null) {
            if (!book.isAvailable()) {
                book.returnBook();
            } else {
                System.out.println("Book is already available in the library.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    public void sendQuery(String query, HashMap<String, String> adminEmails) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your email address: ");
        String userEmail = scanner.nextLine();

        for (String adminEmail : adminEmails.keySet()) {
            System.out.println("Sending query to admin: " + adminEmails.get(adminEmail));
            // You can implement actual email functionality here or use a library for sending emails.
        }
    }
}

class Admin {
    public void addBookToLibrary(Library library, Book book) {
        library.addBook(book);
        System.out.println("Book added successfully.");
    }

    public void deleteBookFromLibrary(Library library, int bookId) {
        Book book = library.findBook(bookId);
        if (book != null) {
            library.removeBook(book);
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void updateBook(Library library, int bookId, String title, String author) {
       Book book = library.findBook(bookId);
       if (book != null) {
           book.setTitle(title);
           book.setAuthor(author);
        System.out.println("Book information updated successfully.");
    } else {
        System.out.println("Book not found.");
    }
}

    public void issueBook(Book book) {
        if (book != null) {
            if (book.isAvailable()) {
                book.checkOut();
            } else {
                System.out.println("Book is already checked out.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    public void returnBook(Book book) {
        if (book != null) {
            if (!book.isAvailable()) {
                book.returnBook();
            } else {
                System.out.println("Book is already available in the library.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        User user = new User();
        Admin admin = new Admin();
        HashMap<String, String> adminEmails = new HashMap<>();
        adminEmails.put("admin1@example.com", "Admin 1");
        adminEmails.put("admin2@example.com", "Admin 2");

        Book book1 = new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 1);
        Book book2 = new Book("The Hobbit", "J.R.R. Tolkien", 2);

        library.addBook(book1);
        library.addBook(book2);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Library Management System");
            System.out.println("1. Browse Books");
            System.out.println("2. Search for a Book");
            System.out.println("3. Issue a Book");
            System.out.println("4. Return a Book");
            System.out.println("5. Send Query to Admin");
            System.out.println("6. Admin Menu");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    user.browseBooks(library);
                    break;
                case 2:
                    System.out.print("Enter a search query: ");
                    String query = scanner.next();
                    Book foundBook = user.searchBook(library, query);
                    if (foundBook != null) {
                        System.out.println("Found Book: " + foundBook);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Book ID to issue: ");
                    int issueId = scanner.nextInt();
                    Book issueBook = library.findBook(issueId);
                    admin.issueBook(issueBook);
                    break;
                case 4:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = scanner.nextInt();
                    Book returnBook = library.findBook(returnId);
                    admin.returnBook(returnBook);
                    break;
                case 5:
                    System.out.print("Enter your query: ");
                    String userQuery = scanner.next();
                    user.sendQuery(userQuery, adminEmails);
                    break;
                case 6:
                    System.out.println("Admin Menu");
                    System.out.println("1. Add Book");
                    System.out.println("2. Delete Book");
                    System.out.println("3. Update Book");
                    System.out.println("4. Exit Admin Menu");
                    System.out.print("Enter your choice: ");

                    int adminChoice = scanner.nextInt();

                    switch (adminChoice) {
                        case 1:
                             Scanner bookInfoScanner = new Scanner(System.in);
                             System.out.print("Enter Book Title: ");
                             String title = bookInfoScanner.nextLine();
                             System.out.print("Enter Author: ");
                             String author = bookInfoScanner.nextLine();
                             System.out.print("Enter Book ID: ");
                             int bookId = bookInfoScanner.nextInt();
                             Book newBook = new Book(title, author, bookId);
                             admin.addBookToLibrary(library, newBook);
                             break;
                        case 2:
                            System.out.print("Enter Book ID to delete: ");
                            int deleteId = scanner.nextInt();
                            admin.deleteBookFromLibrary(library, deleteId);
                            break;
                        case 3:
                            System.out.print("Enter Book ID to update: ");
                            int updateId = scanner.nextInt();
                            System.out.print("Enter new Title: ");
                            String newTitle = scanner.next();
                            System.out.print("Enter new Author: ");
                            String newAuthor = scanner.next();
                            admin.updateBook(library, updateId, newTitle, newAuthor);
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;
                case 7:
                    System.out.println("Exiting Library Management System.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
