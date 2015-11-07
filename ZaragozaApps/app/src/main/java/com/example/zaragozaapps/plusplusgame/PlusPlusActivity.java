package com.example.zaragozaapps.plusplusgame;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zaragozaapps.zaragozaapps.MainActivity;
import com.example.zaragozaapps.zaragozaapps.R;

import java.util.Random;

/**
 * Created by sergiolazaromagdalena on 6/11/15.
 */
public class PlusPlusActivity extends AppCompatActivity {

    private static final String[] operations = {"+","-","*","/",};
    private static final int maxNumberPlusMinus = 30;
    private static final int maxNumberPlusMinus2 = 10;
    private static final int maxNumberMultiDiv = 10;
    private static final int maxNumberMultiDiv2 = 10;
    private static final int numOperations = 4;
    private String currentOperation = "";
    private int number1;
    private int number2;
    private int operationNumber;
    private int correctAnswers;

    private TextView operator, firstNumber, secondNumber;
    private Button confirmButton;
    private EditText insertedResult;
    private String id;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plus_plus);
        context = getApplicationContext();

        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");

        //Setting up the GUI
        operator = (TextView) findViewById(R.id.operation);
        firstNumber = (TextView) findViewById(R.id.firstNumber);
        secondNumber = (TextView) findViewById(R.id.secondNumber);
        confirmButton = (Button) findViewById(R.id.confirmButton);
        insertedResult = (EditText) findViewById(R.id.insertedResult);
        insertedResult.setKeyListener(new DigitsKeyListener());

        //Setting up variables
        operationNumber = 0;
        correctAnswers = 0;

        //Setting up listeners
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if result is a number
                if(insertedResult.getText().toString().length() == 0) {
                    Toast toast = Toast.makeText(v.getContext(), "El resultado no puede ser vacio", Toast.LENGTH_LONG);
                    toast.show();
                    insertedResult.setText("");
                }
                else{
                    //Check result
                    if (checkResult(Integer.parseInt(insertedResult.getText().toString()))) {
                        correctAnswers++;
                    }
                    //Clear fields
                    firstNumber.setText("");
                    secondNumber.setText("");
                    operator.setText("");
                    insertedResult.setText("");

                    if (operationNumber < numOperations - 1 ) {
                        operationNumber++;
                        newOperation();
                    } else {
                        //Show Dialog
                        final Dialog dialog = new Dialog(v.getContext());
                        dialog.setContentView(R.layout.plus_plus_result);

                        // set the custom dialog components - text, image and button
                        TextView playerResult = (TextView) dialog.findViewById(R.id.playerResult);
                        TextView playerColour = (TextView) dialog.findViewById(R.id.colourLabel);
                        if(id.equals("0")){
                            playerColour.setText("Rojo");
                            playerColour.setTextColor(Color.RED);
                        }
                        else{
                            playerColour.setText("Azul");
                            playerColour.setTextColor(Color.BLUE);
                        }
                        playerResult.setText(correctAnswers + "");
                        new SendPlusPlusResult(v.getContext()).execute(id,correctAnswers+"");

                        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButton);
                        // if button is clicked, close the custom dialog
                        dialogButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(context, MainActivity.class);
                                startActivity(i);
                            }
                        });
                        dialog.show();
                    }
                }
            }
        });
        newOperation();
    }

    /**
     * This method create a new operation for the user. It checks the values to make real math operations
     * adapted to kids.
     */
    private void newOperation(){
        Random r = new Random();
        currentOperation = operations[r.nextInt(4)];
        if(currentOperation.equals("/")){
            //Selecting our operator
            number1 = r.nextInt(maxNumberMultiDiv);
            number2 = r.nextInt(maxNumberMultiDiv);

            //Now, selecting an easy division
            while(number1 == 0 || number2 == 0 || (number1 % number2 != 0) || number1 < number2){
                number1 = r.nextInt(maxNumberMultiDiv);
                number2 = r.nextInt(maxNumberMultiDiv2);
            }

            //Changing operation
            firstNumber.setText(number1 + "");
            secondNumber.setText(number2 + "");
            operator.setText(currentOperation);
        }
        else if(currentOperation.equals("*")){
            //Selecting our operator
            number1 = r.nextInt(maxNumberMultiDiv);
            number2 = r.nextInt(maxNumberMultiDiv2);

            //Changing operation
            firstNumber.setText(number1 + "");
            secondNumber.setText(number2 + "");
            operator.setText(currentOperation);
        }
        else{
            //Selecting our operator
            number1 = r.nextInt(maxNumberPlusMinus);
            number2 = r.nextInt(maxNumberPlusMinus2);
            if(currentOperation.equals("-")){       //Checking minus operation result is higher than 0
                while((number1 < number2)){
                    number1 = r.nextInt(maxNumberPlusMinus);
                    number2 = r.nextInt(maxNumberPlusMinus2);
                }
            }
            //Changing operation
            firstNumber.setText(number1 + "");
            secondNumber.setText(number2 + "");
            operator.setText(currentOperation);
        }
    }

    /**
     * @param result is the result inserted by the player and must be a number
     * @return true if and only if the inserted result is equal to number1 (+,-,*,/) number2
     */
    private boolean checkResult(int result){
        boolean correct = false;
        if(currentOperation.equals("+")){       //Addition
            if(result == (number1 + number2)){
                correct = true;
            }
        }
        else if(currentOperation.equals("-")){  //Subtraction
            if(result == (number1 - number2)){
                correct = true;
            }
        }
        else if(currentOperation.equals("*")){  //Multiplication
            if(result == (number1 * number2)){
                correct = true;
            }
        }
        else{   //Division
            if(result == (number1 / number2)){
                correct = true;
            }
        }
        return correct;
    }

}
