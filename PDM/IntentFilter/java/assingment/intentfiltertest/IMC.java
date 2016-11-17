package assingment.intentfiltertest;



import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class IMC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imc);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Calculadora IMC");
        actionBar.setSubtitle("by Cecilia Assis");
        System.out.println(actionBar);
    }

    public void calcularIMC(View v) {
        EditText etQuilos = (EditText) findViewById(R.id.quilos);
        Double quilos = Double.parseDouble(etQuilos.getText().toString());

        EditText etAltura = (EditText) findViewById(R.id.altura);
        Double altura = Double.parseDouble(etAltura.getText().toString());

        Double imc = quilos / (altura * altura);

        ImageView iv = (ImageView) findViewById(R.id.resultado);

        if (imc < 16) {
            iv.setImageResource(R.drawable.muito_grave);
        } else if (imc >= 16 && imc <= 16.99) {
            iv.setImageResource(R.drawable.grave);
        } else if (imc >= 17 && imc <= 18.49) {
            iv.setImageResource(R.drawable.baixo_peso);
        } else if (imc >= 18.50 && imc <= 24.99) {
            iv.setImageResource(R.drawable.peso_normal);
        } else if (imc >= 25 && imc <= 29.99) {
            iv.setImageResource(R.drawable.sobrepeso);
        } else if (imc >= 30 && imc <= 34.99) {
            iv.setImageResource(R.drawable.grau1);
        } else if (imc >= 35 && imc <= 39.99) {
            iv.setImageResource(R.drawable.grau2);
        } else {
            iv.setImageResource(R.drawable.morbida);
        }


    }
}
