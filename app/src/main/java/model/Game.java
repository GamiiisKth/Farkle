package model;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

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
   private boolean [] selectImageEnable;
    // Hashmap f�r att kunna visa en listview med varje round och resultatet
   private HashMap<Integer,Integer> roundAndScore=new HashMap<Integer,Integer>();
   private boolean saveButton=false;
   private boolean throwButton=false;
   private boolean scoreButton=false;

    private GameCalculator gameCalculator= new GameCalculator();

    public boolean isFirstRound() {
        return firstRound;
    }

    public void setFirstRound(boolean firstRound) {
        this.firstRound = firstRound;
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

    public boolean[] getSelectImageEnable() {
        return selectImageEnable;
    }

    public Dice[] getDices() {
        return dices;
    }

    public Game(){

    }

    // start a new game
    public void startGame(){
        dices= new Dice[6];
        selectImageEnable = new boolean[dices.length];
        for (int i =0; i<= dices.length-1; i++){
            dices[i]=new Dice();
            selectImageEnable[i]=false;
        }
    }

    public void onThrow(){
        if (isFirstRound()){
            setRound(getRound()+1);
        }
            setSaveButton(false);
            setScoreButton(true);
            setThrowButton(false);

            roundAndScore.put(getRound(), getRoundScore());
            for (int a = 0; a <= dices.length - 1; a++) {
                if (!dices[a].isSave()) {
                    dices[a].setDiceSide((int) ((Math.random() * 6) + 1));
                    selectImageEnable[a]=true;
                    // a+1 f�r stege
                    // (int) ((Math.random() * 6) + 1)
                }
            }


    }
    public void onScore(){
        checkMarkDice();
        calculateRoundScore();
    }
    public void onSave(){
        endOfGame();
    }


    private void checkMarkDice(){
        int []markedDiceArray= new int[dices.length];
        for (int i=0; i<= dices.length-1; i++) {
            if (dices[i].isMark()) {
                markedDiceArray[i] = dices[i].getDiceSide();
                dices[i].setSave();
                selectImageEnable[i]=false;
            }
        }
        gameCalculator.setMarkedDiceArray(markedDiceArray);
    }


    private void calculateRoundScore(){
            setRoundScore(gameCalculator.RoundScoreValue());
            if (isFirstRound() && getRoundScore() > 300) {
                System.out.println("firstround access");

                setFirstRound(false);
                gameFirstRound();

            } else if (isFirstRound() && getRoundScore() < 300) {
                System.out.println("first round is over");
                //TODO end the round

                roundAndScore.put(getRound(),0);
                setFirstRound(true);
                    setThrowButton(true);
                    setSaveButton(false);
                    setScoreButton(false);

            } else {
                middleOfGame();
            }
        }


    public void gameFirstRound(){
        // disable touch imagebutton

     setSaveButton(true);
     setScoreButton(false);
     setThrowButton(true);
     roundAndScore.put(getRound(),(roundAndScore.get(getRound())) + getRoundScore());
    }


    public void middleOfGame(){

    if (getRoundScore()>50){
        setSaveButton(true);
        setScoreButton(true);
        setThrowButton(false);
        roundAndScore.put(getRound(),(roundAndScore.get(getRound())) + getRoundScore());
     }else {
        setFirstRound(true);
        roundAndScore.put(getRound(),0);
        endOfGame();
    }
    }
    public void endOfGame(){
        setFirstRound(true);
        setThrowButton(true);
        setSaveButton(false);
        setScoreButton(false);
        reset();
        Arrays.fill(selectImageEnable,true);
    }

    private void sumOfGame(){
        int sum=0;
        Collection<Integer> values=roundAndScore.values();
        for (Integer value: values){
            sum+=value;

        }
        setGameTotalScore(sum);
    }
    private void reset(){
        for (Dice d: dices){
            d.setDiceSide(0);
            d.cancelMark();
            d.cancelSaved();
        }
    }

}
