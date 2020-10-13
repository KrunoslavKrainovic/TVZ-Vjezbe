package hr.java.vjezbe.entitet;



import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;

import java.util.ArrayList;
import java.util.List;

public abstract class ObrazovnaUstanova {

   private String naziv;
   private List<Predmet> predmeti;
    private List<Profesor> profesori;
    private List<Student> studenti;
    private List<Ispit> ispiti;

    public ObrazovnaUstanova(List<Ispit> ispiti,List<Student> studenti) {

        this.ispiti = ispiti;
        this.studenti = studenti;

    }




    public abstract Student odrediNajuspjesnijegStudentaNaGodini(Integer godina) throws NemoguceOdreditiProsjekStudentaException;

    public ObrazovnaUstanova() {}

    public ObrazovnaUstanova(List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti, List<Ispit> ispiti) {

        this.predmeti = predmeti;
        this.profesori = profesori;
        this.studenti = studenti;
        this.ispiti = ispiti;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Predmet> getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(List<Predmet> predmeti) {
        this.predmeti = predmeti;
    }

    public List<Profesor> getProfesori() {
        return profesori;
    }

    public void setProfesori(List<Profesor> profesori) {
        this.profesori = profesori;
    }

    public List<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(List<Student> studenti) {
        this.studenti = studenti;
    }

    public List<Ispit> getIspiti() {
        return ispiti;
    }

    public void setIspiti(List<Ispit> ispiti) {
        this.ispiti = ispiti;
    }



}
