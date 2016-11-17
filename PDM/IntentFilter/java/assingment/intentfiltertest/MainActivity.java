package assingment.intentfiltertest;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] lis = new String[]{"Act1", "Act2", "Act3", "Calculadora IMC"};
        ListView lst = (ListView) findViewById(R.id.listView);
        lst.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,
                lis));

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent inte;
                if (i == 0) {
                    inte = new Intent(MainActivity.this, Act1.class);
                    startActivity(inte);
                } else if (i == 1) {
                    inte = new Intent(MainActivity.this, Act2.class);
                    startActivity(inte);
                } else if(i==2) {
                    inte = new Intent(MainActivity.this, Act3.class);
                    startActivity(inte);
                }else {
                    inte = new Intent(MainActivity.this, IMC.class);
                    startActivity(inte);
                }
            }
        });
    }

    public void wallpaper(View v) {
        Intent inte = new Intent(Intent.ACTION_SET_WALLPAPER);
        startActivity(inte);
    }

    public void websearch(View v) {
        Intent inte = new Intent(Intent.ACTION_WEB_SEARCH);
        inte.putExtra(SearchManager.QUERY, "Maria");
        startActivity(inte);
    }

    public void insert(View v) {
        Uri uri = Uri.parse("content://com.android.contacts/contacts");
        Intent inte = new Intent(Intent.ACTION_INSERT, uri);
        startActivity(inte);
    }
}
