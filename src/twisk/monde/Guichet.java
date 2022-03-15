package twisk.monde;

import twisk.outils.FabriqueNumero;

public class Guichet extends Etape{
   protected int nbjetons, sema;

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

    public int getNbjetons() {
        return nbjetons;
    }

    public String toString(){return super.toString();}

    @Override
    public String toC() {
        StringBuilder str = new StringBuilder();
        str.append("P(ids,")
                .append(sema)
                .append(");\n")
                .append("transfert(")
                .append(nom)
                .append(",")
                .append(gestSucc.getSucc(0).nom)
                .append(");\n")
                .append("V(ids,")
                .append(sema)
                .append(");\n")
                .append(gestSucc.getSucc(0).toC());
        return str.toString();
    }
}
