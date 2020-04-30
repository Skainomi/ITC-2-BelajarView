package com.example.MAID;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

//import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.Intent;
//import android.graphics.Color;
//import android.media.Image;
import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
//import android.view.LayoutInflater;
//import android.view.KeyEvent;
import android.view.View;
//import android.view.inputmethod.EditorInfo;
import android.widget.Button;
//import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public short VisibleStatus = 1;
    public String varStringUsername;
    public String varStringPassword;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText Username = findViewById(R.id.EtUsername);
        final EditText Password = findViewById(R.id.EtPassword);

        final Button Login = findViewById(R.id.BtnLogin);
        final Button Reset = findViewById(R.id.BtnReset);


        final ImageButton Visible = findViewById(R.id.IbVisible);
        resetVar();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    varStringUsername = Username.getText().toString();
                    varStringPassword = Password.getText().toString();
                    if (varStringUsername.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Empty Username cannot be empty", Toast.LENGTH_LONG).show();
                    } else if (varStringPassword.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Password cannot be empty", Toast.LENGTH_LONG).show();
                    }
                    if(varStringUsername.equals("admin") && varStringPassword.equals("admin"))
                    {
                        Intent pindah = new Intent(getApplicationContext(),Inside1.class);
                        pindah.putExtra(Inside1.Extra_Username,varStringUsername);
                        startActivity(pindah);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Password atau Username Salah!", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetVar();
            }
        });

        Visible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (VisibleStatus == 0) {
                    Visible.setImageDrawable(getResources().getDrawable(R.drawable.ic_visibility_black_24dp));
                    Password.setTransformationMethod(new PasswordTransformationMethod());
                    VisibleStatus = 1;
                } else if (VisibleStatus == 1) {
                    Visible.setImageDrawable(getResources().getDrawable(R.drawable.ic_visibility_off_black_24dp));
                    Password.setTransformationMethod(null);
                    VisibleStatus = 0;
                }

            }
        });
    }

    public void resetVar() {
        EditText Username = findViewById(R.id.EtUsername);
        EditText Password = findViewById(R.id.EtPassword);

        varStringUsername = null;
        varStringPassword = null;
        VisibleStatus = 1;

        Username.setText(null);
        Password.setText(null);

    }


    @Override
    public void onBackPressed() {


    }

    public void onClick(View view) {
        AlertDialog.Builder notif = new AlertDialog.Builder(MainActivity.this, R.style.Transparent_Dialog);
        View showNotif = getLayoutInflater().inflate(R.layout.activity_forget_password, null);
        notif.setView(showNotif);
        final AlertDialog dialog = notif.create();
        final Button btnOkay = showNotif.findViewById(R.id.BtnContinue);
        dialog.show();
        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dialog.hide();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}
