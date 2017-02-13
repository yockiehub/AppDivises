package edu.ub.pis2016.palmecro.appdivises;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText amountText;
    private EditText changeText;
    private EditText taxText;

    private Button eurToDolButton;
    private Button dolToEurButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountText = (EditText)findViewById(R.id.amount);
        changeText = (EditText)findViewById(R.id.change);
        taxText = (EditText)findViewById(R.id.tax);

        eurToDolButton = (Button)findViewById(R.id.eurToDol);
        eurToDolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double val;
                String str;
                if (!amountText.getText().toString().isEmpty() && (!changeText.getText().toString().isEmpty() &&
                        !changeText.getText().toString().equals('0'))){
                    val = Double.parseDouble(amountText.getText().toString());
                    val = val * Double.parseDouble(changeText.getText().toString());
                    if (!taxText.getText().toString().isEmpty()){
                        double taxRate = Double.parseDouble(taxText.getText().toString());
                        val = val - (taxRate/100 * val);
                    }
                    str = String.valueOf(val);
                    amountText.setText(str);
                }else if (amountText.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter a correct amount", Toast.LENGTH_SHORT).show();

                }else if (changeText.getText().toString().isEmpty() || changeText.getText().toString().equals('0')){
                    Toast.makeText(MainActivity.this, "Enter a correct change rate", Toast.LENGTH_SHORT).show();
                }


                /*if (){

                }*/
            }
        });


    }
}
