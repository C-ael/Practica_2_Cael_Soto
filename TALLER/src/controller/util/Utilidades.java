package controller.util;

import java.util.Random;

//Por Cael Soto

public class Utilidades {

    public static boolean validar(String num) {
        boolean band = true;
        if(num.charAt(0) == '-') {
            num = num.substring(1);
        }
        
        int cont_p = 0; 
        for(int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            if(!Character.isDigit(c) && c != '.') {
                band = false;
                break;
            }
            if(c == '.') {
                cont_p++; 
            }
        }
        
        if(cont_p > 1) {
            band = false;
        }
        
        return band;
    }

    public static int transformStringInt(String num) {
        int resp = 0;
        if(validar(num)) {
            resp = Integer.parseInt(num);
        }
        return resp;
    }

    public static float transformStringFloat(String num) {
        float resp = 0.0f;
        if(validar(num)) {
            resp = Float.parseFloat(num);
        }
        return resp;
    }

    public static int generarNumeroRango(int minimo, int maximo){
        Random random = new Random();
        /*2 - 10
         * 2 + 11 - 2 ------- 2 + (9) , osea, rango del 0 al 9 */
        return minimo + random.nextInt((maximo + 1) - minimo);
    }

    public static float generarNumeroRangoFloat(float minimo, float maximo){
        Random random = new Random();
        return minimo + (random.nextFloat() * (maximo - minimo));
    }

    public static float redondear(float num){
        float aux = num*100.00f;
        float aux1 = (float) ((int) aux);
        return (aux1/100.00f);
        //return (float) (Math.round(num*100.00)/100.00);
    }

    public static double transformStringDouble(String num) {
        double resp = 0.0;
        if (validar(num)) {
            resp = Double.parseDouble(num);
        }
        return resp;
    }

    //Opcional: método para validar que la clave de zona esté en el rango permitido
    public static boolean validarClaveZona(int clave) {
        //claves válidas de zona:
        return clave == 12 || clave == 15 || clave == 18 || clave == 19 || clave == 23 || clave == 25 || clave == 29 || clave == 31;
    }
}