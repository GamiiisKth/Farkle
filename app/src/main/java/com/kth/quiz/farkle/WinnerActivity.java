package com.kth.quiz.farkle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class WinnerActivity extends Activity {
    private TextView mWinnerResult;
    private Button mTryAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        Bundle i=getIntent().getExtras();
        mWinnerResult =(TextView)findViewById(R.id.winnerResult);
        mWinnerResult.setText("You got "+i.getInt("totalScore")+" points after "+i.getInt("round") +" rounds");

        mTryAgain=(Button) findViewById(R.id.try_again_button);
        mTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();

            }
        });
    }


    @Override
    public void finish() {
        Intent data = new Intent();
        boolean tryAgain=true;
        data.putExtra("pressed",tryAgain);
        setResult(RESULT_OK,data);
        super.finish();
    }
}
