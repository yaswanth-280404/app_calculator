package com.example.calculator;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;  // Import statement
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {  // Extending AppCompatActivity

    private TextView display;
    private String currentInput = "";
    private String operator = "";
    private double firstNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Link to XML layout

        display = findViewById(R.id.display);

        // Numbers
        setNumberClickListeners();

        // Operations
        findViewById(R.id.btnAdd).setOnClickListener(this::onOperatorClick);
        findViewById(R.id.btnSubtract).setOnClickListener(this::onOperatorClick);
        findViewById(R.id.btnMultiply).setOnClickListener(this::onOperatorClick);
        findViewById(R.id.btnDivide).setOnClickListener(this::onOperatorClick);

        // Clear and Equals
        findViewById(R.id.btnClear).setOnClickListener(v -> clear());
        findViewById(R.id.btnEquals).setOnClickListener(v -> calculateResult());
    }

    private void setNumberClickListeners() {
        int[] numberIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
        for (int id : numberIds) {
            findViewById(id).setOnClickListener(v -> onNumberClick((Button) v));
        }
    }

    private void onNumberClick(Button button) {
        currentInput += button.getText().toString();
        display.setText(currentInput);
    }

    private void onOperatorClick(View view) {
        firstNumber = Double.parseDouble(currentInput);
        currentInput = "";
        operator = ((Button) view).getText().toString();
    }

    private void calculateResult() {
        double secondNumber = Double.parseDouble(currentInput);
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
                result = firstNumber / secondNumber;
                break;
        }

        display.setText(String.valueOf(result));
        currentInput = String.valueOf(result);
    }

    private void clear() {
        currentInput = "";
        firstNumber = 0;
        operator = "";
        display.setText("0");
    }
}
