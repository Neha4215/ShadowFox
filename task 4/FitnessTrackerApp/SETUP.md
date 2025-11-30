# Setup Instructions for Fitness Tracker App

## Prerequisites
- Android Studio Arctic Fox or later
- Android SDK API level 21 or higher
- Java Development Kit (JDK) 8 or higher

## Project Setup

1. **Open the Project**
   - Launch Android Studio
   - Select "Open an existing Android Studio project"
   - Navigate to the `FitnessTrackerApp` folder and select it

2. **Sync Project with Gradle**
   - After opening the project, Android Studio will automatically prompt to sync the project
   - If not prompted, click on "File" > "Sync Project with Gradle Files"

3. **Install Dependencies**
   - The project will automatically download the required dependencies:
     - AndroidX AppCompat
     - Material Design Components
     - CardView
     - ConstraintLayout

4. **Build the Project**
   - Click on "Build" > "Make Project" or press `Ctrl+F9` (Windows/Linux) or `Cmd+F9` (Mac)

## Running the Application

### On an Emulator
1. Create an Android Virtual Device (AVD):
   - Go to "Tools" > "AVD Manager"
   - Click "Create Virtual Device"
   - Select a device definition and click "Next"
   - Select a system image (API 21 or higher) and click "Next"
   - Complete the AVD configuration and click "Finish"

2. Run the app:
   - Select the AVD from the device dropdown menu
   - Click the "Run" button or press `Shift+F10`

### On a Physical Device
1. Enable Developer Options and USB Debugging on your Android device
2. Connect your device to your computer via USB
3. Select your device from the device dropdown menu
4. Click the "Run" button or press `Shift+F10`

## Project Features

### Main Screen
- **Set Custom Goals**: Navigate to the goal setting screen
- **Track Activity**: Start tracking physical activity using device sensors
- **View Progress**: See current activity data and goal progress

### Goal Setting
- Choose from four goal types: Steps, Distance, Calories, Active Minutes
- Enter a target value for your goal
- Goals are persisted locally on the device

### Activity Tracking
- Uses the device's accelerometer and step detector sensors
- Tracks steps taken, distance traveled, and calories burned
- Updates in real-time as you move

### Progress Visualization
- Displays current step count
- Shows calories burned
- Visualizes progress toward goals

## Code Structure

### Activities
- `MainActivity`: Entry point with navigation to other screens
- `GoalActivity`: Interface for setting custom fitness goals
- `ProgressActivity`: Displays activity data and goal progress

### Models
- `FitnessGoal`: Represents a fitness goal with type, target, and progress

### Managers
- `GoalManager`: Handles persistence of goals using SharedPreferences
- `ActivityTracker`: Manages sensor data for activity tracking

## Permissions
The app requires the following permissions:
- `ACTIVITY_RECOGNITION`: To track steps and physical activity
- `ACCESS_FINE_LOCATION`: For enhanced activity tracking (if needed)
- `BODY_SENSORS`: To access heart rate and other body sensors (if needed)

These permissions are declared in the `AndroidManifest.xml` file.

## Troubleshooting

### Common Issues
1. **Gradle sync failed**: Ensure you have a stable internet connection and try again
2. **SDK not found**: Make sure Android SDK is properly installed and configured in Android Studio
3. **Device not recognized**: Check USB cable and ensure USB debugging is enabled on your device

### Sensor Issues
- Some features may not work properly on emulators
- For best results, test on a physical device with sensors
- Ensure location services are enabled for accurate tracking

## Future Enhancements
1. Integration with Google Fit or Apple Health
2. Social features for sharing progress
3. Advanced analytics and insights
4. Personalized workout recommendations
5. Integration with wearable devices