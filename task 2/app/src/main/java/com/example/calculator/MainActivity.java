package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private String currentNumber = "";
    private String operator = "";
    private double firstNumber = 0;
    private boolean isNewOperation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

        // Number buttons
        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonDot = findViewById(R.id.buttonDot);

        // Operation buttons
        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonSubtract = findViewById(R.id.buttonSubtract);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonDivide = findViewById(R.id.buttonDivide);
        Button buttonEquals = findViewById(R.id.buttonEquals);
        Button buttonClear = findViewById(R.id.buttonClear);

        // Number button listeners
        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (isNewOperation) {
                    currentNumber = button.getText().toString();
                    isNewOperation = false;
                } else {
                    currentNumber += button.getText().toString();
                }
                resultTextView.setText(currentNumber);
            }
        };

        button0.setOnClickListener(numberListener);
        button1.setOnClickListener(numberListener);
        button2.setOnClickListener(numberListener);
        button3.setOnClickListener(numberListener);
        button4.setOnClickListener(numberListener);
        button5.setOnClickListener(numberListener);
        button6.setOnClickListener(numberListener);
        button7.setOnClickListener(numberListener);
        button8.setOnClickListener(numberListener);
        button9.setOnClickListener(numberListener);

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentNumber.contains(".")) {
                    if (isNewOperation) {
                        currentNumber = "0.";
                        isNewOperation = false;
                    } else {
                        currentNumber += ".";
                    }
                    resultTextView.setText(currentNumber);
                }
            }
        });

        // Operator button listeners
        View.OnClickListener operatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (!operator.isEmpty() && !isNewOperation) {
                    performCalculation();
                }
                firstNumber = Double.parseDouble(currentNumber);
                operator = button.getText().toString();
                isNewOperation = true;
            }
        };

        buttonAdd.setOnClickListener(operatorListener);
        buttonSubtract.setOnClickListener(operatorListener);
        buttonMultiply.setOnClickListener(operatorListener);
        buttonDivide.setOnClickListener(operatorListener);

        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation();
                operator = "";
                isNewOperation = true;
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentNumber = "";
                operator = "";
                firstNumber = 0;
                isNewOperation = true;
                resultTextView.setText("0");
            }
        });
    }

    private void performCalculation() {
        if (!operator.isEmpty() && !currentNumber.isEmpty()) {
            double secondNumber = Double.parseDouble(currentNumber);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        resultTextView.setText("Error");
                        return;
                    }
                    break;
            }

            // Format result to remove unnecessary decimal places
            if (result == (long) result) {
                currentNumber = String.valueOf((long) result);
            } else {
                currentNumber = String.valueOf(result);
            }

            resultTextView.setText(currentNumber);
            firstNumber = result;
        }
    }
}