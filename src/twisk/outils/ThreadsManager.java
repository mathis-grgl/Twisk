package twisk.outils;

import javafx.concurrent.Task;

import java.util.ArrayList;

public class ThreadsManager {
    private static ThreadsManager instance;
    private ArrayList<Thread> threadList;

    private ThreadsManager() {
        threadList = new ArrayList<>();
    }

    /**
     * Retourne l'instance de TailleComposant.
     *
     * @return L'instance TailleComposant
     */
    public static ThreadsManager getInstance() {
        if (instance == null) instance = new ThreadsManager();
        return instance;
    }

    /**
     * Crée un thread et l'ajoute à la liste puis lance la task.
     * @param task la task à lancer
     */
    public void lancer(Task task){
        Thread thread = new Thread(task);
        threadList.add(thread);
        thread.start();
    }

    /**
     * Arrête tous les threads dans la liste des threads.
     */
    public void detruireTout(){
        for(Thread thr : threadList){
            thr.interrupt();
        }
    }
}
