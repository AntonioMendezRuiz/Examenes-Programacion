package com.company;

import java.security.InvalidParameterException;

public class Main
{
    public static String repiteCaracter(char c, int veces)
    {
        String cadena = "";

        for(int i = 0; i < veces; i++)
        {
            cadena = cadena + c;
        }

        return cadena;
    }


    public static void dibujaXestrellitas(int pisos)
    {
        if(pisos >= 3 && pisos %2 == 1)
        {
            int star = pisos;
            int esp = 0;

            while (star > 1)
            {
                System.out.print(repiteCaracter(' ', esp));
                System.out.println(repiteCaracter('X', star));

                star = star - 2;
                esp = esp + 1;
            }

            while (star <= pisos)
            {
                System.out.print(repiteCaracter(' ', esp));
                System.out.println(repiteCaracter('X', star));

                star = star + 2;
                esp = esp - 1;
            }
        }
        else
        {
            throw new InvalidParameterException("El número de pisos debe ser impar y al menos 3");
        }
    }


    public static void main(String[] args)
    {
        dibujaXestrellitas(5);
    }
}

