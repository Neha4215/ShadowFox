package com.example.fitnesstracker;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.util.Random;

public class ActivityTracker implements SensorEventListener {
    private Context context;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor stepDetector;
    
    // For demo purposes, we'll simulate data
    // In a real app, these would be calculated from sensor data
    private int currentSteps = 0;
    private double distanceTraveled = 0.0;
    private double caloriesBurned = 0.0;
    private long lastUpdateTime = 0;
    
    public ActivityTracker(Context context) {
        this.context = context;
        initializeSensors();
        // For demo, initialize with some values
        Random rand = new Random();
        currentSteps = rand.nextInt(5000);
        distanceTraveled = rand.nextDouble() * 5.0; // km
        caloriesBurned = rand.nextDouble() * 300; // calories
    }
    
    private void initializeSensors() {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            stepDetector = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        }
    }
    
    public void startTracking() {
        if (sensorManager != null && accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (sensorManager != null && stepDetector != null) {
            sensorManager.registerListener(this, stepDetector, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    
    public void stopTracking() {
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }
    
    @Override
    public void onSensorChanged(SensorEvent event) {
        long currentTime = System.currentTimeMillis();
        
        // Limit updates to once per second
        if (currentTime - lastUpdateTime < 1000) {
            return;
        }
        lastUpdateTime = currentTime;
        
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // Process accelerometer data to detect movement/activity
            processAccelerometerData(event.values);
        } else if (event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            // Increment step count when step is detected
            currentSteps++;
            // Update distance and calories (simplified calculations)
            distanceTraveled += 0.0007; // Roughly 0.7 meters per step
            caloriesBurned += 0.04; // Roughly 0.04 calories per step
        }
    }
    
    private void processAccelerometerData(float[] values) {
        // Simplified activity detection based on acceleration changes
        float x = values[0];
        float y = values[1];
        float z = values[2];
        
        // Calculate magnitude of acceleration
        double magnitude = Math.sqrt(x*x + y*y + z*z);
        
        // If significant movement detected, increment activity metrics
        // This is a simplified approach - real implementation would be more complex
        if (magnitude > 12.0) { // Threshold for significant movement
            // For demo purposes, we won't increment here since we're using step detector
            // In a real app, you might use this for activities that don't trigger step detection
        }
    }
    
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this implementation
    }
    
    // Getters for activity data
    public int getCurrentSteps() {
        return currentSteps;
    }
    
    public double getDistanceTraveled() {
        return distanceTraveled;
    }
    
    public double getCaloriesBurned() {
        return caloriesBurned;
    }
    
    // Method to reset daily counters
    public void resetDailyCounters() {
        currentSteps = 0;
        distanceTraveled = 0.0;
        caloriesBurned = 0.0;
    }
}