/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GestionBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author yessm
 */
public class GestionBDD {

    private static String datosConexion = "jdbc:mysql://localhost:3306/";
    private static String baseDatos = "BDDLibros_Gestion";
    private static String usuario = "root";
    private static String password = "";
    private Connection con;

    public GestionBDD(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public GestionBDD() {
        try {
            con = DriverManager.getConnection(datosConexion, usuario, password);
            try {
                crearBDD();
                crearTabla();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Crear la base de datos
    private void crearBDD() throws SQLException {
        String query = "create database if not exists " + baseDatos + ";";
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.execute(query);
            con = DriverManager.getConnection(
                    datosConexion + baseDatos,
                    usuario, password
            );
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt.close();
        }
    }

    private void crearTabla() throws SQLException {
        String query = "create table if not exists Libros("
                + "isbn varchar(20) primary key,"
                + "titulo varchar(200) not null,"
                + "autores varchar(300),"
                + "precio double,"
                + "cantidadInventario int);";
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.execute(query);
            
        } catch (SQLException e) {
            
            e.printStackTrace();
            
        } finally {
            
            stmt.close();
        }
    }

    public void insertarDatosLibro(String isbn, String titulo, String autores, double precio, int cantidadInventario) throws SQLException {
        // "Libros", precio y cantidadInventario 
        String query = "insert into Libros (isbn, titulo, autores, precio, cantidadInventario) "
                + "values('" + isbn + "', '" + titulo + "', '" + autores + "', " + precio + ", " + cantidadInventario + ");";

        Statement stmt = null;

        try {
            stmt = con.createStatement();
            stmt.execute(query); // executeQuery() > execute() para INSERT
            
        } catch (SQLException e) {
            
            e.printStackTrace();
            
        } finally {
            
            stmt.close();
            
        }
    }
}
