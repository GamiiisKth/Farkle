package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joshuapro on 15-07-13.
 */
public class Game  {

    private List<Observers> observer=new ArrayList<Observers>();

    private Dice dice;


    public Dice getState(){
        return dice;
    }

    public void setStatMark(Dice dice){
        this.dice=dice;
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
