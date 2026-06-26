package view;

import model.Course;
import model.KRS;
import model.Student;
import view.LoginForm;

// mendeklarasikan kelas jendela input nilai, menyimpan mhs, matkul dan krs
public class InputNilai extends javax.swing.JFrame {
    java.util.ArrayList<Student> listMahasiswa = new java.util.ArrayList<>();
    java.util.ArrayList<Course> listMK = new java.util.ArrayList<>();
    final String[] semester = {"-- Pilih Semester --", "1", "2", "3"};
    final javax.swing.DefaultComboBoxModel comboModelSM = new javax.swing.DefaultComboBoxModel(semester);
    javax.swing.DefaultComboBoxModel<Student> comboModelMhs = new javax.swing.DefaultComboBoxModel<>();
    javax.swing.DefaultComboBoxModel<Course> comboModelCourse = new javax.swing.DefaultComboBoxModel<>();
    Student selecttedStudent;
    Course selecttedCourse;
    KRS krs;

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(InputNilai.class.getName());
    private dao.KRSDAO krsDao = new dao.KRSDAO();
    
    public InputNilai() {
        initComponents();

        // ambil data asli mahasiswa dari database via studendao
        dao.StudentDAO studentDAO = new dao.StudentDAO();
        java.util.List<String[]> listMhsDB = studentDAO.getAllStudents();

        for (String[] data : listMhsDB) {
            comboModelMhs.addElement(new model.Student(data[0], data[1], data[2], data[3]));
        }

        // ambil data asli mata kuliah dari database via coursedao
        dao.CourseDAO courseDAO = new dao.CourseDAO();
        java.util.List<String[]> listMKDB = courseDAO.getAllCourses();

        System.out.println("DEBUG: Jumlah data MK yang berhasil ditarik dari database = " + listMKDB.size());
         
        comboModelCourse.removeAllElements(); // bersihkan sebelum diisi
        for (String[] data : listMKDB) {
            try {
                int semValue = Integer.parseInt(data[2].trim());
                int sksValue = Integer.parseInt(data[3].trim());

                comboModelCourse.addElement(new model.Course(data[0], data[1], sksValue, semValue));
            } catch (Exception e) {
                System.out.println("Gagal memuat satu mata kuliah: " + e.getMessage());
            }
        }

        // mengisi data dosen pengampu bawaan Anda
        jComboBoxDosen.removeAllItems(); 
        jComboBoxDosen.addItem("Made Ayaka");
        jComboBoxDosen.addItem("Gusti Raiden");
        jComboBoxDosen.addItem("Bagus Bennet");
        jComboBoxMhs.setModel(comboModelMhs);
        jComboBoxMK.setModel(comboModelCourse);
        // panggil fungsi tampil tabel dari database
        tampilkanDataKRSDariDatabase(); 
    }

    /** 
    *
    *   @ author idaba("unchecked")
    */
    
    // untuk membangun, mengatur posisi, dan menampilkan semua komponen visual
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        formInputNilai = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxMhs = new javax.swing.JComboBox<>();
        jLabelNIM = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxMK = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabelKodeSKS = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldNilaiSikap = new javax.swing.JTextField();
        jTextFieldNilaiUTS = new javax.swing.JTextField();
        jTextFieldNilaiUAS = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxSM = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabelHuruf = new javax.swing.JLabel();
        jButtonAdd = new javax.swing.JButton();
        jComboBoxDosen = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableNilai = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("InputNilai"); // NOI18N

        formInputNilai.setText("Form Input Nilai");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Identitas Mahasiswa"));

        jLabel1.setText("Nama");

        jComboBoxMhs.setModel(comboModelMhs);
        jComboBoxMhs.addActionListener(this::jComboBoxMhsActionPerformed);

        jLabelNIM.setText("NIM & Prodi");

