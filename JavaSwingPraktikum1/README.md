# (Java Swing - OOP) #

Aplikasi desktop ini dirancang menggunakan basis bahasa pemrograman **Java Swing** untuk memenuhi Tugas Proyek Mandiri Persiapan UAS Mata Kuliah Pemrograman Berorientasi Objek. Pengembangan sistem ini menerapkan prinsip arsitektur **MVC (Model-View-Controller)** dan **DAO (Data Access Object)** untuk memastikan kode program tetap bersih (*Clean Code*), modular, dan mudah dikembangkan.

## Fitur Utama Aplikasi
* **Autentikasi Multi-User (Sistem Sesi Statis)**: Aplikasi mampu membatasi akses menu visual secara otomatis berdasarkan tingkat akun yang melakukan login (*Role-Based Access Control*).
* **CRUD Berkas Master Terintegrasi**: Sinkronisasi penuh dengan database MySQL untuk manajemen data Mahasiswa, Dosen, dan Mata Kuliah.
* **Fitur Pencarian & Pembagian Halaman (Search & Pagination)**: Navigasi tabel data mahasiswa yang interaktif untuk mengoptimalkan performa memori.
* **Navigasi Sesi Aman**: Fitur tombol Kembali (*Back*) dan Keluar (*Logout*) yang dinamis tanpa menyebabkan kebocoran memori aplikasi latar belakang.

## Struktur Paket Proyek (Architecture Overview)
Proyek ini terorganisir ke dalam beberapa paket (*packages*) utama:
* `config`: Berisi berkas pengaturan koneksi database utama (`DBConnection.java`).
* `controller`: Menjembatani logika data dari DAO menuju komponen tampilan grafis.
* `dao` (Data Access Object): Berisi kumpulan fungsi *Query SQL* (Insert, Select, Delete).
* `model`: Representasi objek data (mengimplementasikan konsep *Inheritance* dari `Person.java`).
* `utils`: Menyimpan fungsi utilitas sistem seperti manajemen sesi login aktif.
* `view`: Berisi berkas antarmuka grafis atau form visual (*JFrame Form*).

## Akun Akses Demo (Static Login)
Gunakan kombinasi akun di bawah ini untuk menguji coba berbagai hak akses halaman menu utama:
1. **Administrator (Akses Penuh Data Master)**
   * *Username*: `wisnu`
   * *Password*: `admin123`
2. **Dosen / Lecturer (Akses Input & Kelola Nilai)**
   * *Username*: `wisnu`
   * *Password*: `dosen123`
3. **Mahasiswa / Student (Akses Lihat KRS & Transkrip)**
   * *Username*: `wisnu`
   * *Password*: `mhs123`

## Panduan Instalasi & Menjalankan Proyek
1. Klona atau unduh repositori proyek ini ke komputer Anda.
2. Impor database `.sql` yang telah disediakan ke dalam server MySQL lokal Anda (misal: XAMPP / phpMyAdmin).
3. Sesuaikan konfigurasi *username* dan *password* database lokal Anda pada berkas `src/config/DBConnection.java`.
4. Buka proyek menggunakan NetBeans IDE.
5. Klik kanan pada folder utama proyek, pilih **Clean and Build**, kemudian klik **Run**.
