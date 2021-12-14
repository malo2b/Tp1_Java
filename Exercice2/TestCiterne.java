package Exercice2;

import java.time.LocalDate;
import javax.naming.OperationNotSupportedException;

public class TestCiterne {
    public static void main(String[] args) throws OperationNotSupportedException {
        //TEST 1
        Citerne c1 = new Citerne(1000, Liquide.EAU);
        Citerne c2 = new Citerne(1000, Liquide.EAU,LocalDate.of(2001, 5, 28));
        Citerne c3 = new Citerne(1000, Liquide.HUILE);
        System.out.println("Citerne la plus ancienne entre c1 et c2 : " + Citerne.plusAncienne(c1,c2));
        c1.ajouterLiquide(700);
        c2.ajouterLiquide(0.5);
        c1.enleverLiquide(300);
        c3.ajouterLiquide(0.2);
        c1.nettoyage();
        c1.setTypeDeLiquide(Liquide.HUILE);
        System.out.println("c1 compareA c3 :" + c1.compareA(c3));
        System.out.println("c1 == c2 : " + c1.equals(c2));
        System.out.println(c1);

        //TestType2
        CiterneSecurisee cs1 = new CiterneSecurisee(1000, Liquide.EAU,800);
        CiterneSecurisee cs2 = new CiterneSecurisee(1000, Liquide.EAU,800);
        CiterneSecurisee cs3 = new CiterneSecurisee(1500, Liquide.HUILE, 500);
        CiterneSecurisee cs4 = null;
        cs1.ajouterLiquide(900);
        cs1.ajouterLiquide(600);
        cs1.enleverLiquide(700);
        cs1.enleverLiquideTropPlein(800);
        cs1.nettoyage();
        cs1.setTypeDeLiquide(Liquide.VIN);
        try {
            cs4 = (CiterneSecurisee) cs1.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println("Comparaison deux citernes securisee identique: "+cs1.equals(cs2));
        System.out.println("Comparaison deux citernes securisee Differente: "+cs1.equals(cs3));
        System.out.println("Comparaison deux citernes cloner : "+cs1.equals(cs4));
        cs4.nettoyage();
        cs4.setTypeDeLiquide(Liquide.HUILE);
        cs4.ajouterLiquide(1200);
        System.out.println(cs1);
        System.out.println("Copie de Cs1 avec modification "+ cs4);
    }
}
