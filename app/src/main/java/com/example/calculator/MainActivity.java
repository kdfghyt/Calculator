package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    TextView resultTv, solutionTv;
    MaterialButton buttonC, buttonOpenBracket, buttonCloseBracket, buttonDivide;
    MaterialButton button7, button8, button9, buttonMultiply;
    MaterialButton button4, button5, button6, buttonPlus;
    MaterialButton button1, button2, button3, buttonMinus;
    MaterialButton buttonAC, button0, buttonPoint, buttonEqually;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.resultTv);
        solutionTv = findViewById(R.id.solutionTv);
        assignId(buttonC, R.id.buttonC);
        assignId(buttonOpenBracket, R.id.buttonOpenBracket);
        assignId(buttonCloseBracket, R.id.buttonCloseBracket);
        assignId(buttonDivide, R.id.buttonDivide);
        assignId(button7, R.id.button7);
        assignId(button8, R.id.button8);
        assignId(button9, R.id.button9);
        assignId(button4, R.id.button4);
        assignId(button5, R.id.button5);
        assignId(button6, R.id.button6);
        assignId(buttonPlus, R.id.buttonPlus);
        assignId(button1, R.id.button1);
        assignId(button2, R.id.button2);
        assignId(button3, R.id.button3);
        assignId(buttonMinus, R.id.buttonMinus);
        assignId(buttonAC, R.id.buttonAC);
        assignId(buttonPoint, R.id.buttonPoint);
        assignId(button0, R.id.button0);
        assignId(buttonEqually, R.id.buttonEqually);
        assignId(buttonMultiply, R.id.buttonMultiply);
    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonTxt = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if (buttonTxt.equals("ac")) {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (buttonTxt.equals("=")) {
            solutionTv.setText(resultTv.getText());
            return;
        }
        if (buttonTxt.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else {
            dataToCalculate = dataToCalculate + buttonTxt;
        }
        solutionTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if (finalResult.endsWith(".0")) {
            finalResult = finalResult.replace(".0" , "");
        }

        if (!finalResult.equals("Err")) {
            resultTv.setText(finalResult);
        }

    }
    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript,", 1, null).toString();
            return finalResult;
        } catch (Exception e) {
            return "Err";
        }

    }
}