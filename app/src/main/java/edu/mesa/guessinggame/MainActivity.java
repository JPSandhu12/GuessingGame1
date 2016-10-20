package edu.mesa.guessinggame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences preferences;
    String dataName = "highscore";
    String intName = "score";
    int defaultInt = 0;
    //both activities can see this
    public static int hiScore = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize our two SharedPreferences objects
        preferences = getSharedPreferences(dataName, getApplicationContext().MODE_PRIVATE);
        //Either load our High score or
        //if not available our default of 0
        hiScore = preferences.getInt(intName, defaultInt);
        //Make a reference to the Hiscore textview in our layout
        TextView textHiScore =(TextView) findViewById(R.id.textHiScore);

        //Display the hi score
        textHiScore.setText("High Score: "+ hiScore);

        Button buttonPlay = (Button) findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this, R.raw.buttonclick);
        mPlayer.start();
        Intent i;
        i = new Intent(this, LevelOne.class);
        startActivity(i);
    }
}
