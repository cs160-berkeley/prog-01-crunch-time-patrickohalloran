package com.patrickohalloran.improvedcalories;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class DisplayResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);
        Intent intent = getIntent();
        String numberOfExercise = intent.getExtras().getString("numberOfExercise");
        String exerciseName = intent.getExtras().getString("exerciseName");
        //Get the textview where we display the reps and minutes of the other exercises
        TextView otherReps = (TextView) findViewById(R.id.otherExercises);
        this.ratios = new HashMap<String, Double>();
        ratios.put("Pushup", 3.5);
        ratios.put("Situp", 2.0);
        ratios.put("Jumping Jacks", 0.1);
        ratios.put("Jogging", 0.12);
        ratios.put("Squats", 2.25);
        ratios.put("Leg-lift", 0.25);
        ratios.put("Plank", 0.25);
        ratios.put("Pullup", 1.0);
        ratios.put("Cycling", 0.12);
        ratios.put("Walking", 0.20);
        ratios.put("Swimming", 0.13);
        ratios.put("Stair-Climbing", 0.15);
        this.messages = new HashMap<String, String>();
        messages.put("Pushup", getResources().getString(R.string.pushup_message));
        messages.put("Situp", getResources().getString(R.string.situp_message));
        messages.put("Jumping Jacks", getResources().getString(R.string.jumpingjacks_message));
        messages.put("Jogging", getResources().getString(R.string.jogging_message));
        messages.put("Squats", getResources().getString(R.string.squats_message));
        messages.put("Leg-lift", getResources().getString(R.string.leg_lift_message));
        messages.put("Plank", getResources().getString(R.string.plank_message));
        messages.put("Pullup", getResources().getString(R.string.pullup_message));
        messages.put("Cycling", getResources().getString(R.string.cycling_message));
        messages.put("Walking", getResources().getString(R.string.walking_message));
        messages.put("Swimming", getResources().getString(R.string.swimming_message));
        messages.put("Stair-Climbing", getResources().getString(R.string.stair_climbing_message));
        TextView caloriesBurned = (TextView) findViewById(R.id.result);
        Integer numBurned = calculate(exerciseName, parseNum(numberOfExercise));
        caloriesBurned.setText(numBurned.toString());
        otherReps.setText(otherExercises(numBurned.intValue(), exerciseName));
    }

    //parses the number from string representation to int representation
    private int parseNum(String result) {
        int numericalResult = 0;
        if (result.indexOf('.') >= 0) {
            Double decimalResult = Double.parseDouble(result);
            decimalResult = Math.ceil(decimalResult);
            numericalResult = decimalResult.intValue();
        } else {
            numericalResult = Integer.parseInt(result);
        }
        return numericalResult;
    }


    //gets number of calories burned
    private Integer calculate (String exercise, int numRepsMins) {
        Double formula = ratios.get(exercise);
        Double calories = (numRepsMins / formula);
        return Integer.valueOf(calories.intValue());
    }

    //gets number of reps and minutes for other exercises
    private String otherExercises(int numCalories, String currExercise) {
        StringBuilder result = new StringBuilder();
        for (String exercise : ratios.keySet()) {
            if (!exercise.equals(currExercise)) {
                Integer repsMins = new Integer(1);
                if (!exercise.equals("Situp") && !exercise.equals("Pullup")) {
                    Double dRepsMins = numCalories * ratios.get(exercise);
                    repsMins = Integer.valueOf(dRepsMins.intValue());
                } else {
                    repsMins = Integer.valueOf(numCalories * ratios.get(exercise).intValue());
                }

                result.append(repsMins.toString() + " " + messages.get(exercise) + "\n");
            }
        }
        return result.toString();
    }

    public void onClickGoHome(View view) {
        Intent intent = new Intent(this, SelectExercise.class);
        startActivity(intent);
    }



    //The different exercises and their ratios
    private HashMap<String, Double> ratios;

    //Different messages for exercises
    private HashMap<String, String> messages;
}
