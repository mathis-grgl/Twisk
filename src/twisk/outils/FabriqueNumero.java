package twisk.outils;

public class FabriqueNumero {
    private int cptEtape,cptSemaphore;
    private static FabriqueNumero instance = null;

    private FabriqueNumero(){
        cptEtape = 0;
        cptSemaphore = 1;
    }

    public static FabriqueNumero getInstance(){
        if (instance == null) instance = new FabriqueNumero();
        return instance;
    }

    public int getNumeroEtape(){
        int cmp = cptEtape;
        cptEtape+=1;
        return cmp;
    }

    public int getNumeroSemaphore(){
        int cmp = cptSemaphore;
        cptSemaphore+=1;
        return cmp;
    }

    public void reset(){
        cptEtape = 0;
        cptSemaphore = 1;
    }
}
