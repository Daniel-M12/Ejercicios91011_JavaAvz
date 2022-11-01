# Ejercicios 9, 10 y 11 - Java Avanzado

## Enunciados
+ Aplica a uno de los proyectos anteriores un patrón de comportamiento de los vistos durante la sesión 9 y explica por qué has escogido ese patrón en concreto.
+ Aplica refactoring a cualquiera de los proyectos que se han ido tratando en el curso, recuerda hacerte valer de las técnicas vistas en clase incluidos los patrones de diseño.

## Planteamiento del ejercicio anterior seguido

Registro de notas por parte de un profesor. El profesor tiene las notas de sus 5 alumnos en un archivo notas.txt (registradas en un formato específico)
y quiere almacenar sus promedios en su "base de datos" (Base_de_Datos.txt), sin importar el orden.

## Pasos Seguidos para el ejercicio actual
+ Se transformó el código de la clase "EjercicioAnterior" ubicada en la carpeta "AntesDelRefactor" a un programa orientado a objetos.
+ Una vez con este paradigma, se aplicó el patrón de diseño *Strategy*, para que se puedan usar fuentes y destinos de datos diferentes.
+ Se creó la clase *'DatosEnBD'* para ser usada solo como ejemplo de la aplicación del patrón.
