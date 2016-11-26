package assingment.pratica3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Act1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act1);

        EditText nu = (EditText) findViewById(R.id.editText);
        setPhoneTextWatcher(nu);

    }

    public void mudarTela(View v) {
        Intent intent = new Intent(this, Act2.class);
        intent.putExtra("origem", "Act1");
        intent.putExtra("destino", "Act2");
        startActivity(intent);
    }

    private void setPhoneTextWatcher(final EditText et) {
        et.addTextChangedListener(new TextWatcher() {
            private boolean editando = false;
            private String fone;
            private int tamVelho = 0;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!editando) {
                    editando = !editando;
                    String txt = et.getText().toString();
                    fone = txt.replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("-", "").replaceAll(" ", "");
                    if (fone.length() >= 2 && fone.length() > tamVelho) {
                        String part0 = fone.substring(0, 2);
                        String part1 = fone.substring(2);
                        String part2 = "";
                        if (fone.length() < 6) {
                            tamVelho = fone.length();
                            fone = "(" + part0 + ")" + part1;
                        } else {
                            part1 = fone.substring(2, 6);
                            part2 = fone.substring(6);
                            tamVelho = fone.length();
                            fone = "(" + part0 + ")" + part1 + "-" + part2;

                        }
                    } else {
                        tamVelho = fone.length();
                        fone = txt;
                    }
                    if (fone.length() > 2) {
                        et.setTextKeepState(fone, TextView.BufferType.EDITABLE);
                        et.setSelection(fone.length());
                    }
                    editando = !editando;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
