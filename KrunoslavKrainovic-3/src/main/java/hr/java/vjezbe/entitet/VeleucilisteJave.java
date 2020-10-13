package hr.java.vjezbe.entitet;


import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska {

    final static Logger logger = LoggerFactory.getLogger(VeleucilisteJave.class);

    public VeleucilisteJave() {

    }

    public VeleucilisteJave(List<Predmet> predmeti,List<Profesor> profesori,List<Student> studenti,List<Ispit> ispiti) {
        super(predmeti,profesori,studenti,ispiti);
    }

    public VeleucilisteJave(List<Ispit> ispiti,List<Student> student) {
        super(ispiti,student);
    }


    @Override
    public Student odrediNajuspjesnijegStudentaNaGodini(Integer godina) throws NemoguceOdreditiProsjekStudentaException {

        logger.info("START: odrediNajuspjesnijegStudentaNaGodini()");
        logger.debug("Input {}", godina);



        double najvecaOcjena = 0;

        Integer indexStudentaSNajvecimProsjekom = null;
        double prosjekOcjena = 1;
        for (int i = 0; i < super.getStudenti().size(); i++) {

            List<Ispit> ispits = filtrirajIspitePoStudentu(super.getIspiti(),super.getStudenti().get(i));

            try{
                 prosjekOcjena = odrediProsjekOcjenaNaIspitima(ispits).doubleValue();
            }catch (NemoguceOdreditiProsjekStudentaException e){
                logger.info("Student " + super.getStudenti().get(i).getIme() + " " + super.getStudenti().get(i).getPrezime() +" zbog negativne ocjene na jednom od ispita ima prosjek nedovoljan (1)!" ,e);
            }


            if ( prosjekOcjena >= najvecaOcjena){

                    najvecaOcjena = prosjekOcjena;

                    indexStudentaSNajvecimProsjekom = i;

                }
        }




        Student student = super.getStudenti().get(indexStudentaSNajvecimProsjekom);
        logger.info("END: odrediNajuspjesnijegStudentaNaGodini()");
        logger.debug("OUTPUT: {}", student);

        return student;
    }



    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, Integer ocjenaPismenogDijela, Integer ocjenaZavrsnogRada) throws NemoguceOdreditiProsjekStudentaException {

        logger.info("START: izracunajKonacnuOcjenuStudijaZaStudenta()");
        logger.debug("Input {},{},{}", ispiti,ocjenaPismenogDijela,ocjenaZavrsnogRada);


        BigDecimal nedovoljan = new BigDecimal(1);

        BigDecimal ocjenaPismenogIZavrsnogDijela = new BigDecimal(ocjenaPismenogDijela + ocjenaZavrsnogRada);

        BigDecimal prosjecnaOcjenaIspita = new BigDecimal(0);

        BigDecimal dva = new BigDecimal(2);
        try {
            prosjecnaOcjenaIspita = prosjecnaOcjenaIspita.add(dva.multiply(odrediProsjekOcjenaNaIspitima(ispiti)));
        }catch (NemoguceOdreditiProsjekStudentaException e){
            logger.info("Iznimka" ,e);
            System.out.println("Student " + ispiti.get(0).getStudent().getIme() + " " +  ispiti.get(0).getStudent().getPrezime() + " zbog negativne ocjene na jednom od ispita ima prosjek nedovoljan (1)!");
            return nedovoljan;
        }


        prosjecnaOcjenaIspita = prosjecnaOcjenaIspita.add(ocjenaPismenogIZavrsnogDijela);

        BigDecimal cetri = new BigDecimal(4);

        prosjecnaOcjenaIspita = prosjecnaOcjenaIspita.divide(cetri);

        BigDecimal prosjecnaOcjenaIspita1 = prosjecnaOcjenaIspita;

        logger.info("END: izracunajKonacnuOcjenuStudijaZaStudenta()");
        logger.debug("OUTPUT: {}", prosjecnaOcjenaIspita1);

        return prosjecnaOcjenaIspita1;

    }

    public List<Ispit> filtrirajIspitePoStudentu(List<Ispit> ispiti,Student student){
        logger.info("START: filtrirajIspitePoStudentu()");
        logger.debug("Input {},{}", ispiti,student);

        List<Ispit> ispits = Visokoskolska.super.filtrirajIspitePoStudentu(ispiti, student);

        logger.info("END: filtrirajIspitePoStudentu()");
        logger.debug("OUTPUT: {}", ispits);

        return ispits;

    }



}
