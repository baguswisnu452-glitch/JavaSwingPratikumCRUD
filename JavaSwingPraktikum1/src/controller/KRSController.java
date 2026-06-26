/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.KRSDAO;
import model.KRS;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author idaba
 */

public class KRSController {
   private final KRSDAO krsDAO;

    public KRSController() {
        this.krsDAO = new KRSDAO();
    }

    // menambahkan data krs baru
    public boolean addKRS(String nim, String courseCode, double score, String grade, String lecturerIdCard, int semester) {
        // validasi input dasar untuk menerapkan cleancode
        if (nim == null || nim.trim().isEmpty() || courseCode == null || courseCode.trim().isEmpty()) {
            return false;
        }
        return krsDAO.insertKRS(nim, courseCode, score, grade, lecturerIdCard, semester);
    }

    // memuat seluruh data krs dengan join ke jtable
    public void loadAllKRSToTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0); // reset atau bersihkan baris tabel lama
        
        List<String[]> listKRS = krsDAO.getAllKRS();
        for (String[] rowData : listKRS) {
            // memasukkan array String[] langsung sebagai baris jtable
            // urutan kolom di jtable
            tableModel.addRow(new Object[]{
                rowData[0], // nim
                rowData[1], // student_name
                rowData[2], // course_name
                rowData[3], // score
                rowData[4], // grade
                rowData[5], // lecturer_name
                rowData[6]  // semester
            });
        }
    }

    // menghapus data krs
    public boolean removeKRS(String nim, String courseCode) {
        if (nim == null || courseCode == null) {
            return false;
        }
        return krsDAO.deleteKRS(nim, courseCode);
    }
}