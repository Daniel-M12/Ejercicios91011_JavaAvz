package FuenteDeDatos;
import Modelo.Alumno;
import Transferencia.MedioDeTransferenciaDeDatos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

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
    private String almacenamientoDeDatos = "Base_de_Datos.txt";
    private ArrayList<Alumno> listaAlumnos = new ArrayList();

    /**
     * Método que permite obtener los nombres y notas de los alumnos registrados en el fichero pasado como parámetro.
     *
     * @param nombreRecurso: Nombre del fichero del que se extraerán los datos.
     * @return array de alumnos leídos.
     */
    @Override
    public ArrayList<Alumno> leerDatos(String nombreRecurso) {
        int posicion = 1;
        Alumno alumno = new Alumno(".");

        while (alumno.getNombre() != ""){
            alumno.setNombre(leerNombreDeAlumno(nombreRecurso, posicion));
            alumno.setNotas(leerNotasDeAlumno(nombreRecurso, posicion));

            listaAlumnos.add(alumno);

            posicion++;
            alumno = new Alumno(leerNombreDeAlumno(nombreRecurso,posicion));
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
        ArrayList<Integer> arregloNotas = new ArrayList<>();
        int contador = 0;
        try {
            InputStream archivo = DatosEnFichero.class.getResourceAsStream(archivoEntrada);
            int dato = archivo.read();
            while (dato != -1){
                if (contador == posicion-1){
                    while ((char)dato != '\n'){
                        if ((char)dato != ':'){
                            dato = archivo.read();
                        }else{
                            while ((char)dato != '\n' && dato != -1){
                                String nota = "";
                                for (int i = 0; i < 2; i++) {
                                    dato = archivo.read();
                                }
                                for (int i = 0; i < 2; i++) {
                                    nota += (char) dato;
                                    dato = archivo.read();
                                }
                                arregloNotas.add(Integer.parseInt(nota));
                            }
                            break;
                        }
                    }
                    break;
                } else if (contador>posicion-1){
                    break;
                }
                if ((char)dato == '\n'){
                    contador++;
                }
                dato = archivo.read();
            }
            archivo.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println("Ocurrió un error al leer las notas del alumno: " + e.getMessage());
        }
        return arregloNotas;
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
        int contador = 0;
        try {
            InputStream archivo = DatosEnFichero.class.getResourceAsStream(archivoEntrada);
            int dato = archivo.read();
            while (dato != -1){
                if (contador == posicion-1){
                    while ((char)dato != ':'){
                        nombreAlumnoLeido += (char)dato;
                        dato = archivo.read();
                    }
                    break;
                } else if (contador>posicion-1){
                    break;
                }
                if ((char)dato == '\n'){
                    contador++;
                }
                dato = archivo.read();
            }
            archivo.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println("Ocurrió un error al leer nombre de alumno: " + e.getMessage());
        }
        return nombreAlumnoLeido;
    }
}
