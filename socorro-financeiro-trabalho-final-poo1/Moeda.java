/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Account;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * @author p_999126 To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Moeda
{
    /**
     * 
     */
    Moeda()
    {}

    private static String SIMBOLO_BRASIL_REAIS = "R$ ";

    public static String formatarMoeda(double dblValor)
    {
        return formatarMoeda(dblValor, SIMBOLO_BRASIL_REAIS);
    }

    public static String formatarNumero(double dblValor)
    {

        // Declara o retorno
        String strRetorno = "";

        // Declara o Locale para o Brasil
        Locale locale = new Locale("pt", "br");

        // Obtem o formatador de valores monetários
        NumberFormat nf = NumberFormat.getNumberInstance(locale);

        // Formata o valor
        strRetorno = nf.format(dblValor);

        return strRetorno;
    }

    public static String formatarMoeda(double dblValor, String strSimbolo)
    {

        // Declara o retorno
        String strRetorno = "";

        // Formata o valor
        strRetorno = formatarNumero(dblValor);

        int intPosicaoVirgula = strRetorno.indexOf(",");
        if (intPosicaoVirgula == -1)
        {
            strRetorno = strRetorno + ",00";
        }
        else if (strRetorno.length() - intPosicaoVirgula == 1)
        {
            strRetorno = strRetorno + "00";
        }
        else if (strRetorno.length() - intPosicaoVirgula == 2)
        {
            strRetorno = strRetorno + "0";
        }

        strRetorno = strSimbolo + strRetorno;

        return strRetorno;
    }

    public static String retornarValorSemSimbolo(String strMoeda)
    {

        // Declara o Locale para o Brasil
        Locale locale = new Locale("pt", "br");
        StringBuffer stbMoedaSemSimbolo = new StringBuffer();

        for (int i = 0; i < strMoeda.length(); i++)
        {
            if (strMoeda.charAt(i) == '-')
            {
                stbMoedaSemSimbolo.append(strMoeda.charAt(i));
            }
            else if (strMoeda.charAt(i) == '.')
            {
                stbMoedaSemSimbolo.append(strMoeda.charAt(i));
            }
            else if (strMoeda.charAt(i) == ',')
            {
                stbMoedaSemSimbolo.append(strMoeda.charAt(i));
            }
            else if (strMoeda.charAt(i) >= '0' && strMoeda.charAt(i) <= '9')
            {
                stbMoedaSemSimbolo.append(strMoeda.charAt(i));
            }
        }

        // Obtem o formatador de valores monetários
        NumberFormat nf = NumberFormat.getNumberInstance(locale);
        Number numRetorno = null;
        try
        {
            numRetorno = nf.parse(stbMoedaSemSimbolo.toString());
        }
        catch (ParseException pe)
        {
            // Erro...
        }

        return numRetorno.toString();
    }

    public static String formatarMoedaPadraoAmericano(String strValor)
    {
        return retornarValorSemSimbolo(strValor);
    }

    /**
     * @param valorReferencia
     * @return
     */
    public static boolean validarValor(String valorReferencia)
    {
        try
        {
            new Double(valorReferencia);
            return true;
        }
        catch (RuntimeException rtme)
        {
            return false;
        }
    }
}