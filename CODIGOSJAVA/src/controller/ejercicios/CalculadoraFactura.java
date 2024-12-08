package controller.ejercicios;

import java.util.Scanner;

public class CalculadoraFactura {
    /*3. La empresa municipal de agua potable de Loja desea cobrar y calcular mensualmente el valor exacto de consumo del agua potable de
    cada medidor que pertenece a un contribuyente, de acuerdo a la ordenanza vigente, la cual establece la planilla de acuerdo a los
    siguientes rubros:
    A) SERVICIO DE AGUA POTABLE: Para obtener el rubro ríjase a la siguiente tabla
    B) IMPUESTO DE ALCANTARILLADO: 35% DEL VALOR DEL RUBRO DEL SERVICIO DE AGUA POTABLE.
    C) TASA POR RECOLECCIÓN DE BASURA: 0.75 DÓLARES;
    D) TASA POR COSTO DE PROCESAMIENTO DE DATOS: 0.50 DÓLARES
    
    La ordenanza también permite descuentos para los medidores que estén registrados al contribuyente de la tercera edad o posean algún
    tipo discapacidad, para lo cual sólo se los puede aplicar sobre el rubro del SERVICIO DE AGUA POTABLE, en base a las siguientes
    condiciones.
    (a) Si pertenece a la tercera edad y su consumo esta entre 0 hasta 15 m³ (rango base) existe un descuento del 50%, en caso de exceder
    sólo se realizará el descuento del 30% sobre el rango base.
    (b) Si pertenece a un discapacitado, se tomará como descuento su porcentaje de discapacidad sólo sobre el rango base.*/

    //Por Cael Soto

