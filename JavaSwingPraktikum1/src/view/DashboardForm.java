/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package view;

import view.InputNilai;
import view.LoginForm;

/**
 *
 * @author idaba
 */

public class DashboardForm extends javax.swing.JFrame {
private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DashboardForm.class.getName());

    /// membuat new form untuk dashboardform
    public DashboardForm() {
        initComponents();
        batasiHakAksesMenu(); // panggil fungsi pembatasan saat menu utama terbuka
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuMhs = new javax.swing.JMenuItem();
        jMenuKRS = new javax.swing.JMenuItem();
        jMenuDosen = new javax.swing.JMenuItem();
        jMenuMataKuliah = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("File");
        jMenu1.addActionListener(this::jMenu1ActionPerformed);

        jMenuItem1.setText("Logout");
        jMenuItem1.addActionListener(this::jMenuItem1ActionPerformed);
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(this::jMenuItem2ActionPerformed);
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Data");
        jMenu2.addActionListener(this::jMenu2ActionPerformed);

        jMenuMhs.setText("Mahasiswa");
        jMenuMhs.addActionListener(this::jMenuMhsActionPerformed);
        jMenu2.add(jMenuMhs);

        jMenuKRS.setText("KRS");
        jMenuKRS.addActionListener(this::jMenuKRSActionPerformed);
        jMenu2.add(jMenuKRS);

        jMenuDosen.setText("Dosen");
        jMenuDosen.addActionListener(this::jMenuDosenActionPerformed);
        jMenu2.add(jMenuDosen);

        jMenuMataKuliah.setText("Mata Kuliah");
        jMenuMataKuliah.addActionListener(this::jMenuMataKuliahActionPerformed);
        jMenu2.add(jMenuMataKuliah);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // fungsi krs di dashboardform
    private void jMenuKRSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuKRSActionPerformed
    new InputNilai().setVisible(true);

    this.dispose(); // menutup form
    }//GEN-LAST:event_jMenuKRSActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
    
    }//GEN-LAST:event_jMenu1ActionPerformed

    // fungsi exit
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    // fungsi logout
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    int konfirmasi = javax.swing.JOptionPane.showConfirmDialog(this, 
                "Apakah Anda yakin ingin Logout?", "Konfirmasi", 
                javax.swing.JOptionPane.YES_NO_OPTION);
        
        if (konfirmasi == javax.swing.JOptionPane.YES_OPTION) {
            new LoginForm().setVisible(true);
            this.dispose(); 
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        StudentForm formMhs = new StudentForm();
        formMhs.setVisible(true);
    }//GEN-LAST:event_jMenu2ActionPerformed

    // fungsi mahasiswa di dashboardform
    private void jMenuMhsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuMhsActionPerformed
        new StudentForm().setVisible(true); //membuka

        this.dispose(); //menutup
    }//GEN-LAST:event_jMenuMhsActionPerformed

    private void jMenuDosenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuDosenActionPerformed
        // membuat objek dari form dosen
        view.LecturerForm formDosen = new view.LecturerForm();
    
        // menampilkan form dosen ke layar
        formDosen.setVisible(true);

        // mengatur agar posisi form dosen otomatis berada di tengah layar
        formDosen.setLocationRelativeTo(null);
        
        this.setVisible(false);
    }//GEN-LAST:event_jMenuDosenActionPerformed

    // fungsi matkul
    private void jMenuMataKuliahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuMataKuliahActionPerformed
        view.CourseForm formMK = new view.CourseForm(); //membuka
        formMK.setVisible(true);
        formMK.setLocationRelativeTo(null); //posisikan jendela
        
        this.setVisible(false); // menyembunyikan
    }//GEN-LAST:event_jMenuMataKuliahActionPerformed

    private void batasiHakAksesMenu() {
        // Ambil status role yang tersimpan dari memori latar belakang UserSession
        String roleAktif = utils.UserSession.getRole();
        
        if (roleAktif.equalsIgnoreCase("Student")) {
            // Mahasiswa TIDAK boleh edit data master dosen, matakuliah, dan input nilai
            jMenuDosen.setVisible(false); // Sesuaikan dengan nama variabel tombol Anda
            jMenuMataKuliah.setVisible(false);
            jMenuKRS.setVisible(false);
            
            // Mahasiswa HANYA bisa buka form data dirinya sendiri atau form krs
            jMenuMhs.setVisible(true);
            
        } else if (roleAktif.equalsIgnoreCase("Lecturer")) {
            // Dosen HANYA boleh melihat data mahasiswa dan melakukan input nilai
            jMenuDosen.setVisible(false);
            jMenuMataKuliah.setVisible(false);
            
            jMenuMhs.setVisible(true);
            jMenuKRS.setVisible(true);
            
        } else if (roleAktif.equalsIgnoreCase("Admin")) {
            // Admin memiliki kekuasaan penuh untuk mengelola semua tombol form anak
            jMenuDosen.setVisible(true);
            jMenuMhs.setVisible(true);
            jMenuMataKuliah.setVisible(true);
            jMenuKRS.setVisible(true);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        // buat dan tampilkan dashboardform
        java.awt.EventQueue.invokeLater(() -> new DashboardForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuDosen;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuKRS;
    private javax.swing.JMenuItem jMenuMataKuliah;
    private javax.swing.JMenuItem jMenuMhs;
    // End of variables declaration//GEN-END:variables
}
