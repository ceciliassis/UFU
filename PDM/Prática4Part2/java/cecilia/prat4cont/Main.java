package cecilia.prat4cont;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;


public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//EXE8
        NotificationCompat.Builder ncb = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("My notification")
                .setContentText("Hello World!");

        Intent in = new Intent(this, Tabbed.class);

        TaskStackBuilder st = TaskStackBuilder.create(this);
        st.addParentStack(Main.class);
        st.addNextIntent(in);
        PendingIntent pIn = st.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);

        ncb.setContentIntent(pIn);
        NotificationManager nm = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);

        nm.notify(ncb.mNumber, ncb.build()); //mid

        //como clicar e fechar?

//EXE8 nao deu certo
//        NotificationCompat.Builder ncbb = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.drawable.notification_icon)
//                .setContentTitle("Event tracker")
//                .setContentText("Events received");
//
//        NotificationCompat.InboxStyle inbox = new NotificationCompat.InboxStyle();
//        String[] events = new String[6];
//
//        inbox.setBigContentTitle("Event tracker details: ");
//
//        for (int i = 0; i < events.length; i++) {
//            inbox.addLine(events[i]);
//        }
//
//        ncbb.setStyle(inbox);
//
//        Intent inn = new Intent(this, Basic.class);
//
//        TaskStackBuilder stt = TaskStackBuilder.create(this);
//        stt.addParentStack(Main.class);
//        stt.addNextIntent(inn);
//        PendingIntent ppIn = stt.getPendingIntent(0,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//
//        ncbb.setContentIntent(ppIn);
//        NotificationManager nmm = (NotificationManager)
//                getSystemService(Context.NOTIFICATION_SERVICE);
//
//        nmm.notify(ncbb.mNumber, ncbb.build());
    }
}
