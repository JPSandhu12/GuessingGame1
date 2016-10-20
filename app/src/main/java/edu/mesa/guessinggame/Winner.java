package edu.mesa.guessinggame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Winner extends AppCompatActivity {
    TextView textHighScore,win;
    SharedPreferences preferences;
    String dataName = "highscore";
    String intName = "score";
    int defaultInt = 0;
    int hiScoreWinner;
    SharedPreferences.Editor editor;
    ImageView imageView;
    TextView yougotnewhighscore;
    Button playagain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        preferences = getSharedPreferences(dataName, getApplicationContext().MODE_PRIVATE);
        editor = preferences.edit();


        //get previous highscore
        hiScoreWinner = preferences.getInt(intName, defaultInt);
        Log.d("Hi score winner: ",""+hiScoreWinner);
        Log.d("Hi Hiscore: ",""+MainActivity.hiScore);

        textHighScore = (TextView) findViewById(R.id.textHighScore);
        win = (TextView) findViewById(R.id.win);
        imageView = (ImageView) findViewById(R.id.imageView2);
        yougotnewhighscore = (TextView) findViewById(R.id.yougotnewhighscore);
        playagain = (Button) findViewById(R.id.playagain);
        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Winner.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

            textHighScore.setText("Score: "+MainActivity.hiScore);
            editor.putInt(intName,MainActivity.hiScore);
            editor.commit();
            Log.d("Saved Score: ", String.valueOf(preferences.getInt("score",1)));
    }
}
