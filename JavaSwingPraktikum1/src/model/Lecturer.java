package model;

// ngambil data dari kelas induk
public class Lecturer extends Person {
    private String nidn;
    private String expertise;

    // constructor untuk membuat objek dosen baru dengan menyimpan data nama, NIDN, dan bidang
    public Lecturer(String idCard, String name, String nidn, String expertise) {
        super(idCard, name);
        this.nidn = nidn;
        this.expertise = expertise;
    }

    // getter - setter untuk nidn dan bidang
    public String getNidn() {
        return nidn;
    }

    public void setNidn(String nidn) {
        this.nidn = nidn;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }
}
