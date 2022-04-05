package twisk;

import twisk.monde.*;
import twisk.outils.ClassLoaderPerso;
import twisk.outils.FabriqueNumero;
import twisk.simulation.Client;
import twisk.simulation.Simulation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClientTwisk {

    public ClientTwisk() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException{
        Monde monde = new Monde();
        Etape fpi = new Guichet("FilePiscine", 3);
        Etape pi = new ActiviteRestreinte("Piscine", 8, 2);
        Etape fto = new Guichet("FileToboggan", 1);
        Etape to = new ActiviteRestreinte("Toboggan", 5, 2);
        Etape bas = new ActiviteRestreinte("BacAsable", 2, 1);
        Etape test = new ActiviteRestreinte("BacAsable",2,1);
        fpi.ajouterSuccesseur(pi);
        pi.ajouterSuccesseur(fto);
        fto.ajouterSuccesseur(to);
        to.ajouterSuccesseur(bas);
        bas.ajouterSuccesseur(test);


        monde.aCommeEntree(fpi);
        monde.ajouter(fpi, pi, to, fto, bas,test);
        monde.aCommeSortie(test);

        FabriqueNumero.getInstance().reset();

        Monde monde2 = new Monde();
        Etape fpi2 = new Guichet("FilePiscine", 3);
        Etape pi2 = new ActiviteRestreinte("Piscine", 8, 2);
        Etape fto2 = new Guichet("FileToboggan", 1);
        Etape to2 = new ActiviteRestreinte("Toboggan", 5, 2);
        Etape bas2 = new ActiviteRestreinte("BacAsable", 2, 1);
        Etape test2 = new ActiviteRestreinte("BacAsable",2,1);
        fpi2.ajouterSuccesseur(pi2);
        pi2.ajouterSuccesseur(fto2);
        fto2.ajouterSuccesseur(to2);
        to2.ajouterSuccesseur(bas2);
        bas2.ajouterSuccesseur(test2);


        monde2.aCommeEntree(fpi2);
        monde2.ajouter(fpi2, pi2, to2, fto2, bas2,test2);
        monde2.aCommeSortie(test2);

        ClassLoaderPerso classloader = new ClassLoaderPerso(this.getClass().getClassLoader());
        Class classeSimu = classloader.loadClass("twisk.simulation.Simulation");
        Object sim = classeSimu.getConstructor().newInstance();
        Method simuler = classeSimu.getMethod("simuler",Monde.class);
        simuler.invoke(sim,monde);

        ClassLoaderPerso classloader2 = new ClassLoaderPerso(this.getClass().getClassLoader());
        Class classeSimu2 = classloader2.loadClass("twisk.simulation.Simulation");
        Object sim2 = classeSimu2.getConstructor().newInstance();
        Method simuler2 = classeSimu2.getMethod("simuler",Monde.class);
        simuler2.invoke(sim2,monde2);
    }

    public static void main(String[] args)  {
        try {
            ClientTwisk c = new ClientTwisk();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

