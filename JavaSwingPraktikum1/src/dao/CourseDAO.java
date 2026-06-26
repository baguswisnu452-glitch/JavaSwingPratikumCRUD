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

public class CourseDAO {
    // tambah matkul
    public boolean insertCourse(String code, String courseName, int sks, int semester) {
        String sql = "INSERT INTO course (code, course_name, sks, semester) VALUES (?, ?, ?, ?)";
        try (Connection kon = DBConnection.getKoneksi();
             PreparedStatement perintah = kon.prepareStatement(sql)) {
            
            perintah.setString(1, code);
            perintah.setString(2, courseName);
            perintah.setInt(3, sks);
            perintah.setInt(4, semester);
            
            perintah.executeUpdate();
            return true; 
        } catch (SQLException e) {
            System.out.println("Gagal di CourseDAO (Insert): " + e.getMessage());
            return false; 
        }
    }

    // ambil semua data matkul
    public List<String[]> getAllCourses() {
        java.util.List<String[]> listData = new java.util.ArrayList<>();
        String sql = "SELECT * FROM course";
        try (java.sql.Connection kon = config.DBConnection.getKoneksi();
             java.sql.PreparedStatement perintah = kon.prepareStatement(sql);
             java.sql.ResultSet hasil = perintah.executeQuery()) {

            while (hasil.next()) {
                String[] data = new String[4];
                data[0] = hasil.getString("code");
                data[1] = hasil.getString("course_name");
                data[2] = String.valueOf(hasil.getInt("semester"));
                data[3] = String.valueOf(hasil.getInt("sks"));
                listData.add(data);
            }
        } catch (java.sql.SQLException e) {
            System.out.println("Gagal ambil data di CourseDAO: " + e.getMessage());
        }
        return listData;
    }

    // hapus matkul berdasarkan kode
    public boolean deleteCourse(String code) {
        String sql = "DELETE FROM course WHERE code = ?";
        try (Connection kon = DBConnection.getKoneksi();
             PreparedStatement perintah = kon.prepareStatement(sql)) {

            perintah.setString(1, code);
            perintah.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Gagal hapus di CourseDAO: " + e.getMessage());
            return false;
        }
    }

    // fungsi cek apakah kode mk sudah ada
    public boolean isCourseExist(String code) {
        String sql = "SELECT COUNT(*) FROM course WHERE code = ?";
        try (Connection kon = DBConnection.getKoneksi();
             PreparedStatement perintah = kon.prepareStatement(sql)) {
            
            perintah.setString(1, code);
            try (ResultSet hasil = perintah.executeQuery()) {
                if (hasil.next()) {
                    return hasil.getInt(1) > 0;
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Error cek kode di CourseDAO: " + e.getMessage());
            return false;
        }
    }

    // update data matkul
    public boolean updateCourse(String code, String courseName, int sks, int semester) {
        String sql = "UPDATE course SET course_name = ?, sks = ?, semester = ? WHERE code = ?";
        try (Connection kon = DBConnection.getKoneksi();
             PreparedStatement perintah = kon.prepareStatement(sql)) {
            
            perintah.setString(1, courseName);
            perintah.setInt(2, sks);
            perintah.setInt(3, semester);
            perintah.setString(4, code);
            
            return perintah.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Gagal UPDATE di CourseDAO: " + e.getMessage());
            return false;
        }
    }
}
