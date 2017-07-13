package com.example.cecilia.pratica2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Exercicio1 extends AppCompatActivity {
//    Button btn;   //exe2.1
//    EditText et;   //exe2.1, 2.2
//    ToggleButton tg;    //exe2..2
//    RadioButton rb1, rb2, rb3;    //exe2.3
//    CheckBox cb1, cb2;    //exe2.4

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_exercicio1); //exe2.1, 2.2
//        setContentView(R.layout.exe23);
        setContentView(R.layout.exe24);

        //EXE2.1
//        btn = (Button) findViewById(R.id.btn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                et = (EditText) findViewById(R.id.et);
//                String text = et.getText().toString();
//                text = text.toUpperCase();
//                et.setText(text);
//            }
//        });

        //EXE2.2
//        tg = (ToggleButton) findViewById(R.id.tg);
//
//        tg.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                et = (EditText) findViewById(R.id.et2);
//                if(tg.isChecked()){
//                    String txt = "Clicou hein!";
//                    et.setText(txt);
//                    System.out.println("Pressionado");
//                }
//                else{
//                    String txt = "Nao Clicou ainda?";
//                    et.setText(txt);
//                    System.out.println("Liberado");
//                }
//            }
//        });

        //EXE2.3
//        rb1 = (RadioButton) findViewById(R.id.rdGroup01);
//        rb2 = (RadioButton) findViewById(R.id.rdGroup02);
//        rb3 = (RadioButton) findViewById(R.id.rdGroup03);
//
//        rb1.setOnClickListener(rdGroup_Click);
//        rb2.setOnClickListener(rdGroup_Click);
//        rb3.setOnClickListener(rdGroup_Click);
//
//        rb1.setChecked(true);

        //EXE2.4
//        cb1 = (CheckBox) findViewById(R.id.gato);
//        cb2 = (CheckBox) findViewById(R.id.cachorro);
//
//        cb1.setOnClickListener(cbGroup_Click);
//        cb2.setOnClickListener(cbGroup_Click);

    }
    //EXE1
//    protected void onStart(){
//        super.onStart();
//        System.out.println("Start main");
//    }
//
//    protected void onRestart(){
//        super.onRestart();
//        System.out.println("onRestart main");
//    }
//
//    protected void onResume(){
//        super.onResume();
//        System.out.println("onResume main");
//    }

//    protected void onPause(){
//
//        System.out.println("onPause main");
//        super.onPause();
//    }
//
//    protected void onStop(){
//        System.out.println("onStop main");
//        super.onStop();
//    }
//
//    protected void onDestroy(){
//        System.out.println("onDestroy main");
//        super.onDestroy();
//    }
    //EXE2.3 cont
//    private RadioButton.OnClickListener rdGroup_Click = new RadioButton.OnClickListener(){
//        @Override
//            public void onClick(View view) {
//                String str = "Op1: " + rb1.isChecked()+"\n Op2: "
//                        +rb2.isChecked()+" \n Op3: "+ rb3.isChecked();
//                Toast.makeText(Exercicio1.this, str, Toast.LENGTH_SHORT).show();
//            }
//    };
    //EXE2.4 cont
//    private CheckBox.OnClickListener cbGroup_Click = new CheckBox.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            String str = "Tem  gatineos: " + cb1.isChecked()
//                    + "\n Tem catioros: " + cb2.isChecked();
//            Toast.makeText(Exercicio1.this, str, Toast.LENGTH_SHORT).show();
//        }
//    };

}
