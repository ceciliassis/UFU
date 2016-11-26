package assingment.pratica2cont;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.view.ViewPropertyAnimatorCompatSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;

public class Main extends Activity implements AdapterView.OnItemSelectedListener {
    private final String OPS[] = new String[]{"Quejio", "Presunto", "Mussarela"
    , "Cadeira", "Braço", "Cor", "Encosto", "Mola", "Estofada",
    "Azul", "Vermelha", "Rodinha"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //EXE 3.1
        Spinner spn = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, OPS);
        spn.setAdapter(adp);
//        int x = spn.getSelectedItemPosition();
        spn.setOnItemSelectedListener(this);

        //EXE 3.2
        DatePicker dp = (DatePicker) findViewById(R.id.datePicker);
        dp.updateDate(1996,01,25);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePicker dp = (DatePicker) findViewById(R.id.datePicker);
                String str = "Data: "+ dp.getDayOfMonth()+"/"+dp.getMonth()+1
                        +"/"+dp.getYear();
                Toast.makeText(Main.this, str,Toast.LENGTH_SHORT).show();

            }
        });

    }

    //EXE 3.1
    /*ocorre quando um item  é selecionado*/
    public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
        Toast.makeText(parent.getContext(), "Voce escolheu: " + OPS[pos],
                Toast.LENGTH_LONG).show();
    }

    //     lista aberta, nada selecionado
    public void onNothingSelected(AdapterView parent) {
        //nothing
    }
}
