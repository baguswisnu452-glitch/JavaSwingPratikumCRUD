package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author idaba
 */

public class DBConnection {
    
    // ubah biar logika nya selalu menghasilkan koneksi aktif yang segar
    public static Connection getKoneksi() {
        String url = "jdbc:mysql://localhost:3306/db_praktikum";
        String user = "root";
        String password = "";
        
        try {
            // membuat dan langsung mengembalikan koneksi baru yang aktif
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Koneksi Database Gagal: " + e.getMessage());
            return null;
        }
    }
}
