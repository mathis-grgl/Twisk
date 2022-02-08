package twisk.monde;

public class Guichet extends Etape{
   public int nbjetons;

    public Guichet(String nom){
        super(nom);
        nbjetons = 1;
    }

    public Guichet(String nom, int nb){
        super(nom);
        this.nbjetons = nb ;
    }
    @Override
    public boolean estUneActivite() {
        return false;
    }

    @Override
    public boolean estUnGuichet() {
        return true;
    }

    public String toString(){return super.toString();}
}
