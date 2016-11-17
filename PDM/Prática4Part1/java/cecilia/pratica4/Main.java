package cecilia.pratica4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends AppCompatActivity {
    private AlertDialog alert;
    EditText edUser;
    String PREFS_NAME = "settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edUser = (EditText) findViewById(R.id.editText);

        //EXE1
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure want to exit?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do something
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //do something
            }
        });

        alert = builder.create();


////////////////////////////////////////////////////////////////////////////////
        final CharSequence[] items = {"Red", "Green", "Blue"};

        final AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        builder2.setTitle("Pick a color");
        builder2.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Main.this, "Clicou em: " + items[which].toString(), Toast.LENGTH_SHORT).show();
            }
        });

        Button conf = (Button) findViewById(R.id.button), select = (Button) findViewById(R.id.button2);
        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.show();
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder2.show();
            }
        });

        ////////////////////////////////////////// exempl 2
        exemplo_simples();

    }

    private void exemplo_simples() {
        AlertDialog.Builder bu = new AlertDialog.Builder(this);
        bu.setTitle("SOFTMAX");
        bu.setMessage("Qualifique esse software");
        bu.setPositiveButton("Bom", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Main.this, "Ficamos felizes com o apreço!", Toast.LENGTH_SHORT).show();
            }
        });
        bu.setNegativeButton("Ruim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Main.this, "Mande um email para nos: ufu@ufu.br",
                        Toast.LENGTH_SHORT).show();
            }
        });

        alert = bu.create();
        alert.show();


    }

    //EXE2
    public boolean onCreateOptionsMenu(Menu menu) { //MENU NA ACTION BAR
        super.onCreateOptionsMenu(menu);

//        MenuItem item = menu.add(0, NOVO, 0, "Novo");
//        item = menu.add(0, ABRIR, 0, "ABRIR");
//        item = menu.add(0, SALVAR, 0, "SALVAR");
//
//        SubMenu submenu = menu.addSubMenu("Outros");
//        item = submenu.add(0, PESQUISAR, 0, "PESQUISAR");
//        item = submenu.add(0, LIMPAR, 0, "LIMPAR");
//        item = submenu.add(0, SAIR, 0, "SAIR");

        MenuItem item = menu.add("NOVO");
        item = menu.add("ABRIR");
        item = menu.add("SALVAR");

        SubMenu submenu = menu.addSubMenu("OUTROS");
        item = submenu.add("PESQUISAR");
        item = submenu.add("LIMPAR");
        item = submenu.add("SAIR");


        return true;
    }


    protected void onStop() {
        super.onStop();
        CheckBox ckSalvar = (CheckBox) findViewById(R.id.checkBox);
        if (ckSalvar.isChecked()) {
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor ed = settings.edit();
            ed.putString("PrefUsuario", edUser.getText().toString());
            ed.commit();
        }
    }

    public void readSharedPreferences(View v) {
        SharedPreferences sets = getSharedPreferences(PREFS_NAME, 0);
        String nomeUser = sets.getString("PrefUsuario", null);
        nomeUser = "Seu nome é : " + nomeUser;
        Toast.makeText(Main.this, nomeUser, Toast.LENGTH_SHORT).show();
    }

    public void irPgCadastro(View v){
        Intent in = new Intent(this, Cadatro.class);
        startActivity(in);
    }

}
