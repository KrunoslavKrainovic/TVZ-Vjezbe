package hr.java.vjezbe.entitet;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VisokoskolskaTest {

    ArrayList<Ispit> ispiti;

    ArrayList<Student> studenti;

    private VeleucilisteJave java = new VeleucilisteJave();





    LocalDate localDate1 = LocalDate.of(2016, 12, 31);
    LocalDate localDate2 = LocalDate.of(2017, 12, 31);
    LocalDate localDate3 = LocalDate.of(2018, 12, 31);
    LocalDate localDate4 = LocalDate.of(2019, 12, 31);


    private Student student = new Student("Kruno1","Kra","321321321",localDate1);
    private Student student1 = new Student("Kruno2","Kra1","3211111321321",localDate2);
    private Student student2 = new Student("Kruno3","Kra12","333123677821",localDate3);
    private Student student3 = new Student("Kruno4","Kra123","466788",localDate4);

    private Profesor profesor = new Profesor("Prof","Prof","srot","srot");

    private Predmet predmet = new Predmet("1337","Java",5,profesor);
    FakultetRacunarstva fax;

    @Before
    public void setUp(){
        ispiti = new ArrayList();
        ispiti.add(new Ispit(5,student,predmet));






        studenti = new ArrayList();
        studenti.add(student);
        studenti.add(student1);
        studenti.add(student2);
        studenti.add(student3);


        fax = new FakultetRacunarstva(studenti,ispiti);


    }

    @Test
    public void test(){
        System.out.println(fax.izracunajKonacnuOcjenuStudijaZaStudenta(ispiti,5,5));
    }


}