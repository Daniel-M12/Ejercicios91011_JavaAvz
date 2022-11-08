import Argumentos.OpcionesParser;
import FuenteDeDatos.DatosEnBD;
import FuenteDeDatos.DatosEnFichero;
import Modelo.Alumno;
import Modelo.RegistroAlumnos;
import Transferencia.MedioDeTransferenciaDeDatos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Registrando nueva opción para el uso de argumentos mediante la clase Opciones
        OpcionesParser parserOpciones = new OpcionesParser(args);

        parserOpciones.registrarOpcion("file");
        parserOpciones.parsear();

        //Obteniendo el valor desde el HashMap con clave file, registrado con el método .parsear()
        boolean desdeFichero = Boolean.parseBoolean(parserOpciones.getOption("file"));

        //Usando el argumento que se pasó a la aplicación para seleccionar si los datos se obtendrán desde fichero o no:
        String archivoEntrada = "notas.txt";
        RegistroAlumnos registro = new RegistroAlumnos();
        MedioDeTransferenciaDeDatos transferenciaDeDatos;

        if (desdeFichero){
            transferenciaDeDatos = new DatosEnFichero(archivoEntrada);
        } else {
        /*
        Gracias al patrón Strategy, sólo hace falta cambiar la nueva clase a generar,
        de DatosEnFichero() a DatosEnBD() para que el programa utilice una fuente de datos distinta:
         */
            transferenciaDeDatos = new DatosEnBD("BD");
        }

        ArrayList<Alumno> listaAlumnos = transferenciaDeDatos.leerDatos();
        registro.setListaAlumnos(listaAlumnos);

        registro.calcularPromedios();

        transferenciaDeDatos.escribirDatos();

        System.out.printf(registro.getListaAlumnos().get(5).getNombre() + ": "
                + registro.getListaAlumnos().get(5).getNotas() + " -> "
                + registro.getListaAlumnos().get(5).getPromedio());

    }
}