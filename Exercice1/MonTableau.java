package Exercice1;

import java.security.InvalidParameterException;

public class MonTableau implements EstComparable{
    
    private int[] tab;
    public MonTableau(int[] tab)
    {
        this.tab = tab;
    }

    public int[] getTab() {
        return tab;
    }

    /**
     * compare l'objet courant avec un objet passer en parametre
     * @param o Object implementant EstComparable
     * @return int 1 si la somme du tableau de l'objet courant > la somme du tableau de l'objet passer en parametre -1 si l'inverse ou 0 si sont egaux
     */
    @Override
    public int compareA(Object o) {
        if (o == null) {throw new NullPointerException("L'objet passé en paramètre doit être non null");}
        else if (! (o instanceof MonTableau)) {throw new InvalidParameterException("Instance de la classe \"MonTableau\" attendu");}
        else {
            int SommeTab = 0;
            int SommeTabParametre = 0;
            int [] tabParametre = ((MonTableau)o).getTab();
            int codeRetour = 0;
            try {
                for (int val : tab) {
                    SommeTab += val;
                }
                for (int val : tabParametre) {
                    SommeTabParametre += val;
                }

                if(SommeTab > SommeTabParametre)
                    codeRetour = 1;
                else if(SommeTab < SommeTabParametre)
                    codeRetour = -1;
            } catch (NullPointerException e) {
                System.err.println("Les attributs tab des deux objets a comparer ne doivent pas être null");
            } catch (Exception e) {
                throw e;
            }
            return codeRetour;
        }
    }
}
