package cecilia.receivertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by cecilia on 11/17/16.
 */
public class Re extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Re received !", Toast.LENGTH_SHORT).show();
        Log.w("RECEIVER R", "RE RECEIVER");
    }
}
