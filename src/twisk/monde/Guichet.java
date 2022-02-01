package twisk.monde;

public class Guichet extends Etape{
   public int nbjetons;

    public Guichet(String nom){
        super(nom);
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
    public boolean estUnGuicher() {
        return true;
    }
}
