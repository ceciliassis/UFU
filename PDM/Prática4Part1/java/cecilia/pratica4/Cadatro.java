package cecilia.pratica4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class Cadatro extends AppCompatActivity {
    String fileName = "cadastroFile";
    String PREFS_NAME = "settings";
    FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadatro);

        Button cadatrar = (Button) findViewById(R.id.button5);
        final EditText nome = (EditText) findViewById(R.id.editText2);
        final EditText email = (EditText) findViewById(R.id.editText3);
        final CheckBox ch = (CheckBox) findViewById(R.id.checkBox2);

        cadatrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeS = nome.getText().toString();
                String emailS = email.getText().toString();

                if(ch.isChecked()){
                    SharedPreferences sp = getSharedPreferences(PREFS_NAME,0);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putBoolean("stayLogged", true);
                    editor.commit();
                }
                try {
                    fos = openFileOutput(fileName, Context.MODE_PRIVATE);
                    fos.write(nomeS.getBytes());
                    fos.write(emailS.getBytes());
                    fos.close();
                    Toast.makeText(Cadatro.this, "Cadastrado com sucesso!",
                            Toast.LENGTH_SHORT).show();
                    nome.setText("");
                    email.setText("");
                    ch.setChecked(false);

                    Intent in = new Intent(Cadatro.this, External.class);
                    startActivity(in);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            }
        });

    }


}
