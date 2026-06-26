package controller;

import dao.StudentDAO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.StudentForm;

/**
 * @author idaba
 */

public class StudentController {
    private final StudentDAO studentDao;
    
    // variabel tambahan untuk fitur pagination
    private int currentPage = 1; 
    private final int limitPerPage = 12; // max jumlah halaman

    public StudentController() {
        this.studentDao = new StudentDAO();
    }

    // mengatur logika untuk tombol simpan, insert dan update
    public void saveStudent(StudentForm view) {
        String name = view.getTxtNama().getText().trim();
        String nim = view.getTxtNIM().getText().trim();
        String idCard = view.getTxtNIK().getText().trim(); 
        String studyProgram = view.getCmbProdi().getSelectedItem().toString();

        if (idCard.isEmpty() || name.isEmpty() || nim.isEmpty() || studyProgram.equals("-- Pilih Prodi --")) {
            JOptionPane.showMessageDialog(view, "Semua kolom input wajib diisi!");
            return;
        }

        if (idCard.length() != 16) {
            JOptionPane.showMessageDialog(view, "Format Salah! NIK harus berisi tepat 16 digit.");
            return;
        }

        if (studentDao.isStudentExist(idCard)) {
            System.out.println("Sistem Mendeteksi: NIK Terdaftar. Menjalankan fungsi UPDATE...");
            if (studentDao.updateStudent(idCard, name, nim, studyProgram)) {
                JOptionPane.showMessageDialog(view, "Data Mahasiswa Berhasil Diperbarui!");
                refreshTable(view); // memperbarui tampilan sesuai halaman aktif
                clearForm(view);
            } else {
                JOptionPane.showMessageDialog(view, "Gagal Memperbarui Data ke Database! Periksa struktur SQL.");
            }
        } else {
            System.out.println("Sistem Mendeteksi: NIK Baru. Menjalankan fungsi INSERT...");
            if (studentDao.insertStudent(idCard, name, nim, studyProgram)) {
                JOptionPane.showMessageDialog(view, "Data Mahasiswa Baru Berhasil Disimpan!");
                refreshTable(view); // memperbarui tampilan sesuai halaman aktif
                clearForm(view);
            } else {
                JOptionPane.showMessageDialog(view, "Gagal Menyimpan Data! Periksa koneksi atau duplikasi NIK/NIM.");
            }
        }
    }

    // refresh table, mengambil data terpotong berdasarkan halaman aktif
    public void refreshTable(StudentForm view) {
        DefaultTableModel model = (DefaultTableModel) view.getjTableStudent().getModel();
        model.setRowCount(0);
        
        // hitung offset: (halaman - 1) * jumlah data per halaman
        int offset = (currentPage - 1) * limitPerPage;
        
        // panggil fungsi pagination yang sudah dibuat di studentdao
        java.util.List<String[]> list = studentDao.getStudentsWithPagination(limitPerPage, offset);
        for (String[] data : list) {
            model.addRow(data);
        }
    }

    // tombol next dan prev
    public void nextPage(StudentForm view) {
        int totalData = studentDao.getTotalStudentCount();
        int maxPage = (int) Math.ceil((double) totalData / limitPerPage);
        
        if (currentPage < maxPage) {
            currentPage++;
            refreshTable(view);
        } else {
            JOptionPane.showMessageDialog(view, "Ini sudah halaman terakhir!");
        }
    }

    public void prevPage(StudentForm view) {
        if (currentPage > 1) {
            currentPage--;
            refreshTable(view);
        } else {
            JOptionPane.showMessageDialog(view, "Ini sudah halaman pertama!");
        }
    }
        
    // mengatur logika bersihkan form
    public void clearForm(StudentForm view) {
        view.getTxtNama().setText("");
        view.getTxtNIM().setText("");
        view.getTxtNIK().setText("");
        view.getCmbProdi().setSelectedIndex(0);
        view.getTxtNIK().setEditable(true);
    }
}
