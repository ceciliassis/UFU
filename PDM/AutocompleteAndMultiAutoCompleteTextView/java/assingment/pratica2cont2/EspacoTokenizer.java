package assingment.pratica2cont2;

import android.widget.MultiAutoCompleteTextView;

/**
 * Created by cecilia on 10/10/16.
 */
public class EspacoTokenizer implements MultiAutoCompleteTextView.Tokenizer {
   //Funciona somente para a primeira palavra
    @Override
    public int findTokenStart(CharSequence charSequence, int i) {
        int pos = charSequence.toString().indexOf(' ', i);
        return (pos < 0) ? 0 : (pos + 1);
    }

    @Override
    public int findTokenEnd(CharSequence charSequence, int i) {
        int pos = charSequence.toString().indexOf(' ', i);
        return (pos < 0) ? (charSequence.length() - 1) : (pos - 1);
    }

    @Override
    public CharSequence terminateToken(CharSequence charSequence) {
        String txt = charSequence.toString();
        if (txt.endsWith(" "))
            return txt;
        else return txt + " ";
    }
}
