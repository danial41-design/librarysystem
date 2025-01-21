import java.util.List;
import java.util.Optional;

public class LibraryService {
    private LibraryDB libraryDB;

    public LibraryService(LibraryDB libraryDB) {
        this.libraryDB = libraryDB;
    }

    public void showAllBooks() {
        List<Book> books = libraryDB.getAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void takeBook(int id) {
        List<Book> books = libraryDB.getAllBooks();
        Optional<Book> bookOpt = books.stream().filter(book -> book.getId() == id).findFirst();

        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            if (book.getQuantity() > 0) {
                libraryDB.updateBookQuantity(id, book.getQuantity() - 1);
                System.out.println("You got: " + book.getName());
            } else {
                System.out.println("This book isn't available.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    public void returnBook(int id) {
        List<Book> books = libraryDB.getAllBooks();
        Optional<Book> bookOpt = books.stream().filter(book -> book.getId() == id).findFirst();

        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            libraryDB.updateBookQuantity(id, book.getQuantity() + 1);
            System.out.println("You gave book: " + book.getName());
        } else {
            System.out.println("Book not found.");
        }
    }
    public void addNewBook(String name, String author, int year, int quantity) {
        boolean success = libraryDB.addBook(name, author, year, quantity);
        if (success) {
            System.out.println("Book added successfully: " + name);
        } else {
            System.out.println("Book doesn't added.");
        }
    }

}
