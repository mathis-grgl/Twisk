package twisk.monde;

public class Activite extends Etape{
    private int temps,ecartTemps;
    public Activite(String nom){
        super(nom);
        temps = 4;
        ecartTemps = 2;
    }

    public Activite(String nom, int t, int e){
        super(nom);
        this.temps = t;
        this.ecartTemps = e;
    }

    @Override
    public boolean estUneActivite() {
        return true;
    }

    @Override
    public boolean estUnGuichet() {
        return false;
    }

    public String toString(){return super.toString()+temps+ecartTemps;}
}
