package com.example.amandaspolti.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class TelaSobre extends AppCompatActivity {

    private Button volta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_sobre);

        volta = (Button) findViewById(R.id.button_volta_list);
        volta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TelaSobre.this, ListView.class);
                startActivity(i);
            }
        });
    }
}
