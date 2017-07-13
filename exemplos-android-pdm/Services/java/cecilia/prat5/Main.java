package cecilia.prat5;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button s = (Button) findViewById(R.id.start_button);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Main.this, DownloadService.class);
                Messenger m = new Messenger(han);
                in.putExtra("messenger", m);
                in.setData(Uri.parse("cursos.html"));
                in.putExtra("urlPath", "http://k19.com.br/cursos");
                startService(in);
            }
        });
    }

    private Handler han = new Handler() {
        public void handleMessage(Message m) {
            Object path = m.obj;
            if (m.arg1 == RESULT_OK && path != null) {
                Toast.makeText(Main.this, getString(R.string.download_success, path.toString()),
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(Main.this, getString(R.string.download_error), Toast.LENGTH_LONG)
                        .show();
            }
        }
    };
}
