package com.kth.quiz.farkle;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
        Intent i=getIntent();
        mWinnerResult =(TextView)findViewById(R.id.withText);
        int totalScore=i.getIntExtra("totalScore");
        mWinnerResult.setText(" "++ "-"+ i.getStringExtra("round"));

        mTryAgain=(Button) findViewById(R.id.try_again_button);
        mTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }




}
