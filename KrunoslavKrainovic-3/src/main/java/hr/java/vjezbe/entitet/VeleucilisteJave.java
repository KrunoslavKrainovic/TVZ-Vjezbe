package hr.java.vjezbe.entitet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;

public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska {

    final static Logger logger = LoggerFactory.getLogger(VeleucilisteJave.class);

    public VeleucilisteJave() {

    }

    public VeleucilisteJave(ArrayList<Predmet> predmeti,ArrayList<Profesor> profesori,ArrayList<Student> studenti,ArrayList<Ispit> ispiti) {
        super(predmeti,profesori,studenti,ispiti);
    }

    public VeleucilisteJave(ArrayList<Ispit> ispiti,ArrayList<Student> student) {
        super(ispiti,student);
    }


    @Override
    public Student odrediNajuspjesnijegStudentaNaGodini(Integer godina) {

        logger.info("START: odrediNajuspjesnijegStudentaNaGodini()");
        logger.debug("Input {}", godina);



        double najvecaOcjena = 0;

        Integer indexStudentaSNajvecimProsjekom = null;

        for (int i = 0; i < super.getStudenti().size(); i++) {

            ArrayList<Ispit> ispits = filtrirajIspitePoStudentu(super.getIspiti(),super.getStudenti().get(i));

            double prosjekOcjena = odrediProsjekOcjenaNaIspitima(ispits).doubleValue();

            if ( prosjekOcjena >= najvecaOcjena){

                    najvecaOcjena = odrediProsjekOcjenaNaIspitima(filtrirajIspitePoStudentu(super.getIspiti(),super.getStudenti().get(i))).doubleValue();

                    indexStudentaSNajvecimProsjekom = i;

                }
        }




        Student student = super.getStudenti().get(indexStudentaSNajvecimProsjekom);
        logger.info("END: odrediNajuspjesnijegStudentaNaGodini()");
        logger.debug("OUTPUT: {}", student);

        return student;
    }



    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(ArrayList<Ispit> ispiti, Integer ocjenaPismenogDijela, Integer ocjenaZavrsnogRada) {

        logger.info("START: izracunajKonacnuOcjenuStudijaZaStudenta()");
        logger.debug("Input {},{},{}", ispiti,ocjenaPismenogDijela,ocjenaZavrsnogRada);



        BigDecimal ocjenaPismenogIZavrsnogDijela = new BigDecimal(ocjenaPismenogDijela + ocjenaZavrsnogRada);

        BigDecimal prosjecnaOcjenaIspita = new BigDecimal(0);

        BigDecimal dva = new BigDecimal(2);

        prosjecnaOcjenaIspita = prosjecnaOcjenaIspita.add(dva.multiply(odrediProsjekOcjenaNaIspitima(ispiti)));

        prosjecnaOcjenaIspita = prosjecnaOcjenaIspita.add(ocjenaPismenogIZavrsnogDijela);

        BigDecimal cetri = new BigDecimal(4);

        prosjecnaOcjenaIspita = prosjecnaOcjenaIspita.divide(cetri);

        BigDecimal prosjecnaOcjenaIspita1 = prosjecnaOcjenaIspita;

        logger.info("END: izracunajKonacnuOcjenuStudijaZaStudenta()");
        logger.debug("OUTPUT: {}", prosjecnaOcjenaIspita1);

        return prosjecnaOcjenaIspita1;

    }

    public ArrayList<Ispit> filtrirajIspitePoStudentu(ArrayList<Ispit> ispiti,Student student){
        logger.info("START: filtrirajIspitePoStudentu()");
        logger.debug("Input {},{}", ispiti,student);

        ArrayList<Ispit> ispits = Visokoskolska.super.filtrirajIspitePoStudentu(ispiti, student);

        logger.info("END: filtrirajIspitePoStudentu()");
        logger.debug("OUTPUT: {}", ispits);

        return ispits;

    }



}
