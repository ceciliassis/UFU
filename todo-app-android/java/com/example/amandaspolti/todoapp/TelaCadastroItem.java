package com.example.amandaspolti.todoapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class TelaCadastroItem extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private ItemCadastroTask mAuthTask = null;

    // UI references.
    private EditText mEditItem;
    private EditText mEditData;
    private View mProgressView;
    private View mCadastroItemFormView;
    RadioButton mRadioFacil, mRadioMedio, mRadioDificil;
    Button voltar_itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro_item);


        mEditItem = (EditText) findViewById(R.id.cadastro_item_text);
        mEditData = (EditText) findViewById(R.id.cadastro_item_data);

        mRadioFacil = (RadioButton) findViewById(R.id.radioFacil);
        mRadioMedio = (RadioButton) findViewById(R.id.radioMedio);
        mRadioDificil = (RadioButton) findViewById(R.id.radioHard);

        mEditData = (EditText) findViewById(R.id.cadastro_item_data);
        mEditData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);

            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.cadastro_item_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarItem();
            }
        });

        voltar_itens = (Button) findViewById(R.id.voltar_itens_button);
        voltar_itens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TelaCadastroItem.this, ListView.class);
                startActivity(i);
            }
        });

        mCadastroItemFormView = findViewById(R.id.item_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void cadastrarItem() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEditItem.setError(null);
        mEditData.setError(null);
        mRadioFacil.setError(null);

        String item_text = mEditItem.getText().toString();
        String dueDate = mEditData.getText().toString();


        boolean cancel = false;
        View focusView = null;

        if (!mRadioFacil.isChecked() && !mRadioMedio.isChecked() && !mRadioDificil.isChecked()) {
            mRadioFacil.setError(getString(R.string.error_field_required));
            focusView = mRadioFacil;
            cancel = true;
        }

        if (TextUtils.isEmpty(item_text)) {
            mEditItem.setError(getString(R.string.error_field_required));
            focusView = mEditItem;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {

            showProgress(true);
            int nivelItem = 0;
            if (mRadioFacil.isChecked()) {
                nivelItem = 1;
            } else if (mRadioMedio.isChecked()) {
                nivelItem = 2;
            } else if (mRadioDificil.isChecked()) {
                nivelItem = 3;
            }

            mAuthTask = new ItemCadastroTask(item_text, nivelItem, dueDate);
            Toast.makeText(TelaCadastroItem.this, "Tarefa Cadastrada com Sucesso", Toast.LENGTH_SHORT).show();
            mAuthTask.execute((Void) null);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mCadastroItemFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mCadastroItemFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mCadastroItemFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mCadastroItemFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    public class ItemCadastroTask extends AsyncTask<Void, Void, Boolean> {

        private final String mText;
        private final int mNivel;
        private final String mDueDate;

        ItemCadastroTask(String text, int nivel, String dueDate) {
            mText = text;
            mNivel = nivel;
            mDueDate = dueDate;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                return false;
            }

            ItemDAO itemdao = ItemDAO.getInstance(TelaCadastroItem.this);
            itemdao.create(mText, mDueDate, mNivel);


            return true;

        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                Intent i = new Intent(TelaCadastroItem.this, ListView.class);
                startActivity(i);
                finish();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }


    public boolean showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment(TelaCadastroItem.this);
        newFragment.show(getSupportFragmentManager(), "datePicker");
        return true;

    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Log.w("DatePicker", "Date = " + year);
        ((EditText) findViewById(R.id.cadastro_item_data)).setText(day + "/" + (month+1) + "/" + year);
        NotificationService.datas.add(day + "/" + (month+1) + "/" + year);
    }

}

