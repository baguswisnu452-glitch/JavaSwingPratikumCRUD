package dao;

import config.DBConnection; // Menggunakan koneksi dari package config agar Clean Code
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author idaba
 */

public class StudentDAO {
    
    // tambah data
    public boolean insertStudent(String idCard, String name, String nim, String studyProgram) {
        String sql = "INSERT INTO student (id_card, name, nim, study_program) VALUES (?, ?, ?, ?)";
        try (Connection kon = DBConnection.getKoneksi(); // Panggil dari config
             PreparedStatement perintah = kon.prepareStatement(sql)) {
            
            perintah.setString(1, idCard);
            perintah.setString(2, name);
            perintah.setString(3, nim);
            perintah.setString(4, studyProgram);
            
            perintah.executeUpdate();
            return true; 
        } catch (SQLException e) {
            System.out.println("Gagal di DAO: " + e.getMessage());
            return false; 
        }
    }

    // mengambil semua data dari mysql 
    public List<String[]> getAllStudents() {
        List<String[]> listData = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try (Connection kon = DBConnection.getKoneksi();
             PreparedStatement perintah = kon.prepareStatement(sql);
             ResultSet hasil = perintah.executeQuery()) {
            
            while (hasil.next()) {
                String[] data = new String[4];
                data[0] = hasil.getString("id_card");
                data[1] = hasil.getString("name");
                data[2] = hasil.getString("nim");
                data[3] = hasil.getString("study_program");
                listData.add(data);
            }
        } catch (SQLException e) {
            System.out.println("Gagal ambil data di DAO: " + e.getMessage());
        }
        return listData;
    }

    // pagination
    public List<String[]> getStudentsWithPagination(int limit, int offset) {
        List<String[]> listData = new ArrayList<>();
        String sql = "SELECT * FROM student LIMIT ? OFFSET ?"; // Query Pagination
        try (Connection kon = DBConnection.getKoneksi();
             PreparedStatement perintah = kon.prepareStatement(sql)) {
            
            perintah.setInt(1, limit);  // max hal
            perintah.setInt(2, offset); // min hal
            
            try (ResultSet hasil = perintah.executeQuery()) {
                while (hasil.next()) {
                    String[] data = new String[4];
                    data[0] = hasil.getString("id_card");
                    data[1] = hasil.getString("name");
                    data[2] = hasil.getString("nim");
                    data[3] = hasil.getString("study_program");
                    listData.add(data);
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal pagination di DAO: " + e.getMessage());
        }
        return listData;
    }

    // menghitung total data nentukan jmlh halaman
    public int getTotalStudentCount() {
        String sql = "SELECT COUNT(*) FROM student";
        try (Connection kon = DBConnection.getKoneksi();
             PreparedStatement perintah = kon.prepareStatement(sql);
             ResultSet hasil = perintah.executeQuery()) {
            if (hasil.next()) {
                return hasil.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Gagal hitung total mahasiswa: " + e.getMessage());
        }
        return 0;
    }
    
    //  untuk menghapus data mahasiswa berdasarkan nik
    public boolean deleteStudent(String idCard) {
        String sql = "DELETE FROM student WHERE id_card = ?";
        try (Connection kon = DBConnection.getKoneksi();
             PreparedStatement perintah = kon.prepareStatement(sql)) {

            perintah.setString(1, idCard);
            perintah.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Gagal hapus di DAO: " + e.getMessage());
            return false;
        }
    }
    
    // untuk mencari data mahasiswa berdasarkan nama
    public List<String[]> searchStudentByName(String keyword) {
        List<String[]> listData = new ArrayList<>();
        String sql = "SELECT * FROM student WHERE name LIKE ?";
        try (Connection kon = DBConnection.getKoneksi();
             PreparedStatement perintah = kon.prepareStatement(sql)) {

            perintah.setString(1, "%" + keyword + "%");
            try (ResultSet hasil = perintah.executeQuery()) {
                while (hasil.next()) {
                    String[] data = new String[4];
                    data[0] = hasil.getString("id_card");
                    data[1] = hasil.getString("name");
                    data[2] = hasil.getString("nim");
                    data[3] = hasil.getString("study_program");
                    listData.add(data);
                }
            }
        } catch (SQLException e) {
            System.out.println("Gagal cari data di DAO: " + e.getMessage());
        }
        return listData;
    }
    
    // cek apakah data sdh ada
    public boolean isStudentExist(String idCard) {
        String sql = "SELECT COUNT(*) FROM student WHERE id_card = ?";
        try (Connection kon = DBConnection.getKoneksi();
             PreparedStatement perintah = kon.prepareStatement(sql)) {
            
            perintah.setString(1, idCard);
            try (ResultSet hasil = perintah.executeQuery()) {
                if (hasil.next()) {
                    int jumlah = hasil.getInt(1);
                    return jumlah > 0;
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Eror saat cek NIK di DAO: " + e.getMessage());
            return false;
        }
    }

    // update data
    public boolean updateStudent(String idCard, String name, String nim, String studyProgram) {
        String sql = "UPDATE student SET name = ?, nim = ?, study_program = ? WHERE id_card = ?";
        try (Connection kon = DBConnection.getKoneksi();
             PreparedStatement perintah = kon.prepareStatement(sql)) {
            
            perintah.setString(1, name);
            perintah.setString(2, nim);
            perintah.setString(3, studyProgram);
            perintah.setString(4, idCard);
            
            int barisTerubah = perintah.executeUpdate();
            return barisTerubah > 0;
        } catch (SQLException e) {
            System.out.println("Gagal jalankan UPDATE di DAO: " + e.getMessage());
            return false;
        }
    }
}
