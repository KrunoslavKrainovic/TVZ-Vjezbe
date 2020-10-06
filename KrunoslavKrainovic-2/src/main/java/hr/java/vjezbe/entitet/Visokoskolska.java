package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface Visokoskolska {


    BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(ArrayList<Ispit> ispiti, Integer ocjenaPismenogDijela, Integer ocjenaZavrsnogRada);

    default BigDecimal odrediProsjekOcjenaNaIspitima(ArrayList<Ispit> ispiti) {
        BigDecimal prosjecnaOcjena = new BigDecimal(0);

        BigDecimal nedovoljan = new BigDecimal(1);

        ArrayList<Ispit> pozitivniIspiti = filtrirajPolozeneIspite(ispiti);



        for (int i = 0; i < pozitivniIspiti.size(); i++) {

            prosjecnaOcjena = prosjecnaOcjena.add(BigDecimal.valueOf(pozitivniIspiti.get(i).getOcjena()));

        }

        if (pozitivniIspiti.size() >= 1) {

            return prosjecnaOcjena.divide(BigDecimal.valueOf(pozitivniIspiti.size()));
        }
        else
            return nedovoljan;


    }

    private ArrayList<Ispit> filtrirajPolozeneIspite(ArrayList<Ispit> ispiti) {
        ArrayList<Ispit> pozitivniIspiti = new ArrayList();

        for (int i = 0; i < ispiti.size(); i++) {
            if (ispiti.get(i).getOcjena() > 1) {
                pozitivniIspiti.add(ispiti.get(i));
            }
        }


        return pozitivniIspiti;
    }

     default ArrayList<Ispit> filtrirajIspitePoStudentu(ArrayList<Ispit> ispiti,Student student){

            ArrayList<Ispit> ispitiStudenta = new ArrayList();

         for (int i = 0; i < ispiti.size(); i++) {

             if (ispiti.get(i).getStudent().getJmbag() == student.getJmbag()) {
                 ispitiStudenta.add(ispiti.get(i));
             }

         }

        return ispitiStudenta;

     }



}
