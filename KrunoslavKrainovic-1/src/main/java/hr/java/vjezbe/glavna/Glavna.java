package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Glavna {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Profesor> profesori = new ArrayList();

        ArrayList<Predmet> predmeti = new ArrayList();

        ArrayList<Student> studenti = new ArrayList();

        ArrayList<Ispit> ispiti = new ArrayList();


        for (int i = 0; i < 2 ; i++) {

            System.out.println("Unesite " + (i+1) + ". profesora: ");

            System.out.println("Unesite sifru profesora: ");
            String sifraProfesora = scanner.nextLine();

            System.out.println("Unesite ime profesora: ");
            String imeProfesora = scanner.nextLine();

            System.out.println("Unesite prezime profesora: ");
            String prezimeProfesora = scanner.nextLine();

            System.out.println("Unesite titulu profesora: ");
            String titulaProfesora = scanner.nextLine();

            profesori.add(new Profesor(sifraProfesora,imeProfesora,prezimeProfesora,titulaProfesora));

        }

        for (int i = 0; i < 3 ; i++) {

            System.out.println("Unesite "+ (i+1) +". predmet: ");

            System.out.println("Unesite šifru predmeta: ");
            String sifraPredmeta = scanner.nextLine();


            System.out.println("Unesite naziv predmeta: ");
            String nazivPredmeta = scanner.nextLine();

            System.out.println("Unesite broj ECTS bodova za predmet '" + nazivPredmeta + "': ");
            Integer brojEctsBodova = scanner.nextInt();

            System.out.println("Odaberite profesora: ");

            for (int j = 0; j < profesori.size(); j++) {

                System.out.println((j+1) + ". " + profesori.get(j).getIme() + " " + profesori.get(j).getPrezime());

            }

            Integer odabraniProfesor = scanner.nextInt();

            System.out.println("Unesite broj studenata za predmetu '" + nazivPredmeta + "': ");
            Integer brojStudenata = scanner.nextInt();
            scanner.nextLine();

            Student[] students = new Student[brojStudenata];
            predmeti.add(new Predmet(sifraPredmeta,nazivPredmeta,brojEctsBodova,profesori.get(odabraniProfesor -1),students));

        }


        for (int i = 0; i < 3 ; i++) {

            System.out.println("Unesite " + (i+1) + ". studenta: ");

            System.out.println("Unesite ime studenta: ");
            String imeStudenta = scanner.nextLine();

            System.out.println("Unesite prezime studenta: ");
            String prezimeStudenta = scanner.nextLine();

            System.out.println("Unesite datum rodenja studenta " + imeStudenta + " " + prezimeStudenta + " u formatu (dd.MM.yyyy)");
            String datumRodjena = scanner.nextLine();

            System.out.println("Unesite JMBAG studenta: " + imeStudenta + " " + prezimeStudenta + ":");
            String jmbagStudenta = scanner.nextLine();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
            LocalDate dateTime = LocalDate.parse(datumRodjena, formatter);

            studenti.add(new Student(imeStudenta,prezimeStudenta,jmbagStudenta,dateTime));

        }


        for (int i = 0; i < 1 ; i++) {

            System.out.println("Odaberite predmet: ");

            for (int j = 0; j < predmeti.size(); j++) {

                System.out.println((j+1) + ". " + predmeti.get(j).getNaziv());

            }

            Integer odabirPredemta = scanner.nextInt();

            System.out.println("Odabir studenta");

            for (int j = 0; j < studenti.size(); j++) {

                System.out.println((j+1) + ". " + studenti.get(j).getIme() + " " + studenti.get(j).getPrezime());

            }

            Integer odabirStudenta = scanner.nextInt();

            System.out.println("Unesite ocjenu na ispitu (1-5)");
            Integer ocjenaIspita = scanner.nextInt();
            scanner.nextLine();
            String ocjenaUText = null;

            System.out.println("Unesite datum i vrijeme ispita u formatu (dd.MM.yyyy.THH:mm): ");
            String datumIVrijemeIspita = scanner.nextLine();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.'T'HH:mm", Locale.getDefault());

            LocalDateTime date = LocalDateTime.parse(datumIVrijemeIspita, dtf);

            ispiti.add(new Ispit(predmeti.get(odabirPredemta -1),studenti.get(odabirStudenta - 1),ocjenaIspita,date));


            if(ocjenaIspita.equals(1)){
                ocjenaUText = "nedovoljan";
            }
            else if (ocjenaIspita.equals(2)) {
                ocjenaUText = "dovoljan";
            }

            else if (ocjenaIspita.equals(3)) {
                ocjenaUText = "dobar";
            }

            else if (ocjenaIspita.equals(4)) {
                ocjenaUText = "vrlo dobar";
            }

            else if (ocjenaIspita.equals(5)) {
                ocjenaUText = "izvrstan";
            }

            for (int j = 0; j < ispiti.size(); j++) {
                System.out.println("Student " + ispiti.get(j).getStudent().getIme() + " " + ispiti.get(j).getStudent().getPrezime() +"  je ostvario ocjenu '" + ocjenaUText + "' na predmetu '" + ispiti.get(j).getPredmet().getNaziv() + "'");
            }



        }


    }
}
