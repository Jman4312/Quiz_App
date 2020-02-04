package com.lowejimmy.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button submitButton;
    Button submitButton2;
    TextView helloName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton = findViewById(R.id.clickButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Hello mom");
                Log.i("testButton","Hi dad!");
                helloName = findViewById(R.id.textBox);
                EditText nameBox = findViewById(R.id.responseEditText);

                String name = nameBox.getText().toString();
                helloName.setText(R.string.Greeting + name);

                getWindow().getDecorView().setBackgroundColor(Color.BLUE);
            }
        });

        submitButton2 = findViewById(R.id.clickButton2);
        submitButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Hello mom");
                Log.i("testButton","Name and Background Reset");
                helloName = findViewById(R.id.textBox);
                EditText nameBox = findViewById(R.id.responseEditText);
                helloName.setText(R.string.Hello_World);

                getWindow().getDecorView().setBackgroundColor(Color.WHITE);
            }
        });
    }
}
