package data;
import data.interfaces.IDB;
import java.sql.Connection;
import java.sql.DriverManager;

    public class PostgresDB implements IDB {
        private String host;
        private String username;
        private String password;
        private String dbname;

        private Connection connection;

        public PostgresDB(String host, String username, String password, String dbname) {
            setHost(host);
            setUsername(username);
            setPassword(password);
            setDbname(dbname);
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }
        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        public String getDbname() {
            return dbname;
        }
        public void setDbname(String dbname) {
            this.dbname = dbname;
        }

        @Override
        public Connection getConnection() {
            String connectionUrl = host + "/" + dbname;
            try {
                if (connection != null && !connection.isClosed()) {
                    return connection;
                }

                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(connectionUrl, username, password);
                System.out.println("Connection successful.");
                return connection;
            } catch (Exception e) {
                System.out.println("Connection is failed: " + e.getMessage());
                e.printStackTrace();
            }
            return null;
        }



        @Override
        public void close() {
            if (connection != null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Failed to close database connection: " + e.getMessage());
                }
            }
        }

    }

