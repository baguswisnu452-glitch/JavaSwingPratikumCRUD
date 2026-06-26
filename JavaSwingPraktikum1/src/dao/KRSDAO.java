/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author idaba
 */

public class KRSDAO {
    // simpan data KRS baru ke database
    public boolean insertKRS(String nim, String courseCode, double score, String grade, String lecturerIdCard, int semester) {
        String sql = "INSERT INTO krs (nim, course_code, score, grade, lecturer_id_card, semester) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection kon = DBConnection.getKoneksi();
             PreparedStatement perintah = kon.prepareStatement(sql)) {
            
            perintah.setString(1, nim);
            perintah.setString(2, courseCode);
            perintah.setDouble(3, score);
            perintah.setString(4, grade);
            perintah.setString(5, lecturerIdCard);
            perintah.setInt(6, semester);
            
            return perintah.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Gagal di KRSDAO (Insert): " + e.getMessage());
            return false;
        }
    }

    // ambil semua data KRS untuk ditampilkan ke jtable
    public List<String[]> getAllKRS() {
        List<String[]> listData = new ArrayList<>();
        // query join untuk menarik nama mhs, matkul, dan dosen sekaligus
        String sql = "SELECT k.nim, s.name AS student_name, c.course_name, k.score, k.grade, l.name AS lecturer_name, k.semester, k.course_code " +
                     "FROM krs k " +
                     "JOIN student s ON k.nim = s.nim " +
                     "JOIN course c ON k.course_code = c.code " +
                     "JOIN lecturer l ON k.lecturer_id_card = l.id_card";
        
        try (Connection kon = DBConnection.getKoneksi();
             PreparedStatement perintah = kon.prepareStatement(sql);
             ResultSet hasil = perintah.executeQuery()) {
            
            while (hasil.next()) {
                String[] data = new String[8];
                data[0] = hasil.getString("nim");
                data[1] = hasil.getString("student_name");
                data[2] = hasil.getString("course_name");
                data[3] = String.valueOf(hasil.getDouble("score"));
                data[4] = hasil.getString("grade");
                data[5] = hasil.getString("lecturer_name");
                data[6] = String.valueOf(hasil.getInt("semester"));
                data[7] = hasil.getString("course_code"); // untuk kebutuhan update/delete kunci
                listData.add(data);
            }
        } catch (SQLException e) {
            System.out.println("Gagal ambil data di KRSDAO: " + e.getMessage());
        }
        return listData;
    }

    // hapus data krs berdasarkan nim dan kode matkul
    public boolean deleteKRS(String nim, String courseCode) {
        String sql = "DELETE FROM krs WHERE nim = ? AND course_code = ?";
        try (Connection kon = DBConnection.getKoneksi();
             PreparedStatement perintah = kon.prepareStatement(sql)) {

            perintah.setString(1, nim);
            perintah.setString(2, courseCode);
            return perintah.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Gagal hapus di KRSDAO: " + e.getMessage());
            return false;
        }
    }
}
