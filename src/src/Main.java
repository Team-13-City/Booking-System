import java.sql.*;


public class Main {
    public static void main(String[] args) {
        String query = "SELECT * FROM `Testing`";

        try (
                Connection con = DatabaseManager.getConnection();
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
