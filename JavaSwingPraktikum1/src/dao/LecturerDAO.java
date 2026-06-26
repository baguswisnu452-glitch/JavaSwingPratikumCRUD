package dao;

import config.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author idaba
 */ 

public class LecturerDAO {
    // tambah dosen
    public boolean insertLecturer(String idCard, String name, String nidn, String expertise) {
        String sql = "INSERT INTO lecturer (id_card, name, nidn, expertise) VALUES (?, ?, ?, ?)";
        try (Connection kon = DBConnection.getKoneksi();
             PreparedStatement perintah = kon.prepareStatement(sql)) {
            
            perintah.setString(1, idCard);
            perintah.setString(2, name);
            perintah.setString(3, nidn);
            perintah.setString(4, expertise);
            
            perintah.executeUpdate();
            return true; 
        } catch (SQLException e) {
            System.out.println("Gagal di LecturerDAO (Insert): " + e.getMessage());
            return false; 
        }
    }

    // ambil semua data dosen
    public List<String[]> getAllLecturers() {
        List<String[]> listData = new ArrayList<>();
        String sql = "SELECT * FROM lecturer";
        try (Connection kon = DBConnection.getKoneksi();
             PreparedStatement perintah = kon.prepareStatement(sql);
             ResultSet hasil = perintah.executeQuery()) {
            
            while (hasil.next()) {
                String[] data = new String[4];
                data[0] = hasil.getString("id_card");
                data[1] = hasil.getString("name");
                data[2] = hasil.getString("nidn");
                data[3] = hasil.getString("expertise");
                listData.add(data);
            }
        } catch (SQLException e) {
            System.out.println("Gagal ambil data di LecturerDAO: " + e.getMessage());
        }
        return listData;
    }

    // hapus dosen berdasarkan nik
    public boolean deleteLecturer(String idCard) {
        String sql = "DELETE FROM lecturer WHERE id_card = ?";
        try (Connection kon = DBConnection.getKoneksi();
             PreparedStatement perintah = kon.prepareStatement(sql)) {

            perintah.setString(1, idCard);
            perintah.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Gagal hapus di LecturerDAO: " + e.getMessage());
            return false;
        }
    }

    // cek apakah dosen sdh ada
    public boolean isLecturerExist(String idCard) {
        String sql = "SELECT COUNT(*) FROM lecturer WHERE id_card = ?";
        try (Connection kon = DBConnection.getKoneksi();
             PreparedStatement perintah = kon.prepareStatement(sql)) {
            
            perintah.setString(1, idCard);
            try (ResultSet hasil = perintah.executeQuery()) {
                if (hasil.next()) {
                    return hasil.getInt(1) > 0;
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Error cek NIK di LecturerDAO: " + e.getMessage());
            return false;
        }
    }

    // update data dosen
    public boolean updateLecturer(String idCard, String name, String nidn, String expertise) {
        String sql = "UPDATE lecturer SET name = ?, nidn = ?, expertise = ? WHERE id_card = ?";
        try (Connection kon = DBConnection.getKoneksi();
             PreparedStatement perintah = kon.prepareStatement(sql)) {
            
            perintah.setString(1, name);
            perintah.setString(2, nidn);
            perintah.setString(3, expertise);
            perintah.setString(4, idCard);
            
            return perintah.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Gagal UPDATE di LecturerDAO: " + e.getMessage());
            return false;
        }
    }
}
