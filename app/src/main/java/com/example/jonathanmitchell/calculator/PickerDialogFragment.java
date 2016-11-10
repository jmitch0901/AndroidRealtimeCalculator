package com.example.jonathanmitchell.calculator;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by jonathanmitchell on 11/9/16.
 */

public class PickerDialogFragment extends AppCompatDialogFragment implements View.OnClickListener {


    public static PickerDialogFragment newInstance() {
        PickerDialogFragment f = new PickerDialogFragment();
        return f;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_fragment_picker,container);



        Button[] buttons = new Button[3];

        buttons[0] = (Button)v.findViewById(R.id.addButton);
        buttons[1] = (Button)v.findViewById(R.id.subtractButton);
        buttons[2] = (Button)v.findViewById(R.id.multiplyButton);

        for(int i = 0; i < 3; i++){
            buttons[i].setOnClickListener(this);
        }


        return v;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        CalculatorFactory calculatorFactory = CalculatorFactory.get(getActivity());
        switch(id){
            case R.id.addButton: {
                calculatorFactory.setAdd();
                break;
            }
            case R.id.subtractButton: {
                calculatorFactory.setSubtract();
                break;
            }
            case R.id.multiplyButton: {
                calculatorFactory.setMultiply();
                break;
            }
        }

        calculatorFactory.performOperation();
        this.dismiss();
    }
}
