/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import modelo.Libro;
import persistencia.FicheroLibro;

/**
 *
 * @author yessm
 */
//el HashMap debe estar en el main o sea aquí 
public class GestionLibros {

    private static HashMap<String, Libro> inventarioLibro = new HashMap<>(); //inicializado

    public static void guardar() throws IOException {
        FicheroLibro.guardar(inventarioLibro);
    }

    public static void cargar() throws IOException {
        inventarioLibro = FicheroLibro.cargar();
    }

    public static void agregarLibro(Libro l) {
        //equals() y hashCode() para verificar si ya existe por título
        boolean existe = false;
        for (Libro libro : inventarioLibro.values()) {
            if (libro.equals(l)) { //equals() compara por título
                existe = true;
                break;
            }
        }

        if (existe) {
            System.err.println("El libro ya existe");
        } else {
            inventarioLibro.put(l.getIsbn(), l); // key es el ISBN
        }
    }

    public static void actualizarLibro(String titulo, double precio, int cantidad) {
        Libro libroEncontrado = null;
        for (Libro libro : inventarioLibro.values()) {
            if (libro.getTitulo().equals(titulo)) {
                libroEncontrado = libro;
                break;
            }
        }

        if (libroEncontrado != null) {
            libroEncontrado.setPrecio(precio);
            libroEncontrado.setCantidadInventario(cantidad);
        } else {
            System.err.println("El libro no existe");
        }
    }

    public static void eliminarLibro(String titulo) {
        String isbnAEliminar = null;
        for (Libro libro : inventarioLibro.values()) {
            if (libro.getTitulo().equals(titulo)) {
                isbnAEliminar = libro.getIsbn();
                break;
            }
        }

        if (isbnAEliminar != null) {
            inventarioLibro.remove(isbnAEliminar);
        } else {
            System.err.println("El libro no existe");
        }
    }

    public static void salir() throws IOException {
        guardar();
        System.out.println("Programa finalizado");
    }

    public static HashMap<String, Libro> getInventario() {
        return inventarioLibro;
    }
}
