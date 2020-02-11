package com.traf1.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SecondActivity extends AppCompatActivity {
    Timer timer = new Timer();
    int duration = 0;
    TextView timeDisplay = findViewById(R.id.gameTime);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        TextView label = findViewById(R.id.label);
        label.setText(intent.getStringExtra("com.lowejimmy.quizapp.extra.MESSAGE"));

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String currentTime = getString(R.string.time,duration++);
                        if(duration>=5){
                            timer.cancel();
                            timer.purge();
                            timer=new Timer();
                            duration=0;
                            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                            intent.putExtra("com.traf1.demo.extra.MESSAGE",timeDisplay.getText().toString());
                            startActivityForResult(intent, 1);
                        }
                    }
                });
            }
        }, 1000, 1000);
    }

    public void returnToPrevious(View view) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("com.lowejimmy.quizapp.extra.REPLY", "This is my return string!!!");//insert message
        setResult(RESULT_OK, returnIntent);//indicate response was successful
        finish();//close activity and return to main activity
    }
}