package assingment.pratica2cont3;

import android.app.ListActivity;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends AppCompatActivity {
    //EXE4.2
    ListView lst;
    //EXE5.1 ProgressBar
//    static final int PROGRESS = 0x1;
    ProgressBar pb;
    int pbstatus = 0;
    Handler hd = new Handler();

    //EXE5.1 cronometro
    Chronometer ch;
    Button start, pause, reset;
    Boolean click;

    //EXE5.1 calendario
    CalendarView cl;
    Button clBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //EXE4.2
        lst = (ListView) findViewById(R.id.listView);
//
//        ArrayAdapter<String> array = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_multiple_choice);
//
//        lst.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        /*ADAPTEI*/
//        if (lst.getChoiceMode() == ListView.CHOICE_MODE_MULTIPLE) {
//            l_itemClick(v, pos); //cade o resto
//        }


//        Button btn = (Button) findViewById(R.id.button);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SparseBooleanArray checked = lst.getCheckedItemPositions();
//                ArrayAdapter<String> ad = (ArrayAdapter<String>) lst.getAdapter();
//                String txt = "Ops selecionadas:\n ";
//                for (int i = 0; i < checked.size(); i++) {
//                    if (checked.valueAt(i)) {
//                        int pos = checked.keyAt(i);
//                        txt = txt + ad.getItem(pos) + "\n";
//                    }
//                }
//                Toast.makeText(Main.this, txt, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        ArrayAdapter<String> array = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_single_choice);
//
//        lst.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

//        ArrayAdapter<String> array = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_checked);

        ArrayAdapter<String> array = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item);

        lst.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lst.setAdapter(array);

        array.add("Op1");
        array.add("Op2");
        array.add("Op3");
        array.add("Op4");
        array.add("Op5");
        array.add("Op6");
//NAO ESTA FUNCIONANDO, tentei de tudo quanto é forma.
        lst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Main.this, lst.getSelectedItem().toString() ,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(Main.this, "Selecione uma opção!",
                        Toast.LENGTH_SHORT).show();
            }
        });


        //EXE5.1 progress bar
        pb = (ProgressBar) findViewById(R.id.progressBar);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (pbstatus <= 100) {
                        Thread.sleep(1000);

                        hd.post(new Runnable() {
                            @Override
                            public void run() {
                                pb.setProgress(pbstatus++);
                            }
                        });

                    }
                } catch (Exception e) {

                }
            }
        }).start();

        //EXE5.1 cronometro
        click = true;
        start = (Button) findViewById(R.id.btstart);
        pause = (Button) findViewById(R.id.btpause);
        reset = (Button) findViewById(R.id.btreset);
        ch = (Chronometer) findViewById(R.id.chronometer);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click) {
                    ch.setBase(SystemClock.elapsedRealtime());
                    ch.start();
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click = false;
                ch.stop();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click = true;
                ch.stop();
                ch.setText("Total (00:00)");
            }
        });

        //EXE5.1 calendario
//        cl = (CalendarView) findViewById(R.id.calendarView);
//        clBtn = (Button) findViewById(R.id.okButton);
//
//        clBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
//                Date dtd = new Date();
//                dtd.setTime(cl.getDate());
//
//                String date = dt.format(dtd);
//
//                Toast.makeText(Main.this,
//                        "Data: " + date, Toast.LENGTH_SHORT).show();
//
//            }
//        });


    }


//    //EXE4.2
//    private void l_itemClick(View v, int pos) {
//        String word = ((lst.isItemChecked(pos)) ? "selecionada" : "desslecionada");
//        Toast.makeText(Main.this, "Opçao " + word + ": " + lst.getItemAtPosition(pos),
//                Toast.LENGTH_SHORT).show();
//    }


}
