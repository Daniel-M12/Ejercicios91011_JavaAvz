package Argumentos;

import java.util.HashMap;

public class OpcionesParser {
    private HashMap<String,String> opciones = new HashMap<>();
    private String[] argumentos;

    private OpcionesParser() {
    }

    public OpcionesParser(String[] argumentos) {
        this.argumentos = argumentos;
    }

    public void registrarOpcion(String opcion) {
        opciones.put(opcion, "");
    }

    public String getOption(String opcion) {
        return opciones.get(opcion);
    }

    public void setValue(String opcion, String valor) {
        opciones.replace(opcion, valor);
    }

    public void parsear(){
        for (int i = 0; i < argumentos.length; i++) {
            String nombreDeLaOpcion = argumentos[i].replace("--","");

            if (opciones.containsKey(nombreDeLaOpcion)){
                setValue(nombreDeLaOpcion,argumentos[i+1]);
                i++; //Se salta el valor del argumento en la siguiente iteración
            }
        }
    }
}
