package Exercice1;

/**
 * AppExercice1
 */
public class AppExercice1 {

    public static void main(String[] args) {
        int[] tab = new int[] {1,2,3,4};
        int[] tab2 = new int[] {-1,2,-3,4,5};
        MonTableau mt1 = new MonTableau(tab);
        MonTableau mt2 = new MonTableau(tab2);
        System.out.println(mt1.compareA(mt2));
    }
}