package com.example.fitnesstracker;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class GoalManager {
    private static final String PREFS_NAME = "FitnessGoals";
    private static final String GOALS_KEY = "goals";
    private SharedPreferences sharedPreferences;

    public GoalManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveGoal(FitnessGoal goal) {
        List<FitnessGoal> goals = getAllGoals();
        goals.add(goal);
        saveAllGoals(goals);
    }

    public List<FitnessGoal> getAllGoals() {
        List<FitnessGoal> goals = new ArrayList<>();
        String goalsJson = sharedPreferences.getString(GOALS_KEY, null);
        
        if (goalsJson != null) {
            try {
                JSONArray jsonArray = new JSONArray(goalsJson);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String type = jsonObject.getString("type");
                    double targetValue = jsonObject.getDouble("targetValue");
                    double currentValue = jsonObject.getDouble("currentValue");
                    
                    FitnessGoal goal = new FitnessGoal(type, targetValue);
                    goal.setCurrentValue(currentValue);
                    goals.add(goal);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        
        return goals;
    }

    public void saveAllGoals(List<FitnessGoal> goals) {
        JSONArray jsonArray = new JSONArray();
        
        for (FitnessGoal goal : goals) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("type", goal.getType());
                jsonObject.put("targetValue", goal.getTargetValue());
                jsonObject.put("currentValue", goal.getCurrentValue());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(GOALS_KEY, jsonArray.toString());
        editor.apply();
    }

    public void updateGoalProgress(String goalType, double newValue) {
        List<FitnessGoal> goals = getAllGoals();
        boolean updated = false;
        
        for (FitnessGoal goal : goals) {
            if (goal.getType().equals(goalType)) {
                goal.setCurrentValue(newValue);
                updated = true;
                break;
            }
        }
        
        if (updated) {
            saveAllGoals(goals);
        }
    }
}