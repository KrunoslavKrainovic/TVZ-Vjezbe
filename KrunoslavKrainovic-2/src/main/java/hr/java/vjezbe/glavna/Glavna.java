package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Glavna {

    public static ArrayList<Profesor> unosProfesora(Scanner scanner, ArrayList<Profesor> profesori) {

        scanner.nextLine();
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
        return profesori;
    }

    public static ArrayList<Predmet> unosPredmeta (Scanner scanner, ArrayList<Predmet> predmeti, ArrayList<Profesor> profesori ) {

        for (int i = 0; i < 2 ; i++) {

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

        return predmeti;
    }

    public static ArrayList<Student> unosStudenta(Scanner scanner,ArrayList<Student> studenti){

        for (int i = 0; i < 2 ; i++) {

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
        return studenti;

    }

    public static ArrayList<Ispit> unosIspita(Scanner scanner,ArrayList<Ispit> ispiti, ArrayList<Student> studenti,ArrayList<Predmet> predmeti) {

        for (int i = 0; i < 2 ; i++) {

            System.out.println("Unesite " +  (i + 1) + ". ispitni rok");

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

        return ispiti;
    }

    public static void unosObrazovneUstanove(Scanner scanner,ArrayList<Ispit> ispiti, ArrayList<Student> studenti,ArrayList<Predmet> predmeti,ArrayList<Profesor> profesori) {

        ObrazovnaUstanova ustanova = null;




        String nazivUstanove;
        Integer ocjenaZavrsnogRada;
        Integer ocjenaObranaZavrsnogRada;


        System.out.println("Odaberite obrazovnu ustanovu za navedene podatke koju želite unijeti (1 - Veleučilište Jave, 2 - Fakultet računarstva): ");
        Integer odabirUstanove = scanner.nextInt();
        scanner.nextLine();
        
        if(odabirUstanove == 1){
            ustanova = new VeleucilisteJave();
        }

        if(odabirUstanove == 2){
            ustanova = new FakultetRacunarstva();
        }

        System.out.println("Unesite naziv obrazovne ustanove: ");
        
        nazivUstanove = scanner.nextLine();



        for (int i = 0; i < studenti.size(); i++) {

            System.out.println("Unesite ocjenu završnog rada za studenta: " + studenti.get(i).getIme() + " " + studenti.get(i).getPrezime() + ":");

            ocjenaZavrsnogRada = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Unesite ocjenu obrane završnog rada za studenta: "  + studenti.get(i).getIme() + " " + studenti.get(i).getPrezime() + ":");

            ocjenaObranaZavrsnogRada = scanner.nextInt();
            scanner.nextLine();

            if(ustanova instanceof VeleucilisteJave){

                System.out.println("Konačna ocjena studija studenta " + studenti.get(i).getIme() + " " + studenti.get(i).getPrezime() + " " +((VeleucilisteJave) ustanova).izracunajKonacnuOcjenuStudijaZaStudenta( ((VeleucilisteJave) ustanova).filtrirajIspitePoStudentu(ispiti,studenti.get(i)), ocjenaZavrsnogRada, ocjenaObranaZavrsnogRada));
            }
            if(ustanova instanceof FakultetRacunarstva){

                System.out.println("Konačna ocjena studija studenta " + studenti.get(i).getIme() +  " " +studenti.get(i).getPrezime() + " " +((FakultetRacunarstva) ustanova).izracunajKonacnuOcjenuStudijaZaStudenta(((FakultetRacunarstva) ustanova).filtrirajIspitePoStudentu(ispiti,studenti.get(i)), ocjenaZavrsnogRada, ocjenaObranaZavrsnogRada));
            }

        }

        if(odabirUstanove == 1){
            ustanova = new VeleucilisteJave(nazivUstanove,predmeti,profesori,studenti,ispiti);
        }

        if(odabirUstanove == 2){
            ustanova = new FakultetRacunarstva(nazivUstanove,predmeti,profesori,studenti,ispiti);
        }

        if(ustanova instanceof VeleucilisteJave){

            Student najuspijesniStudent = ((VeleucilisteJave) ustanova).odrediNajuspjesnijegStudentaNaGodini(2018);

            System.out.println("Najbolji student 2018. godine je " + najuspijesniStudent.getIme() + " " +najuspijesniStudent.getPrezime() + " JMBAG: " + najuspijesniStudent.getJmbag());
        }

        if(ustanova instanceof FakultetRacunarstva){

            Student rektorovaNagrada = ((FakultetRacunarstva) ustanova).odrediStudentaZaRektorovuNagradu();

            Student najuspijesniStudent = ((FakultetRacunarstva) ustanova).odrediNajuspjesnijegStudentaNaGodini(2018);

            System.out.println("Najbolji student 2018. godine je " + najuspijesniStudent.getIme() + " " +najuspijesniStudent.getPrezime() + " JMBAG: " + najuspijesniStudent.getJmbag());

            System.out.println("Student koji je osvojio rektorovu nagradu je: " + rektorovaNagrada.getIme() + " " + rektorovaNagrada.getPrezime() + " JMBAG: " + rektorovaNagrada.getJmbag());

        }


    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Unesite broj obrazovnih ustanova: ");

        Integer brojUstanova = scanner.nextInt();

        for (int i = 0; i < brojUstanova; i++) {

            System.out.println("Unesite podatke za " + (i + 1) +". obrazovnu ustanovu: ");

            ArrayList<Profesor> profesori = new ArrayList();

            profesori = unosProfesora(scanner,profesori);

            ArrayList<Predmet> predmeti = new ArrayList();

            predmeti = unosPredmeta(scanner,predmeti,profesori);

            ArrayList<Student> studenti = new ArrayList();

            studenti = unosStudenta(scanner,studenti);

            ArrayList<Ispit> ispiti = new ArrayList();

            ispiti = unosIspita(scanner,ispiti,studenti,predmeti);

            unosObrazovneUstanove(scanner,ispiti,studenti,predmeti,profesori);



        }





    }
}
