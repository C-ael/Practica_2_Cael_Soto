package controller.ejercicios;

import java.util.Scanner;

import controller.util.Utilidades;

/*3.La empresa municipal de agua potable de Loja desea cobrar y calcular mensualmente el valor exacto de consumo del agua potable de cada
medidor que pertenece a un contribuyente, de acuerdo a la ordenanza vigente, la cual establece la planilla de acuerdo a los siguientes rubros:
	
	A) SERVICIO DE AGUA POTABLE: Para obtener el rubro ríjase a la siguiente tabla:
    B) IMPUESTO DE ALCANTARILLADO: 35% DEL VALOR DEL RUBRO DEL SERVICIO DE AGUA POTABLE.
	C) TASA POR RECOLECCIÓN DE BASURA: 0.75 DÓLARES;
	D) TASA POR COSTO DE PROCESAMIENTO DE DATOS: 0.50 DÓLARES
	
La ordenanza también permite descuentos para los medidores que estén registrados al contribuyente de la tercera edad o posean algún tipo
discapacidad, para lo cual sólo se los puede aplicar sobre el rubro del SERVICIO DE AGUA POTABLE, en base a las siguientes condiciones.
(a) Si pertenece a la tercera edad y su consumo esta entre 0 hasta15 m³ (rango base) existe un descuento del 50%, en caso de exceder sólo
se realizará el descuento del 30% sobre el rango base.
(b) Si pertenece a un discapacitado, se tomará como descuento su porcentaje de discapacidad sólo sobre el rango base.

Por: Cael Soto
*/

public class CalculadoraFactura {

