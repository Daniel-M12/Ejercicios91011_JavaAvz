package Modelo;

import java.util.ArrayList;

public class RegistroAlumnos {
    private ArrayList<Alumno> listaAlumnos;

    public RegistroAlumnos() {
        listaAlumnos = new ArrayList<>();
    }

    public ArrayList<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public void setListaAlumnos(ArrayList<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    public void calcularPromedios() {

        for (Alumno alumno:listaAlumnos) {
            double promedio = alumno.getPromedio();
            for (Integer nota: alumno.getNotas()) {
                promedio += nota;
            }
            alumno.setPromedio(promedio/alumno.getNotas().size());
        }
    }
}
