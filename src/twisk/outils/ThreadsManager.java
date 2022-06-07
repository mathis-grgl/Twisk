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

    public void lancer(Task task){
        Thread thread = new Thread(task);
        threadList.add(thread);
        thread.start();
    }

    public void detruireTout(){
        for(Thread thr : threadList){
            if (thr.isInterrupted()) {
                try {
                    throw new InterruptedException();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else
                thr.interrupt();
        }
    }
}