    public void ejecutar() {
        Scanner sc = new Scanner(System.in);

        //definición de constantes para tasas adicionales como recolección de basura y procesamiento de datos
        final double TASA_BASURA = 0.75;
        final double TASA_PROCESAMIENTO = 0.50;
        System.out.println("\nCalculo del agua potable.");

        //variable de control para repetir el proceso de cálculo
        boolean continuar = true;

        while (continuar) {
            //solicitar el nombre del contribuyente
            System.out.println("\nIngrese el nombre del contribuyente:");
            String nombre = sc.nextLine();
            System.out.println("Factura de agua para: " + nombre);

            // Verificar si el contribuyente tiene discapacidad y obtener el porcentaje si es necesario
            char discapacidad = Utilidades.obtenerRespuesta(sc, "Responda: ¿El contribuyente tiene discapacidad? (s/n):");
            double porcentajeDiscapacidad = 0;

            // Verificar si el contribuyente es de la tercera edad
            char terceraEdad = Utilidades.obtenerRespuesta(sc, "Responda: ¿El contribuyente es de la tercera edad? (s/n):");

            // Si tiene discapacidad, ingresar el porcentaje de discapacidad
            if (discapacidad == 's') {
                System.out.println("Ingrese el porcentaje de discapacidad del contribuyente (ejemplo: 30 para 30%):");
                while (!sc.hasNextDouble()) {
                    System.out.println("Querido contribuyente, ingrese un valor válido.");
                    sc.next(); // Limpiar entrada inválida
                }
                porcentajeDiscapacidad = sc.nextDouble();
                sc.nextLine(); // Limpiar salto de línea restante
            }

            // Solicitar el número de medidores que se deben procesar
            int nroMedidores;
            while (true) {
                System.out.println("Ingrese el numero de medidores:");
                if (sc.hasNextInt()) {
                    nroMedidores = sc.nextInt();
                    sc.nextLine(); // Limpiar salto de línea restante
                    if (nroMedidores > 0)
                        break;
                    else
                        System.out.println("Querido contribuyente, debe ingresar un numero positivo.");
                } else {
                    System.out.println("Querido contribuyente, ingrese un numero válido.");
                    sc.next(); // Limpiar entrada inválida
                }
            }

            // Variable para acumular el total general de todos los medidores
            double totalGeneral = 0;

            // Procesar cada medidor
            for (int i = 1; i <= nroMedidores; i++) {
                System.out.println("\nMedidor nro. " + i);

                // Solicitar el consumo de agua en metros cúbicos (m³)
                double consumo;
                while (true) {
                    System.out.println("Ingrese el consumo de agua en m3 / metros cubicos:");
                    if (sc.hasNextDouble()) {
                        consumo = sc.nextDouble();
                        sc.nextLine(); // Limpiar salto de línea restante
                        if (consumo > 0)
                            break;
                        else
                            System.out.println("El consumo debe ser un numero positivo.");
                    } else {
                        System.out.println("Querido contribuyente, ingrese un numero valido.");
                        sc.next(); // Limpiar entrada inválida
                    }
                }

                // Calcular el costo de agua para este medidor (por el consumo)
                double costoAgua = calcularCostoAgua(consumo);

                // Aplicar descuento por tercera edad
                if (terceraEdad == 's') {
                    if (consumo <= 15) {
                        costoAgua *= 0.5; // 50% de descuento sobre los primeros 15 m³
                    } else {
                        // Descuento del 30% sobre los primeros 15 m³, y sobre el exceso sin descuento
                        double costoDescuento = calcularCostoAgua(15) * 0.7; // Descuento del 30% sobre los primeros 15 m³
                        double costoExceso = calcularCostoAgua(consumo) - calcularCostoAgua(15); // Exceso sin descuento
                        costoAgua = costoDescuento + costoExceso;
                    }
                }

                // Aplicar descuento por discapacidad
                if (discapacidad == 's') {
                    // Descuento solo sobre los primeros 15 m³ según el porcentaje de discapacidad
                    double descDiscapacidad = calcularCostoAgua(15) * (porcentajeDiscapacidad / 100);
                    costoAgua -= descDiscapacidad; // Aplicar descuento sobre los primeros 15 m³
                }

                // Calcular rubros adicionales como el impuesto de alcantarillado
                double impAlcantarillado = costoAgua * 0.35; // Impuesto de alcantarillado: 35% del servicio de agua
                // Total de la factura sumando los rubros adicionales
                double totalFactura = costoAgua + impAlcantarillado + TASA_BASURA + TASA_PROCESAMIENTO;

                // Acumular el total general de todos los medidores
                totalGeneral += totalFactura;

                // Presentar factura para el medidor actual
                System.out.println("\nFACTURA DE AGUA POTABLE PARA EL MEDIDOR NRO " + i + ":");
                System.out.println("-----------------------------------------------");
                System.out.println("Contribuyente: " + nombre);
                System.out.printf("Servicio de Agua Potable: $%.2f\n", costoAgua);
                System.out.printf("Impuesto de Alcantarillado: $%.2f\n", impAlcantarillado);
                System.out.printf("Tasa por Recolección de Basura: $%.2f\n", TASA_BASURA);
                System.out.printf("Tasa por Procesamiento de Datos: $%.2f\n", TASA_PROCESAMIENTO);
                System.out.printf("Total a pagar: $%.2f\n", totalFactura);
                System.out.println("-----------------------------------------------");
            }

            // Presentar el total general después de procesar todos los medidores
            System.out.printf("TOTAL POR TODOS LOS MEDIDORES: $%.2f\n", totalGeneral);

            // Preguntar si desea agregar otro contribuyente
            char otro = Utilidades.obtenerRespuesta(sc, "¿Desea agregar otro contribuyente? (s/n):");
            if (otro != 's') {
                continuar = false; // Cambiar el valor de continuar a false para salir del bucle
            }
        }
    }

    //método para calcular el costo del agua basado en el consumo
    private double calcularCostoAgua(double consumo) {
        double costo = 0;

        //calcular el costo según las tablas proporcionadas
        if (consumo <= 15) {
            costo = 3.00; // $3.00 por los primeros 15 m³
        } else if (consumo <= 25) {
            costo = 3.00 + (consumo - 15) * 0.10; // $0.10 por m³ entre 15 y 25 m³
        } else if (consumo <= 40) {
            costo = 3.00 + (10 * 0.10) + (consumo - 25) * 0.20; // $0.20 por m³ entre 25 y 40 m³
        } else if (consumo <= 60) {
            costo = 3.00 + (10 * 0.10) + (15 * 0.20) + (consumo - 40) * 0.30; // $0.30 por m³ entre 40 y 60 m³
        } else {
            costo = 3.00 + (10 * 0.10) + (15 * 0.20) + (20 * 0.30) + (consumo - 60) * 0.35; // $0.35 por m³ después de 60 m³
        }

        return costo; //devolver costo al método principal
    }
}