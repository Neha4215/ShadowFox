package com.example.fitnesstracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnSetGoals;
    private Button btnTrackActivity;
    private Button btnViewProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        btnSetGoals = findViewById(R.id.btn_set_goals);
        btnTrackActivity = findViewById(R.id.btn_track_activity);
        btnViewProgress = findViewById(R.id.btn_view_progress);
    }

    private void setupClickListeners() {
        btnSetGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GoalActivity.class);
                startActivity(intent);
            }
        });

        btnTrackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start activity tracking
                startTracking();
            }
        });

        btnViewProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProgressActivity.class);
                startActivity(intent);
            }
        });
    }

    private void startTracking() {
        // Implementation for starting activity tracking using device sensors
        // This would typically involve using SensorManager and registering listeners
        // for accelerometer, gyroscope, etc.
    }
}