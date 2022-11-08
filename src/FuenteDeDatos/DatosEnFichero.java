package FuenteDeDatos;
import Modelo.Alumno;
import Transferencia.MedioDeTransferenciaDeDatos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Esta clase permite manipular ficheros para usarlos como fuentes de los datos.
 * Implementa la interfaz <i>MedioDeTransferenciaDeDatos</i>, la cual le permite actuar acorde al patrón Strategy.
 *
 * Cuenta con 2 métodos públicos: <i>leerDatos</i> y <i>escbribirDatos</i>.
 * El método <i>leerDatos</i> hace uso de 2 métodos privados para cumplir su objetivo.
 *
 * - Su atributo <i>almacenamientoDeDatos</i> guarda el nombre del fichero que se creará con el método <i>escribirDatos</i>.
 * - Su atributo <i>listaAlumnos</i> almacena los alumnos leídos del fichero de origen.
 */
public class DatosEnFichero implements MedioDeTransferenciaDeDatos {
    private String fuenteDeDatos;
    private String almacenamientoDeDatos = "Base_de_Datos.txt";
    private ArrayList<Alumno> listaAlumnos = new ArrayList();
    private static int lineaActual = 1;

    public DatosEnFichero(String fuenteDeDatos) {
        this.fuenteDeDatos = fuenteDeDatos;
    }


    /**
     * Método que permite obtener los nombres y notas de los alumnos registrados en el fichero pasado como parámetro.
     *
     * @return array de alumnos leídos.
     */
    @Override
    public ArrayList<Alumno> leerDatos() {
        int posicion = 1;
        Alumno alumno = new Alumno(".");

        while (alumno.getNombre() != ""){
            alumno.setNombre(leerNombreDeAlumno(fuenteDeDatos, posicion));
            alumno.setNotas(leerNotasDeAlumno(fuenteDeDatos, posicion));

            listaAlumnos.add(alumno);

            posicion++;
            alumno = new Alumno(leerNombreDeAlumno(fuenteDeDatos,posicion));
        }
        return listaAlumnos;
    }

    /**
     * Método que permite guardar los datos de los alumnos (nombres y promedios),
     * guardados en su atributo <i>listaAlumnos</i>, en un nuevo fichero.
     */
    @Override
    public void escribirDatos() {

        try {
            PrintStream print = new PrintStream(almacenamientoDeDatos);
            for (Alumno alumno:listaAlumnos) {
                print.println(alumno.getNombre() + ": "
                        + alumno.getNotas() + " -> "
                        + alumno.getPromedio());
            }
            print.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método privado llamado por el método <i>leerDatos</i>.
     *
     * @param archivoEntrada: Fichero de origen de los datos
     * @param posicion: línea en la que se encuentra el alumno a leer. Esta se va modificando mediante un bucle en el método <i>leerDatos</i>.
     * @return notas leídas del alumno en la línea indicada en el parámetro <i>posicion</i>.
     */
    private static ArrayList<Integer> leerNotasDeAlumno(String archivoEntrada, int posicion) {
        String notasJuntas = "";

        try {
            InputStream archivo = DatosEnFichero.class.getResourceAsStream(archivoEntrada);

            notasJuntas = leerCadenaEnLineaEnArchivoEntre(':','\n', archivo, posicion);

            archivo.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println("Ocurrió un error al leer las notas del alumno: " + e.getMessage());
        }

        String[] arregloNotas = notasJuntas.replace(" ","").split(",");
        ArrayList<Integer> arregloNotasInteger = new ArrayList<>();

        for (String nota:arregloNotas) {
            arregloNotasInteger.add(Integer.parseInt(nota));
        }

        lineaActual = 1;
        return arregloNotasInteger;
    }

    /**
     * Método privado llamado por el método <i>leerDatos</i>.
     *
     * @param archivoEntrada: Fichero de origen de los datos
     * @param posicion: línea en la que se encuentra el alumno a leer. Esta se va modificando mediante un bucle en el método <i>leerDatos</i>.
     * @return nombre del alumno en la línea indicada en el parámetro <i>posicion</i>.
     */
    private static String leerNombreDeAlumno(String archivoEntrada, int posicion) {
        String nombreAlumnoLeido = "";

        try {
            InputStream archivo = DatosEnFichero.class.getResourceAsStream(archivoEntrada);

            nombreAlumnoLeido = leerCadenaEnLineaEnArchivoHasta(':', archivo, posicion);

            archivo.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println("Ocurrió un error al leer nombre de alumno: " + e.getMessage());
        }

        lineaActual = 1;
        return nombreAlumnoLeido;
    }

    /**
     * Método interno que permite a los otros métodos leer una cadena de texto en una línea específica del fichero,
     * desde un carpacter inicial hasta un carácter final.
     * Hace uso del método leerCadenaEnLineaEnArchivoHasta para completar la lectura.
     *
     * @param inicio Carácter inicial de lectura. El método lee desde el carácter siguiente.
     * @param fin Carácter final de lectura. El método lee hasta una posición anterior a este.
     * @param archivo Fichero a leer.
     * @param linea Línea del fichero que se leerá. El salto Debe haber un salto de línea '\n' para avanzar a la siguiente.
     * @return Devuelve la cadena leída.
     */
    private static String leerCadenaEnLineaEnArchivoEntre(char inicio, char fin, InputStream archivo, int linea){
        String cadenaLeida = "";
        int contador = 0;

        try {
            int caracter = archivo.read();

            avanzarLinea(archivo, linea);

            while ((char) caracter != inicio) {
                caracter = archivo.read();
            }
            cadenaLeida += leerCadenaEnLineaEnArchivoHasta(fin,archivo,linea);

        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch(IOException e){
            System.out.println(e.getMessage());
        }

        lineaActual = 1;
        return cadenaLeida;
    }

    /**
     * Método interno que permite a los otros métodos leer una cadena de texto en una línea específica del fichero,
     * hasta un carácter específico.
     *
     * @param fin Carácter final de lectura.
     * @param archivo Fichero a leer.
     * @param linea Línea en la que se hará la lectura.
     * @return Devuelve la cadena leída.
     */
    private static String leerCadenaEnLineaEnArchivoHasta(char fin, InputStream archivo,int linea){
        String cadenaLeida = "";

        try {
            avanzarLinea(archivo, linea);
            int caracter = archivo.read();

            while ((char) caracter != fin && caracter != -1) { //Llena la cadena a leer hasta encontrar el último carácter necesario
                cadenaLeida += (char)caracter;
                caracter = archivo.read();
            }

        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch(IOException e){
            System.out.println(e.getMessage());
        }

        return cadenaLeida;
    }

    /**
     * Método interno que permite encontrar una determinada dentro de un fichero, avanzando el "cursor"
     * de lectura hasta esta.
     * Hace uso del atributo lineaActual de la clase para saber en qué línea se encuentra la lectura del fichero actualmente.
     *
     * @param archivo Fichero en el cual se avanza la línea.
     * @param linea Linea buscada.
     */
    private static void avanzarLinea(InputStream archivo, int linea){
        if (linea<=0 || lineaActual == linea){
            return;
        }

        try {
            while (!(lineaActual == linea)){
                char caracterActual = (char)archivo.read();
                if (caracterActual == '\n' || caracterActual == '\uFFFF') {
                    lineaActual++;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
