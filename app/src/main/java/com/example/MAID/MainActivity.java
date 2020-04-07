package com.example.MAID;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
//import android.content.Intent;
import android.content.Intent;
import android.graphics.Color;
//import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
//import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public short VisibleStatus = 1;
    public String varStringUsername;
    public String varStringPassword;
    public String varStringEmail;
    public short score = 0;

    public boolean uppercase = false;
    public boolean lowercase = false;
    public boolean length = false;
    public boolean number = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView passwordStatus = findViewById(R.id.TvPasswordStatus);

        final EditText Username = findViewById(R.id.EtUsername);
        final EditText Password = findViewById(R.id.EtPassword);
        final EditText Email = findViewById(R.id.EtEmail);

        final Button Login = findViewById(R.id.BtnLogin);
        final Button Reset = findViewById(R.id.BtnReset);

        final RadioButton premium = findViewById(R.id.RbPremium);
        final RadioButton reguler = findViewById(R.id.RbReguler);

        final ImageButton Visible = findViewById(R.id.IbVisible);
        resetVar();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try
                {
                    AlertDialog.Builder notif = new AlertDialog.Builder(MainActivity.this, R.style.Transparent_Dialog);
                    View showNotif = getLayoutInflater().inflate(R.layout.termofagreement,null);
                    notif.setView(showNotif);
                    final AlertDialog dialog = notif.create();

                    final CheckBox agree = showNotif.findViewById(R.id.CbAgree);
                    final Button next = showNotif.findViewById(R.id.BtnContinue);
                    final TextView term = showNotif.findViewById(R.id.TvTerm);

                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(agree.isChecked())
                            {
                                try
                                {
                                    dialog.hide();
                                    Intent succes = new Intent(getApplicationContext(),Inside1.class);
                                    startActivity(succes);
                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                term.setTextColor(Color.parseColor("#ff0000"));
                            }

                        }
                    });
                    varStringUsername = Username.getText().toString();
                    varStringPassword = Password.getText().toString();
                    varStringEmail = Email.getText().toString();

                    if(varStringUsername.isEmpty())
                    {
                        Toast.makeText(getApplicationContext(),"Empty Username cannot be empty",Toast.LENGTH_LONG).show();
                    }
                    else if(varStringPassword.isEmpty())
                    {
                        Toast.makeText(getApplicationContext(),"Password cannot be empty",Toast.LENGTH_LONG).show();
                    }
                    else if(varStringEmail.isEmpty())
                    {
                        Toast.makeText(getApplicationContext(),"Email cannot be empty",Toast.LENGTH_LONG).show();
                    }
                    else if(!premium.isChecked() && !reguler.isChecked())
                    {
                        Toast.makeText(getApplicationContext(),"Please select one package",Toast.LENGTH_LONG).show();
                    }
                    else if(!varStringEmail.contains("@"))
                    {
                        Toast.makeText(getApplicationContext(),"Invalid Email",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        dialog.show();
                    }

                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                }

            }
        });

        Password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String passwordString = Password.getText().toString();
                uppercase = false;
                lowercase = false;
                length = false;
                number = false;
                score = 0;
                for(short j = 0; j < Password.length(); j++)
                {
                    char passwordCheck = passwordString.charAt(j);
                    if(!number && Character.isDigit(passwordCheck))
                    {
                        number = true;
                        score++;
                    }
                    if(!uppercase && Character.isUpperCase(passwordCheck))
                    {
                        uppercase = true;
                        score++;
                    }
                    if(!lowercase && Character.isLowerCase(passwordCheck))
                    {
                        lowercase = true;
                        score++;
                    }
                    if(!length && passwordString.length() > 12)
                    {
                        length = true;
                        score++;
                    }
                }
                if(passwordString.isEmpty())
                {
                    passwordStatus.setText(R.string.none);
                    passwordStatus.setTextColor(Color.parseColor("#333333"));
                }
                else if(passwordString.equals(getString(R.string.goku)) || passwordString.equals("Jotaro"))
                {
                    passwordStatus.setText("Too Strong, Help!");
                    passwordStatus.setTextColor(Color.parseColor("#ffffff"));
                }
                else if(score == 1)
                {
                    passwordStatus.setText(R.string.weak);
                    passwordStatus.setTextColor(Color.parseColor("#00cc44"));
                }
                else if(score == 2)
                {
                    passwordStatus.setText(R.string.medium);
                    passwordStatus.setTextColor(Color.parseColor("#cc6600"));
                }
                else if(score == 3)
                {
                    passwordStatus.setText(R.string.strong);
                    passwordStatus.setTextColor(Color.parseColor("#ff0000"));
                }
                else if(score == 4)
                {
                    passwordStatus.setText("Very Strong");
                    passwordStatus.setTextColor(Color.parseColor("#800000"));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        Reset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                resetVar();
            }
        });

        Visible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(VisibleStatus == 0)
                {
                    Visible.setImageDrawable(getResources().getDrawable(R.drawable.ic_visibility_black_24dp));
                    Password.setTransformationMethod(new PasswordTransformationMethod());
                    VisibleStatus = 1;
                }
                else if(VisibleStatus == 1)
                {
                    Visible.setImageDrawable(getResources().getDrawable(R.drawable.ic_visibility_off_black_24dp));
                    Password.setTransformationMethod(null);
                    VisibleStatus = 0;
                }

            }
        });
    }

    public void resetVar()
    {
        EditText Username = findViewById(R.id.EtUsername);
        EditText Password = findViewById(R.id.EtPassword);
        EditText Email = findViewById(R.id.EtEmail);
        RadioButton premium = findViewById(R.id.RbPremium);
        RadioButton reguler = findViewById(R.id.RbReguler);

        varStringUsername = null;
        varStringPassword = null;
        varStringEmail = null;
        VisibleStatus = 1;
        premium.setChecked(false);
        reguler.setChecked(false);

        Username.setText(null);
        Password.setText(null);
        Email.setText(null);
    }

    @Override
    public void onBackPressed()
    {


    }
}
