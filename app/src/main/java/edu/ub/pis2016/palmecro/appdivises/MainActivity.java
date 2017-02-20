package edu.ub.pis2016.palmecro.appdivises;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private EditText eurosEt;
    private EditText dollarsEt;
    private EditText changeEt;
    private EditText taxEt;

    private Button eurToDolButton;
    private Button dolToEurButton;
    private Button convertButton;

    private ToggleButton toggleConv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eurosEt = (EditText)findViewById(R.id.euros);
        dollarsEt = (EditText)findViewById(R.id.dollars);
        changeEt = (EditText)findViewById(R.id.change);
        taxEt = (EditText)findViewById(R.id.tax);

        eurToDolButton = (Button)findViewById(R.id.eurToDol);
        dolToEurButton = (Button)findViewById(R.id.dolToEur);
        convertButton = (Button)findViewById(R.id.convert);

        /**
         * Based on the orientation of the device, the app will work one way or another.
         * In portrait, a two-buttons layout will be loaded allowing the user to perform
         * the exchange both ways.
         * In landscape, the layout shows a toggle button to perform the currency exchange
         * in only the direction selected.
         */
        toggleConv = (ToggleButton)findViewById(R.id.toggleConv);

        if (toggleConv != null){

            eurosEt.setEnabled(true);
            dollarsEt.setEnabled(false);
            eurosEt.requestFocus();

            toggleConv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        eurosEt.setEnabled(false);
                        dollarsEt.setEnabled(true);
                        dollarsEt.requestFocus();
                    }else{
                        eurosEt.setEnabled(true);
                        dollarsEt.setEnabled(false);
                        eurosEt.requestFocus();
                    }
                }
            });

            convertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (toggleConv.isChecked()){
                        buttonClick(dollarsEt, eurosEt, taxEt, changeEt);
                    }else{
                        buttonClick(eurosEt, dollarsEt, taxEt, changeEt);
                    }
                }
            });


        }else{
            eurToDolButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonClick(eurosEt, dollarsEt, taxEt, changeEt);
                }
            });

            dolToEurButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonClick(dollarsEt, eurosEt, taxEt, changeEt);
                }
            });
        }

        eurosEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textChange(s, eurosEt);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        dollarsEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textChange(s, dollarsEt);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        taxEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textChange(s, taxEt);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        changeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textChange(s, changeEt);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

/**
 * Encapsulation of common methods
 */
    /**
     * Method to make sure the input within the edit texts is correct
     *
     * @param s Sequence of characters being written in the edti text
     * @param eT The edit text selected
     */
    private void textChange(CharSequence s, EditText eT){
        if (s.toString().equals(".")){
            eT.setText("0.");
            eT.setSelection(eT.getText().length());
        }

        if (s.toString().equals("00")){
            eT.setText("0");
            eT.setSelection(eT.getText().length());
        }
    }

    /**
     * Method to calculate the final conversion value after applying the different rates and taxes
     * (optional).
     *
     * @param currencyIn Edit text being edited to convert from
     * @param currencyOut Edit text where the output of the conversion will be added to
     * @param tax Tax rate
     * @param change Change rate
     */
    private void buttonClick(EditText currencyIn, EditText currencyOut, EditText tax, EditText change){
        double val;
        String str;
        if (!currencyIn.getText().toString().isEmpty() && (!change.getText().toString().isEmpty())){
            val = Double.parseDouble(currencyIn.getText().toString());
            val = val * Double.parseDouble(change.getText().toString());
            if (!tax.getText().toString().isEmpty()){
                double taxRate = Double.parseDouble(tax.getText().toString());
                val = val - (taxRate/100 * val);
        }
            str = String.format("%.2f", val);
            currencyOut.setText(str);
        }else if (currencyIn.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this, "Enter a correct amount", Toast.LENGTH_SHORT).show();

        }else if (change.getText().toString().isEmpty() || change.getText().toString().equals("0")){
            Toast.makeText(MainActivity.this, "Enter a correct change rate", Toast.LENGTH_SHORT).show();
        }
    }
}
