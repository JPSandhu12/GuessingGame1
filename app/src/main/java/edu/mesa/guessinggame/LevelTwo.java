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

public class LevelTwo extends AppCompatActivity implements View.OnClickListener{
    TextView HighScoreLevel2;
    int ourRandom;
    int answerCorrect = 5;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button buttonLevel2Replay;
    Random randInt = new Random();
    String level = "Level Two";
    Animation wobble,rotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_two);
        wobble = AnimationUtils.loadAnimation(this, R.anim.wobble);
        rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);


        //get views
        button1 = (Button)findViewById(R.id.button1Level2);
        button2 = (Button)findViewById(R.id.button2Level2);
        button3 = (Button)findViewById(R.id.button3Level2);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        buttonLevel2Replay = (Button) findViewById(R.id.buttonLevel2Replay);
        HighScoreLevel2 = (TextView) findViewById(R.id.HighScoreLevel2);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        buttonLevel2Replay.setOnClickListener(this);

        //set current high score
        HighScoreLevel2.setText("Score: "+MainActivity.hiScore);

        //Genrate a random number on start of the Level
        ourRandom = randInt.nextInt(5);
        ourRandom++;
        Log.d(level+ "random: ",ourRandom+"");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.button1Level2:
                checkAnswer(R.id.button1Level2,1);
                break;
            case R.id.button2Level2:
                checkAnswer(R.id.button2Level2,2);
                break;
            case R.id.button3Level2:
                checkAnswer(R.id.button3Level2,3);
                break;
            case R.id.button4:
                checkAnswer(R.id.button4,4);
                break;
            case R.id.button5:
                checkAnswer(R.id.button5,5);
                break;
            default:
                Intent intent = new Intent(LevelTwo.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
    private void checkAnswer(int btnid,int answerValue) {
        //check answer is correct
        if (answerValue==ourRandom){
            Button b = (Button) findViewById(btnid);
            b.startAnimation(wobble);
            MainActivity.hiScore = answerCorrect + MainActivity.hiScore;
            Intent intent = new Intent(LevelTwo.this,LevelThree.class);
            gotoNextPage(intent);
        }else {

            //Incorrect answer
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
        },800);
    }

}
