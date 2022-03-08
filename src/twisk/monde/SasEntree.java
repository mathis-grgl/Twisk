package twisk.monde;

public class SasEntree extends Activite {
    public SasEntree() {
        super("entree");
    }

    public String toString(){return super.toString();}

    @Override
    public String toC() {
        return "entrer("+nom+");\ndelai("+temps+","+ecartTemps+");\n"+super.toC();
    }
}
