package FuenteDeDatos;
import Modelo.Alumno;
import Transferencia.MedioDeTransferenciaDeDatos;

import java.util.ArrayList;

public class DatosEnBD implements MedioDeTransferenciaDeDatos {
    //Simulando los datos en una BD
    private ArrayList<Alumno> listaAlumnos;

    public DatosEnBD() {
        listaAlumnos = new ArrayList<>();
    }

    @Override
    public ArrayList<Alumno> leerDatos(String nombreRecurso) {
        //Generando la "BD". Este paso no es parte del método, se realizó sólo por el ejemplo de BD ficticia
        generarBD();

        //Ejecutando el método leerDatos, "Leyendo" los datos de la BD
        System.out.println("Se leyeron los datos de la BD: " + nombreRecurso);

        return listaAlumnos;
    }

    @Override
    public void escribirDatos() {
        //Simulando el método de escribir en otra BD se mostrarán los datos en consola
        for (Alumno alumno:listaAlumnos) {
            System.out.println(alumno.getNombre() + ": "
                    + alumno.getNotas() + " -> "
                    + alumno.getPromedio());
        }
    }

    private void generarBD(){
        //Creando de 0 la "BD"
        ArrayList<Integer> notas1 = new ArrayList<>();
        notas1.add(15);
        notas1.add(18);
        notas1.add(20);
        notas1.add(14);
        listaAlumnos.add(new Alumno("Alumno 1", notas1));

        ArrayList<Integer> notas2 = new ArrayList<>();
        notas2.add(17);
        notas2.add(15);
        notas2.add(16);
        notas2.add(15);
        listaAlumnos.add(new Alumno("Alumno 2", notas2));

        ArrayList<Integer> notas3 = new ArrayList<>();
        notas3.add(14);
        notas3.add(15);
        notas3.add(14);
        notas3.add(16);
        listaAlumnos.add(new Alumno("Alumno 3", notas3));
    }
}
