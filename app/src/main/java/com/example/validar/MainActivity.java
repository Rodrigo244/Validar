package com.example.validar;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText name;
    private TextInputEditText curp;
    private TextInputEditText correo;
    

    private Button validar_campos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (TextInputEditText) findViewById(R.id.name);
        curp = (TextInputEditText) findViewById(R.id.CURP);
        correo = (TextInputEditText) findViewById(R.id.email);


        validar_campos = (Button) findViewById(R.id.btn_validar);
        validar_campos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String nombre = name.getText().toString();
                String CURP = curp.getText().toString();
                String email = correo.getText().toString();

                if (TextUtils.isEmpty(nombre)) {
                    name.setError("Campo vacio");

                } else if (!validarCURP(CURP)) {

                    curp.setError("CURP no valido");
                } else if (!validateEmail(email)) {

                    correo.setError("Email no valido");

                }else
                    Toast.makeText(getApplication(), "Todo bien", Toast.LENGTH_SHORT).show();
                }

        });
    }


    public static boolean validateEmail(String email) {

        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }


    private boolean validarCURP(String curp) {
        String regex =
                "[A-Z]{1}[AEIOU]{1}[A-Z]{2}[0-9]{2}" +
                        "(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1])" +
                        "[HM]{1}" +
                        "(AS|BC|BS|CC|CS|CH|CL|CM|DF|DG|GT|GR|HG|JC|MC|MN|MS|NT|NL|OC|PL|QT|QR|SP|SL|SR|TC|TS|TL|VZ|YN|ZS|NE)" +
                        "[B-DF-HJ-NP-TV-Z]{3}" +
                        "[0-9A-Z]{1}[0-9]{1}$";
        Pattern patron = Pattern.compile(regex);
        if (!patron.matcher(curp).matches()) {
            return false;
        } else {
            return true;
        }
    }


}