package com.company;

public class Main
{
    public static void main(String[] args)
    {
//        Primero buscaríamos la cadena "cc-ratebox".
//        Desde ahí, buscaríamos "tabindex".
//        Y extraeríamos la cadena comprendida entre ">" y "<".
//        Nos debería salir: "EUR/USD = 0,88822".

        BuscaTexto bt = new BuscaTexto();
        bt.cargaFichero("cambio.html");
        bt.busca("cc-ratebox");
        bt.buscaSiguiente("tabindex");
        System.out.println(bt.extraeCadena(">", "<"));
    }
}
