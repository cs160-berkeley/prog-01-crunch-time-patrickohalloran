package com.patrickohalloran.improvedcalories;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.HashMap;

public class SelectExercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_exercise);
        populateLayout();

    }

    public void populateLayout() {
        Button option;
        LinearLayout ll = (LinearLayout) findViewById(R.id.verticalLayout);
        //LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams();
        String[] workouts = getResources().getStringArray(R.array.exercises);
        for (int i=0; i < workouts.length; i++) {
            final String exercise = workouts[i];
            option = new Button(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            option.setLayoutParams(params);
            option.setText(exercise);
            int newID = i;
            option.setId(newID);
            //idMappings.put(exercise, i);
            option.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), SelectNumber.class);
                    intent.putExtra("selectedExercise", exercise);
                    startActivity(intent);
                }
            });
            option.setBackgroundColor(getResources().getColor(R.color.myWhite));
            LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            buttonParams.setMargins(0, 0, 0, 10);
            option.setLayoutParams(buttonParams);
            option.setTextColor(getResources().getColor(R.color.colorAccent));
            ll.addView(option);
        }
    }

    //private HashMap<String, Integer> idMappings = new HashMap<String, Integer>();
}
