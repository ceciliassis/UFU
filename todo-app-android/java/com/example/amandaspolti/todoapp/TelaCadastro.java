package com.example.amandaspolti.todoapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TelaCadastro extends AppCompatActivity {

    private UserCadastroTask mAuthTask = null;
    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private CheckBox mCheckboxView;
    private View mProgressView;
    private View mCadastroFormView;
    private Button volta_principal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        mEmailView = (EditText) findViewById(R.id.cadastro_email);
        mPasswordView = (EditText) findViewById(R.id.cadastro_password);
        mCheckboxView = (CheckBox) findViewById(R.id.cadastro_checkbox);

        Button mEmailSignInButton = (Button) findViewById(R.id.cadastro_usuario_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptCadastrar();
            }
        });

        volta_principal = (Button) findViewById(R.id.voltar_login_button);
        volta_principal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TelaCadastro.this, TelaLogin.class);
                startActivity(i);

            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();

        categories.add("Norte");
        categories.add("Nordeste");
        categories.add("Centro-Oeste");
        categories.add("Sudeste");
        categories.add("Sul");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        mCadastroFormView = findViewById(R.id.cadastro_form);
        mProgressView = findViewById(R.id.cadastro_progress);
    }

    private void attemptCadastrar() {
        if (mAuthTask != null) {
            return;
        }

        mEmailView.setError(null);
        mPasswordView.setError(null);
        mCheckboxView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        boolean checkbox = mCheckboxView.isChecked();


        boolean cancel = false;
        View focusView = null;

        if (!checkbox) {
            mCheckboxView.setError(getString(R.string.error_field_required));
            focusView = mCheckboxView;
            cancel = true;
        }

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            mAuthTask = new UserCadastroTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mCadastroFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mCadastroFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mCadastroFormView.setVisibility(show ? View.GONE : View.VISIBLE);
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
            mCadastroFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public class UserCadastroTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserCadastroTask(String email, String password) {
            mEmail = email;
            mPassword = Criptografia.cryptWithMD5(password);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return false;
            }

            System.out.println("Cheguei AquCheguei AquCheguei AquCheguei Aqu");
            return UsuarioDAO.getInstance().tryCadastrar(mEmail, mPassword);
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                finish();
            } else {
                mEmailView.setError("Este aplicativo é exclusivo. Usuário " +
                        UsuarioDAO.getInstance().getUsername() + " já cadastrado.");
                mEmailView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

