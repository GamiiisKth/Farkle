package com.kth.quiz.farkle;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class GameActivity extends ActionBarActivity {

    private GameState gameState;

    private boolean pic1;
    private boolean pic2;
    private boolean pic3;
    private boolean pic4;
    private boolean pic5;
    private boolean pic6;

    private ImageButton mImagePic1;
    private ImageButton mImagePic2;
    private ImageButton mImagePic3;
    private ImageButton mImagePic4;
    private ImageButton mImagePic5;
    private ImageButton mImagePic6;

    private int button01pos = 0;
    private int button02pos = 0;
    private int button03pos = 0;
    private int button04pos = 0;
    private int button05pos = 0;
    private int button06pos = 0;

    private Button mSaveButton;
    private Button mScoreButton;
    private Button mThrowButton;

    private TextView mScoreText;
    private TextView mRoundText;
    private TextView mGameScoreText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameState = new GameState();

        mSaveButton = (Button) findViewById(R.id.save_Button);
        mScoreButton = (Button) findViewById(R.id.Score_Button);
        mThrowButton = (Button) findViewById(R.id.throw_Button);

        mScoreText=(TextView) findViewById(R.id.Score_Value);
        mRoundText=(TextView)findViewById(R.id.round_Value);
        mGameScoreText=(TextView) findViewById(R.id.Game_Score_Value);

        mImagePic1 = (ImageButton) findViewById(R.id.pic1);
        mImagePic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mImagePic2 = (ImageButton) findViewById(R.id.pic2);
        mImagePic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mImagePic3 = (ImageButton) findViewById(R.id.pic3);
        mImagePic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mImagePic4 = (ImageButton) findViewById(R.id.pic4);
        mImagePic4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mImagePic5 = (ImageButton) findViewById(R.id.pic5);
        mImagePic5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mImagePic6 = (ImageButton) findViewById(R.id.pic6);
        mImagePic6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        updateView();

        mScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameState.onScore();
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameState.onSave();
            }
        });

        mThrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameState.onThrow();
            }
        });

    }


    private void updateView() {
        mScoreButton.setEnabled(gameState.isScoreButtonState());
        mThrowButton.setEnabled(gameState.isThrowButtonState());
        mSaveButton.setEnabled(gameState.isSaveButtonState());

        isImageViewSelectEnabled();

        mImagePic1.setSelected(pic1);
        mImagePic2.setSelected(pic2);
        mImagePic3.setSelected(pic3);
        mImagePic4.setSelected(pic4);
        mImagePic5.setSelected(pic5);
        mImagePic6.setSelected(pic6);

        mScoreText.setText(gameState.getScoreOfRound());
        mGameScoreText.setText(gameState.getSumOfGame());
        mRoundText.setText(gameState.getRound());

        getDiceView();
    }

    private void isImageViewSelectEnabled() {
        pic1=gameState.getImageSelectEnable()[0];
        pic2=gameState.getImageSelectEnable()[1];
        pic3=gameState.getImageSelectEnable()[2];
        pic4=gameState.getImageSelectEnable()[3];
        pic5=gameState.getImageSelectEnable()[4];
        pic6=gameState.getImageSelectEnable()[5];



    }

    private void getDiceView(){


    }
}