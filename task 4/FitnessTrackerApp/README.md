# Fitness Tracking App with Custom Goals

## Overview
This is a fitness tracking application developed in Java and XML for Android devices. The app uses device sensors to monitor physical activities and allows users to set custom fitness goals and track their progress.

## Features
- Activity tracking using device sensors (accelerometer, step detector)
- Custom goal setting (steps, distance, calories, active minutes)
- Progress visualization and insights
- Data persistence using SharedPreferences

## Project Structure
```
FitnessTrackerApp/
├── src/
│   ├── main/
│   │   ├── java/com/example/fitnesstracker/
│   │   │   ├── MainActivity.java
│   │   │   ├── GoalActivity.java
│   │   │   ├── ProgressActivity.java
│   │   │   ├── FitnessGoal.java
│   │   │   ├── GoalManager.java
│   │   │   └── ActivityTracker.java
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml
│   │   │   │   ├── activity_goal.xml
│   │   │   │   └── activity_progress.xml
│   │   │   ├── values/
│   │   │   │   ├── strings.xml
│   │   │   │   ├── colors.xml
│   │   │   │   └── styles.xml
│   │   │   ├── drawable/
│   │   │   ├── mipmap/
│   │   └── AndroidManifest.xml
├── build.gradle
└── settings.gradle
```

## Key Components

### 1. MainActivity
The main entry point of the application with three primary functions:
- Set Custom Goals
- Track Activity
- View Progress

### 2. GoalActivity
Allows users to set custom fitness goals by selecting a goal type and entering a target value.

### 3. ProgressActivity
Displays current activity data and progress toward goals.

### 4. FitnessGoal
Model class representing a fitness goal with type, target value, and current progress.

### 5. GoalManager
Handles persistence of fitness goals using SharedPreferences and JSON serialization.

### 6. ActivityTracker
Manages sensor data from device sensors to track physical activity.

## How to Build and Run
1. Open the project in Android Studio
2. Sync the project with Gradle files
3. Build and run the application on an Android device or emulator

## Permissions Required
- ACTIVITY_RECOGNITION
- ACCESS_FINE_LOCATION
- BODY_SENSORS

## Future Enhancements
- Integration with Google Fit or other health platforms
- Advanced analytics and insights
- Social features for sharing progress
- Notification system for goal reminders
- More detailed activity categorization