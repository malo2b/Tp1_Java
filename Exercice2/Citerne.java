package Exercice2;

import java.time.LocalDate;
import javax.naming.OperationNotSupportedException;

import Exercice1.EstComparable;

public class Citerne implements Cloneable, EstComparable {
    private final int identifiant;
    private static int compteurCiterne = 1;
    protected final int capacite;
    private Liquide typeDeLiquide;
    private LocalDate dateDeMiseEnService;
    protected int remplissage;
    private static final int capaciteMax = 20000; // Capacité max de la cuve

    /**
     * Constructeur de Citerne
     * @param capacite Capacité de la cuve
     * @param typeDeLiquide Type de liquide
     */
    public Citerne(int capacite, Liquide typeDeLiquide)
    {
        if(capacite <= 0 || capacite > capaciteMax) {
            throw new IllegalArgumentException("La capacite passée en paramètre n'est pas dans l'intervale de 1 a 20000m³");
        } else {
            this.identifiant = compteurCiterne;
            this.capacite = capacite;
            this.typeDeLiquide = typeDeLiquide;
            this.dateDeMiseEnService = LocalDate.now();
            this.remplissage = 0;
            compteurCiterne++;
        }
            
    }

    public Citerne(int capacite,Liquide typeDeLiquide,LocalDate ld)
    {
        this(capacite,typeDeLiquide);
        this.dateDeMiseEnService = ld;
    }

    /**
     * Méthode statique qui retourne la citerne la plus ancienne entre c1 et c2
     * @param c1
     * @param c2
     * @return Citerne la plus ancienne
     */
    public static Citerne plusAncienne(Citerne c1,Citerne c2)
    {
        Citerne retour = c2;
        if (c1.getDateDeMiseEnService().isBefore(c2.getDateDeMiseEnService()))
            retour = c1;
        return retour;
    }
    /**
     * @return the dateDeMiseEnService
     */
    public LocalDate getDateDeMiseEnService() {
        return dateDeMiseEnService;
    }
    /**
     * @return the capacite
     */
    public int getCapacite() {
        return capacite;
    }

    /**
     * @return Quantité de liquide stockée
     */
    public int getQteLiquide() {
        return remplissage;
    }
    /**
     * @return the typeDeLiquide
     */
    public Liquide getTypeDeLiquide() {
        return typeDeLiquide;
    }
    /**
     * @return the compteurCiterne
     */
    public static int getNombreCiterne() {
        return compteurCiterne;
    }

    /**
     * Ajouter une quantité de liquide dans la cuve
     * @param quantite Valeur entiere en m³
     * @throws OperationNotSupportedException OperationNotSupportedException car l'operation n'as pas pu être menée a bien
     */
    public void ajouterLiquide(int quantite) throws OperationNotSupportedException {
        if (typeDeLiquide != null) {

            try {
                if (quantite+remplissage > capacite) {
                    remplissage = capacite;
                    throw new OperationNotSupportedException("ATTENTION la quantité ajoutée est superieure a la capacité de la cuve. La cuve est désormais pleine");
                } else {
                    remplissage += quantite;
                }
            } catch (OperationNotSupportedException e) {
                System.err.println(e.getMessage());
            }

        } else {
            throw new OperationNotSupportedException("ATTENTION Le type de liquide n'est pas definit");
        }
    }

    /**
     * Ajouter une quantité de liquide dans la cuve
     * @param quantite % de la contenance de la cuve (valeur attendue entre 0 et 1)
     * @throws OperationNotSupportedException OperationNotSupportedException car l'operation n'as pas pu être menée a bien
     */
    public void ajouterLiquide(double quantite) throws OperationNotSupportedException, IllegalArgumentException {
        if (typeDeLiquide != null) {
            if (quantite >= 0 || quantite <= 1) {
                try {
                    if ( (quantite * capacite) + remplissage > capacite) {
                        remplissage = capacite;
                        throw new OperationNotSupportedException("ATTENTION la quantité ajoutée est superieure a la capacité de la cuve. La cuve est désormais pleine");
                    } else {
                        remplissage += (quantite * capacite);
                    }
                } catch (OperationNotSupportedException e) {
                    System.err.println(e.getMessage());
                }
            } else {
                throw new IllegalArgumentException("La valeur saisie doit etre comprise entre 0 et 1");
            }
        } else {
            throw new OperationNotSupportedException("ATTENTION Le type de liquide n'est pas definit");
        }
    }

