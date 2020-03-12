package com.traf1.demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;


public class MainActivity extends AppCompatActivity {
    Button submitButton;
    EditText responseText, response2Text;
    TextView displayText, displayText2;
    Timer timer = new Timer();
    SharedPreferences mPreferences;
    int duration = 0;
    int numOfClicks = 0;
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
    Map leaderBoard;
    Gson gson = new Gson();
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize preferences
        mPreferences = getSharedPreferences("com.traf1.demo.sharedprefs",MODE_PRIVATE);
        leaderBoard = gson.fromJson(mPreferences.getString("leaderboard", ""), HashMap.class);
        intent = new Intent(getApplicationContext(), SecondActivity.class);
        intent.putExtra("com.lowejimmy.quizapp.extra.LEADERBOARD", gson.toJson(leaderBoard));
        //response2Text = findViewById(R.id.response2EditText);
        submitButton=findViewById(R.id.clickButton);
        responseText=findViewById(R.id.responseEditText);
        displayText=findViewById(R.id.textBox);
        displayText2=findViewById(R.id.textBox2);
        //restore preferences into submit button
        //submitButton.setText(mPreferences.getString("mResponse","defaultString")
        //        +mPreferences.getInt("mResponseNum",99));
        responseText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(responseText.getText().toString().equals("TJ")){
                        displayText.setText("TJ Rocks!");
                        responseText.setText("");
                        responseText.setHint("That's a good name.");
                    }
                }
            }
        });

    }
    @Override
    protected void onPause() {
        super.onPause();
        //store values into preferences onPause
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString("mResponse", responseText.getText().toString());
        // preferencesEditor.putInt("mResponseNum", Integer.parseInt(response2Text.getText().toString()));
        preferencesEditor.apply();
    }
    public void submit(View view) {//process button onClick event
    /*
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String currentTime = getString(R.string.time,duration++);
                        displayText.setText(currentTime);
                        if(duration>=5){
                            timer.cancel();
                            timer.purge();
                            timer=new Timer();
                            duration=0;
                            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                            intent.putExtra("com.traf1.demo.extra.MESSAGE",displayText.getText().toString());
                            startActivityForResult(intent, 1);
                        }
                    }
                });
            }
        }, 1000, 1000);
    */
        // ArrayList<String> texts = new ArrayList<>();
        // texts.add("Hi Mom!");
        // texts.add("Hi Dad!");
        // texts.add("Hi Person!");

        /*

        String[] texts = getResources().getStringArray(R.array.textArray);
        displayText.setText(texts[numOfClicks]);
        numOfClicks += 1;
        if (numOfClicks >= texts.length){ numOfClicks = 0;}
        //Toast.makeText(getApplicationContext(),"Done reading.",Toast.LENGTH_SHORT).show();
        */

        // Create New Activity
        EditText nameBox = findViewById(R.id.responseEditText);
        intent.putExtra("com.lowejimmy.quizapp.extra.MESSAGE",nameBox.getText().toString());
        displayText2.setText(nameBox.getText().toString());
        startActivityForResult(intent, 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 & resultCode==RESULT_OK){
            displayText.setText(data.getStringExtra("com.lowejimmy.quizapp.extra.REPLY"));
            leaderBoard = (HashMap) data.getSerializableExtra("com.lowejimmy.quizapp.extra.LEADERBOARD");
            String leaderBoardString2 = gson.toJson(leaderBoard);
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.putString("leaderboard", leaderBoardString2);
            // preferencesEditor.putInt("mResponseNum", Integer.parseInt(response2Text.getText().toString()));
            preferencesEditor.apply();

            int[] values = new int[10];
            String[] names = new String[10];
            for (Object key : leaderBoard.keySet()) {
                String value2 = String.valueOf(leaderBoard.get(key));
                int value;
                if (value2.contains(".")) {
                    value = Integer.parseInt(value2.substring(0, value2.indexOf('.')));
                }
                else {
                    value = Integer.parseInt(value2);
                }
                int index = 0;
                for (int i = 0; i < values.length; i ++) {
                    if (value > values[i]) {
                        index += 1;
                    }
                    else {break;}
                }
                if (index >= 1) {
                    values[index - 1] = value;
                    names[index-1] = String.valueOf(key);
                }
            }
            String leaderBoardString = "";
            for (int i = 9; i >= 0; i--) {
                if(values[i] > 0) {
                    leaderBoardString = leaderBoardString +  "\n" + String.valueOf(10-i) + ": " + names[i] + " Score: " + String.valueOf(values[i]);
                }
            }
            TextView leaderBoardView = findViewById(R.id.leaderBoard);
            leaderBoardView.setText(leaderBoardString);
        }
    }
}