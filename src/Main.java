import FuenteDeDatos.DatosEnBD;
import FuenteDeDatos.DatosEnFichero;
import Modelo.Alumno;
import Modelo.RegistroAlumnos;
import Transferencia.MedioDeTransferenciaDeDatos;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String archivoEntrada = "notas.txt";
        RegistroAlumnos registro = new RegistroAlumnos();

        MedioDeTransferenciaDeDatos transferenciaDeDatos = new DatosEnFichero();
        /*
        Gracias al patrón Strategy, sólo hace falta cambiar la nueva clase a generar,
        de DatosEnFichero() a DatosEnBD() para que el programa utilice una fuente de datos distinta:
        MedioDeTransferenciaDeDatos transferenciaDeDatos = new DatosEnBD();
         */

        ArrayList<Alumno> listaAlumnos = transferenciaDeDatos.leerDatos(archivoEntrada);
        registro.setListaAlumnos(listaAlumnos);

        registro.calcularPromedios();

        transferenciaDeDatos.escribirDatos();

        System.out.printf(registro.getListaAlumnos().get(1).getNombre() + ": "
                + registro.getListaAlumnos().get(1).getNotas() + " -> "
                + registro.getListaAlumnos().get(1).getPromedio());

    }
}