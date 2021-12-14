package Exercice2;

/**
 * La valeur associée a chacun des liquide correspond à la temperature de conservation
 */
public enum Liquide {
    EAU (10),VIN (15),HUILE (9);
    private final int value;

    private Liquide(int value) {
        this.value = value;
    }
}
