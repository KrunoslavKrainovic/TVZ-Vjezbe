package hr.java.vjezbe.entitet;


import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;

public interface Diplomski extends Visokoskolska {


    Student odrediStudentaZaRektorovuNagradu() throws NemoguceOdreditiProsjekStudentaException;
}
