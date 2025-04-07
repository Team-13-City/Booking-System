import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:mysql://sst-stuproj.city.ac.uk:3306/in2033t13";
    private static final String DB_USERNAME = "in2033t13_d";
    private static final String DB_PASSWORD = "bl_XTYJRC5A";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
}

