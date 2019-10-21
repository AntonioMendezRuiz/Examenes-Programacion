package com.company;

import java.io.BufferedReader;
import java.io.FileReader;

public class BuscaTexto
{
    private String texto;
    private int puntero;

    public BuscaTexto()
    {
        texto = "";
        puntero = 0;
    }

    public BuscaTexto(String texto)
    {
        this.texto = texto;
        puntero = 0;
    }

    public String getTexto()
    {
        return texto;
    }

    public int getPuntero()
    {
        return puntero;
    }

    public void setPuntero(int puntero)
    {
        if(puntero < 0)
        {
            puntero = 0;
        }
        else
        {
            if(puntero > texto.length())
            {
                puntero = texto.length();
            }
        }

        this.puntero = puntero;
    }

    public void cargaFichero(String fichero)
    {
        try
        {
            texto = "";

            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);

            String linea = br.readLine();

            while(linea != null)
            {
                texto = texto + linea + "\n";

                linea = br.readLine();
            }

            br.close();
            fr.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void busca(String cadena)
    {
        setPuntero(texto.indexOf(cadena));
    }

    public void buscaSiguiente(String cadena)
    {
        setPuntero(texto.indexOf(cadena, puntero+1));
    }

    public String extraeCadena(String del1, String del2)
    {
        int pos1 = texto.indexOf(del1, puntero);
        int pos2 = texto.indexOf(del2, pos1+1);

        String cadena = texto.substring(pos1 + del1.length(), pos2);
        return cadena;
    }
}
