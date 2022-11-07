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

        System.out.println(leerCadenaEnLineaEnArchivoEntre(':','\n',"notas.txt",1));

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
            transferenciaDeDatos = new DatosEnFichero();
        } else {
        /*
        Gracias al patrón Strategy, sólo hace falta cambiar la nueva clase a generar,
        de DatosEnFichero() a DatosEnBD() para que el programa utilice una fuente de datos distinta:
         */
            transferenciaDeDatos = new DatosEnBD();
        }

        ArrayList<Alumno> listaAlumnos = transferenciaDeDatos.leerDatos(archivoEntrada);
        registro.setListaAlumnos(listaAlumnos);

        registro.calcularPromedios();

        transferenciaDeDatos.escribirDatos();

        System.out.printf(registro.getListaAlumnos().get(1).getNombre() + ": "
                + registro.getListaAlumnos().get(1).getNotas() + " -> "
                + registro.getListaAlumnos().get(1).getPromedio());

    }

    private static String leerCadenaEnLineaEnArchivoEntre(char inicio, char fin, String nombreArchivoALeer,int linea){
        String cadenaLeida = "";
        int contador = 0;

        try {
            InputStream archivo = DatosEnFichero.class.getResourceAsStream(nombreArchivoALeer);
            int caracter = archivo.read();

            while (caracter != -1){
                if (contador == linea-1){
                    while ((char) caracter != '\n') { //Busca el siguiente salto de línea para encontrar la fila a leer, aumentando el contador
                        if ((char) caracter == inicio) { //Busca el carácter inicial de lectura en la línea
                            cadenaLeida += leerCadenaEnLineaEnArchivoHasta(fin,archivo,linea);
                            caracter = archivo.read();
                            break;
                        } else {
                            caracter = archivo.read();
                        }
                    }
                    break;
                } else if (contador>linea-1){
                    break;
                }
                if ((char)caracter == '\n'){
                    contador++;
                }
                caracter = archivo.read();
            }

        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch(IOException e){
            System.out.println(e.getMessage());
        }

        return cadenaLeida;
    }

    private static String leerCadenaEnLineaEnArchivoHasta(char fin, InputStream archivoALeer,int linea){
        String cadenaLeida = "";
        int contador = 0;

        try {
            int caracter = archivoALeer.read();

            while (caracter != -1){
                if (contador == linea-1){
                    while ((char) caracter != '\n') { //Busca el siguiente salto de línea para encontrar la fila a leer, aumentando el contador
                        while ((char) caracter != fin && caracter != -1) { //Llena la cadena a leer hasta encontrar el último carácter necesario
                            cadenaLeida += caracter;
                            caracter = archivoALeer.read();
                        }
                        break;
                    }
                    break;
                } else if (contador>linea-1){
                    break;
                }
                if ((char)caracter == '\n'){
                    contador++;
                }
                caracter = archivoALeer.read();
            }

        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch(IOException e){
            System.out.println(e.getMessage());
        }

        return cadenaLeida;
    }
}