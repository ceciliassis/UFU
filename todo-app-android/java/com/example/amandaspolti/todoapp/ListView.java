package com.example.amandaspolti.todoapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListView extends AppCompatActivity {

    private ImageButton adcItem;
    private MyListAdaper adp;
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startService(new Intent(this, NotificationService.class));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_com_tasks);
        android.widget.ListView lv = (android.widget.ListView) findViewById(R.id.listview);

        final ItemDAO itemdao = ItemDAO.getInstance(ListView.this);
        adp = new MyListAdaper(this, R.layout.list_item, itemdao.getAll());
        lv.setAdapter(adp);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String s = (String) parent.getItemAtPosition(position);

                final String[] mDadosItem = s.split("\\|\\|");

                final Item item = new Item(mDadosItem[0], mDadosItem[1], mDadosItem[2], mDadosItem[3],
                        mDadosItem[4]);

                Intent intent = new Intent(ListView.this, TelaEditaItem.class);
                intent.putExtra("item", item);

                startActivity(intent);
            }
        });

        adcItem = (ImageButton) findViewById(R.id.criaItem);
        adcItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListView.this, TelaCadastroItem.class);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onResume() {
        reloadAllData();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_preferencias) {
            Intent i = new Intent(ListView.this, TelaConfig.class);
            startActivity(i);
            return true;
        } else if (id == R.id.action_sobre) {
            Intent i = new Intent(ListView.this, TelaSobre.class);
            startActivity(i);
            return true;
        } else if (id == R.id.action_sair) {
            this.finishAffinity();
        }

        return super.onOptionsItemSelected(item);
    }


    private void reloadAllData() {
        final ItemDAO itemdao = ItemDAO.getInstance(ListView.this);
        // get new modified random data
        List<String> itens = itemdao.getAll();
        // update data in our adapter
        adp.clear();
        adp.addAll(itens);
        // fire the event
        adp.notifyDataSetChanged();
    }

    private class MyListAdaper extends ArrayAdapter<String> {
        private ViewHolder mainViewholder = new ViewHolder();
        private int layout;
        private List<String> mObjects;

        private MyListAdaper(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            mObjects = objects;
            layout = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            mainViewholder = null;
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.list_item_thumbnail);
                viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_text);
                viewHolder.button = (Button) convertView.findViewById(R.id.list_item_btn);
                viewHolder.data = (TextView) convertView.findViewById(R.id.list_item_data);
                convertView.setTag(viewHolder);
            }

            final String[] mDadosItem = getItem(position).split("\\|\\|");
            final ItemDAO itemdao = ItemDAO.getInstance(ListView.this);

            final Item item = new Item(mDadosItem[0], mDadosItem[1], mDadosItem[2], mDadosItem[3],
                    mDadosItem[4]);

            mainViewholder = (ViewHolder) convertView.getTag();

            if (item.getNivel() == 1) {
                mainViewholder.thumbnail.setImageResource(R.drawable.easy);
            } else if (item.getNivel() == 2) {
                mainViewholder.thumbnail.setImageResource(R.drawable.medium);
            } else {
                mainViewholder.thumbnail.setImageResource(R.drawable.hard);
            }


            if (item.isDone()) {


                AlertDialog.Builder builder = new AlertDialog.Builder(ListView.this);
                //define o titulo
                builder.setTitle("Item Feito");
                //define a mensagem
                builder.setMessage("Tem Certeza?");
                //define um botão como positivo
                builder.setPositiveButton("Positivo", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        itemdao.delete(item);
                        reloadAllData();
                    }
                });
                //define um botão como negativo.
                builder.setNegativeButton("Negativo", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        //nao faz nada
                    }
                });
                //cria o AlertDialog
                alerta = builder.create();
                //Exibe
                alerta.show();

            } else {
                mainViewholder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        item.setDone(true);
                        itemdao.update(item);
                        reloadAllData();
                    }
                });
            }

            mainViewholder.title.setText(item.getText());

            if(item.getDueDate() != null)
                 mainViewholder.data.setText(item.getDueDate());
            return convertView;
        }



    }



    public class ViewHolder {
        ImageView thumbnail;
        TextView title;
        Button button;
        TextView data;
    }
}