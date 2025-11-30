package com.example.fitnesstracker;

public class FitnessGoal {
    private String type;
    private double targetValue;
    private double currentValue;

    public FitnessGoal(String type, double targetValue) {
        this.type = type;
        this.targetValue = targetValue;
        this.currentValue = 0.0;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(double targetValue) {
        this.targetValue = targetValue;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public double getProgressPercentage() {
        if (targetValue <= 0) {
            return 0;
        }
        return (currentValue / targetValue) * 100;
    }

    @Override
    public String toString() {
        return "FitnessGoal{" +
                "type='" + type + '\'' +
                ", targetValue=" + targetValue +
                ", currentValue=" + currentValue +
                '}';
    }
}