package hr.java.vjezbe.entitet;

import java.time.LocalDate;
import java.util.Objects;

public class Student  extends Osoba  {

    private String jmbag;
    private LocalDate datumRodjena;

    public Student() {}

    public Student(String ime, String prezime, String jmbag, LocalDate datumRodjena) {
        super(ime, prezime);
        this.jmbag = jmbag;
        this.datumRodjena = datumRodjena;
    }

    public String getJmbag() {
        return jmbag;
    }

    public void setJmbag(String jmbag) {
        this.jmbag = jmbag;
    }

    public LocalDate getDatumRodjena() {
        return datumRodjena;
    }

    public void setDatumRodjena(LocalDate datumRodjena) {
        this.datumRodjena = datumRodjena;
    }

    @Override
    public String toString() {
        return "Student{" +
                "jmbag='" + jmbag + '\'' +
                ", datumRodjena=" + datumRodjena +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(jmbag, student.jmbag) &&
                Objects.equals(datumRodjena, student.datumRodjena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jmbag, datumRodjena);
    }
}
