package twisk.monde;

public class Activite extends Etape{
    protected int temps,ecartTemps;

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


    public String toString(){return super.toString();}

    @Override
    public String toC() {
        StringBuilder str = new StringBuilder();
        str.append("delai(")
                .append(temps)
                .append(",")
                .append(ecartTemps)
                .append(");\n")
                .append("transfert(")
                .append(getNomBien())
                .append(",")
                .append(gestSucc.getSucc(0).getNomBien())
                .append(");\n")
                .append(gestSucc.getSucc(0).toC());
        return str.toString();
    }
}
