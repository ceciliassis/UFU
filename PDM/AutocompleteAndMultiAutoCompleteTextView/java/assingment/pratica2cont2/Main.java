package assingment.pratica2cont2;

import android.app.ListActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //EXE 3.2 timepicker
//        Button btn = (Button) findViewById(R.id.button);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                TimePicker tp = (TimePicker) findViewById(R.id.timePicker);
//                String str = "Hora: "+tp.getCurrentHour()+":"+tp.getCurrentMinute();
//                Toast.makeText(Main.this,str,Toast.LENGTH_SHORT).show();
//            }
//        });

        //EXE 3.2 Datepicker

//        Button btn = (Button) findViewById(R.id.button);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatePicker tp = (DatePicker) findViewById(R.id.datePicker);
//                String str = "Data: "+tp.getDayOfMonth()+"/"+tp.getMonth()+1
//                        +"/"+tp.getYear();
//                Toast.makeText(Main.this,str,Toast.LENGTH_SHORT).show();
//            }
//        });

        //EXE 3.3
//        final String OPS[] = new String[]{"Quejio", "Presunto", "Mussarela"
//                , "Cadeira", "Braço", "Cor", "Encosto", "Mola", "Estofada",
//                "Azul", "Vermelha", "Rodinha"};
//
//        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,
//                OPS);
//
//        AutoCompleteTextView ac = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
//        MultiAutoCompleteTextView mc = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView);
//
//        /*ops qe serão completadas*/
//        ac.setAdapter(ad);
//        mc.setAdapter(ad);
//        /*virgula*/
////        mc.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
//        /*espaço*/
//        mc.setTokenizer(new EspacoTokenizer());

        //EXE 3.4
//        WebView wb = new WebView(this);
//        WebSettings ws = wb.getSettings();
//        ws.setSavePassword(false);
//        ws.setSaveFormData(false);
//        ws.setJavaScriptEnabled(true);
//        ws.setSupportZoom(true);
//        wb.loadUrl("http://www.google.com.br");
//        setContentView(wb);

        //EXE4.1
        final String OPS[] = new String[]{"Quejio", "Presunto", "Mussarela"
                , "Cadeira", "Braço", "Cor", "Encosto", "Mola", "Estofada",
                "Azul", "Vermelha", "Rodinha"};

        final ListView lv = (ListView) findViewById(R.id.listView);
        //EXE4.1
//        lv.setAdapter(new ArrayAdapter<String>(this,
//                R.layout.support_simple_spinner_dropdown_item,OPS));

//        lv.setAdapter(new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,OPS));

        ArrayAdapter<String> ad = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item);

        lv.setAdapter(ad);

        ad.add("Contatinho 1");
        ad.add("Contatinho 2");
        ad.add("Contatinho 3");
        ad.add("Contatinho 4");

        //ADAPTACAO ABAIXO
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if(lv.getSelectedItem()!=null){ //SEMPRE NULL
//                    AlertDialog.Builder diag = new AlertDialog.Builder(getApplicationContext());
//                    diag.setTitle("Contato Selecionado");
//                    diag.setMessage(lv.getSelectedItem().toString());
//                    diag.setNeutralButton("OK", null);
//                    diag.show();
//                    System.out.println("OI");
//                }
//
//            }
//        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String txt = "Op clicada: " + lv.getAdapter().getItem(i).toString();
                Toast.makeText(Main.this, txt, Toast.LENGTH_SHORT).show();

                AlertDialog.Builder diag = new AlertDialog.Builder(Main.this);
                diag.setTitle("Contato Selecionado");
                diag.setMessage(lv.getAdapter().getItem(i).toString());
                diag.setNeutralButton("OK", null);
                diag.show();
            }
        });

    }
}
