package edu.sjsu.android.mortgagecalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    Button button;
    EditText editText;
    RadioGroup radioGroup;
    CheckBox checkBox;
    TextView textView;
    RadioButton radiobutton1, radiobutton2, radiobutton3;
    int years;

  double annualInterest;
  double monthlyInterest;
  double monthlyTaxInsurance;
  float monthlyPayment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);
        radiobutton1=(RadioButton) findViewById(R.id.radioButton4);
        radiobutton2=(RadioButton) findViewById(R.id.radioButton5);
        radiobutton3=(RadioButton) findViewById(R.id.radioButton7);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        textView = (TextView) findViewById(R.id.textView2);





        // initiate  views
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        // get progress value from the Seek bar
        double seekBarValue = getConvertedValue(seekBar.getProgress());

        annualInterest = seekBarValue;
        monthlyInterest = annualInterest / 1200;



        // perform seek bar change listener event used for getting the progress value
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {



                Toast.makeText(seekBar.getContext(), "Interest Rate: " + getConvertedValue(progress),Toast.LENGTH_SHORT).show();


                //this is the value of the annual interest
                annualInterest = getConvertedValue(progress);
                //this is the value of the monthly interest
                monthlyInterest = annualInterest / 1200;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });



                //set the value for the button
                button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){

                        //check if the edit text is empty
                if(editText.getText().length()==0){
                    Toast.makeText(getApplicationContext(), "Please Enter Principal", Toast.LENGTH_SHORT).show();
                    return;
                }

                if ((!radiobutton1.isChecked()) && (!radiobutton2.isChecked()) && (!radiobutton3.isChecked())){
                    Toast.makeText(getApplicationContext(), "Please Select loan years", Toast.LENGTH_SHORT).show();
                    return;
                }

                float principal = Float.parseFloat(editText.getText().toString());


                       //set the values for radio buttons
                        if(radiobutton1.isChecked()){
                            years = 15;
                        }
                        else if (radiobutton2.isChecked()){
                            years = 20;
                        }
                        else if (radiobutton3.isChecked()){
                            years = 30;
                        }


                        //get the value if the check box is checked
                        if(checkBox.isChecked()){
                            monthlyTaxInsurance = 0.001 * principal;
                        } else {
                            monthlyTaxInsurance = 0.0;
                        }

                        //get the value for the monthly payment
                        if ( monthlyInterest==0.0){
                            monthlyPayment = (float) ((principal/(years*12)) + monthlyTaxInsurance);
                        }
                        else {
                           monthlyPayment = (float) ((principal * ( monthlyInterest/
                                   (1-(Math.pow(1+monthlyInterest, (-1*years*12))))))+monthlyTaxInsurance);
                        }



                        //set the value for the textview
                        textView.setText(String.valueOf(monthlyPayment));



            }
        });
    }






    //make a method to convert a int value to a float
    public double getConvertedValue(int intVal){
        double floatVal;
        floatVal = 0.1f*intVal;
        return floatVal;
    }




}