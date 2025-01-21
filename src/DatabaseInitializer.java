import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initializeDatabase(Connection connection) {
        try {
            String sql = new String(Files.readAllBytes(
                    Paths.get(DatabaseInitializer.class.getClassLoader().getResource("init.sql").toURI())
            ));

            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            System.out.println("Database initialized successfully.");
        } catch (URISyntaxException | IOException | SQLException e) {
            System.out.println("Failed to initialize database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
