/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */

package com.mycompany.proyectorencar;

import java.io.*;
import java.util.*;

public class ArchivoUtil {

    public static List<String> leerArchivo(String nombreArchivo) {
        List<String> lineas = new ArrayList<>();
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) return lineas;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) lineas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error leyendo archivo: " + e.getMessage());
        }
        return lineas;
    }

    public static void escribirArchivo(String nombreArchivo, List<String> lineas) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo))) {
            for (String linea : lineas) pw.println(linea);
        } catch (IOException e) {
            System.out.println("Error escribiendo archivo: " + e.getMessage());
        }
    }

    public static void agregarLinea(String nombreArchivo, String linea) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo, true))) {
            pw.println(linea);
        } catch (IOException e) {
            System.out.println("Error agregando línea: " + e.getMessage());
        }
    }

    public static String[] buscarVehiculo(String matricula) {
        List<String> lineas = leerArchivo("vehiculos.txt");
        for (String linea : lineas) {
            String[] partes = linea.split(";");
            if (partes[0].equalsIgnoreCase(matricula)) return partes;
        }
        return null;
    }

    public static String[] buscarCliente(String cedula) {
        List<String> lineas = leerArchivo("clientes.txt");
        for (String linea : lineas) {
            String[] partes = linea.split(";");
            if (partes[0].equalsIgnoreCase(cedula)) return partes;
        }
        return null;
    }

    public static String[] buscarOferta(String idOferta) {
        List<String> lineas = leerArchivo("Oferta.txt");
        for (String linea : lineas) {
            String[] partes = linea.split(";");
            if (partes[0].equalsIgnoreCase(idOferta)) return partes;
        }
        return null;
    }

    public static String[] buscarGama(String idGama) {
        List<String> lineas = leerArchivo("gamas.txt");
        for (String linea : lineas) {
            String[] partes = linea.split(";");
            if (partes[0].equalsIgnoreCase(idGama)) return partes;
        }
        return null;
    }
    
    
public static String[] buscarRecepcion(String idRecepcion) {
    List<String> lineas = leerArchivo("recepciones.txt");
    for (String linea : lineas) {
        String[] partes = linea.split(";");
        if (partes[0].equalsIgnoreCase(idRecepcion)) return partes;
    }
    return null;
}

public static String[] buscarReservaPorMatricula(String matricula) {
    List<String> lineas = leerArchivo("reservas.txt");
    for (String linea : lineas) {
        String[] partes = linea.split(";");
        if (partes[0].equalsIgnoreCase(matricula)) return partes;
    }
    return null;
}

}


