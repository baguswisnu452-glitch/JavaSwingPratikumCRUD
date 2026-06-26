package model;

public class Course {
    private String code;
    private String courseName;
    private int sks;
    private int semester;

    // constructor membuat objek mata kuliah baru dengan langsung menyimpan data
    public Course(String code, String courseName, int sks, int semester) {
        this.code = code;
        this.courseName = courseName;
        this.sks = sks;
        this.semester = semester;
    }

    // getter - setter
    public String getCode() { 
        return code; 
    }
    public void setCode(String code) { 
        this.code = code; 
    }

    public String getCourseName() { 
        return courseName; 
    }
    public void setCourseName(String courseName) { 
        this.courseName = courseName; 
    }

    public int getSks() { 
        return sks; 
    }
    public void setSks(int sks) {
        this.sks = sks; 
    }

    public int getSemester() {
        return semester; 
    }
    public void setSemester(int semester) { 
        this.semester = semester; 
    }

    @Override
    public String toString() { 
        return this.courseName; 
    }
}
