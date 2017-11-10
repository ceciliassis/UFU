package com.example.amandaspolti.todoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

public class TelaConfig extends AppCompatActivity {

    private Button volta;
    private Button salva;
    private ToggleButton vibrar;
    private ToggleButton tocar;

    public static final String PREFS_NAME = "Preferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_config);

              vibrar = (ToggleButton) findViewById(R.id.vibrar);
        tocar = (ToggleButton)findViewById(R.id.tocar);

        //Restaura as preferencias gravadas
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if(settings.getString("Vibra","").equals("true")){
            vibrar.setChecked(true);
        }
        if(settings.getString("Toca", "").equals("true")){
            tocar.setChecked(true);
        }

        volta = (Button) findViewById(R.id.volta_conf);
        volta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(UsuarioDAO.getInstance().getUsername());
                System.out.println(UsuarioDAO.getInstance().getSenha());
                Intent i = new Intent(TelaConfig.this, ListView.class);
                startActivity(i);
            }
        });


        salva = (Button) findViewById(R.id.salva_conf);
        salva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(TelaConfig.this, ListView.class);
//                startActivity(i);
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("Vibra", String.valueOf(vibrar.isChecked()));
                editor.putString("Toca", String.valueOf(tocar.isChecked()));

                //Confirma a gravação dos dados
                editor.commit();

                Intent i = new Intent(TelaConfig.this, ListView.class);
                startActivity(i);
            }
        });
    }

    //Evento click do botão Fechar prorama
    public void btnFechar_Click(View v){
        finish(); // fecha aplicativo
    }
}
