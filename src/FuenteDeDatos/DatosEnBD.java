package FuenteDeDatos;
import Modelo.Alumno;
import Transferencia.MedioDeTransferenciaDeDatos;

import java.util.ArrayList;

public class DatosEnBD implements MedioDeTransferenciaDeDatos {

    @Override
    public ArrayList<Alumno> leerDatos(String nombreRecurso) {
        return null;
    }

    @Override
    public void escribirDatos() {

    }
}
