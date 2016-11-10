package com.example.jonathanmitchell.calculator;

import android.content.Context;

/**
 * Created by jonathanmitchell on 11/9/16.
 */

public class CalculatorFactory {

    public interface OnCalculationChangedListener{
        public void onSignChange(char sign);
    }

    private OnCalculationChangedListener listener;


    private static CalculatorFactory factory;
    private Context context;


    private double numberOne;
    private double numberTwo;
    private char sign;

    private CalculatorFactory(Context c){
        this.context = c;
        this.numberOne = this.numberTwo = 0.0;
        sign = '+';
    }

    public static CalculatorFactory get(Context c) {
        if(factory == null){
            factory = new CalculatorFactory(c);
        }

        return factory;
    }

    public void setListener(OnCalculationChangedListener listener){
        this.listener = listener;
    }

    private void notifySignChange(){
        if(listener!=null){
            listener.onSignChange(this.sign);
        }
    }

    public void setAdd() {
        this.sign = '+';
        notifySignChange();
    }

    public void setSubtract(){
        this.sign = '-';
        notifySignChange();
    }

    public void setMultiply(){
        this.sign = 'X';
        notifySignChange();
    }

    public void setNumberOne(double num){
        this.numberOne = num;
    }

    public void setNumberTwo(double num){
        this.numberTwo = num;
    }


    public double performOperation(){
        switch(this.sign){
            case '+':
                return numberOne + numberTwo;
            case '-':
                return numberOne-numberTwo;
            case 'X':
                return numberOne*numberTwo;
        }

        return 0;
    }


}
