package hr.java.vjezbe.entitet;



import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface Visokoskolska {


    BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, Integer ocjenaPismenogDijela, Integer ocjenaZavrsnogRada) throws NemoguceOdreditiProsjekStudentaException;

    default BigDecimal odrediProsjekOcjenaNaIspitima(List<Ispit> ispiti) throws NemoguceOdreditiProsjekStudentaException {
        BigDecimal prosjecnaOcjena = new BigDecimal(0);

        BigDecimal nedovoljan = new BigDecimal(1);

        String negativanIspitIliFalse = filtrirajNedovoljneIspite(ispiti);

         if(!negativanIspitIliFalse.equals("false")){
             throw new NemoguceOdreditiProsjekStudentaException(ispiti.get(0).getStudent().getIme(),ispiti.get(0).getStudent().getPrezime(),negativanIspitIliFalse);
         }



        for (int i = 0; i < ispiti.size(); i++) {

            prosjecnaOcjena = prosjecnaOcjena.add(BigDecimal.valueOf(ispiti.get(i).getOcjena()));

        }

        if (ispiti.size() >= 1) {

            return prosjecnaOcjena.divide(BigDecimal.valueOf(ispiti.size()));
        }
        else
            return nedovoljan;


    }

    private List<Ispit> filtrirajPolozeneIspite(List<Ispit> ispiti) {
        List<Ispit> pozitivniIspiti = new ArrayList();

        for (int i = 0; i < ispiti.size(); i++) {
            if (ispiti.get(i).getOcjena() > 1) {
                pozitivniIspiti.add(ispiti.get(i));
            }
        }


        return pozitivniIspiti;
    }

    private String filtrirajNedovoljneIspite(List<Ispit> ispiti) {


        for (int i = 0; i < ispiti.size(); i++) {
            if (ispiti.get(i).getOcjena() == 1) {
                return ispiti.get(i).getPredmet().getNaziv();
            }
        }


        return "false";
    }

     default List<Ispit> filtrirajIspitePoStudentu(List<Ispit> ispiti,Student student){

         List<Ispit> ispitiStudenta = new ArrayList();

         for (int i = 0; i < ispiti.size(); i++) {

             if (ispiti.get(i).getStudent().getJmbag() == student.getJmbag()) {
                 ispitiStudenta.add(ispiti.get(i));
             }

         }

        return ispitiStudenta;

     }



}
