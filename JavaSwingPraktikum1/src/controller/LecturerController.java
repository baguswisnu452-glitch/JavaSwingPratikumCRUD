/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.LecturerDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author idaba
 */

public class LecturerController {
    private final LecturerDAO lecturerDao;

    public LecturerController() {
        this.lecturerDao = new LecturerDAO();
    }

    // mengatur logika untuk tombol simpan dosen, yaitu: insert dan update
    public void saveLecturer(javax.swing.JFrame view, String idCard, String name, String nidn, String expertise, javax.swing.JTable table) {
        idCard = idCard.trim();
        name = name.trim();
        nidn = nidn.trim();
        expertise = expertise.trim();

        // validasi input kosong
        if (idCard.isEmpty() || name.isEmpty() || nidn.isEmpty() || expertise.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Semua kolom input wajib diisi!");
            return;
        }

        // validasi panjang nik harus 16 digit
        if (idCard.length() != 16) {
            JOptionPane.showMessageDialog(view, "Format Salah! NIK harus berisi tepat 16 digit.");
            return;
        }

        // cek apakah nik sudah ada, kalau ada.. lakukan update. jika tidak, lakukan insert
        if (lecturerDao.isLecturerExist(idCard)) {
            System.out.println("Sistem Mendeteksi: NIK Dosen Terdaftar. Menjalankan fungsi UPDATE...");
            if (lecturerDao.updateLecturer(idCard, name, nidn, expertise)) {
                JOptionPane.showMessageDialog(view, "Data Dosen Berhasil Diperbarui!");
                refreshTable(table);
            } else {
                JOptionPane.showMessageDialog(view, "Gagal Memperbarui Data Dosen!");
            }
        } else {
            System.out.println("Sistem Mendeteksi: NIK Dosen Baru. Menjalankan fungsi INSERT...");
            if (lecturerDao.insertLecturer(idCard, name, nidn, expertise)) {
                JOptionPane.showMessageDialog(view, "Data Dosen Baru Berhasil Disimpan!");
                refreshTable(table);
            } else {
                JOptionPane.showMessageDialog(view, "Gagal Menyimpan Data Dosen! Periksa koneksi atau duplikasi NIK/NIDN.");
            }
        }
    }

    // mengatur logika menampilkan atau memperbarui data di tabel gui
    public void refreshTable(javax.swing.JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // kosongkan tabel terlebih dahulu
        
        List<String[]> list = lecturerDao.getAllLecturers();
        for (String[] data : list) {
            model.addRow(data);
        }
    }

    // mengatur logika tombol hapus dosen
    public void deleteLecturer(javax.swing.JFrame view, String idCard, javax.swing.JTable table) {
        if (idCard == null || idCard.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Pilih baris data dosen di tabel terlebih dahulu untuk dihapus!");
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(view, "Apakah Anda yakin ingin menghapus dosen dengan NIK " + idCard + "?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            if (lecturerDao.deleteLecturer(idCard)) {
                JOptionPane.showMessageDialog(view, "Data Dosen Berhasil Dihapus!");
                refreshTable(table);
            } else {
                JOptionPane.showMessageDialog(view, "Gagal Menghapus Data Dosen!");
            }
        }
    }
}
