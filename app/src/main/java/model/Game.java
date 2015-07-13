package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joshuapro on 15-07-13.
 */
public class Game  {
   private Dice [] dices;
    private Subject subject;
    GameCalculator gameCalculator= new GameCalculator();

    public Game(){
        dices= new Dice[6];
    }

    public void startGame(){
        for (int i =0; i<= dices.length-1; i++){
            dices[i]=new Dice(subject);

        }
        gameCalculator.setDeck(dices);
    }


}
