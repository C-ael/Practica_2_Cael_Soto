package controller.util;

import java.util.Scanner;

// por cael soto

public class Utilidades {

    /* Ahora crearemos un método global para las respuestas char (s/n) ya que estas son las que más usé, este método permite validar las
    respuestas del usuario para opciones tipo 's' o 'n'. el objetivo es asegurarse de que el usuario solo ingrese esas dos opciones y,
    si ingresa algo incorrecto, el programa le pedirá la respuesta nuevamente. */
    
    public static char obtenerRespuesta(Scanner sc, String mensaje) {
        char respuesta; // variable para almacenar la respuesta del usuario
        do {
            System.out.print(mensaje); // mostrar el mensaje solicitando una respuesta
            respuesta = sc.next().toLowerCase().charAt(0); // leer la primera letra de la entrada y convertirla a minúscula
            // verificar que la respuesta sea válida (s o n)
            if (respuesta != 's' && respuesta != 'n') {
                System.out.println("respuesta incorrecta. Querido usuario por favor ingrese 's' para si o 'n' para no."); // mensaje de error
            }
        } while (respuesta != 's' && respuesta != 'n'); // repetir hasta que la respuesta sea válida
        sc.nextLine(); // consumir el salto de línea pendiente después de la entrada
        return respuesta; // retornar la respuesta válida
    }

    // opcional: método para validar que la clave de zona esté en el rango permitido
    // en caso de no usarlo, se debe utilizar lo que sería el validador del programa por defecto
    
    public static boolean validarClaveZona(int clave) {
        // claves válidas de zona:
        return clave == 12 || clave == 15 || clave == 18 || clave == 19 || clave == 23 || clave == 25 || clave == 29 || clave == 31;
    }
}