        jToggleButton1.setText("Back");
        jToggleButton1.addActionListener(this::jToggleButton1ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNIM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(jComboBoxMhs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jToggleButton1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxMhs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelNIM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToggleButton1)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Identitas Matakuliah"));

        jLabel3.setText("MataKuliah");

        jComboBoxMK.setModel(comboModelCourse);
        jComboBoxMK.addActionListener(this::jComboBoxMKActionPerformed);

        jLabel4.setText("Kode dan SKS");

        jLabel5.setText("Nilai");

        jTextFieldNilaiSikap.setText("0");
        jTextFieldNilaiSikap.addActionListener(this::jTextFieldNilaiSikapActionPerformed);

        jTextFieldNilaiUTS.setText("0");

        jTextFieldNilaiUAS.setText("0");

        jLabel6.setText("Semester");

        jComboBoxSM.setModel(comboModelSM);
        jComboBoxSM.addActionListener(this::jComboBoxSMActionPerformed);

        jLabel7.setText("Nilai Huruf");

        jButtonAdd.setText("Add");
        jButtonAdd.addActionListener(this::jButtonAddActionPerformed);

        jComboBoxDosen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Made Ayaka", "Gusti Raiden", "Bagus Bennet", " " }));
        jComboBoxDosen.addActionListener(this::jComboBoxDosenActionPerformed);

        jLabel2.setText("Pengampu");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelHuruf, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAdd))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxSM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelKodeSKS)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboBoxDosen, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextFieldNilaiSikap, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldNilaiUTS, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldNilaiUAS, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBoxMK, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxMK)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabelKodeSKS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldNilaiSikap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNilaiUTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNilaiUAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jComboBoxSM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addComponent(jLabel7))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jButtonAdd))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxDosen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelHuruf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(13, 13, 13))
        );

        jTableNilai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NIM", "Nama", "Matakuliah", "SKS", "Nilai", "Pengampu"
            }
        ));
        jScrollPane1.setViewportView(jTableNilai);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(formInputNilai)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(formInputNilai)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // untuk memproses dan menyimpan data nilai matkul mahasiswa ketika tombol add diklik
    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        if (jComboBoxSM.getSelectedIndex() == 0) { // validasi untuk pilih semester agar tidak kosong
        javax.swing.JOptionPane.showMessageDialog(this, "Silakan pilih Semester terlebih dahulu!", 
        "Peringatan", javax.swing.JOptionPane.WARNING_MESSAGE);
        return; 
    }
    
    try {
        // ambil data asli dari pilihan objek komponen gui
        String nimMhs = selecttedStudent.getNim();
        String namaMhs = selecttedStudent.getName();
        String namaMK = selecttedCourse.getCourseName();
        String kodeMK = selecttedCourse.getCode();
        
        // panggil setkrs biar objek krs terisi nilai rata-rata terbaru
        setKRS(); 
        double skorNilai = krs.getScore();
        String nilaiHuruf = krs.getGrade();
        int smt = Integer.parseInt(jComboBoxSM.getSelectedItem().toString());
        String dosenPengampu = jComboBoxDosen.getSelectedItem().toString();

        // simpan secara permanen ke mysql via krsdao
        boolean suksesSimpan = krsDao.insertKRS(nimMhs, kodeMK, skorNilai, nilaiHuruf, dosenPengampu, smt);
        
        if (suksesSimpan) {
            javax.swing.JOptionPane.showMessageDialog(this, "Data KRS & Nilai Berhasil Disimpan ke Database MySQL!");
            
            // masukkan data baru langsung ke JTable visual agar instan muncul
            javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTableNilai.getModel();
            Object[] barisBaru = {
                nimMhs,       
                namaMhs,
                namaMK,       
                skorNilai,    
                nilaiHuruf,   
                dosenPengampu 
            };
            model.addRow(barisBaru); // data langsung bertambah di layar tanpa jeda database
            
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Gagal menyimpan ke database! Periksa apakah data sudah pernah ada.");
        }
        
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage());
        }
    }//GEN-LAST:event_jButtonAddActionPerformed

    // pilih nm mhs
    private void jComboBoxMhsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMhsActionPerformed
          this.selecttedStudent = (Student) jComboBoxMhs.getSelectedItem();
        if (selecttedStudent != null) {
            jComboBoxSM.setSelectedIndex(0);
            jLabelNIM.setText(selecttedStudent.getNim() + " | Prodi: " + selecttedStudent.getStudyProgram());
        }
    }//GEN-LAST:event_jComboBoxMhsActionPerformed

    // pilih matkul
    private void jComboBoxMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMKActionPerformed
        this.selecttedCourse = (Course) jComboBoxMK.getSelectedItem();
        if (selecttedCourse != null) {
            jComboBoxSM.setSelectedIndex(0);
            jLabelKodeSKS.setText(selecttedCourse.getCode() + " | SKS: " + Integer.toString(selecttedCourse.getSks()));
        }
    }//GEN-LAST:event_jComboBoxMKActionPerformed

    // menghitung nilai rata-rata mahasiswa
    private void setKRS() {
        int uas = Integer.parseInt(jTextFieldNilaiUAS.getText());
        int uts = Integer.parseInt(jTextFieldNilaiUTS.getText());
        int sikap = Integer.parseInt(jTextFieldNilaiSikap.getText());
        double rataRata = (uas + uts + sikap) / 3.0;
        this.krs = new KRS(selecttedCourse, rataRata);
    }
    
    // menarik data asli dari database mysql ke jtable
    public void tampilkanDataKRSDariDatabase() {
    javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) jTableNilai.getModel();
    model.setRowCount(0); // kosongkan tabel sebelum diisi
    
    // ambil list data dari database via dao
    java.util.List<String[]> listKrs = krsDao.getAllKRS();
    
    for (String[] data : listKrs) {
        // memasukkan data ke baris tabel berdasarkan urutan kolom di query sql krsdao
        Object[] rowData = {
            data[0], // nim
            data[1], // nm mhs
            data[2], // matkul
            data[3], // nilai
            data[4], // grade
            data[5]  // dospem
        };
        model.addRow(rowData);
    }
}

    //
    private void jComboBoxSMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSMActionPerformed
        setKRS();
        jLabelHuruf.setText(this.krs.getGrade());
    }//GEN-LAST:event_jComboBoxSMActionPerformed

    // input dospem
    private void jComboBoxDosenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDosenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxDosenActionPerformed

    // input nilai
    private void jTextFieldNilaiSikapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNilaiSikapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNilaiSikapActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // membuat objek dashboardform baru
        view.DashboardForm dashboard = new view.DashboardForm();

        // menampilkan dashboard ke layar
        dashboard.setVisible(true);

        // mengatur posisi dashboard otomatis di tengah layar
        dashboard.setLocationRelativeTo(null);

        // menutup form agar hemat memori ram
        this.dispose(); 
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new LoginForm().setVisible(true); // Diubah dari InputNilai() menjadi LoginForm()
        }
    });
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel formInputNilai;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JComboBox<String> jComboBoxDosen;
    private javax.swing.JComboBox<Course> jComboBoxMK;
    private javax.swing.JComboBox<Student> jComboBoxMhs;
    private javax.swing.JComboBox<String> jComboBoxSM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelHuruf;
    private javax.swing.JLabel jLabelKodeSKS;
    private javax.swing.JLabel jLabelNIM;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableNilai;
    private javax.swing.JTextField jTextFieldNilaiSikap;
    private javax.swing.JTextField jTextFieldNilaiUAS;
    private javax.swing.JTextField jTextFieldNilaiUTS;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
