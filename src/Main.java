import data.PostgresDB;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PostgresDB postgresDB = new PostgresDB(
                "jdbc:postgresql://localhost:5432",
                "postgres",
                "4584",
                "library"
        );
        Connection connection = postgresDB.getConnection();
        DatabaseInitializer.initializeDatabase(connection);
        LibraryDB libraryDB = new LibraryDB(postgresDB.getConnection());
        LibraryService libraryService = new LibraryService(libraryDB);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. show all books");
            System.out.println("2. get books");
            System.out.println("3. give a book");
            System.out.println("4. Add a new book");
            System.out.println("5. close");
            System.out.print("select action: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    libraryService.showAllBooks();
                    break;
                case 2:
                    System.out.print("input ID: ");
                    int takeId = scanner.nextInt();
                    libraryService.takeBook(takeId);
                    break;
                case 3:
                    System.out.print("input ID: ");
                    int returnId = scanner.nextInt();
                    libraryService.returnBook(returnId);
                    break;
                case 4:
                    System.out.print("name of book: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    System.out.print("autor of book: ");
                    String author = scanner.nextLine();
                    System.out.print("year of book: ");
                    int year = scanner.nextInt();
                    System.out.print("number of books: ");
                    int quantity = scanner.nextInt();
                    libraryService.addNewBook(name, author, year, quantity);
                    break;

                case 5:
                    postgresDB.close();
                    System.out.println("code is closed.");
                    return;
                default:
                    System.out.println("wrong option, please try again.");
            }
        }
    }
}
