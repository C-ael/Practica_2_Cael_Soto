package controller.ejercicios;

import controller.util.Utilidades; //importar la clase Utilidades para usar el método de validación (opcional)

import java.util.Scanner;

//Por Cael Soto

public class Llamadas { //creamos la clase principal Llamadas

    public enum Zona { //declaramos un enum llamado Zona para representar las zonas geográficas por clave y por precio
        AMERICA_DEL_NORTE(12, 2.75f), //zona: américa del norte con clave 12 y precio por minuto 2.75
        AMERICA_CENTRAL(15, 1.89f), //zona: américa central con clave 15 y precio por minuto 1.89
        AMERICA_DEL_SUR(18, 1.60f), //zona: américa del sur con clave 18 y precio por minuto 1.60
        EUROPA(19, 3.5f), //zona: europa con clave 19 y precio por minuto 3.50
        ASIA(23, 4.5f), //zona: asia con clave 23 y precio por minuto 4.50
        AFRICA(25, 3.1f), //zona: áfrica con clave 25 y precio por minuto 3.10
        OCEANIA(29, 3.0f), //zona: oceanía con clave 29 y precio por minuto 3.00
        RESTO_DEL_MUNDO(31, 6.0f); //zona: resto del mundo con clave 31 y precio por minuto 6.00

        //Las constantes del enum como AMERICA_DEL_NORTE, EUROPA, etc., tienen asociados dos valores: clave, que es el identificador numérico de
        //la zona, y precioPorMinuto que es el costo por minuto para llamar a esa zona.

        int clave; //definir una variable para almacenar la clave de la zona
        float precioPorMinuto; //definir una variable para almacenar el precio por minuto de la zona

        //this.clave y this.precioPorMinuto son las variables de instancia del enum, o sea, hace referencia a la instancia actual del enum
        Zona(int clave, float precioPorMinuto) { //crear un constructor del enum que inicializa clave y precioPorMinuto
            this.clave = clave; //asigna el valor de clave al atributo de la zona
            this.precioPorMinuto = precioPorMinuto; //asigna el valor del precio por minuto al atributo de la zona
        }
    }

    public void ejecutar() { //método principal que ejecutará el main
        Scanner scanner = new Scanner(System.in); //se llama al Scanner para leer la entrada del usuario
        System.out.println("\nCosto de llamadas telefónicas Internacionales:");

        Zona[] zonas = Zona.values(); //ahora se debe convertir el enum Zona en un arreglo para poder mostrar la lista enum
        System.out.println("A continuación, se mostraran las zonas geograficas disponibles:"); //mostrar las zonas disponibles
        for (int i = 0; i < zonas.length; i++) { //bucle for para poder interactuar sobre las zonas, zonas.length nos da el número total de zonas
            //definidas en el enum
            Zona zona = zonas[i]; //obtenemos la zona actual en cada iteración, que corresponde a una constante del enum Zona

            //se muestra la información de cada zona llamando a cada elemento del enum, zona.name: el nombre: zona.clave al zona y zona.preciopor
            //minuto al precio por minuto de dicha zona
            System.out.println(zona.name() + " - Clave: " + zona.clave + " - Precio por minuto: $" + zona.precioPorMinuto);
        }

        System.out.println("\nIngrese la clave de la zona geografica: "); //solicita al usuario la clave de la zona
        int claveZona = scanner.nextInt(); //lee la clave ingresada por el usuario

        if (!Utilidades.validarClaveZona(claveZona)) { //verifica si la clave ingresada es válida usando el método de Utilidades
            System.out.println("Querido usuario, la clave de zona es invalida"); //muestra un mensaje si la clave es inválida
            return; //termina la ejecución del método si la clave es inválida
        }

        System.out.println("Ingrese el numero de minutos de la llamada: "); //solicita al usuario los minutos de la llamada
        int minutos = 0; //creamos la variable minutos

        //validamos que el número de minutos ingresado sea un valor entero positivo con .hasNextInt para saber si lo que el usuario ingresa es
        //un número entero
        if(scanner.hasNextInt()) {
            minutos = scanner.nextInt(); //si se valida, entonces el usuario asigna el valor ingresado a minutos

        } else {
            System.out.println("Querido usuario, por favor ingrese un numero entero para los minutos"); //si no se ingresó un número válido
            //mostramos error
            return; //termina la ejecución del método si no es un número entero
        }
        
        if(minutos <= 0) { //opcionalmente también crearemos un verificador por si los minutos son negativos o cero
            System.out.println("Querido usuario, el numero de minutos debe ser mayor que cero");
            return; //termina la ejecución si los minutos no son válidos
        }

        float precioMinuto = 0.0f;

        switch (claveZona) { //usa un switch para determinar el precio por minuto según la clave de la zona
            case 12: //si la clave es 12
                precioMinuto = 2.75f; //asigna el precio de américa del norte
                break; //finaliza este caso
            case 15: //si la clave es 15
                precioMinuto = 1.89f; //asigna el precio de américa central
                break; //finaliza este caso
            case 18: //si la clave es 18
                precioMinuto = 1.60f; //asigna el precio de américa del sur
                break; //finaliza este caso
            case 19: //si la clave es 19
                precioMinuto = 3.5f; //asigna el precio de europa
                break; //finaliza este caso
            case 23: //si la clave es 23
                precioMinuto = 4.5f; //asigna el precio de asia
                break; //finaliza este caso
            case 25: //si la clave es 25
                precioMinuto = 3.1f; //asigna el precio de áfrica
                break; //finaliza este caso
            case 29: //si la clave es 29
                precioMinuto = 3.0f; //asigna el precio de oceanía
                break; //finaliza este caso
            case 31: //si la clave es 31
                precioMinuto = 6.0f; //asigna el precio del resto del mundo
                break; //finaliza este caso
        }

        float costoTotal = precioMinuto * minutos; //calcula el costo total de la llamada multiplicando el precio por minuto por los minutos
        System.out.println("El costo de la llamada es: $" + costoTotal); //muestra el costo total de la llamada al usuario
    }
}