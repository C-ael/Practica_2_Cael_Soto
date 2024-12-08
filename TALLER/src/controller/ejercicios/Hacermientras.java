package controller.ejercicios;

//Por Cael Soto

public class Hacermientras {
    public static void main(String[] args) {
        boolean bandera = false;
        int intentos = 1; //de este valor inician los intentos

        //Bucle HACER...MIENTRAS
        do {
            System.out.println("Intento número: " + (intentos)); //mostrar el número de intento por interacción

            if (intentos == 6) { //cambiar la bandera después de x intentos que se quiera poner, en este caso 3
                bandera = true;
            }

            intentos++; //incrementar el contador por interacción

        } while (!bandera); //continuar mientras la bandera sea falsa
    }
}