# Ejercicios 9, 10 y 11 - Java Avanzado

## Enunciados
+ Aplica a uno de los proyectos anteriores un patrón de comportamiento de los vistos durante la sesión 9 y explica por qué has escogido ese patrón en concreto.
+ Aplica refactoring a cualquiera de los proyectos que se han ido tratando en el curso, recuerda hacerte valer de las técnicas vistas en clase incluidos los patrones de diseño.

## Planteamiento del ejercicio anterior seguido

Registro de notas por parte de un profesor. El profesor tiene las notas de sus 5 alumnos en un archivo notas.txt (registradas en un formato específico)
y quiere almacenar sus promedios en su "base de datos" (Base_de_Datos.txt), sin importar el orden.

## Pasos Seguidos para el funcionamiento actual
+ Se transformó el código de la clase "EjercicioAnterior" ubicada en la carpeta "AntesDelRefactor" a un programa orientado a objetos.
+ Una vez con este paradigma, se aplicó el patrón de diseño *Strategy*, para que se puedan usar fuentes y destinos de datos diferentes.
+ Se creó la clase *'DatosEnBD'* para ser usada solo como ejemplo de la aplicación del patrón.
+ Finalmente, se refactorizó el método de calcular promedio en la clase *'RegistroAlumnos'* usando programación funcional.

## Ejercicio 14
+ Se agregaron comentarios explicativos y de documentación apropiados.

## Ejercicios 19, 20 y 21
+ Se implementó el uso de *args* en la clase *Main*, para que el usuario pueda escoger si desea leer a través de fichero ("*--true*") o de BD ("*--false*"). La implementeación se realizó mediante la nueva clase *OpcionesParser*.
+ Se realizó una limpieza del código, reemplazando el código repetido en los métodos de la clase *DatosEnFichero* mediante la creación de 3 métodos privados nuevos:
  + *leerCadenaEnLineaEnArchivoEntre*
  + *leerCadenaEnLineaEnArchivoHasta*
  + *avanzarLinea*
+ #### Ejercicio 20
  + Los principales principios de la arquitectura limpia son los siguientes:
    + Independencia de frameworks: Se debería poder utilizar cualquier framework y a su vez ser reemplazable, para no estar limitados por sus restricciones.
    + Independiente de UI: Debido a que las interfases de usuario son muy propensas a ser modificadas constantemente, el programa debe ser independiente de ellas para no ser afectado cada vez que ocurra un cambio.
    + Independeiente de BD: Se debería poder escoger cualquier base de datos sin que por ello se tengan que hacer grandes modificaciones al sistema.
    + Independiente de entidades externas: Al igual que los anteriores puntos, no se debería depender de ninguna entidad externa, para no tener que estar cambiando el sistema por razones ajenas a sí mismo.
    + Testeable: Aplicando los anteriores principios, se debe poder testear las reglas de negocio independientemente de las implementaciones externas configuradas actualmente.
  + Este tipo de arquitectura es útil para ayudar al mantenimiento del programa cuando va escalando. El tener el código menos acoplado a agentes externos permitirá un más fácil y rápido mantenimiento del mismo.
  + Para hacer uso de estos principios se puede dividir la aplicación en diferentes componentes, con propósitos más específicos para cada uno, como:
    + Entidades: Abarcan las clases que contienen reglas del negocio críticas. Las cuales, debido a su desacoplamiento e independencia, pueden ser usadas por cualquier otro componente del sistema.
    + Casos de uso: Permiten la utilización de las entidades para poder hacer cumplir las reglas de negocio, conectándolas con las otras capas de la arquitectura, que a su vez deben estar desacopladas de estas.
    + Adaptadores de interfaz: Permiten a las casos de uso conectarse con los controladores y las vistas, para facilitar el desacoplamiento entre los mismos. Lo logran mediante la transformación de los datos recibidos y su adaptación antes de enviarlo a la otra capa.
    + Frameworks y drivers: Esta capa contiene todos los agentes externos como BDs, framework específicos, etc. Es la última capa, que es acoplada al sistema solo gracias a los adaptadores de interfaz. 