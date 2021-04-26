package databaseConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DbConnection {
    private static final Logger L = LoggerFactory.getLogger(DbConnection.class);

    private String DB_URL;

    private String DB_USER;

    private String DB_PASSWORD;

    private static DbConnection dbInstance; // vor Zugriff von außen geschützt und statisch
	private DbConnection() {
        System.out.println(System.getenv("DB_URL"));
        System.out.println(System.getenv("DB_USER"));
        System.out.println(System.getenv("DB_PASSWORD"));
        DB_URL = System.getenv("DB_URL") != null ? System.getenv("DB_URL") : DbCred.url;
        DB_USER = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : DbCred.user;
        DB_PASSWORD = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : DbCred.password;
    } // privater Konstruktor mit Zugriffsschutz von außen
	public static DbConnection getInstance() { // öffentliche Methode, Aufruf durch Code
		if (dbInstance == null) { // nur wenn keine Instanz besteht, dann erstelle eine neue
			dbInstance = new DbConnection();
		}
		return dbInstance;
	}

    public boolean checkDbConnection() {
        L.info("Start");

        try (Connection c = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            L.info("Verbindungsaufbau erfolgreich");
            return true;
            // Aufruf der eigentlichen Anwendungsfunktion
            // selectAll(c);
        } catch (SQLException e) {
            L.error("Verbindungsaufbau gescheitert", e);
            return false;
        } catch (DataException e) {
            L.error("DataException");
        }
        L.info("Ende");
        return false;
    }

    //für Übungszwecke
    // public void selectAll(Connection c) {
    //     L.info("Start selectAll");
    //     String sql = "select * from customer";
    //     L.info(sql);
    //     try (Statement stmt = c.createStatement()) {
    //         try (ResultSet rs = stmt.executeQuery(sql)) {
    //             //System.out.println(String.format("|%3d|%6s|%6s|%6s|", "customer_id", "first_name", "last_name", "birthday"));
    //             while (rs.next()) {
    //                 Integer customer_id = rs.getInt("customer_id");
    //                 String first_name = rs.getString("first_name");
    //                 String last_name = rs.getString("last_name");
    //                 java.sql.Date birthday = rs.getDate("birthday");
    //                 System.out.println(String.format("|%3d|%6s|%6s|%6s|", customer_id, first_name, last_name, birthday));
    //             }
    //         }
    //     } catch (SQLException e) {
    //         L.error("", e);
    //         throw new DataException(e);
    //     }
    //     L.info("Ende selectAll");
    // }


}

