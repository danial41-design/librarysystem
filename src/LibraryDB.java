import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryDB {
    private Connection connection;

    public LibraryDB(Connection connection) {
        this.connection = connection;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"libraryDb\"");
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("avtor"),
                        rs.getInt("zhyl"),
                        rs.getInt("sany")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch books: " + e.getMessage());
        }
        return books;
    }

    public boolean addBook(String name, String author, int year, int quantity) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO public.\"libraryDb\" (name, avtor, zhyl, sany) VALUES (?, ?, ?, ?)"
            );
            stmt.setString(1, name);
            stmt.setString(2, author);
            stmt.setInt(3, year);
            stmt.setInt(4, quantity);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Failed to add book: " + e.getMessage());
            return false;
        }
    }


    public boolean updateBookQuantity(int id, int newQuantity) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE public.\"libraryDb\" SET sany = ? WHERE id = ?"
            );
            stmt.setInt(1, newQuantity);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Failed to update book quantity: " + e.getMessage());
            return false;
        }
    }

}
