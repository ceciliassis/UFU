package assingment.pratica3;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Act2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2);

        Bundle extras = getIntent().getExtras();
        String origem = extras.getString("origem");
        String destino = extras.getString("destino");
        String str = "Origem? " + origem + " Destino? " + destino;
        Toast.makeText(this, str, Toast.LENGTH_SHORT);


    }

    public void make_call(View v) {
        Intent in = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:*222#"));
        startActivity(in);
    }

    public void view_site(View v) {
        Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        startActivity(in);
    }

    public void send_email(View v) {
        Intent in = new Intent(Intent.ACTION_SEND);
        in.setType("plain/text");
        in.putExtra(Intent.EXTRA_EMAIL, new String("cy_assis@hotmai.com"));
        startActivity(Intent.createChooser(in, "Enviar Email"));

    }
}