    /**
     * Enlever une quantité de liquide dans la cuve
     * @param quantite en m³
     * @throws OperationNotSupportedException OperationNotSupportedException car l'operation n'as pas pu être menée a bien
     */
    public void enleverLiquide(int quantite) throws OperationNotSupportedException {
        if (typeDeLiquide != null) {
            try {
                if ((remplissage-quantite)<0)
                {
                    remplissage = 0;
                    throw new OperationNotSupportedException("ATTENTION La cuve est vide");
                }
                else
                {
                    remplissage -= quantite;
                }
            } catch (OperationNotSupportedException e) {
                System.err.println(e.getMessage());
            }
        } else {
            throw new OperationNotSupportedException("ATTENTION Le type de liquide n'est pas definit");
        }
    }

    /**
     * Enlever une quantité de liquide dans la cuve
     * @param quantite % de la capacité de la cuve
     * @throws OperationNotSupportedException OperationNotSupportedException car l'operation n'as pas pu être menée a bien
     */
    public void enleverLiquide(double quantite) throws OperationNotSupportedException {
        if (typeDeLiquide != null) {
            if (quantite >= 0 || quantite <= 1) {
                try {
                    if ( remplissage - (quantite * capacite)  > capacite) {
                        remplissage = 0;
                        throw new OperationNotSupportedException("ATTENTION la quantité ajoutée est superieure a la capacité de la cuve. La cuve est désormais pleine");
                    } else {
                        remplissage -= (quantite * capacite);
                    }
                } catch (OperationNotSupportedException e) {
                    System.err.println(e.getMessage());
                }
            } else {
                throw new IllegalArgumentException("La valeur saisie doit etre comprise entre 0 et 1");
            }
        } else {
            throw new OperationNotSupportedException("ATTENTION Le type de liquide n'est pas definit");
        }
    }

    /**
     * Vide la cuve et reinitialise le type de liquide
     */
    public void nettoyage() {
        typeDeLiquide = null;
        this.remplissage = 0;
    }

    /**
     * @param typeDeLiquide the typeDeLiquide to set
     */
    public void setTypeDeLiquide(Liquide typeDeLiquide) {
        try {
            if (this.typeDeLiquide == null)
                this.typeDeLiquide = typeDeLiquide;
            else
                throw new OperationNotSupportedException("La cuve contient deja un liquide, veuillez la nettoyer avant");
        } catch (OperationNotSupportedException e) {
            System.err.println(e.getMessage());
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean res = false;
        if( obj instanceof Citerne)
        {
            Citerne c = (Citerne) obj;
            res =  this.capacite == c.capacite && this.dateDeMiseEnService.isEqual(c.dateDeMiseEnService) && this.typeDeLiquide == c.typeDeLiquide && this.remplissage == c.remplissage;
        }
        return res;
    }
    @Override
    public int compareA(Object o) {
        int res = -2;
        if(o instanceof Citerne) {
            Citerne c = (Citerne) o;

            if(this.remplissage > c.remplissage)
                res = 1;
            else if(this.remplissage < c.remplissage)
                res = -1;
            else {
                if(this.capacite < c.capacite)
                    res = -1;
                else 
                    res=1;
            }
        }
        return res;
    }
    @Override
    public String toString() {
        return getClass().getName() + " n°"+identifiant + ", "+typeDeLiquide+", Capacite: "+capacite + "m3, mise en service:"+dateDeMiseEnService+", volume occupe:"+remplissage+"m3 ";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Citerne c = null;
        try {
            c = (Citerne) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new CloneNotSupportedException("L'objet n'implemente pas cloneable");
        }
        return c;
    }
}
