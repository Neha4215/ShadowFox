package com.example.fitnesstracker;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class ProgressActivity extends AppCompatActivity {

    private TextView tvCurrentSteps;
    private TextView tvGoalProgress;
    private TextView tvCaloriesBurned;
    private GoalManager goalManager;
    private ActivityTracker activityTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        initializeViews();
        goalManager = new GoalManager(this);
        activityTracker = new ActivityTracker(this);
        updateProgressDisplay();
    }

    private void initializeViews() {
        tvCurrentSteps = findViewById(R.id.tv_current_steps);
        tvGoalProgress = findViewById(R.id.tv_goal_progress);
        tvCaloriesBurned = findViewById(R.id.tv_calories_burned);
    }

    private void updateProgressDisplay() {
        // Get current activity data
        int currentSteps = activityTracker.getCurrentSteps();
        double caloriesBurned = activityTracker.getCaloriesBurned();
        
        // Update UI
        tvCurrentSteps.setText(String.valueOf(currentSteps));
        tvCaloriesBurned.setText(String.format("%.2f", caloriesBurned));
        
        // Show goal progress
        List<FitnessGoal> goals = goalManager.getAllGoals();
        if (!goals.isEmpty()) {
            StringBuilder progressText = new StringBuilder();
            for (FitnessGoal goal : goals) {
                double progress = calculateProgress(goal);
                progressText.append(goal.getType())
                           .append(": ")
                           .append(String.format("%.1f", progress))
                           .append("%\n");
            }
            tvGoalProgress.setText(progressText.toString());
        } else {
            tvGoalProgress.setText("No goals set yet");
        }
    }

    private double calculateProgress(FitnessGoal goal) {
        // Simplified progress calculation
        // In a real app, this would compare current activity data with goal targets
        switch (goal.getType()) {
            case "Steps":
                return Math.min(100, (activityTracker.getCurrentSteps() / goal.getTargetValue()) * 100);
            case "Distance (km)":
                return Math.min(100, (activityTracker.getDistanceTraveled() / goal.getTargetValue()) * 100);
            case "Calories":
                return Math.min(100, (activityTracker.getCaloriesBurned() / goal.getTargetValue()) * 100);
            default:
                return 0;
        }
    }
}