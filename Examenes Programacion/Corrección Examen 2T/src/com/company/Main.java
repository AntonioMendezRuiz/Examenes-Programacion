package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main
{
    public static boolean tiene4vocales(String palabra)
    {
        String vocales = "aeiouAEIOUáéíóúÁÉÍÓÚüÜ";
        int i, cont = 0;

        for (i = 0; i < palabra.length(); i++)
        {
            if (vocales.indexOf(palabra.charAt(i)) != -1)
            {
                cont++;
            }
        }

        if (cont >= 4)
        {
            return true;
        } else
        {
            return false;
        }
    }

    public static String limpiaLinea(String linea)
    {
        int i;
        String limpia = "";

        for (i = 0; i < linea.length(); i++)
        {
            if (linea.charAt(i) == ' ' || Character.isLetter(linea.charAt(i)))
            {
                limpia = limpia + linea.charAt(i);
            }
        }

        return limpia;
    }

    public static void cuatroVocales(String nombreFichero)
    {
        try
        {
            int i;
            String linea;
            String[] palabras;

            FileReader fr = new FileReader(nombreFichero);
            BufferedReader br = new BufferedReader(fr);

            FileWriter fw = new FileWriter("4vocales.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            linea = br.readLine();
            while (linea != null)
            {
                linea = limpiaLinea(linea);
                palabras = linea.split(" ");

                for (i = 0; i < palabras.length; i++)
                {
                    if (tiene4vocales(palabras[i]))
                    {
                        bw.write(palabras[i]);
                        bw.newLine();
                    }
                }

                linea = br.readLine();
            }

            bw.close();
            fw.close();

            br.close();
            fr.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public static void escribeArrayBi(int[][] array)
    {
        int i, j;

        for (i = 0; i < array.length; i++)
        {
            for (j = 0; j < array[i].length; j++)
            {
                System.out.print(array[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public static int[][] leeSudoku(String nombreFichero)
    {
        int[][] sudoku = new int[9][9];

        try
        {
            boolean error;
            String cabecera;
            int i, j, c;
            List<Integer> lista = new ArrayList<>();

            FileInputStream fis = new FileInputStream(nombreFichero);
            DataInputStream dis = new DataInputStream(fis);

            cabecera = dis.readUTF();

            if (cabecera.equals("SUDOKU"))
            {
                while(dis.available() > 0)
                {
                    lista.add(dis.readInt());
                }

                if(lista.size() == 81)
                {
                    c = 0;
                    error = false;
                    for (i = 0; i < 9; i++)
                    {
                        for (j = 0; j < 9; j++)
                        {
                            if(lista.get(c) >= 1 && lista.get(c) <= 9)
                            {
                                sudoku[i][j] = lista.get(c);
                                c++;
                            }
                            else
                            {
                                error = true;
                                i=9;
                                j=9;
                            }
                        }
                    }

                    if(error)
                    {
                        sudoku = null;
                        System.out.println("Los valores no están entre 1 y 9");
                    }
                }
                else
                {
                    sudoku = null;

                    if(lista.size() > 81)
                    {
                        System.out.println("Hay más números de la cuenta");
                    }
                    else
                    {
                        System.out.println("Hay menos números de la cuenta");
                    }
                }
            }
            else
            {
                System.out.println("El archivo no es un archivo de sudoku");
                sudoku = null;
            }

            dis.close();
            fis.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return sudoku;
    }

    public static void escribeSudoku(String nombreFichero, int[][] sudoku)
    {
        boolean error = false;
        int i, j;

        if(sudoku.length == 9)
        {
            for(i=0;i<sudoku.length;i++)
            {
                if(sudoku[i].length != 9)
                {
                    error = true;
                    i = sudoku.length;
                }
            }

            if(!error)
            {
                for(i=0;i<9;i++)
                {
                    for(j=0;j<9;j++)
                    {
                        if(sudoku[i][j] > 9 || sudoku[i][j] < 1)
                        {
                            error = true;
                            i = 9;
                            j = 9;
                        }
                    }
                }

                if(!error)
                {
                    try
                    {
                        FileOutputStream fos = new FileOutputStream(nombreFichero);
                        DataOutputStream dos = new DataOutputStream(fos);

                        dos.writeUTF("SUDOKU");

                        for(i=0;i<9;i++)
                        {
                            for(j=0;j<9;j++)
                            {
                                dos.writeInt(sudoku[i][j]);
                            }
                        }

                        dos.close();
                        fos.close();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    System.out.println("Los valores del sudoku no están entre 1 y 9");
                }
            }
            else
            {
                System.out.println("El sudoku no tiene 9 columnas");
            }
        }
        else
        {
            System.out.println("El sudoku no tiene 9 filas");
        }
    }


    public static void haceCalor(List<String> ciudades, List<Double> temperaturas)
    {
        int i;
        double suma = 0, media;

        if(ciudades.size() == temperaturas.size())
        {
            for(i=0;i<temperaturas.size();i++)
            {
                suma = suma + temperaturas.get(i);
            }

            media = suma / temperaturas.size();


            for(i=0;i<ciudades.size();i++)
            {
                if(temperaturas.get(i) < media)
                {
                    temperaturas.remove(i);
                    ciudades.remove(i);
                    i--;
                }
            }
        }
        else
        {
            System.out.println("Las listas tienen distinto tamaño");
        }
    }


    public static void main(String[] args)
    {
        //cuatroVocales("lazarilloBien.txt");

        //int[][] sudoku = leeSudoku("cinco.sudoku");
        //escribeSudoku("cinco.sudoku", sudoku);
        //escribeArrayBi(sudoku);

        List<String> ciudades = new ArrayList<>(Arrays.asList("Jerez", "Siberia", "Tarifa", "Barbate"));
        List<Double> temperaturas = new ArrayList<>(Arrays.asList(18.9, 11.0, 14.1, 18.6));

        haceCalor(ciudades, temperaturas);
        System.out.println(ciudades);
        System.out.println(temperaturas);
    }
}
