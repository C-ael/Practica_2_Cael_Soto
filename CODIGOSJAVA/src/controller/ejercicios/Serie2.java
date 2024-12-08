package controller.ejercicios;

import java.util.Scanner;

//Por Cael Soto
//Serie: (1 / 1)^2 + (1 / 3)^4 - (2 / 5)^6 - (3 / 7)^8 + (5 / 9)^10 + (8 / 11)^12 …

public class Serie2 {

    //método para generar el número Fibonacci
    private int generar_fibonacci(int indice) {
        if (indice == 1 || indice == 2) return 1; //los dos primeros números de Fibonacci son 1 segun la cadena
        int a = 1, b = 1;
        for (int i = 2; i < indice; i++) {
            int aux = a;
            a = b;
            b = aux + b;
        }
        return b; //retorna el Fibonacci en el índice dado
    }

    //método para generar los números impares
    private int generar_impar(int indice) {
        return 2 * indice - 1; //genera números impares 1, 3, 5, 7, ... etc
    }

    //método principal que ejecuta el cálculo de la serie
    public void ejecutar() {
        Scanner sc = new Scanner(System.in); // Scanner para leer la entrada
        System.out.println("\nPrograma para generar la serie de tipo: (1 / 1)^2 + (1 / 3)^4 - (2 / 5)^6 - (3 / 7)^8 + (5 / 9)^10 + (8 / 11)^12 …");
        System.out.print("Ingrese el número de términos que desea generar: ");
        String numero = sc.nextLine();

        //se debe convertir la entrada a entero usando un método de validación y transformación
        int num_serie = Integer.parseInt(numero);

        //if general que verifica si el número de términos es válido
        if (num_serie > 0) {
            String serie = "S = "; //es lo que inicia la cadena de la serie
            double suma = 0.0; //variable que se utilizará para la suma total de la serie
            int signo = 1; //variale que determina el signo, ademas, el signo de inicio es positivo
            int potencia = 2; //la potencia de la secuencia comienza en 2
            int cont = 0; //contador para alternar el signo

            //bucle para generar los términos de la serie
            for (int i = 1; i <= num_serie; i++) {
                int fibonacci = generar_fibonacci(i); // Obtener el número Fibonacci correspondiente
                int impar = generar_impar(i); // Obtener el número impar correspondiente
                double fraccion = (double) fibonacci / (double) impar; //el numerador de la fracción debe ser el Fibonacci correspondiente
                double fraccion_potenciada = Math.pow(fraccion, potencia); //elevar la fracción a la potencia

                //realizar la secuencia correspondiente segun el caso
                if (signo == 1) {
                    serie += "+ (" + fibonacci + "/" + impar + ")^" + potencia + " ";
                    suma += fraccion_potenciada;
                } else {
                    serie += "- (" + fibonacci + "/" + impar + ")^" + potencia + " ";
                    suma -= fraccion_potenciada;
                }

                cont++; //incrementar el contador en cada interacción
                potencia += 2; //incrementar la potencia por 2 en cada iteración

                //cuendo el contador llegue a 2, se debe alternar el signo y reiniciar el contador para repetir el proceso
                if (cont == 2) {
                    signo = -signo; //alternar el signo
                    cont = 0; //reiniciar el contador
                }
            }

            //mostrar la serie y el resultado final
            System.out.println("La serie es: " + serie);
            System.out.println("El resultado es: " + suma);
        } else {
            System.out.println("Ingrese un número mayor a 0."); //en caso de que el usuario ponga un numero negativo o 0 a la cadena
        }
    }
}