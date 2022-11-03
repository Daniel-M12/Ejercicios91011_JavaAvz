package Modelo;

import java.util.ArrayList;

/**
 * Clase que modela a un <i>Alumno</i>. Tiene como parámetros:
 * - Nombre del alumno. Registrado por el profesor
 * - Notas del alumno. Registrado por el profesor
 * - Promedio de notas del alumno. Calculado en la clase <i>RegistroAlumnos</i>.
 */
public class Alumno {
    private String nombre;
    private ArrayList<Integer> notas;
    private double promedio;

    public Alumno(String nombre) {
        this.nombre = nombre;
        notas = new ArrayList<>();
    }

    public Alumno(String nombre, ArrayList<Integer> notas){
        this.nombre = nombre;
        this.notas = notas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Integer> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Integer> notas) {
        this.notas = notas;
    }

    public void agregarNota(Integer nota){
        notas.add(nota);
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }
}
