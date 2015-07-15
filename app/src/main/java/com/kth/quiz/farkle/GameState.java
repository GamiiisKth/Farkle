package com.kth.quiz.farkle;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import model.Dice;
import model.GameCalculator;
import model.Subject;

/**
 * Created by joshuapro on 15-07-13.
 */
public class GameState implements Parcelable {

    private Dice[] dices;
    private Subject subject;
    private boolean firstRound=true;
    private int scoreOfRound=0;
    private int round=0;
    private int gameTotalScore=0;
    private int []markedDiceArray;
    private boolean [] selectImageEnable;
    // Hashmap f�r att kunna visa en listview med varje round och resultatet
    private HashMap<Integer,Integer> roundAndScore=new HashMap<Integer,Integer>();
    private boolean saveButton=false;
    private boolean throwButton=true;
    private boolean scoreButton=false;


    private GameCalculator gameCalculator= new GameCalculator();

    protected  GameState(Parcel in){
        round=in.readInt();
        scoreOfRound=in.readInt();
        gameTotalScore=in.readInt();
        boolean [] value=in.createBooleanArray();
        firstRound=value[0];
        saveButton=value[1];
        scoreButton=value[2];
        throwButton=value[3];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(round);
    dest.writeInt(scoreOfRound);
    dest.writeInt(gameTotalScore);
        boolean []value= new boolean[]{
          isFirstRound(),
          isFirstRound(),
          isScoreButton(),
           isThrowButton()
        };

    }
    public GameState(){

    }


    public int getScoreOfRound() {
        return scoreOfRound;
    }

    public void setScoreOfRound(int scoreOfRound) {
        this.scoreOfRound = scoreOfRound;
    }

    public boolean isFirstRound() {
        return firstRound;
    }

    public void setFirstRound(boolean firstRound) {
        this.firstRound = firstRound;
    }

    public int getRoundScoreFromMap(int round) {
        return roundAndScore.get(round);
    }

    public void setRoundScoreTomap(int roundId,int roundScore) {
        roundAndScore.put(roundId,roundScore);
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
            setScoreOfRound(0);
            Arrays.fill(selectImageEnable, true);
        }
        setSaveButton(false);
        setScoreButton(true);
        setThrowButton(false);
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
        markedDiceArray= new int[dices.length];
        for (int i=0; i<= dices.length-1; i++) {
            if (dices[i].isMark()  ) {
                markedDiceArray[i] = dices[i].getDiceSide();
                dices[i].setSave(true);
                dices[i].setMark(false);
                selectImageEnable[i]=false;

            }
        }
        gameCalculator.setMarkedDiceArray(markedDiceArray);
    }


    private void calculateRoundScore(){

        setScoreOfRound( gameCalculator.RoundScoreValue());

        if (isFirstRound() && getScoreOfRound() >= 300) {
            System.out.println("firstround access");

            setFirstRound(false);
            setSaveButton(true);
            setScoreButton(false);
            setThrowButton(true);
            setRoundScoreTomap(getRound(), getScoreOfRound());


        } else if (isFirstRound() && getScoreOfRound() < 300) {
            System.out.println("first round is over");
            //TODO end the round

            setRoundScoreTomap(getRound(),0);

            setFirstRound(true);
            setThrowButton(true);
            setSaveButton(false);
            setScoreButton(false);
            endOfGame();

        } else {
            middleOfGame();
        }
    }




    public void middleOfGame(){

        if (getScoreOfRound()>=50){
            setSaveButton(true);
            setScoreButton(false);
            setThrowButton(true);
            setFirstRound(false);
            setRoundScoreTomap(getRound(), getRoundScoreFromMap(getRound())+ getScoreOfRound());

        }else {
            setScoreOfRound(0);
            setRoundScoreTomap(getRound(),0);

            endOfGame();
        }
    }

    public void endOfGame(){
        setFirstRound(true);
        setThrowButton(true);
        setSaveButton(false);
        setScoreButton(false);
        //Arrays.fill(markedDiceArray, 0, markedDiceArray.length, 0);
        reset();
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
        for (Dice d : dices){
            d.setDiceSide(0);
            d.setMark(false);
            d.setSave(false);
//            Arrays.fill(dices,0);
        }
    }

    public void markAllDice(){
        for (Dice d: dices){
            d.isSave();
        }
    }
    public int countMarkedDice(){
        int i=0;
        for (Dice d: dices){
            if (d.isMark()){
                i++;
            }

        }
        return  i;
    }

    public void resetTheGame(){
        setScoreButton(false);
        setSaveButton(false);
        setThrowButton(true);
        setRound(0);
        setScoreOfRound(0);
        setGameTotalScore(0);
        setFirstRound(true);
    }
}
