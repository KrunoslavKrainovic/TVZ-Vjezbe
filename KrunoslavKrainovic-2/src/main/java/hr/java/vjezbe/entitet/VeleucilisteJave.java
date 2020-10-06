package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.ArrayList;

public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska {


    public VeleucilisteJave() {

    }

    public VeleucilisteJave(String naziv,ArrayList<Predmet> predmeti,ArrayList<Profesor> profesori,ArrayList<Student> studenti,ArrayList<Ispit> ispiti) {
        super(naziv,predmeti,profesori,studenti,ispiti);
    }

    public VeleucilisteJave(ArrayList<Ispit> ispiti,ArrayList<Student> student) {
        super(ispiti,student);
    }


    @Override
    public Student odrediNajuspjesnijegStudentaNaGodini(Integer godina) {
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



        return super.getStudenti().get(indexStudentaSNajvecimProsjekom);
    }



    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(ArrayList<Ispit> ispiti, Integer ocjenaPismenogDijela, Integer ocjenaZavrsnogRada) {

        BigDecimal ocjenaPismenogIZavrsnogDijela = new BigDecimal(ocjenaPismenogDijela + ocjenaZavrsnogRada);

        BigDecimal prosjecnaOcjenaIspita = new BigDecimal(0);

        BigDecimal dva = new BigDecimal(2);

        prosjecnaOcjenaIspita = prosjecnaOcjenaIspita.add(dva.multiply(odrediProsjekOcjenaNaIspitima(ispiti)));

        prosjecnaOcjenaIspita = prosjecnaOcjenaIspita.add(ocjenaPismenogIZavrsnogDijela);

        BigDecimal cetri = new BigDecimal(4);

        prosjecnaOcjenaIspita = prosjecnaOcjenaIspita.divide(cetri);

        return prosjecnaOcjenaIspita;

    }

    public ArrayList<Ispit> filtrirajIspitePoStudentu(ArrayList<Ispit> ispiti,Student student){
        return Visokoskolska.super.filtrirajIspitePoStudentu(ispiti,student);

    }



}
