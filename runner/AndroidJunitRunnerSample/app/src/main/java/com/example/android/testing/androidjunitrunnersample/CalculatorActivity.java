/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.testing.androidjunitrunnersample;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * {@link android.app.Activity} which contains a simple calculator. Numbers can be entered in the
 * two {@link EditText} fields and result can be obtained by pressing one of the
 * operation {@link Button}s at the bottom.
 */
public class CalculatorActivity extends Activity {

    private static final String TAG = "CalculatorActivity";

    private Calculator mCalculator;

    private EditText mOperandOneEditText;
    private EditText mOperandTwoEditText;

    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        mCalculator = new Calculator();
        mResultTextView = (TextView) findViewById(R.id.operation_result_text_view);
        mOperandOneEditText = (EditText) findViewById(R.id.operand_one_edit_text);
        mOperandTwoEditText = (EditText) findViewById(R.id.operand_two_edit_text);
    }

    /**
     * OnClick method that is called when the add {@link Button} is pressed.
     */
    public void onAdd(View view) {
        compute(Calculator.Operator.ADD);
    }

    /**
     * OnClick method that is called when the substract {@link Button} is pressed.
     */
    public void onSub(View view) {
        compute(Calculator.Operator.SUB);
    }

    /**
     * OnClick method that is called when the divide {@link Button} is pressed.
     */
    public void onDiv(View view) {
        try {
            compute(Calculator.Operator.DIV);
        } catch (IllegalArgumentException iae) {
            Log.e(TAG, "IllegalArgumentException", iae);
            mResultTextView.setText(getString(R.string.computationError));
        }
    }

    /**
     * OnClick method that is called when the multiply {@link Button} is pressed.
     */
    public void onMul(View view) {
        compute(Calculator.Operator.MUL);
    }

    private void compute(Calculator.Operator operator) {
        double operandOne;
        double operandTwo;
        try {
            operandOne = getOperand(mOperandOneEditText);
            operandTwo = getOperand(mOperandTwoEditText);
        } catch (NumberFormatException nfe) {
            Log.e(TAG, "NumberFormatException", nfe);
            mResultTextView.setText(getString(R.string.computationError));
            return;
        }

        String result;
        switch (operator) {
            case ADD:
                result = String.valueOf(mCalculator.add(operandOne, operandTwo));
                break;
            case SUB:
                result = String.valueOf(mCalculator.sub(operandOne, operandTwo));
                break;
            case DIV:
                result = String.valueOf(mCalculator.div(operandOne, operandTwo));
                break;
            case MUL:
                result = String.valueOf(mCalculator.mul(operandOne, operandTwo));
                break;
            default:
                result = getString(R.string.computationError);
                break;
        }
        mResultTextView.setText(result);
    }

    /**
     * @return the operand value which was entered in an {@link EditText} as a double
     */
    private static Double getOperand(EditText operandEditText) {
        String operandText = getOperandText(operandEditText);
        return Double.valueOf(operandText);
    }

    /**
     * @return the operand text which was entered in an {@link EditText}.
     */
    private static String getOperandText(EditText operandEditText) {
        String operandText = operandEditText.getText().toString();
        if (TextUtils.isEmpty(operandText)) {
            throw new NumberFormatException("operand cannot be empty!");
        }
        return operandText;
    }
}
