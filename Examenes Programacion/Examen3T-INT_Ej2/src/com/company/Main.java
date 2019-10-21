package com.company;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.InvalidParameterException;
import java.sql.*;
import java.time.LocalDate;

public class Main
{
    public static void transfiereFicheroBD(String fichero, String basedatos)
    {
        Connection conn;
        ResultSet rs;

        try
        {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + basedatos);

            Statement st = conn.createStatement();

            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);

            String linea = br.readLine();

            if(linea.equals("NOMBRE,FECHA DE NACIMIENTO,CURSO"))
            {
                linea = br.readLine();

                while (linea != null)
                {
                    String[] trozos = linea.split(",");

                    String nombre = trozos[0];
                    String fecha = trozos[1];
                    String curso = trozos[2];
                    int idCurso;

                    String sql1 = "SELECT id FROM cursos WHERE nombre = '" + curso + "';";
                    rs = st.executeQuery(sql1);

                    if(rs.next())
                    {
                        idCurso = rs.getInt(1);
                    }
                    else
                    {
                        System.out.println("El alumno '" + nombre + "' no se puede insertar porque su curso '" + curso + "' no está en la base de datos");
                        idCurso = -1;
                    }

                    rs.close();

                    if(idCurso != -1)
                    {
                        String[] trozosFecha = fecha.split("/");
                        int dia = Integer.valueOf(trozosFecha[0]);
                        int mes = Integer.valueOf(trozosFecha[1]);
                        int anno = Integer.valueOf(trozosFecha[2]);
                        LocalDate fecha2 = null;
                        boolean bien = true;

                        try
                        {
                            fecha2 = LocalDate.of(anno, mes, dia);
                        }
                        catch(Exception e)
                        {
                            bien = false;
                        }

                        if(bien)
                        {
                            String fechaBien = fecha2.toString() + " 00:00:00.000";

                            String sql2 = "INSERT INTO alumnos(nombre, fechaNacimiento, idCurso) VALUES ('" + nombre + "', '" + fechaBien + "', " + idCurso + ");";
                            st.executeUpdate(sql2);
                        }
                        else
                        {
                            System.out.println("El alumno '" + nombre + "' no se puede insertar porque su fecha '" + fecha + "' no es correcta");
                        }
                    }

                    linea = br.readLine();
                }
            }
            else
            {
                throw new InvalidParameterException("Formato del fichero no correcto");
            }
            br.close();
            fr.close();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        //transfiereFicheroBD("alumnos.txt", "alumnos.db");
        //transfiereFicheroBD("alumnos_cabeceramal.txt", "alumnos.db");
        //transfiereFicheroBD("alumnos_cursomal.txt", "alumnos.db");
        transfiereFicheroBD("alumnos_fechamal.txt", "alumnos.db");
    }
}
