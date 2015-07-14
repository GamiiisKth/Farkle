package model;

import android.widget.Toast;

import com.kth.quiz.farkle.GameActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
   private HashMap<Integer,Integer> roundAndScore=new HashMap<Integer,Integer>();
   private boolean saveButton=false;
   private boolean throwButton=false;
   private boolean scoreButton=false;

    private GameCalculator gameCalculator= new GameCalculator();

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
        sumOfGame();
        return gameTotalScore;
    }

    public void setGameTotalScore(int gameTotalScore) {
        this.gameTotalScore = gameTotalScore;
    }

    public boolean isSaveButton() {
        return saveButton;
    }

    public boolean isScoreButton() {
        return scoreButton;
    }

    public boolean isThrowButton() {
        return throwButton;
    }

    public void setSaveButton(boolean saveButton) {
        this.saveButton = saveButton;
    }

    public void setScoreButton(boolean scoreButton) {
        this.scoreButton = scoreButton;
    }

    public void setThrowButton(boolean throwButton) {
        this.throwButton = throwButton;
    }

    public Game(){
        dices= new Dice[6];
    }

    // start a new game
    public void startGame(){
        imagevalue = new int[dices.length];
        for (int i =0; i<= dices.length-1; i++){
            dices[i]=new Dice(subject);
            imagevalue[i]=1;
        }
    }

    public void onThrow(){
        if (isThrowButton()) {
            setSaveButton(false);
            setScoreButton(false);
            setRoundScore(getRound()+1);
            roundAndScore.put(getRound(),0);
            for (int a = 0; a <= dices.length - 1; a++) {
                if (!dices[a].isSave()) {
                    dices[a].setDiceSide((int) ((Math.random() * 6) + 1));
                    // a+1 f�r stege
                    // (int) ((Math.random() * 6) + 1)
                }
            }
            checkMarkDice();
        }
    }

    private void checkMarkDice(){
        int []markedDiceArray= new int[dices.length];
        for (int i=0; i<= dices.length-1; i++) {
            if (dices[i].isMark()) {
                markedDiceArray[i] = dices[i].getDiceSide();
            }
        }
        gameCalculator.setMarkedDiceArray(markedDiceArray);
    }


    public void calculateRoundScore(){
        if (scoreButton) {
            setRoundScore(gameCalculator.RoundScoreValue());
            if (isFirstRound() && getRoundScore() > 300) {
                System.out.println("firstround access");

                gameFirstRound();

            } else if (isFirstRound() && getRoundScore() < 300) {
                System.out.println("first round is over");
                //TODO end the round

                roundAndScore.put(getRound(),0);
                    setThrowButton(true);
                    setSaveButton(false);
                    setScoreButton(false);

            } else {
                middleOfGame();
            }
        }
    }

    public void gameFirstRound(){
        // disable touch imagebutton

     setSaveButton(true);
     setScoreButton(false);
     setThrowButton(false);
     roundAndScore.put(getRound(),(roundAndScore.get(getRound())) + getRoundScore());
    }


    public void middleOfGame(){

    if (getRoundScore()>50){
        setSaveButton(true);
        setScoreButton(false);
        setThrowButton(false);
        roundAndScore.put(getRound(),(roundAndScore.get(getRound())) + getRoundScore());
     }else {
        roundAndScore.put(getRound(),0);
        subject.notifySaveObservers();
        endOfGame();
    }
    }
    public void endOfGame(){
        setSaveButton(false);
        setScoreButton(false);
        subject.reset();
    }

    private void sumOfGame(){
        int sum=0;
        Collection<Integer> values=roundAndScore.values();
        for (Integer value: values){
            sum+=value;

        }
        setGameTotalScore(sum);
    }

}
