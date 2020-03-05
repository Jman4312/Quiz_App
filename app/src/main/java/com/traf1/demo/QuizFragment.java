package com.traf1.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Timer;

public class QuizFragment extends Fragment {
    Timer timer = new Timer();
    int duration = 0;
    TextView timeDisplay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_second, container, false);
    }

    public void returnToPrevious(View view) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("com.lowejimmy.quizapp.extra.REPLY", "Results");//insert message

        returnIntent.putExtra("com.lowejimmy.quizapp.extra.REPLY", "Results");//insert message
    }

    private void timeOut() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("com.lowejimmy.quizapp.extra.REPLY", "You ran out of time\nResults");//insert message
    }
}
