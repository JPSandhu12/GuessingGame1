package edu.mesa.guessinggame;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class LevelThree extends AppCompatActivity implements View.OnClickListener {
    TextView highScoreText;
    int ourRandom;
    int answerCorrect = 10;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button10;
    Button buttonReplayLevelThree;
    Random randInt = new Random();
    String level = "LevelThree";
    Animation wobble,rotate;
    String dataName = "highscore";
    String intName = "score";
    SharedPreferences preferences;
    int hiScoreWinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_three);
        wobble = AnimationUtils.loadAnimation(this, R.anim.wobble);
        rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);


        preferences = getSharedPreferences(dataName, getApplicationContext().MODE_PRIVATE);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button10 = (Button) findViewById(R.id.button10);

        buttonReplayLevelThree = (Button) findViewById(R.id.buttonReplay);
        highScoreText = (TextView) findViewById(R.id.highScoreText);
        highScoreText.setText("Score: "+MainActivity.hiScore);




        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);
        buttonReplayLevelThree.setOnClickListener(this);


        ourRandom = randInt.nextInt(10);
        ourRandom++;
        Log.d(level+ "randrom: ",ourRandom+"");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.button1:
                checkAnswer(R.id.button1,1);
                break;
            case R.id.button2:
                checkAnswer(R.id.button2,2);
                break;
            case R.id.button3:
                checkAnswer(R.id.button3,3);
                break;
            case R.id.button4:
                checkAnswer(R.id.button4,4);
                break;
            case R.id.button5:
                checkAnswer(R.id.button5,5);
                break;
            case R.id.button6:
                checkAnswer(R.id.button6,6);
                break;
            case R.id.button7:
                checkAnswer(R.id.button7,7);
                break;
            case R.id.button8:
                checkAnswer(R.id.button8,8);
                break;
            case R.id.button9:
                checkAnswer(R.id.button9,9);
                break;
            case R.id.button10:
                checkAnswer(R.id.button10,10);
                break;
            default:
                Intent intent = new Intent(LevelThree.this,MainActivity.class);
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
            Intent intent = new Intent(LevelThree.this,Winner.class);


            hiScoreWinner = preferences.getInt(intName, 0);
            if(MainActivity.hiScore<hiScoreWinner){
                Intent intentHome = new Intent(LevelThree.this,MainActivity.class);
                startActivity(intentHome);


            }
            else
            {
                gotoNextPage(intent);
            }




        }else
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
        },800);
    }

}