    public void ejecutar() {
        Scanner sc = new Scanner(System.in); //llamar al scanner para registrar via teclado lo necesario
        // Definir variables y constantes
        final double tasaBasura = 0.75;
        final double tasaProcesamiento = 0.50;
        int nroMedidores;
        double consumo;
        char respuesta;
        double costoAgua;
        double impAlcantarillado;
        double totalFactura;

        System.out.println("\nCalcular la factura de consumo de agua potable:"); //dar la bienvenida

        while (true) { //validamos número de medidores a través de un bucle while
            System.out.println("Ingrese el numero de medidores:");
            
            //a continuación se muestran 2 casos if, el principal que valida que sea un número entero, y el segundo que valida que sea
            //positivo
            if (sc.hasNextInt()) { //entero
                nroMedidores = sc.nextInt();

                if (nroMedidores > 0) { //positivo
                    break;  //salir del bucle, o sea continuar la ejecución del programa si el consumo es válido
                } else {
                    System.out.println("Querido usuario el consumo de agua debe ser un numero positivo. Vuelva a intentarlo");
                }

            } else {
                System.out.println("Error: Debe ingresar un numero entero. Vuelva a intentarlo");
                sc.next();
                //usamos esto para eliminar el valor incorrecto que quedó en el buffer para evitar errores en la siguiente entrada
            }
        }

        //bucle para calcular valores de cada medidor, usamos for porque ya sabemos el nro de medidores
        for (int i = 1; i <= nroMedidores; i++) {
            System.out.println("\nMedidor nro. " + i); //mostrar el medidor del cual se piden los datos

            while (true) { //validar consumo de agua y que se ingrese correctamente
                System.out.println("Ingrese el consumo de agua en m3 (debe ser un número positivo):");

                //al igual que la validación anterior del nro de medidores, usamos casos if para validar el nro de consumo (en double)
                //y que este numero sea positivo
                if (sc.hasNextDouble()) { //Nro en double
                    consumo = sc.nextDouble();

                    if (consumo > 0) { //positivo
                        break;  //salir del bucle, o sea continuar la ejecución del programa si el consumo es válido
                    } else {
                        System.out.println("Querido usuario el consumo de agua debe ser un numero positivo. Vuelva a intentarlo");
                    }

                } else {
                    System.out.println("Error: Debe ingresar un numero valido para el consumo. Vuelva a intentarlo");
                    sc.next(); 
                    //usamos esto para eliminar el valor incorrecto que quedó en el buffer para evitar errores en la siguiente entrada
                }
            }

            // Validar si el usuario tiene tercera edad o discapacidad a través de un bucle while
            while (true) {
                System.out.println("¿El usuario es de la tercera edad? (s/n):");
                respuesta = sc.next().trim().toLowerCase().charAt(0); //guardar respuesta como char

                if (respuesta == 's' || respuesta == 'n') { // validar que se escribió ya sea s o n
                    break;  //continuar con la ejecución si la respuesta es válida

                } else {
                    System.out.println("Queridisimo usuario, debe ingresar 's' para sí o 'n' para no. Vuelva a intentarlo");
                }
            }

            System.out.println("¿El usuario tiene discapacidad? (s/n):"); // Preguntar si el usuario tiene discapacidad
            char tieneDiscapacidad = sc.next().trim().toLowerCase().charAt(0); //guardar en char

            costoAgua = calcularCostoAgua(consumo); //calcular el costo base del agua

            //aplicar descuento por tercera edad
            if (respuesta == 's') { //si el usuario tiene tercera edad
                if (consumo <= 15) { //si el consumo está en el rango base de 0 a 15 m³
                    costoAgua *= 0.5;  //aplicar el descuento del 50% si el consumo está entre 0 y 15 m³
                } else { //si el consumo excede los 15 m³
                    double costoBase = calcularCostoAgua(15); //obtener el costo de los primeros 15 m³
                    double exceso = consumo - 15; //calcular la cantidad de m³ que excede los 15 m³
                    costoAgua = costoBase * 0.7 + calcularCostoAgua(exceso); //aplicar el descuento del 30% solo sobre los primeros 15 m³
                }
            }

            //aplicar descuento por discapacidad
            if (tieneDiscapacidad == 's') { //si el usuario tiene discapacidad
                System.out.println("Ingrese el porcentaje de discapacidad (por ejemplo, 30 para referirte al 30%):");
                double porcentajeDiscapacidad = sc.nextDouble(); //solicitar el porcentaje de discapacidad
                double descuentoDiscapacidad = calcularCostoAgua(15) * (porcentajeDiscapacidad / 100); //calcular descuento sobre
                //los 15 m³
                costoAgua -= descuentoDiscapacidad; //reducir el costo base por el descuento de discapacidad
            }

            //realizar los cálculos solicitados por la factura
            impAlcantarillado = costoAgua * 0.35; // Impuesto de alcantarillado (35% del costo del agua)
            totalFactura = costoAgua + impAlcantarillado + tasaBasura + tasaProcesamiento; // Total de la factura

            //presentar la factura con los datos redondeados
            System.out.printf("\nSU FACTURA DE AGUA POTABLE PARA EL MEDIDOR #%d ES LA SIGUIENTE:%n", i);
            System.out.println("-----------------------------------------------");
            System.out.printf("Servicio de Agua Potable: $%.2f%n", costoAgua);
            System.out.printf("Impuesto de Alcantarillado: $%.2f%n", impAlcantarillado);
            System.out.printf("Tasa por Recolección de Basura: $%.2f%n", tasaBasura);
            System.out.printf("Tasa por Procesamiento de Datos: $%.2f%n", tasaProcesamiento);
            System.out.printf("Total a pagar: $%.2f%n", totalFactura);
        }
    }

    //método para calcular el costo del agua en base a la tabla proporcionada por el problema
    private double calcularCostoAgua(double consumo) {
        double costo; //definir variable costo para aplicar la tabla

        if (consumo <= 15) { //casos en base a la tabla, que determinan el costo en base al consumo de agua
            costo = 3.00; // 0-15 m³ tiene un costo fijo de 3.00
        } else if (consumo <= 25) {
            costo = 3.00 + (consumo - 15) * 0.10; // el consumo que exceda los 15 m³ se cobra a $0.10 por m³
        } else if (consumo <= 40) {
            costo = 3.00 + (10 * 0.10) + (consumo - 25) * 0.20; // los siguientes 10 m³ a $0.20 por m³
        } else if (consumo <= 60) {
            costo = 3.00 + (10 * 0.10) + (15 * 0.20) + (consumo - 40) * 0.30; // los siguientes 15 m³ a $0.30 por m³
        } else {
            costo = 3.00 + (10 * 0.10) + (15 * 0.20) + (20 * 0.30) + (consumo - 60) * 0.35; // el exceso a 60 m³ se cobra a $0.35 por m³
        }

        return costo; //devolver costo al método principal
    }
}