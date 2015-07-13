package model;

import android.widget.Toast;

import com.kth.quiz.farkle.GameActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joshuapro on 15-07-13.
 */
public class Game  {
   private Dice [] dices;
   private Subject subject;
   private boolean firstRound=true;
   private int roundScore=0;
   private int round=0;
   private int gameTotalScore=0;
   private int [] imagevalue;


    GameCalculator gameCalculator= new GameCalculator();

    public Game(){
        dices= new Dice[6];
    }

    public boolean isFirstRound() {
        return firstRound;
    }

    public void setFirstRound() {
        this.firstRound = true;
    }

    public int getRoundScore() {
        return roundScore;
    }

    public void setRoundScore(int roundScore) {
        this.roundScore = roundScore;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getRound() {
        return round;
    }

    public int getGameTotalScore() {
        return gameTotalScore;
    }

    public void setGameTotalScore(int gameTotalScore) {
        this.gameTotalScore = gameTotalScore;
    }

    // start a new game
    public void startGame(){
        imagevalue = new int[dices.length];
        for (int i =0; i<= dices.length-1; i++){
            dices[i]=new Dice(subject);
            imagevalue[i]=1;
        }
        gameCalculator.setDeck(dices);
    }

    public void onThrow(){
        gameCalculator.throwDice();

    }

    public void calculateRoundScore(){
        setRoundScore(gameCalculator.RoundScoreValue());
        if(isFirstRound() && getRoundScore() > 300){
            System.out.println("firstround access");

            gameFirstRound();
        }else (!isFirstRound()) {

            //TODO end the round
        }
    }

    public void gameFirstRound(){
        // disable touch imagebutton
        for (int i=0; i<= dices.length; i++){
        dices[i].isMark();
        }

    }


    public void middleOfGame(){


    }





}
