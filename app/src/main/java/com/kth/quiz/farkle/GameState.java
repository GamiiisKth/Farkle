package com.kth.quiz.farkle;

import android.os.Parcel;
import android.os.Parcelable;

import model.Dice;
import model.Game;

/**
 * Created by joshuapro on 15-07-13.
 */
public class GameState implements Parcelable {
private Game game;
private int round;
private int scoreOfRound;
private int sumOfGame;
private boolean saveButtonState;
private boolean scoreButtonState;
private boolean throwButtonState;




    protected GameState(Parcel in){
        round=in.readInt();
        scoreOfRound=in.readInt();
        sumOfGame=in.readInt();
        boolean  [] val=in.createBooleanArray();
        saveButtonState=val[0];
        scoreButtonState=val[1];
        throwButtonState=val[2];

    }
    public static final Parcelable.Creator<GameState> CREATOR = new Parcelable.Creator<GameState>() {
        @Override
        public GameState createFromParcel(Parcel in) {
            return new GameState(in);
        }

        @Override
        public GameState[] newArray(int size) {
            return new GameState[size];
        }
    };


    public GameState(){
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(round);
        dest.writeInt(scoreOfRound);
        dest.writeInt(sumOfGame);
        boolean []val= new boolean[]{
                isSaveButtonState(),
                isScoreButtonState(),
                isThrowButtonState()
        };


    }

    public Dice[] getDice(){

     return  game.getDices();
    }

    public void startGame(){
        game.startGame();
    }

    public void onThrow(){
        game.onThrow();
    }
    public void onSave(){
        game.onSave();
    }
    public void onScore(){
        game.onScore();
    }

    public void setRound() {
        this.round = game.getRound();
    }

    public void setSumOfGame() {
        this.sumOfGame = game.getGameTotalScore();
    }

    public void setScoreOfRound() {
        this.scoreOfRound = game.getRoundScore();
    }

    public void setSaveButtonState() {
        this.saveButtonState = game.isSaveButton();
    }

    public void setScoreButtonState() {
        this.scoreButtonState = game.isScoreButton();
    }

    public void setThrowButtonState() {
        this.throwButtonState = game.isThrowButton();
    }

    public int getRound() {
        return round;
    }

    public int getScoreOfRound() {
        return scoreOfRound;
    }

    public int getSumOfGame() {
        return sumOfGame;
    }

    public boolean isSaveButtonState() {
        return saveButtonState;
    }

    public boolean isScoreButtonState() {
        return scoreButtonState;
    }

    public boolean isThrowButtonState() {
        return throwButtonState;
    }
    public boolean [] getImageSelectEnable(){
        return game.getSelectImageEnable();
    }
}
