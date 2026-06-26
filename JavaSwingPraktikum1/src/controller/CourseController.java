/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CourseDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author idaba
 */

public class CourseController {
    private final CourseDAO courseDao;

    public CourseController() {
        this.courseDao = new CourseDAO();
    }

    // mengatur logika untuk Ttombol simpan
    public void saveCourse(javax.swing.JFrame view, String code, String courseName, String sksStr, String semesterStr, javax.swing.JTable table) {
        code = code.trim();
        courseName = courseName.trim();
        sksStr = sksStr.trim();
        semesterStr = semesterStr.trim();

        if (code.isEmpty() || courseName.isEmpty() || sksStr.isEmpty() || semesterStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Semua kolom input wajib diisi!");
            return;
        }

        int sks, semester;
        try {
            sks = Integer.parseInt(sksStr);
            semester = Integer.parseInt(semesterStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "SKS dan Semester harus berupa angka bulat!");
            return;
        }

        if (courseDao.isCourseExist(code)) {
            if (courseDao.updateCourse(code, courseName, sks, semester)) {
                JOptionPane.showMessageDialog(view, "Data Mata Kuliah Berhasil Diperbarui!");
                refreshTable(table);
            } else {
                JOptionPane.showMessageDialog(view, "Gagal Memperbarui Data Mata Kuliah!");
            }
        } else {
            if (courseDao.insertCourse(code, courseName, sks, semester)) {
                JOptionPane.showMessageDialog(view, "Data Mata Kuliah Baru Berhasil Disimpan!");
                refreshTable(table);
            } else {
                JOptionPane.showMessageDialog(view, "Gagal Menyimpan Data Mata Kuliah!");
            }
        }
    }

    // refresh yabel matkul
    public void refreshTable(javax.swing.JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        List<String[]> list = courseDao.getAllCourses();
        for (String[] data : list) {
            model.addRow(data);
        }
    }

    // hapus mata matkul
    public void deleteCourse(javax.swing.JFrame view, String code, javax.swing.JTable table) {
        if (code == null || code.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Pilih baris mata kuliah di tabel terlebih dahulu!");
            return;
        }

        int konfirmasi = JOptionPane.showConfirmDialog(view, "Hapus mata kuliah dengan kode " + code + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == JOptionPane.YES_OPTION) {
            if (courseDao.deleteCourse(code)) {
                JOptionPane.showMessageDialog(view, "Data Mata Kuliah Berhasil Dihapus!");
                refreshTable(table);
            } else {
                JOptionPane.showMessageDialog(view, "Gagal Menghapus Data!");
            }
        }
    }
}
