package edu.ub.pis2016.palmecro.appdivises;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText eurosEt;
    private EditText dollarsEt;
    private EditText changeEd;
    private EditText taxEt;

    private Button eurToDolButton;
    private Button dolToEurButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eurosEt = (EditText)findViewById(R.id.euros);
        dollarsEt = (EditText)findViewById(R.id.dollars);
        changeEd = (EditText)findViewById(R.id.change);
        taxEt = (EditText)findViewById(R.id.tax);

        eurToDolButton = (Button)findViewById(R.id.eurToDol);
        dolToEurButton = (Button)findViewById(R.id.dolToEur);

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
        eurToDolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick(eurosEt, dollarsEt, taxEt, changeEd);
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

        dolToEurButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick(dollarsEt, eurosEt, taxEt, changeEd);
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

        changeEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textChange(s, changeEd);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

/**
 * Encapsulation of common methods
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
