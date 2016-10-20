package edu.mesa.guessinggame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class LevelOne extends AppCompatActivity implements View.OnClickListener{

    Button button1;
    Button button2;
    Button button3;
    String level = "LevelOne";
    Button buttonLevel1Replay;
    Random randInt = new Random();
    int ourRandom;
    int answerCorrect = 3;
    TextView highscoreLevel1;
    Animation wobble,rotate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);
        wobble = AnimationUtils.loadAnimation(this, R.anim.wobble);
        rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);


        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        buttonLevel1Replay = (Button) findViewById(R.id.buttonLevel1Replay);
        highscoreLevel1 = (TextView) findViewById(R.id.highscoreLevel1);


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        buttonLevel1Replay.setOnClickListener(this);


        ourRandom = randInt.nextInt(3);
        ourRandom++;
        Log.d(level+ "random: ",ourRandom+"");
    }




    @Override
    public void onClick(View view) {

        switch (view.getId())
        {

            case R.id.button1:
                checkAnswer(R.id.button1,1);
                break;
            case R.id.button2:
                checkAnswer(R.id.button2,2);
                break;
            case R.id.button3:
                checkAnswer(R.id.button3,3);
                break;
            default:
                Intent intent = new Intent(LevelOne.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void checkAnswer(int btnid, int answerValue) {
        //check answer is correct
        if (answerValue==ourRandom)
        {
            Button b = (Button) findViewById(btnid);
            b.startAnimation(wobble);
            MainActivity.hiScore = answerCorrect;
            Intent intent = new Intent(LevelOne.this,LevelTwo.class);
            //Two second delay to go to next level
            gotoNextPage(intent);
            highscoreLevel1.setText("Score: "+answerCorrect);
        }
            else
            {
            Button b = (Button) findViewById(btnid);
            b.startAnimation(rotate);
            answerCorrect = answerCorrect - 1;
            if(answerCorrect < 0){
                answerCorrect = 1;
            }
        }
    }

    public void gotoNextPage(final Intent intent){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        },1500);
    }




}
