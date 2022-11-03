package Transferencia;
import Modelo.Alumno;

import java.util.ArrayList;

/**
 * Interface que permite implementar el patrón <i>Strategy</i>, para poder usar diferentes fuentes de datos
 * de las que obtener los nombres y las notas de los alumnos.
 */
public interface MedioDeTransferenciaDeDatos {
    ArrayList<Alumno> leerDatos(String nombreRecurso);

    void escribirDatos();
}
