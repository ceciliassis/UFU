package cecilia.prat5cont;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by cecilia on 11/17/16.
 */
//EXE3
public class PhoneReceiver extends BroadcastReceiver {
    private static final String TAG = "PhoneReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle ex = intent.getExtras();
        if (ex != null) {
            String st = ex.getString(TelephonyManager.EXTRA_STATE);
            Log.w(TAG, st);
            if (st.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                String pn = ex.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                Log.w(TAG, pn);
            }
        }
    }
}
