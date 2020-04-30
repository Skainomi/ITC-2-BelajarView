package com.example.MAID;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Inside1 extends AppCompatActivity {

    public static String Extra_Username;

    Button back;
    TextView text;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside1);

        back = findViewById(R.id.BtnBack);
        text = findViewById(R.id.Tvtext);

        String nama = getIntent().getStringExtra(Extra_Username);
        text.setText("Hallo " + nama);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(back);
            }
        });

    }
}
