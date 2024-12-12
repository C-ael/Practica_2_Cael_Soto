package controller.ejercicios;

import java.util.Scanner;

import controller.util.Utilidades;

public class AutosComp {
    
    /* Una compañía de autos usados paga $2,500.00 de sueldo a sus empleados por
     * mes, Además agrega pagos extras a sus sueldos como:
     * una comisión de $250.00 por cada automóvil vendido cuyo valor de venta supere
     * el $10,000.00 y el 5% de utilidad del valor total de ventas.
     * La compañía necesita emitir un informe desglosado por empleado donde indique
     * el número total de autos vendidos, el valor total de los
     * autos que vendió, el monto total que se le debería pagar al final del mes,
     * indicando: el sueldo mensual, la comisión por cada automóvil
     * y la utilidad por el valor total de la venta. También debe permitir ingresar
     * la venta de los automóviles hasta que se desee y
     * luego emitir el respectivo informe. */

    // Por Cael Soto

    final float Sueldo_Base = 2500.0f;
    final float Comision_Auto = 250.0f;
    final float Porcentaje_Utilidad = 0.05f;

    public void ejecutar() {
        Scanner sc = new Scanner(System.in); //llamamos al scanner al programa
        boolean continuar = true; //variable booleana que determina la ejecución del proceso principal, da la green flag en proceder con
        //el código
        System.out.println("\nRegistro de ventas de automoviles:");

        while (continuar) { //bucle general destinado a permitir la ejecución de cada acción repetitiva general
            System.out.print("Ingrese el nombre del trabajador: ");
            String nombre = sc.nextLine(); //se usa nextLine() para capturar el nombre completo

            //definir las variables para registrar ventas y comisiones
            int autosVendidos = 0;
            float totalVentas = 0.0f;
            float comisiones = 0.0f;
            boolean registrarVentas = true; //boolean que determina hasta cuando parar las acciones de pedir valor de un nuevo auto y de
            //registrar otra venta

            while (registrarVentas) { //bucle destinado a calcular los valores como: n de autos vendidos, total de ventas, comisiones, además,
            //también sirve para repetir si desee agregar otra venta
                System.out.print("Ingrese el valor del automovil vendido por " + nombre + ":");
                float valorVenta = sc.nextFloat();
                totalVentas += valorVenta;
                autosVendidos++;

                //si el valor de la venta supera los $10,000, se aplica la comisión dispuesta por el problema
                if (valorVenta > 10000.0f) {
                    comisiones += Comision_Auto; //guardar en cada interacción el valor de la comisión de los autos en comisiones
                }

                //preguntar si desea registrar otra venta y llamar a las utilidades para validar la respuesta
                char respuesta = Utilidades.obtenerRespuesta(sc, "¿Desea registrar otra venta? (s/n): ");
                if (respuesta == 'n') {
                    registrarVentas = false; //el usuario no quiere registrar otra venta
                }
            }

            //Calcular utilidad y sueldo total
            float utilidad = totalVentas * Porcentaje_Utilidad;
            float sueldoTotal = Sueldo_Base + comisiones + utilidad;

            //Presentar el informe final para este trabajador
            System.out.println("\nINFORME DE VENTAS DEL TRABAJADOR: " + nombre);
            System.out.println("----------------------------------"); //darle una mejor interfaz al informe
            System.out.println("Total de autos vendidos: " + autosVendidos);
            System.out.println("Valor total de las ventas: $" + totalVentas);
            System.out.println("Sueldo base: $" + Sueldo_Base);
            System.out.println("Comisión total por autos vendidos: $" + comisiones);
            System.out.println("Utilidad total por ventas: $" + utilidad);
            System.out.println("Sueldo total a pagar: $" + sueldoTotal);

            //preguntar si desea registrar otro trabajador, y repetir el proceso desde el inicio del programa
            char resp_trabajador = Utilidades.obtenerRespuesta(sc, "\n¿Desea registrar otro trabajador? (s/n): ");
            if (resp_trabajador == 'n') {
                continuar = false; //si no hay más trabajadores cerrar el programa
            }
        }
    }
}