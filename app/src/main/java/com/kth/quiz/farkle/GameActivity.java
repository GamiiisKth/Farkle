package com.kth.quiz.farkle;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;


public class GameActivity extends Activity {

    private GameState gameState;

    private boolean pic1=false;
    private boolean pic2=false;
    private boolean pic3=false;
    private boolean pic4=false;
    private boolean pic5=false;
    private boolean pic6=false;

    private ImageButton mImagePic1;
    private ImageButton mImagePic2;
    private ImageButton mImagePic3;
    private ImageButton mImagePic4;
    private ImageButton mImagePic5;
    private ImageButton mImagePic6;

    private Button mSaveButton;
    private Button mScoreButton;
    private Button mThrowButton;

    private TextView mScoreText;
    private TextView mRoundText;
    private TextView gameStateScoreText;

    private String GAME_STATE_TAG="game";
    private String TAG="tag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        if (savedInstanceState !=null){

            gameState=savedInstanceState.getParcelable(GAME_STATE_TAG);
        }else {
            gameState = new GameState();
            gameState.startGame();

        }


        mSaveButton = (Button) findViewById(R.id.save_Button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameState.onSave();
                if(gameState.checkWinnerState()){
                    Intent intent= new Intent(getApplication(),WinnerActivity.class);
                    intent.putExtra("totalScore",gameState.getGameTotalScore());
                    intent.putExtra("round",gameState.getRound());
                    startActivityForResult(intent,0);
                }else {
                    updateView();
                }

            }
        });
        mScoreButton = (Button) findViewById(R.id.Score_Button);
        mScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameState.onScore();
                updateView();
            }
        });
        mThrowButton = (Button) findViewById(R.id.throw_Button);
        mThrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameState.onThrow();
                updateView();
            }
        });

        mScoreText = (TextView) findViewById(R.id.Score_Value);
        mRoundText = (TextView) findViewById(R.id.round_Value);
        gameStateScoreText = (TextView) findViewById(R.id.Game_Score_Value);

        mImagePic1 = (ImageButton) findViewById(R.id.pic1);
        mImagePic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gameState.getDices()[0].isMark() && !gameState.getDices()[0].isSave() ){
                    gameState.getDices()[0].setMark(true);

                    updateView();
                }else if (!gameState.getDices()[0].isSave()){
                    gameState.getDices()[0].setMark(false);
                    updateView();
                }


            }
        });
        mImagePic2 = (ImageButton) findViewById(R.id.pic2);
        mImagePic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gameState.getDices()[1].isMark() && !gameState.getDices()[1].isSave() ){
                    gameState.getDices()[1].setMark(true);
                    updateView();
                }else if (!gameState.getDices()[1].isSave()){
                    gameState.getDices()[1].setMark(false);
                    updateView();
                }

            }
        });
        mImagePic3 = (ImageButton) findViewById(R.id.pic3);
        mImagePic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gameState.getDices()[2].isMark() && !gameState.getDices()[2].isSave() ){
                    gameState.getDices()[2].setMark(true);

                    updateView();
                }else if (!gameState.getDices()[2].isSave()){
                    gameState.getDices()[2].setMark(false);
                    updateView();
                }

            }
        });
        mImagePic4 = (ImageButton) findViewById(R.id.pic4);
        mImagePic4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gameState.getDices()[3].isMark() && !gameState.getDices()[3].isSave() ){
                    gameState.getDices()[3].setMark(true);

                    updateView();
                }else if (!gameState.getDices()[3].isSave()){
                    gameState.getDices()[3].setMark(false);
                    updateView();
                }

            }
        });
        mImagePic5 = (ImageButton) findViewById(R.id.pic5);
        mImagePic5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gameState.getDices()[4].isMark() && !gameState.getDices()[4].isSave() ){
                    gameState.getDices()[4].setMark(true);

                    updateView();
                }else if (!gameState.getDices()[4].isSave()){
                    gameState.getDices()[4].setMark(false);
                    updateView();
                }

            }
        });
        mImagePic6 = (ImageButton) findViewById(R.id.pic6);
        mImagePic6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gameState.getDices()[5].isMark() && !gameState.getDices()[5].isSave()) {
                    gameState.getDices()[5].setMark(true);

                    updateView();
                } else if (!gameState.getDices()[5].isSave()) {
                    gameState.getDices()[5].setMark(false);
                    updateView();
                }

            }
        });

        LoadPreferences();
        updateView();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK && requestCode==0){
            if (data.hasExtra("pressed")){
                gameState.resetTheGame();
                gameState.resetTheSaveDice();
                updateView();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);



    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(GAME_STATE_TAG, gameState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
     gameState=savedInstanceState.getParcelable(GAME_STATE_TAG);
        super.onRestoreInstanceState(savedInstanceState);
        updateView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SavePreferences();
    }



    private void SavePreferences() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(gameState);
        editor.putString(GAME_STATE_TAG, json);
        editor.apply();
    }

    private void LoadPreferences() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(GAME_STATE_TAG,"");
        gameState = gson.fromJson(json, GameState.class);
        System.out.println("load not null");
        if(gameState==null){
            Log.d(TAG, "LoadPreferences():  mGame==null");
                System.out.println("load is null");
            gameState = new GameState();
            gameState.startGame();
        }

    }

    private void updateView() {
        mScoreButton.setEnabled(gameState.isScoreButton());
        mThrowButton.setEnabled(gameState.isThrowButton());
        mSaveButton.setEnabled(gameState.isSaveButton());



        mImagePic1.setSelected(pic1);
        mImagePic2.setSelected(pic2);
        mImagePic3.setSelected(pic3);
        mImagePic4.setSelected(pic4);
        mImagePic5.setSelected(pic5);
        mImagePic6.setSelected(pic6);

        mScoreText.setText(" " + gameState.getScoreOfRound());
        gameStateScoreText.setText(" "+gameState.getGameTotalScore());
        mRoundText.setText(" "+gameState.getRound());

        isImageViewSelectEnabled();

        updateDiceView(mImagePic1, 0, gameState.getDices()[0].getDiceSide());
        updateDiceView(mImagePic2, 1, gameState.getDices()[1].getDiceSide());
        updateDiceView(mImagePic3, 2, gameState.getDices()[2].getDiceSide());
        updateDiceView(mImagePic4, 3, gameState.getDices()[3].getDiceSide());
        updateDiceView(mImagePic5, 4, gameState.getDices()[4].getDiceSide());
        updateDiceView(mImagePic6, 5, gameState.getDices()[5].getDiceSide());


    }

    private void isImageViewSelectEnabled() {
        pic1 = gameState.getSelectImageEnable()[0];
        pic2 = gameState.getSelectImageEnable()[1];
        pic3 = gameState.getSelectImageEnable()[2];
        pic4 = gameState.getSelectImageEnable()[3];
        pic5 = gameState.getSelectImageEnable()[4];
        pic6 = gameState.getSelectImageEnable()[5];


    }

    private void updateDiceView(ImageButton dice, int deckIndex, int diceNr) {

        if (diceNr == 1) {
            if (gameState.getDices()[deckIndex].isSave()) {
                dice.setImageResource(R.drawable.grey1);
            } else if (gameState.getDices()[deckIndex].isMark()) {
                dice.setImageResource(R.drawable.red1);
            } else {
                dice.setImageResource(R.drawable.white1);
            }
        } else if (diceNr == 2) {
            if (gameState.getDices()[deckIndex].isSave()) {
                dice.setImageResource(R.drawable.grey2);
            } else if (gameState.getDices()[deckIndex].isMark()) {
                dice.setImageResource(R.drawable.red2);
            } else {
                dice.setImageResource(R.drawable.white2);
            }
        } else if (diceNr == 3) {
            if (gameState.getDices()[deckIndex].isSave()) {
                dice.setImageResource(R.drawable.grey1);
            } else if (gameState.getDices()[deckIndex].isMark()) {
                dice.setImageResource(R.drawable.red3);
            } else {
                dice.setImageResource(R.drawable.white3);
            }
        } else if (diceNr == 4) {
            if (gameState.getDices()[deckIndex].isSave()) {
                dice.setImageResource(R.drawable.grey4);
            } else if (gameState.getDices()[deckIndex].isMark()) {
                dice.setImageResource(R.drawable.red4);
            } else {
                dice.setImageResource(R.drawable.white4);
            }
        } else if (diceNr == 5) {
            if (gameState.getDices()[deckIndex].isSave()) {
                dice.setImageResource(R.drawable.grey5);
            } else if (gameState.getDices()[deckIndex].isMark()) {
                dice.setImageResource(R.drawable.red5);
            } else {
                dice.setImageResource(R.drawable.white5);
            }
        } else if (diceNr == 6) {
            if (gameState.getDices()[deckIndex].isSave()) {
                dice.setImageResource(R.drawable.grey6);
            } else if (gameState.getDices()[deckIndex].isMark()) {
                dice.setImageResource(R.drawable.red6);
            } else {
                dice.setImageResource(R.drawable.white6);
            }
        }
    }
}