package Modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

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
            Stream<Integer> notas = alumno.getNotas().stream();

            Integer suma = notas.reduce(0, (x,y) -> x + y);

            alumno.setPromedio((double)suma/alumno.getNotas().size());
        }
    }
}
