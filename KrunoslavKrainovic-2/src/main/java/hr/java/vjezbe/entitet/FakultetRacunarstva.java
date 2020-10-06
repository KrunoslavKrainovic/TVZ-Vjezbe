package hr.java.vjezbe.entitet;


import java.math.BigDecimal;
import java.util.ArrayList;


public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski {


    public FakultetRacunarstva() {}

    public FakultetRacunarstva(String naziv, ArrayList<Predmet> predmeti, ArrayList<Profesor> profesori, ArrayList<Student> studenti, ArrayList<Ispit> ispiti) {
        super(naziv, predmeti, profesori, studenti, ispiti);
    }


    public FakultetRacunarstva(ArrayList<Student> studenti, ArrayList<Ispit> ispiti) {
        super(ispiti, studenti);
    }


    @Override
    public Student odrediStudentaZaRektorovuNagradu() {

        BigDecimal prosjekOcjenaPrethodnogStudenta = new BigDecimal(0);

        BigDecimal prosjekOcjenaStudenta;

        ArrayList<Ispit> ispitiStudenta;

        Integer indexStudenta = null;

        ArrayList<Student> studentiSNajvecimProsjekom = new ArrayList();

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



        return studentiSNajvecimProsjekom.get(indexNajmladegStudenta);
    }




    public Integer zbrojIzvrsnihOcjena(ArrayList<Ispit> ispiti){

        Integer zbrojPetica = 0;


        for (int i = 0; i < ispiti.size() ; i++) {

            if (ispiti.get(i).getOcjena() == 5){
                zbrojPetica = zbrojPetica + ispiti.get(i).getOcjena();
            }

        }

        return zbrojPetica;
    }

    @Override
    public Student odrediNajuspjesnijegStudentaNaGodini(Integer godina) {
        Integer prethodniZbrojPetica = 0;

        Integer zbrojSvihPeticaStudenta;

        ArrayList<Student> studenti = new ArrayList();

        for (int i = 0; i < super.getStudenti().size(); i++) {

            ArrayList<Ispit> ispits = filtrirajIspitePoStudentu(super.getIspiti(),super.getStudenti().get(i));

            zbrojSvihPeticaStudenta = zbrojIzvrsnihOcjena(ispits);

            if ( zbrojSvihPeticaStudenta > prethodniZbrojPetica){

                prethodniZbrojPetica = zbrojIzvrsnihOcjena(ispits);

                studenti.add(super.getStudenti().get(i));

            }
        }


        return studenti.get(studenti.size() - 1);
    }

    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(ArrayList<Ispit> ispiti, Integer ocjenaDiplomskogRada, Integer ocjenaObraneDiplomskogRada) {
        BigDecimal ocjenaPismenogIZavrsnogDijela = new BigDecimal(ocjenaDiplomskogRada + ocjenaObraneDiplomskogRada);

        BigDecimal prosjecnaOcjenaIspita = new BigDecimal(0);

        BigDecimal tri = new BigDecimal(3);

        prosjecnaOcjenaIspita = prosjecnaOcjenaIspita.add(tri.multiply(odrediProsjekOcjenaNaIspitima(ispiti)));

        prosjecnaOcjenaIspita = prosjecnaOcjenaIspita.add(ocjenaPismenogIZavrsnogDijela);

        BigDecimal pet = new BigDecimal(5);

        prosjecnaOcjenaIspita = prosjecnaOcjenaIspita.divide(pet);

        return prosjecnaOcjenaIspita;
    }

    public ArrayList<Ispit> filtrirajIspitePoStudentu(ArrayList<Ispit> ispiti,Student student){
        return Diplomski.super.filtrirajIspitePoStudentu(ispiti,student);

    }

}
