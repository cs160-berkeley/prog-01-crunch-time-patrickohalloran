package com.patrickohalloran.improvedcalories;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.InputMismatchException;

public class SelectNumber extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_number);
        Intent intent = getIntent();
        String exerciseText = intent.getExtras().getString("selectedExercise");
        this.exerciseName = exerciseText;
        String[] repsWorkouts = getResources().getStringArray(R.array.repsExercises);
        String[] minsWorkouts = getResources().getStringArray(R.array.minsExercises);
        TextView message = (TextView) findViewById(R.id.askNumberMessage);
        if (Arrays.asList(repsWorkouts).contains(exerciseText)) {
            message.setText(getResources().getString(R.string.reps_message));
        } else {
            message.setText(getResources().getString(R.string.mins_message));
        }
    }

    public void onCalculate(View view) {
        try {
            EditText textField = (EditText) findViewById(R.id.numberMessage);
            String number = textField.getText().toString();
            Intent intent = new Intent(this, DisplayResult.class);
            intent.putExtra("numberOfExercise", number);
            intent.putExtra("exerciseName", this.exerciseName);
            if (parseNum(number) < 0) {//will trigger the catch if what you typed in is not a number
                throw new InputMismatchException();
            }
            startActivity(intent);
        } catch (Exception e) {
            Context context = getApplicationContext();
            CharSequence text = "Please enter a non-negative number, thanks!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

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

    private String exerciseName;

}
