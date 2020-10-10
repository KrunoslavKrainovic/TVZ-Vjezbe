package hr.java.vjezbe.entitet;


import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;
import hr.java.vjezbe.iznimke.PostojiViseNajmladjihStudenataException;

public interface Diplomski extends Visokoskolska {


    Student odrediStudentaZaRektorovuNagradu()
        throws NemoguceOdreditiProsjekStudentaException, PostojiViseNajmladjihStudenataException;
}
