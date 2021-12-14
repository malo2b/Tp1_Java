package Exercice2;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;


/**
 * CiterneSecurisee
 */
public class CiterneSecurisee extends Citerne implements Cloneable{
    private Citerne tropPlein;
    CiterneSecurisee(int capacite,Liquide typeLiquide,int capaciteTropPlein)
    {
        super(capacite,typeLiquide,LocalDate.now());
        remplissage = 0;
        if(capaciteTropPlein<=0 || capacite>2000)
            capaciteTropPlein = (int)0.10*capacite;
        tropPlein = new Citerne(capaciteTropPlein,typeLiquide);
    }
    /**
     * Ajoute le liquide au remplissage si remplissage a atteint la capacite on remplit le tropPlein
     * @param tropPleinRemplissage the tropPleinRemplissage to set
     * @throws OperationNotSupportedException
     */
    public void ajouterLiquide(int tropPleinRemplissage) throws OperationNotSupportedException {
        super.ajouterLiquide(tropPleinRemplissage);
        tropPlein.ajouterLiquide(this.remplissage+tropPleinRemplissage-capacite);
        if(tropPlein.remplissage>=0.50*capacite)
            System.err.println("ATTENTION la cuve de trop plein est a moitier pleine");
        
        
    }
    /**
     * Enleve la quantite passer en parametre du trop plein
     * @param quantiteARetirerTropPlein
     * @throws OperationNotSupportedException
     */
    public void enleverLiquideTropPlein(int quantiteARetirerTropPlein) throws OperationNotSupportedException {
        try
        {
            if (tropPlein.remplissage-quantiteARetirerTropPlein > 0)
            {
                tropPlein.remplissage -= quantiteARetirerTropPlein;
            }
            else
            {
                tropPlein.remplissage = 0;
                throw new OperationNotSupportedException("ATTENTION La cuve est vide");
            }
               
        } catch (OperationNotSupportedException e) {
               System.err.println(e.getMessage());
           }
        
    }


     @Override
     public void nettoyage() {
         super.nettoyage();
         tropPlein.nettoyage();
     }
     @Override
     public void setTypeDeLiquide(Liquide typeDeLiquide) {
         super.setTypeDeLiquide(typeDeLiquide);
         tropPlein.setTypeDeLiquide(typeDeLiquide);
     }
    @Override
    public String toString() {
        return super.toString() + "Capacite trop Plein: "+ tropPlein.capacite +  "Remplissage trop plein: "+tropPlein.remplissage +  " Etat de remplissage du trop Plein "+ String.valueOf((double) tropPlein.remplissage/tropPlein.capacite*100)+"%";
    }
    @Override
    public boolean equals(Object obj) {
        boolean res = false;
        if (obj instanceof CiterneSecurisee)
        {
            CiterneSecurisee c = (CiterneSecurisee)obj;
            res = super.equals(obj) && tropPlein.equals(c.tropPlein);
        }
        return res;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        CiterneSecurisee cs = null;
        try {
            cs = (CiterneSecurisee)super.clone();
            cs.tropPlein =(Citerne) tropPlein.clone();
        } catch (CloneNotSupportedException e) {
            throw new CloneNotSupportedException("La classe n'implemente pas cloeneable");
        }
        return cs;
    }
}