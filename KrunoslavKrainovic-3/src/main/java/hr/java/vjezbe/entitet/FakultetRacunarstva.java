package hr.java.vjezbe.entitet;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;
import hr.java.vjezbe.iznimke.PostojiViseNajmladjihStudenataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski {

    final static Logger logger = LoggerFactory.getLogger(FakultetRacunarstva.class);



    public FakultetRacunarstva() {}

    public FakultetRacunarstva( List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti, List<Ispit> ispiti) {
        super( predmeti, profesori, studenti, ispiti);
    }


    public FakultetRacunarstva(List<Student> studenti, List<Ispit> ispiti) {
        super(ispiti, studenti);
    }


    @Override
    public Student odrediStudentaZaRektorovuNagradu()
        throws NemoguceOdreditiProsjekStudentaException, PostojiViseNajmladjihStudenataException {

        logger.info("START: odrediStudentaZaRektorovuNagradu()");




        BigDecimal prosjekOcjenaPrethodnogStudenta = new BigDecimal(0);

        BigDecimal prosjekOcjenaStudenta;

        List<Ispit> ispitiStudenta;

        Integer indexStudenta = null;

        List<Student> studentiSNajvecimProsjekom = new ArrayList();

        for (int i = 0; i < super.getStudenti().size(); i++) {

            ispitiStudenta =  filtrirajIspitePoStudentu(super.getIspiti(),super.getStudenti().get(i));

            prosjekOcjenaStudenta = odrediProsjekOcjenaNaIspitima(ispitiStudenta);

            if(prosjekOcjenaStudenta.doubleValue() > prosjekOcjenaPrethodnogStudenta.doubleValue()){

                prosjekOcjenaPrethodnogStudenta = odrediProsjekOcjenaNaIspitima(ispitiStudenta);

                indexStudenta = i;
            }

        }


        studentiSNajvecimProsjekom.add(super.getStudenti().get(indexStudenta));

        for (int i = 0; i < super.getStudenti().size(); i++) {

            ispitiStudenta =  filtrirajIspitePoStudentu(super.getIspiti(),super.getStudenti().get(i));

            prosjekOcjenaStudenta = odrediProsjekOcjenaNaIspitima(ispitiStudenta);

            if(prosjekOcjenaPrethodnogStudenta.doubleValue() ==  prosjekOcjenaStudenta.doubleValue() && super.getStudenti().get(indexStudenta) != super.getStudenti().get(i)){

                studentiSNajvecimProsjekom.add(super.getStudenti().get(i));

            }

        }

        Integer indexNajmladegStudenta = 0;

        for (int i = 0; i < studentiSNajvecimProsjekom.size(); i++) {

            if (studentiSNajvecimProsjekom.get(i).getDatumRodjena().isBefore(studentiSNajvecimProsjekom.get(indexNajmladegStudenta).getDatumRodjena())){

                indexNajmladegStudenta = i;

            }

        }

        List<Student> najmladiStudenti = new ArrayList();
        najmladiStudenti.add(studentiSNajvecimProsjekom.get(indexNajmladegStudenta));

        for (int i = 0; i < studentiSNajvecimProsjekom.size(); i++) {

            if (studentiSNajvecimProsjekom.get(i).getDatumRodjena().isEqual(studentiSNajvecimProsjekom.get(indexNajmladegStudenta).getDatumRodjena()) && !studentiSNajvecimProsjekom.get(indexNajmladegStudenta).equals(studentiSNajvecimProsjekom.get(i))){
                najmladiStudenti.add(studentiSNajvecimProsjekom.get(i));

            }

        }
        if (najmladiStudenti.size() > 1){
            String defaultMessage = "Pronađeno je više najmlađih studenata s istim datumom rođenja, a to su ";
            for (int i = 0; i < najmladiStudenti.size(); i++) {
                if(i == (najmladiStudenti.size() - 1)){

                    defaultMessage += najmladiStudenti.get(i).getIme() + " " + najmladiStudenti.get(i).getPrezime();
                }
                else
                defaultMessage += najmladiStudenti.get(i).getIme() + " " + najmladiStudenti.get(i).getPrezime() + " i ";
            }
            System.out.println("Program završava s izvođenjem.");
            System.out.println(defaultMessage);
            logger.info(defaultMessage,new PostojiViseNajmladjihStudenataException());
            throw new PostojiViseNajmladjihStudenataException(defaultMessage);
        }


        Student student = studentiSNajvecimProsjekom.get(indexNajmladegStudenta);

        logger.info("END: odrediStudentaZaRektorovuNagradu()");
        logger.debug("OUTPUT: {},", student.toString());

        return student;


    }




    public Integer zbrojIzvrsnihOcjena(List<Ispit> ispiti){

        logger.info("START: zbrojIzvrsnihOcjena()");
        logger.debug("Input {}", ispiti.toString());



        Integer zbrojPetica = 0;


        for (int i = 0; i < ispiti.size() ; i++) {

            if (ispiti.get(i).getOcjena().equals(5)){
                zbrojPetica = zbrojPetica + ispiti.get(i).getOcjena();
            }

        }


        logger.info("END: zbrojIzvrsnihOcjena()");
        logger.debug("OUTPUT: {},", zbrojPetica);

        return zbrojPetica;

    }

    @Override
    public Student odrediNajuspjesnijegStudentaNaGodini(Integer godina) {

        logger.info("START: odrediNajuspjesnijegStudentaNaGodini()");
        logger.debug("Input {}", godina);



        Integer prethodniZbrojPetica = 0;

        Integer zbrojSvihPeticaStudenta;

        ArrayList<Student> studenti = new ArrayList();

        for (int i = 0; i < super.getStudenti().size(); i++) {

            List<Ispit> ispits = filtrirajIspitePoStudentu(super.getIspiti(),super.getStudenti().get(i));

            zbrojSvihPeticaStudenta = zbrojIzvrsnihOcjena(ispits);

            if ( zbrojSvihPeticaStudenta > prethodniZbrojPetica){

                prethodniZbrojPetica = zbrojIzvrsnihOcjena(ispits);

                studenti.add(super.getStudenti().get(i));

            }
        }




        Student student = studenti.get(studenti.size() - 1);

        logger.info("END: odrediNajuspjesnijegStudentaNaGodini()");
        logger.debug("OUTPUT: {}", student.toString());

        return student;
    }

    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, Integer ocjenaDiplomskogRada, Integer ocjenaObraneDiplomskogRada) throws NemoguceOdreditiProsjekStudentaException {

        logger.info("START: izracunajKonacnuOcjenuStudijaZaStudenta()");
        logger.debug("Input {},{},{}", ispiti.toString(),ocjenaDiplomskogRada,ocjenaObraneDiplomskogRada);

        BigDecimal nedovoljan = new BigDecimal(1);


        BigDecimal ocjenaPismenogIZavrsnogDijela = new BigDecimal(ocjenaDiplomskogRada + ocjenaObraneDiplomskogRada);

        BigDecimal prosjecnaOcjenaIspita = new BigDecimal(0);

        BigDecimal tri = new BigDecimal(3);

        try {
            prosjecnaOcjenaIspita = prosjecnaOcjenaIspita.add(tri.multiply(odrediProsjekOcjenaNaIspitima(ispiti)));
        }catch (NemoguceOdreditiProsjekStudentaException e){
            logger.info("Iznimka" ,e);
            System.out.println("Student " + ispiti.get(0).getStudent().getIme() + " " +  ispiti.get(0).getStudent().getPrezime() + " zbog negativne ocjene na jednom od ispita ima prosjek nedovoljan (1)!");
            return nedovoljan;
        }

        prosjecnaOcjenaIspita = prosjecnaOcjenaIspita.add(ocjenaPismenogIZavrsnogDijela);

        BigDecimal pet = new BigDecimal(5);

        prosjecnaOcjenaIspita = prosjecnaOcjenaIspita.divide(pet);




        BigDecimal prosjecnaOcjenaIspita1 = prosjecnaOcjenaIspita;
        logger.info("END: izracunajKonacnuOcjenuStudijaZaStudenta()");
        logger.debug("OUTPUT: {}", prosjecnaOcjenaIspita1);

        return prosjecnaOcjenaIspita1;
    }

    public List<Ispit> filtrirajIspitePoStudentu(List<Ispit> ispiti,Student student){
        logger.info("START: filtrirajIspitePoStudentu()");
        logger.debug("Input {},{}", ispiti.toString(),student.toString());

        List<Ispit> ispits = Diplomski.super.filtrirajIspitePoStudentu(ispiti, student);

        logger.info("END: filtrirajIspitePoStudentu()");
        logger.debug("OUTPUT: {}", ispits.toString());
        return ispits;

    }

}
