package twisk;

import twisk.monde.*;
import twisk.outils.ClassLoaderPerso;
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

        ClassLoaderPerso classloader = new ClassLoaderPerso(this.getClass().getClassLoader());
        Class classeSimu = classloader.loadClass("twisk.simulation.Simulation");
        Object sim = classeSimu.getConstructor().newInstance();
        Method simuler = classeSimu.getMethod("simuler",Monde.class);
        simuler.invoke(sim,monde);
    }

    public static void main(String[] args)  {
        try {
            ClientTwisk c = new ClientTwisk();
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

