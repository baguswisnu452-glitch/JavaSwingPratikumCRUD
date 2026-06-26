package model;

public class KRS {
    private String nim; 
    private Course course;
    private double score;
    private String grade;
    private Lecturer lecture;
    private int semester;
    
    // constructor untuk  di inputnilai.java
    public KRS(Course course, double score) {
        this.course = course;
        this.score = score;
        this.grade = hitungGrade(score);
    }
    
    // constructor Untuk database atau dao
    public KRS(String nim, Course course, double score, Lecturer lecture, int semester) {
        this.nim = nim;
        this.course = course;
        this.score = score;
        this.lecture = lecture;
        this.semester = semester;
        this.grade = hitungGrade(score);
    }

    // hitung grade otomatis
    private String hitungGrade(double score) {
        if (score >= 85) return "A";
        else if (score >= 75) return "B";
        else if (score >= 60) return "C";
        else return "D";
    }

    // getter - setter
    public String getNim() {
        return nim; 
    }
    public void setNim(String nim) {
        this.nim = nim; 
    }

    public Course getCourse() {
        return course; 
    }
    public void setCourse(Course course) {
        this.course = course; 
    }

    public double getScore() {
        return score; 
    }
    public void setScore(double score) { 
        this.score = score; 
        this.grade = hitungGrade(score); 
    }

    public String getGrade() {
        return grade; 
    }

    public Lecturer getLecture() {
        return lecture;
    }
    public void setLecture(Lecturer lecture) {
        this.lecture = lecture; 
    }
    
    public int getSemester() {
        return semester; 
    }
    public void setSemester(int semester) {
        this.semester = semester;
    }
}
