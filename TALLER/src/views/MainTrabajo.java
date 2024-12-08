package views;
import controller.ejercicios.AutosComp;
import controller.ejercicios.CalculadoraFactura;
import controller.ejercicios.Llamadas;
import controller.ejercicios.Serie2;

import java.util.Scanner;

//Por Cael Soto

public class MainTrabajo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Seleccione el ejercicio:");
        System.out.println("1. Autos usados / Informe del sueldo");
        System.out.println("2. Costo de las llamadas telefónicas internacionales");
        System.out.println("3. Cobro de Agua potable");
        System.out.println("4. Serie");
        System.out.println("5. Terminar programa");
        int op = sc.nextInt();

        switch (op) {
            case 1:
                AutosComp autos = new AutosComp();
                autos.ejecutar();
                break;

            case 2:
                Llamadas llamada = new Llamadas();
                llamada.ejecutar();
                break;

            case 3:
                CalculadoraFactura calcu = new CalculadoraFactura();
                calcu.ejecutar();
                break;

            case 4:
                Serie2 ss = new Serie2();
                ss.ejecutar();
                break;
            
            case 5:
                System.out.println("Bye :)");
                return;

            default:
                System.out.println("Opcion no valida.");
                break;
        }
    }
}