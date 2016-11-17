package cecilia.prat5;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by cecilia on 11/17/16.
 */
public class ExampleService extends IntentService { //nao preocupa com
    //o stopSelf()
    public ExampleService(){
        super("ExampleService"); //nome da thread worker
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
