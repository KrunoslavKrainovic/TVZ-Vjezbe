package hr.java.vjezbe.glavna;


import hr.java.vjezbe.entitet.*;
import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;
import hr.java.vjezbe.iznimke.PostojiViseNajmladjihStudenataException;
import hr.java.vjezbe.sortiranje.StudentSorter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Glavna {

    final static Logger logger = LoggerFactory.getLogger(Glavna.class);


    public static List<Profesor> unosProfesora(Scanner scanner, List<Profesor> profesori) {

        logger.info("START: unosProfesora()");
        logger.debug("Input {},{}", scanner,profesori.toString());

        scanner.nextLine();
        for (int i = 0; i < 2; i++) {

            System.out.println("Unesite " + (i + 1) + ". profesora: ");

            System.out.println("Unesite sifru profesora: ");
            String sifraProfesora = scanner.nextLine();

            System.out.println("Unesite ime profesora: ");
            String imeProfesora = scanner.nextLine();

            System.out.println("Unesite prezime profesora: ");
            String prezimeProfesora = scanner.nextLine();

            System.out.println("Unesite titulu profesora: ");
            String titulaProfesora = scanner.nextLine();

            profesori.add(new Profesor(sifraProfesora, imeProfesora, prezimeProfesora, titulaProfesora));


        }
        List<Profesor> profesoris = profesori;

        logger.info("END: unosProfesora()");
        logger.debug("OUTPUT: {}", profesoris.toString());



        return profesoris;
    }

    public static List<Predmet> unosPredmeta(Scanner scanner, List<Predmet> predmeti, List<Profesor> profesori) {

        logger.info("START: unosPredmeta()");
        logger.debug("Input {},{},{}", scanner,predmeti.toString(),profesori.toString());

        boolean tocanUnos;
        Integer brojEctsBodova = 0;
        Integer odabraniProfesor = 0;
        Integer brojStudenata = 0;
        boolean unosPredmetaIProfesora = false;
        boolean prviProfesor = false;
        boolean drugiProfesor = false;
        boolean viseOdJednogPredmeta = false;
        Integer brojPredmeta = 0;
  do {
      for (int i = 0; i < 3; i++) {

          System.out.println("Unesite " + (i + 1) + ". predmet: ");

          System.out.println("Unesite šifru predmeta: ");
          String sifraPredmeta = scanner.nextLine();


          System.out.println("Unesite naziv predmeta: ");
          String nazivPredmeta = scanner.nextLine();


          do {
              System.out.println("Unesite broj ECTS bodova za predmet '" + nazivPredmeta + "': ");
              try {
                  brojEctsBodova = scanner.nextInt();
                  tocanUnos = true;
              } catch (Exception e) {
                  scanner.nextLine();
                  logger.info("Upisano je slovo umjesto broja " + e.getMessage());
                  System.out.println("Ponovite unos");
                  tocanUnos = false;
              }
          } while (tocanUnos == false);

          System.out.println("Odaberite profesora: ");

          do {
              for (int j = 0; j < profesori.size(); j++) {
                  System.out.println((j + 1) + ". " + profesori.get(j).getIme() + " " + profesori.get(j).getPrezime());
              }
              try {
                  odabraniProfesor = scanner.nextInt();
                  tocanUnos = true;
              } catch (Exception e) {
                  scanner.nextLine();
                  logger.info("Upisano je slovo umjesto broja ");
                  System.out.println("Ponovite unos");
                  tocanUnos = false;
              }
          } while (tocanUnos == false);


          scanner.nextLine();

          Set<Student> students = new HashSet<>();
          predmeti.add(new Predmet(sifraPredmeta, nazivPredmeta, brojEctsBodova, profesori.get(odabraniProfesor - 1), students));

      }

      for (int i = 0; i < predmeti.size(); i++) {

          if(profesori.get(0) == predmeti.get(i).getNositelj()){
              prviProfesor = true;
          }
          if(profesori.get(1) == predmeti.get(i).getNositelj()){
              drugiProfesor = true;
          }

      }

      for (int i = 0; i < profesori.size(); i++) {
          for (int j = 0; j < predmeti.size(); j++) {
              if (profesori.get(i) == predmeti.get(j).getNositelj()){
                  brojPredmeta++;
              }

              if (brojPredmeta > 1) {
                  viseOdJednogPredmeta = true;
              }
          }
          brojPredmeta = 0;
      }

      if (prviProfesor && drugiProfesor && viseOdJednogPredmeta){
          unosPredmetaIProfesora = true;
      }

  }
  while(unosPredmetaIProfesora == false);

        HashMap<Profesor,List<Predmet>> predmetListHashMap = new HashMap<>();
        List<Predmet> predmetiProfesoraZaDrugiPredemt = new ArrayList<>();
        List<Predmet> predmetiProfesoraZaPrviPredemt = new ArrayList<>();

        for (int i = 0; i < predmeti.size(); i++) {

            if(predmeti.get(i).getNositelj() == profesori.get(0)){
                predmetiProfesoraZaPrviPredemt.add(predmeti.get(i));
            }
            if(predmeti.get(i).getNositelj() == profesori.get(1)){
                predmetiProfesoraZaDrugiPredemt.add(predmeti.get(i));
            }

        }
        predmetListHashMap.put(profesori.get(0),predmetiProfesoraZaPrviPredemt);
        predmetListHashMap.put(profesori.get(1),predmetiProfesoraZaDrugiPredemt);


        for (Profesor i : predmetListHashMap.keySet()) {
            System.out.println("Profesor " + i.getIme() + " " + i.getPrezime() + " predaje sljedeće predmete: ");
            for (int j = 0; j < predmetListHashMap.get(i).size(); j++) {
                System.out.println((j+1) + ") " + predmetListHashMap.get(i).get(j).getNaziv());
            }

        }

        List<Predmet> predmetis = predmeti;

        logger.info("END: unosPredmeta()");
        logger.debug("OUTPUT: {}", predmetis.toString());

        return predmetis;
    }

    public static List<Student> unosStudenta(Scanner scanner, List<Student> studenti) {

        logger.info("START: unosStudenta()");
        logger.debug("Input {},{}", scanner,studenti.toString());


        for (int i = 0; i < 2; i++) {

            System.out.println("Unesite " + (i + 1) + ". studenta: ");

            System.out.println("Unesite ime studenta: ");
            String imeStudenta = scanner.nextLine();

            System.out.println("Unesite prezime studenta: ");
            String prezimeStudenta = scanner.nextLine();

            System.out.println("Unesite datum rodenja studenta " + imeStudenta + " " + prezimeStudenta + " u formatu (dd.MM.yyyy.)");
            String datumRodjena = scanner.nextLine();

            System.out.println("Unesite JMBAG studenta: " + imeStudenta + " " + prezimeStudenta + ":");
            String jmbagStudenta = scanner.nextLine();
            //-------ODRADITI PROVJERU DA SE NE MOZE UNIJETI BILO KAKAV STRING-------------------------

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
            LocalDate dateTime = LocalDate.parse(datumRodjena, formatter);

            studenti.add(new Student(imeStudenta, prezimeStudenta, jmbagStudenta, dateTime));

        }
        List<Student> studentis = studenti;
        logger.info("END: unosStudenta()");
        logger.debug("OUTPUT: {}", studentis.toString());
        return studentis;

    }

    public static List<Ispit> unosIspita(Scanner scanner, List<Ispit> ispiti, List<Student> studenti, List<Predmet> predmeti) {

        logger.info("START: unosIspita()");
        logger.debug("Input {},{},{},{}", ispiti.toString(),scanner,studenti.toString(),predmeti.toString());



        boolean tocanUnos;
        Integer odabirPredemta = 0;
        Integer odabirStudenta = 0;
        Integer ocjenaIspita = 0;

        for (int i = 0; i < 2; i++) {

            System.out.println("Unesite " + (i + 1) + ". ispitni rok");

            System.out.println("Odaberite predmet: ");

            do {
                for (int j = 0; j < predmeti.size(); j++) {
                    System.out.println((j + 1) + ". " + predmeti.get(j).getNaziv());
                }
                try {
                    odabirPredemta = scanner.nextInt();
                    tocanUnos = true;
                } catch (Exception e) {
                    scanner.nextLine();
                    logger.info("Upisano je slovo umjesto broja " + e.getMessage());
                    System.out.println("Ponovite unos");
                    tocanUnos = false;
                }
            } while (tocanUnos == false);



            System.out.println("Odabir studenta");

            do {
                for (int j = 0; j < studenti.size(); j++) {
                    System.out.println((j + 1) + ". " + studenti.get(j).getIme() + " " + studenti.get(j).getPrezime());
                }
                try {
                    odabirStudenta = scanner.nextInt();
                    tocanUnos = true;
                } catch (Exception e) {
                    scanner.nextLine();
                    logger.info("Upisano je slovo umjesto broja " + e.getMessage());
                    System.out.println("Ponovite unos");
                    tocanUnos = false;
                }
            } while (tocanUnos == false);

            do {
                System.out.println("Unesite ocjenu na ispitu (1-5)");
                try {
                    ocjenaIspita = scanner.nextInt();
                    tocanUnos = true;
                } catch (Exception e) {
                    scanner.nextLine();
                    logger.info("Upisano je slovo umjesto broja " + e.getMessage());
                    System.out.println("Ponovite unos");
                    tocanUnos = false;
                }
            } while (tocanUnos == false);


            scanner.nextLine();

            System.out.println("Unesite datum i vrijeme ispita u formatu (dd.MM.yyyy.THH:mm): ");
            String datumIVrijemeIspita = scanner.nextLine();


            //Stavi studente u Set pa stavi u predmet,ako taj predmet postoji stavi
            Set<Student> hs = new HashSet<Student>();
            hs.add(studenti.get(odabirStudenta - 1));
            predmeti.get(odabirPredemta - 1).setStudenti(hs);


            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.'T'HH:mm", Locale.getDefault());

            LocalDateTime date = LocalDateTime.parse(datumIVrijemeIspita, dtf);

            ispiti.add(new Ispit(predmeti.get(odabirPredemta - 1), studenti.get(odabirStudenta - 1), ocjenaIspita, date));




            Ocjena ocjena = null;

            for (int j = 0; j < ispiti.size(); j++) {

                if (ispiti.get(j).getOcjena().equals(1)) {
                   ocjena = Ocjena.nedovoljan;
                } else if (ispiti.get(j).getOcjena().equals(2)) {
                    ocjena = Ocjena.dovoljan;
                } else if (ispiti.get(j).getOcjena().equals(3)) {
                    ocjena = Ocjena.dobar;
                } else if (ispiti.get(j).getOcjena().equals(4)) {
                    ocjena = Ocjena.vrlo_dobar;
                } else if (ispiti.get(j).getOcjena().equals(5)) {
                    ocjena = Ocjena.izvrstan;
                }
                System.out.println("Student " + ispiti.get(j).getStudent().getIme() + " " + ispiti.get(j).getStudent().getPrezime() + "  je ostvario ocjenu '" + ocjena + "' na predmetu '" + ispiti.get(j).getPredmet().getNaziv() + "'");
            }


        }



        ispisPredmeta( ispiti);
        predmetiBezStudenata(ispiti, predmeti);




        Arrays.asList(ispiti).contains(predmeti);
        List<Ispit> ispitis = ispiti;
        logger.info("END: unosIspita()");
        logger.debug("OUTPUT: {}", ispitis.toString());

        return ispitis;

    }

    public static void ispisPredmeta(List<Ispit> ispiti){
        int counterIstihPredmeta = 0;
        List<Student> listaStudenta = new ArrayList<>();
        for (int i = 0; i < ispiti.size(); i++) {

            for (int j = 0; j < ispiti.size(); j++) {

                if (ispiti.get(i) != ispiti.get(j) && ispiti.get(i).getPredmet().getNaziv() == ispiti.get(j).getPredmet().getNaziv()){

                    listaStudenta.add(ispiti.get(j).getStudent());

                    if(counterIstihPredmeta == 0){
                        System.out.println("Studenti upisani na predmet " + ispiti.get(j).getPredmet().getNaziv());
                        counterIstihPredmeta++;
                    }
                    Collections.sort(listaStudenta, new StudentSorter());


                }
            }

        }
        for (int i = 0; i < listaStudenta.size(); i++) {
            System.out.println(listaStudenta.get(i).getIme() + " " + listaStudenta.get(i).getPrezime());
        }
        for (int i = 0; i < ispiti.size(); i++) {
            int counterUniquePredmeta = 0;
            for (int j = 0; j < ispiti.size(); j++) {

                if ( ispiti.get(i).getPredmet().getNaziv() == ispiti.get(j).getPredmet().getNaziv()){
                    counterUniquePredmeta++;

                }

            }
            if(counterUniquePredmeta == 1){
                System.out.println("Studenti upisani na predmet " + ispiti.get(i).getPredmet().getNaziv());
                System.out.println(ispiti.get(i).getStudent().getIme() + " " + ispiti.get(i).getStudent().getPrezime());
            }
        }

    }

    private static void predmetiBezStudenata(List<Ispit> ispiti, List<Predmet> predmeti) {
        List<Predmet> neupisaniPredmeti = new ArrayList<>();

        for (int i = 0; i < ispiti.size(); i++) {
            neupisaniPredmeti.add(ispiti.get(i).getPredmet());
        }

        for (int i = 0; i < predmeti.size(); i++) {
            if(!neupisaniPredmeti.contains(predmeti.get(i))){
                System.out.println("Nema studenata upisanih na predmet '" + predmeti.get(i).getNaziv() + "'.");
            }

        }
    }

    public static void unosObrazovneUstanove(Scanner scanner, List<Ispit> ispiti, List<Student> studenti, List<Predmet> predmeti, List<Profesor> profesori)
        throws NemoguceOdreditiProsjekStudentaException, PostojiViseNajmladjihStudenataException {

        logger.info("START: unosIspita()");
        logger.debug("INPUT: {},{},{},{},{}", scanner,ispiti.toString(),studenti.toString(),predmeti.toString(),profesori.toString());

        ObrazovnaUstanova ustanova = null;


        String nazivUstanove;
        Integer ocjenaZavrsnogRada = 0;
        Integer ocjenaObranaZavrsnogRada = 0;
        Integer odabirUstanove = 0;
        boolean tocanUnos;




        do {
            System.out.println("Odaberite obrazovnu ustanovu za navedene podatke koju želite unijeti (1 - Veleučilište Jave, 2 - Fakultet računarstva): ");
            try {
                odabirUstanove = scanner.nextInt();
                tocanUnos = true;
            } catch (Exception e) {
                scanner.nextLine();
                logger.info("Upisano je slovo umjesto broja " + e.getMessage());
                System.out.println("Ponovite unos");
                tocanUnos = false;
            }
        } while (tocanUnos == false);

        scanner.nextLine();

        if (odabirUstanove == 1) {
            ustanova = new VeleucilisteJave();
        }

        if (odabirUstanove == 2) {
            ustanova = new FakultetRacunarstva();
        }

        System.out.println("Unesite naziv obrazovne ustanove: ");

        nazivUstanove = scanner.nextLine();


        for (int i = 0; i < ispiti.size(); i++) {

            if (ispiti.get(i).getOcjena() > 1) {
                do {
                    System.out.println("Unesite ocjenu završnog rada za studenta: " + ispiti.get(i).getStudent().getIme() + " " + ispiti.get(i).getStudent().getPrezime() + ":");
                    try {
                        ocjenaZavrsnogRada = scanner.nextInt();
                        scanner.nextLine();
                        tocanUnos = true;
                    } catch (Exception e) {
                        scanner.nextLine();
                        logger.info("Upisano je slovo umjesto broja " + e.getMessage());
                        System.out.println("Ponovite unos");
                        tocanUnos = false;
                    }
                } while (tocanUnos == false);



                do {
                    System.out.println("Unesite ocjenu obrane završnog rada za studenta: " + ispiti.get(i).getStudent().getIme() + " " + ispiti.get(i).getStudent().getPrezime() + ":");
                    try {
                        ocjenaObranaZavrsnogRada = scanner.nextInt();
                        scanner.nextLine();
                        tocanUnos = true;
                    } catch (Exception e) {
                        scanner.nextLine();
                        logger.info("Upisano je slovo umjesto broja " + e.getMessage());
                        System.out.println("Ponovite unos");
                        tocanUnos = false;
                    }
                } while (tocanUnos == false);
            } else {
                System.out.println("Student "+ ispiti.get(i).getStudent().getIme() + " " + ispiti.get(i).getStudent().getPrezime() + " zbog negativne ocjene na jednom od ispita ima prosjek „nedovoljan (1)“!");
            }


            if (ustanova instanceof VeleucilisteJave) {

                System.out.println("Konačna ocjena studija studenta " + ispiti.get(i).getStudent().getIme() + " " + ispiti.get(i).getStudent().getPrezime() + " " + ((VeleucilisteJave) ustanova).izracunajKonacnuOcjenuStudijaZaStudenta(((VeleucilisteJave) ustanova).filtrirajIspitePoStudentu(ispiti, ispiti.get(i).getStudent()), ocjenaZavrsnogRada, ocjenaObranaZavrsnogRada));
            }
            if (ustanova instanceof FakultetRacunarstva) {

                System.out.println("Konačna ocjena studija studenta " + ispiti.get(i).getStudent().getIme() + " " + ispiti.get(i).getStudent().getPrezime() + " " + ((FakultetRacunarstva) ustanova).izracunajKonacnuOcjenuStudijaZaStudenta(((FakultetRacunarstva) ustanova).filtrirajIspitePoStudentu(ispiti, ispiti.get(i).getStudent()), ocjenaZavrsnogRada, ocjenaObranaZavrsnogRada));
            }

        }

        if (odabirUstanove.equals(1)) {
            ustanova = new VeleucilisteJave(predmeti, profesori, studenti, ispiti);

            Student najuspijesniStudent = ((VeleucilisteJave) ustanova).odrediNajuspjesnijegStudentaNaGodini(2018);

            System.out.println("Najbolji student 2018. godine je " + najuspijesniStudent.getIme() + " " + najuspijesniStudent.getPrezime() + " JMBAG: " + najuspijesniStudent.getJmbag());

        }

        if (odabirUstanove.equals(2)) {
            ustanova = new FakultetRacunarstva(predmeti, profesori, studenti, ispiti);

            Student najuspijesniStudent = ((FakultetRacunarstva) ustanova).odrediNajuspjesnijegStudentaNaGodini(2018);

            System.out.println("Najbolji student 2018. godine je " + najuspijesniStudent.getIme() + " " + najuspijesniStudent.getPrezime() + " JMBAG: " + najuspijesniStudent.getJmbag());

            Student rektorovaNagrada = ((FakultetRacunarstva) ustanova).odrediStudentaZaRektorovuNagradu();

            System.out.println("Student koji je osvojio rektorovu nagradu je: " + rektorovaNagrada.getIme() + " " + rektorovaNagrada.getPrezime() + " JMBAG: " + rektorovaNagrada.getJmbag());

        }

        logger.info("END: unosObrazovneUstanove()");

    }

    public static void main(String[] args)
        throws NemoguceOdreditiProsjekStudentaException, PostojiViseNajmladjihStudenataException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Unesite broj obrazovnih ustanova: ");

        Integer brojUstanova = scanner.nextInt();

        for (int i = 0; i < brojUstanova; i++) {

            System.out.println("Unesite podatke za " + (i + 1) + ". obrazovnu ustanovu: ");

            List<Profesor> profesori = new ArrayList();

            profesori = unosProfesora(scanner, profesori);

            List<Predmet> predmeti = new ArrayList();

            predmeti = unosPredmeta(scanner, predmeti, profesori);

            List<Student> studenti = new ArrayList();

            studenti = unosStudenta(scanner, studenti);

            List<Ispit> ispiti = new ArrayList();

            ispiti = unosIspita(scanner, ispiti, studenti, predmeti);

            unosObrazovneUstanove(scanner, ispiti, studenti, predmeti, profesori);


        }


    }
}
