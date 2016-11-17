package cecilia.prat5cont;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity {
//    private Handler h; //EXE4
    private ProgressBar pb;
    private Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb = (ProgressBar) findViewById(R.id.progressBar);
        b = (Button) findViewById(R.id.button);
//        h = new Handler(); //EXE4
//EXE4
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Runnable r = new Runnable() {
//                    @Override
//                    public void run() {
//                        for (int i = 1; i <= 10; i++) {
//                            final int value = i;
//                            try {
//                                Thread.sleep(1000);
//
//                            } catch (Exception ec) {
//                                ec.printStackTrace();
//                            }
//                            h.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    pb.setProgress(value);
//                                }
//                            });
//                        }
//                    }
//                };
//                new Thread(r).start();
//            }
//        });
        //EXE5
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 1; i <= 10; i++) {
                            final int value = i;
                            try {
                                Thread.sleep(1000);
                            } catch (Exception ec) {
                                ec.printStackTrace();
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pb.setProgress(value);
                                }
                            });
                        }
                    }
                };
                new Thread(r).start();
            }
        });
    }
}
