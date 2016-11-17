package cecilia.prat5;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


/**
 * Created by cecilia on 11/17/16.
 */
public class DownloadService extends IntentService {
    private int result = Activity.RESULT_CANCELED;

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Uri data = intent.getData();
        String urlPath = intent.getStringExtra("urlPath");
        String fileName = data.getPath();
        File output = new File(Environment.getExternalStorageDirectory()
                , fileName);

        if (output.exists()) {
            output.delete();
        }

        InputStream stream = null;
        FileOutputStream fos = null;

        try {
            URL url = new URL(urlPath);
            stream = url.openConnection().getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);
            fos = new FileOutputStream(output.getPath());
            int next = -1;
            while ((next = reader.read()) != -1) {
                fos.write(next);
            }
            result = Activity.RESULT_OK;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        Bundle extras = intent.getExtras();
        if (extras != null) {
            Messenger m = (Messenger) extras.get("messenger");
            Message msg = Message.obtain();
            msg.arg1 = result;
            msg.obj = output.getAbsolutePath();
            try {
                m.send(msg);

            } catch (Exception ex) {
                Log.e("DownloadService", "Error ao enviar mensagem",
                        ex);
            }
        }

    }
}
