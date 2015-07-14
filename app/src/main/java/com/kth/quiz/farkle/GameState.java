package com.kth.quiz.farkle;

import android.os.Parcel;
import android.os.Parcelable;

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





    public GameState(){

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


    }
}
