package model;

import java.util.ArrayList;

// mengambil data dari kelas induk
public class Student extends Person {
    private String nim;
    private String studyProgram;
    private ArrayList<KRS> krsList;

    // constructor kirim data,isi prmter dan inisialisasi krs
    public Student(String idCard, String name, String nim, String studyProgram) {
        super(idCard, name);
        this.nim = nim;
        this.studyProgram = studyProgram;
        krsList = new ArrayList<>();
    }
    
    // mengambil atau melihat
    @Override
    public String toString() { 
        return this.name; 
    }
    
    public String getNim() { 
        return nim; 
    }
    
    public String getName() { 
        return name; 
    }
    
    public String getStudyProgram() { 
        return studyProgram; 
    }
    
    // menambahkan satu lembar objek krs baru ke dalam daftar krslist
    public void addKRS(KRS krs) { 
        krsList.add(krs); 
    }
}
