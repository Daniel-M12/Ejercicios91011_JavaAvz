package Transferencia;
import Modelo.Alumno;

import java.util.ArrayList;

public interface MedioDeTransferenciaDeDatos {
    ArrayList<Alumno> leerDatos(String nombreRecurso);

    void escribirDatos();
}
