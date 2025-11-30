package com.example.fitnesstracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class GoalActivity extends AppCompatActivity {

    private EditText etGoalValue;
    private Spinner spinnerGoalType;
    private Button btnSaveGoal;
    private GoalManager goalManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        initializeViews();
        goalManager = new GoalManager(this);
        setupClickListeners();
    }

    private void initializeViews() {
        etGoalValue = findViewById(R.id.et_goal_value);
        spinnerGoalType = findViewById(R.id.spinner_goal_type);
        btnSaveGoal = findViewById(R.id.btn_save_goal);
    }

    private void setupClickListeners() {
        btnSaveGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveGoal();
            }
        });
    }

    private void saveGoal() {
        String goalValueStr = etGoalValue.getText().toString();
        String goalType = spinnerGoalType.getSelectedItem().toString();

        if (goalValueStr.isEmpty()) {
            Toast.makeText(this, "Please enter a goal value", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double goalValue = Double.parseDouble(goalValueStr);
            FitnessGoal goal = new FitnessGoal(goalType, goalValue);
            goalManager.saveGoal(goal);
            Toast.makeText(this, "Goal saved successfully!", Toast.LENGTH_SHORT).show();
            finish(); // Close this activity and return to main
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
        }
    }
}