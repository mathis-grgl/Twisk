package twisk.monde;

import twisk.outils.FabriqueNumero;

public class Guichet extends Etape{
   public int nbjetons, sema;

    public Guichet(String nom){
        super(nom);
        nbjetons = 1;
        sema = FabriqueNumero.getInstance().getNumeroSemaphore();
    }

    public Guichet(String nom, int nb){
        super(nom);
        this.nbjetons = nb ;
        sema = FabriqueNumero.getInstance().getNumeroSemaphore();
    }
    @Override
    public boolean estUneActivite() {
        return false;
    }

    @Override
    public boolean estUnGuichet() {
        return true;
    }

    public int getSema(){
        return sema;
    }

    public String toString(){return super.toString();}
}
