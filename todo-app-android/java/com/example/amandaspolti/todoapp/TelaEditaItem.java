package com.example.amandaspolti.todoapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.database.Cursor;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class TelaEditaItem extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    RadioButton mRadioFacil, mRadioMedio, mRadioDificil;
    Button voltar_itens;
    private EditaItemTask mAuthTask = null;
    // UI references.
    private EditText mTextView;
    private View mProgressView;
    private EditText mEditData;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_edita_item);
        // Set up the login form.
        mTextView = (EditText) findViewById(R.id.edita_item_text);

        mRadioFacil = (RadioButton) findViewById(R.id.edita_radioFacil);
        mRadioMedio = (RadioButton) findViewById(R.id.edita_radioMedio);
        mRadioDificil = (RadioButton) findViewById(R.id.edita_radioHard);

        mEditData = (EditText) findViewById(R.id.edita_item_data);
        mEditData.setText("");

        Intent i = getIntent();
        final Item item = (Item) i.getSerializableExtra("item");

        mTextView.setText(item.getText());
        switch (item.getNivel()) {
            case 1:
                mRadioFacil.setChecked(true);
                break;
            case 2:
                mRadioMedio.setChecked(true);
                break;
            case 3:
                mRadioDificil.setChecked(true);
                break;
        }

        String n = item.getDueDate();
        if (!n.equals("null")){
            mEditData.setText(item.getDueDate());
        }

        mEditData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);

            }
        });

        voltar_itens = (Button) findViewById(R.id.edita_voltar_itens_button);
        voltar_itens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TelaEditaItem.this, ListView.class);
                startActivity(i);
            }
        });


        Button mEditaButton = (Button) findViewById(R.id.edita_item_button);
        mEditaButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                editaItem(item);
            }
        });

        mLoginFormView = findViewById(R.id.edita_item_form);
        mProgressView = findViewById(R.id.edita_item_progress);
    }


    private void editaItem(Item item) {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mTextView.setError(null);
        mEditData.setError(null);
        mRadioFacil.setError(null);

        String item_text = mTextView.getText().toString();
        String dueDate = mEditData.getText().toString();

        boolean cancel = false;
        View focusView = null;


        if (!mRadioFacil.isChecked() && !mRadioMedio.isChecked() && !mRadioDificil.isChecked()) {
            mRadioFacil.setError(getString(R.string.error_field_required));
            focusView = mRadioFacil;
            cancel = true;
        }

        if (TextUtils.isEmpty(item_text)) {
            mTextView.setError(getString(R.string.error_field_required));
            focusView = mTextView;
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

            item.setText(item_text);
            item.setDueDate(dueDate);
            item.setNivel(nivelItem);

            mAuthTask = new EditaItemTask(item);
            mAuthTask.execute((Void) null);
        }
    }


    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
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
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public boolean showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment(TelaEditaItem.this);
        newFragment.show(getSupportFragmentManager(), "datePicker");
        return true;

    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        Log.w("DatePicker", "Date = " + year);
        ((EditText) findViewById(R.id.edita_item_data)).setText(day + "/" + (month + 1) + "/" + year);
    }

    public class EditaItemTask extends AsyncTask<Void, Void, Boolean> {

        private Item item;

        EditaItemTask(Item item) {
            this.item = item;
            if(item.getDueDate().equals("null")) item.setDueDate(null);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                return false;
            }

            ItemDAO itemdao = ItemDAO.getInstance(TelaEditaItem.this);
            itemdao.update(item);
            return true;

        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                Intent i = new Intent(TelaEditaItem.this, ListView.class);
                startActivity(i);
                finish();
            } else {
                //TODO
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }

}

