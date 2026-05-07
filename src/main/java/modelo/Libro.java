/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.Objects;

public class Libro {

    private String isbn;
    private String titulo;
    private ArrayList<String> autores;
    private double precio;
    private int cantidadInventario;

    public Libro(String isbn, String titulo, ArrayList<String> autores, double precio, int cantidadInventario) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autores = autores;
        this.precio = precio;
        this.cantidadInventario = cantidadInventario;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<String> getAutores() {
        return autores;
    }

    public void setAutores(ArrayList<String> autores) {
        this.autores = autores;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadInventario() {
        return cantidadInventario;
    }

    public void setCantidadInventario(int cantidadInventario) {
        this.cantidadInventario = cantidadInventario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.titulo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Libro other = (Libro) obj;
        return Objects.equals(this.titulo, other.titulo);
    }

    @Override
    public String toString() {
        String autoresStr = String.join(",", autores); // autor1,autor2,autor3
        return isbn + ";" + titulo + ";" + autoresStr + ";" + precio + ";" + cantidadInventario;
    }
}
