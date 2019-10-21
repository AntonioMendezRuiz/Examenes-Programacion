package com.company;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main
{
    public static String padLeft(String cadena, int longitud)
    {
        while(cadena.length() < longitud)
        {
            cadena = " " + cadena;
        }

        return cadena;
    }

    public static String padRight(String cadena, int longitud)
    {
        while(cadena.length() < longitud)
        {
            cadena = cadena + " ";
        }

        return cadena;
    }

    public static void main(String[] args)
    {
        Connection conn;
        int sumaVotos = 0;

        try
        {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:votacion2.db");

            Statement st = conn.createStatement();

            String sql = "SELECT id, partido FROM votacion;";
            //String sql2 = "SELECT partido, COUNT(id) FROM votacion GROUP BY partido ORDER BY COUNT(id) DESC;";

            ResultSet rs = st.executeQuery(sql);

            List<String> listaPartidos = new ArrayList<>();
            List<Integer> listaVotos = new ArrayList<>();

            while(rs.next())
            {
                String partido = rs.getString(2);

                if(listaPartidos.contains(partido))
                {
                    int index = listaPartidos.indexOf(partido);
                    int votos = listaVotos.get(index);
                    votos = votos + 1;
                    listaVotos.set(index, votos);

                    //listaVotos.set(index, listaVotos.get(index) + 1);
                }
                else
                {
                    listaPartidos.add(partido);
                    listaVotos.add(1);
                }

                sumaVotos++;
            }

            while(listaPartidos.size() > 0)
            {
                int maxVotos = Collections.max(listaVotos);
                int index = listaVotos.indexOf(maxVotos);
                String partido = listaPartidos.get(index);

                int porcentaje = (maxVotos * 100) / sumaVotos;

                //System.out.println(partido + " - " + maxVotos + " votos - " + porcentaje + "%");

                System.out.println(padRight(partido, 15) + padLeft(String.valueOf(maxVotos), 5) + " votos " + padLeft(String.valueOf(porcentaje), 8) + "%");
                listaPartidos.remove(index);
                listaVotos.remove(index);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
