package twisk.monde;

public class SasEntree extends Activite {
    public SasEntree() {
        super("entree");
    }

    public String toString(){return super.toString();}

    @Override
    public String toC() {
        StringBuilder str = new StringBuilder();
        str.append("entrer(")
                .append(nom)
                .append(");\ndelai(")
                .append(temps)
                .append(",")
                .append(ecartTemps)
                .append(");\n")
                .append("transfert(")
                .append(nom)
                .append(",")
                .append(gestSucc.getSucc(0).nom)
                .append(");\n")
                .append(gestSucc.getSucc(0).toC());
        return str.toString();
    }
}
