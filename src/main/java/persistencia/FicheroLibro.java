/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import modelo.Libro;

/**
 *
 * @author yessm
 */
public class FicheroLibro {

    private static String rutaProyecto = System.getProperty("user.dir");
    private static String separador = File.separator;
    private static String rutaCarpeta = rutaProyecto + separador + "data";
    private static File folder = new File(rutaCarpeta);
    private static String rutaArchivo = rutaCarpeta + separador + "libros.txt";
    private static File file = new File(rutaArchivo);

    public static void guardar(HashMap<String, Libro> inventarioLibro) {
        try {
            if (!folder.exists()) {
                folder.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Libro libro : inventarioLibro.values()) {
                bw.write(libro.toString());
                bw.newLine();
                bw.flush();
            }

            bw.close();

        } catch (IOException e) {
            System.getLogger(FicheroLibro.class.getName()).log(System.Logger.Level.ERROR, (String) null, e);
        }
    }

    public static HashMap<String, Libro> cargar() {
        HashMap<String, Libro> listar = new HashMap<>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] info = linea.split(";");
                ArrayList<String> autores = new ArrayList<>(Arrays.asList(info[2].split(",")));
                Libro l = new Libro(info[0], info[1], autores, Double.parseDouble(info[3]), Integer.parseInt(info[4]));
                listar.put(info[0], l);
            }

            return listar;

        } catch (FileNotFoundException e) {
            // El archivo no existe todavía, retorna lista vacía
            return listar;
        } catch (IOException e) {
            System.getLogger(FicheroLibro.class.getName()).log(System.Logger.Level.ERROR, (String) null, e);
        }

        return listar;
    }
}
