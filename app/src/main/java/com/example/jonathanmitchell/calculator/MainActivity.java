package com.example.jonathanmitchell.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.ParseException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView resultText;
    private EditText leftNum;
    private EditText rightNum;
    private Button chooseSignButton;


    private TextWatcher listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            CalculatorFactory calculatorFactory = CalculatorFactory.get(MainActivity.this);

            try {

                double num1 = leftNum.getText().toString().isEmpty() ? 0 : Double.parseDouble(leftNum.getText().toString());
                double num2 = rightNum.getText().toString().isEmpty() ? 0 : Double.parseDouble(rightNum.getText().toString());

                calculatorFactory.setNumberOne(num1);
                calculatorFactory.setNumberTwo(num2);

                resultText.setText(calculatorFactory.performOperation() + "");

            } catch (NumberFormatException e){
                Log.e("ERROR PARSING DOUBLE: ", e.toString());
                resultText.setText("NaN!");
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chooseSignButton = (Button) findViewById(R.id.button_choose_sign);
        chooseSignButton.setOnClickListener(this);
        resultText = (TextView) findViewById(R.id.result);

        leftNum = (EditText) findViewById(R.id.first_num);
        rightNum = (EditText) findViewById(R.id.second_num);

        leftNum.addTextChangedListener(this.listener);
        rightNum.addTextChangedListener(this.listener);

        CalculatorFactory.get(this).setListener(new CalculatorFactory.OnCalculationChangedListener() {

            @Override
            public void onSignChange(char sign) {
                chooseSignButton.setText(sign+"");
                resultText.setText(CalculatorFactory.get(MainActivity.this).performOperation() + "");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("MA", "OnActivityResult");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch(id){
            case R.id.button_choose_sign: {
                PickerDialogFragment.newInstance().show(getSupportFragmentManager(),"TAG");
            }
        }
    }
}
