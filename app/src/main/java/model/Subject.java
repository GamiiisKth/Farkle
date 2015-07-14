package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ali on 2015-07-13.
 */
public class Subject  {
    private List<Observers> observer=new ArrayList<Observers>();

    public void setStatMark(){
        notifyMarkObservers();
    }
    public void attach(Observers Observers){
        observer.add(Observers);
    }

    public void ditach(Observers obs){
        observer.remove(obs);
    }

    public void notifyMarkObservers(){
        for (Observers obs: observer){
            obs.updateMarkState();
        }
    }
    public void notifySaveObservers(){
        for (Observers obs: observer){
            obs.updateSaveState();
        }
    }

    public void reset(){
        for (Observers obs: observer ){
            obs.reset();
        }
    }
}
