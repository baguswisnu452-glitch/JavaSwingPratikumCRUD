package model;

// menyimpan data kartu identitas dan nama yang akan diwariskan ke objek anak
public class Person {
    protected String idCard;
    protected String name;

    public Person(String idCard, String name) {
        this.idCard = idCard;
        this.name = name;
    }
}