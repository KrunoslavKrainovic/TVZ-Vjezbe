package hr.java.vjezbe.sortiranje;

import hr.java.vjezbe.entitet.Student;

import java.util.Comparator;

public class StudentSorter implements Comparator<Student> {



    public int compare(Student s, Student t) {
        int f = s.getPrezime().compareTo(t.getPrezime());
        return (f != 0) ? f : s.getIme().compareTo(t.getIme());
    }


}