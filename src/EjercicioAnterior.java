import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

public class EjercicioAnterior {
    public static void main(String[] args) {
        //9.Sorpréndenos creando un programa de tu elección que utilice InputStream, PrintStream, excepciones, un HashMap y un ArrayList, LinkedList o array.
        /*Registro de notas por parte de un profesor. El profesor tiene las notas de sus 5 alumnos en un archivo notas.txt (registradas en un formato específico)
         y quiere almacenar sus promedios en su "base de datos" (Base_de_Datos.txt), sin importar el orden*/
        String archivoEntrada = "notas.txt";
        String baseDeDatos = "Base_de_Datos.txt";

        HashMap<String, ArrayList<Integer>> AlumnosConNotas = new HashMap<>(); //Alumno, notas
        String Alumno = ".";
        ArrayList<Integer> notasPorAlumno = new ArrayList<>();
        int posicion = 1;

        //Llenando el HashMap
        while (Alumno != ""){
            Alumno = leerNombreDeAlumno(archivoEntrada, posicion);
            notasPorAlumno = leerNotasDeAlumno(archivoEntrada, posicion);

            AlumnosConNotas.put(Alumno, notasPorAlumno);
            //System.out.println(AlumnosConNotas);
            posicion++;
            Alumno = leerNombreDeAlumno(archivoEntrada,posicion);
        }

        //Colocando los valores en la "base de datos"
        try {
            PrintStream print = new PrintStream(baseDeDatos);

            for (String alumno:AlumnosConNotas.keySet()) {
                double promedio = calcularPromedio(AlumnosConNotas.get(alumno));
                print.println(alumno + ": " + promedio);
                //System.out.println(alumno + ": " + promedio);
            }
            print.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static double calcularPromedio(ArrayList<Integer> notas) {
        double promedio = 0;
        for (Integer nota:notas) {
            promedio += nota;
        }
        promedio = promedio/notas.size();
        return promedio;
    }

    private static ArrayList<Integer> leerNotasDeAlumno(String archivoEntrada,int posicion) {
        ArrayList<Integer> arregloNotas = new ArrayList<>();
        int contador = 0;
        try {
            InputStream archivo = EjercicioAnterior.class.getResourceAsStream(archivoEntrada);
            int dato = archivo.read();
            while (dato != -1){
                if (contador == posicion-1){
                    while ((char)dato != '\n'){
                        if ((char)dato != ':'){
                            dato = archivo.read();
                            continue;
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
        }
        return arregloNotas;
    }

    private static String leerNombreDeAlumno(String archivoEntrada, int posicion) {
        String nombreAlumnoLeido = "";
        int contador = 0;
        try {
            InputStream archivo = EjercicioAnterior.class.getResourceAsStream(archivoEntrada);
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
        }
        return nombreAlumnoLeido;
    }
}
