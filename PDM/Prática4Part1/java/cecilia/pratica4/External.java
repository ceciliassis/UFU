package cecilia.pratica4;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.BatchUpdateException;

public class External extends AppCompatActivity {
    boolean externalAv = false;
    boolean externalWr = false;
    String externalSt = Environment.getExternalStorageState();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external);
        final TextView tx = (TextView) findViewById(R.id.textView4);

        if (Environment.MEDIA_MOUNTED.equals(externalSt)) {
            externalAv = externalWr = true;
            tx.setText("Tudo certo com o cartao");
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(externalSt)) {
            externalAv = true;
            externalWr = false;
            tx.setText("Podemos só ler o cartao");
        } else {
            externalAv = externalWr = false;
            tx.setText("Vai dar pra fazer nada,viu");
        }

        Button bt = (Button) findViewById(R.id.button6);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(External.this, BD.class);
                startActivity(in);
            }
        });

    }
}
