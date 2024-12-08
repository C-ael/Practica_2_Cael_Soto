#include <stdio.h>

//Por Cael Soto

enum semana_laboral {LUNES=1, MARTES=2, MIERCOLES=3, JUEVES=4, VIERNES=5};

int main() {
    int dia = 1;
    scanf("%d", &dia);

    for (int i = LUNES; i <= VIERNES; i++) {
        printf("El día de la semana es: %d\n", i);
        if ((i % 2) == 0) {
            printf("Hoy es un gran día para Teoría de la Programación\n");
        }
        if (dia == i) {
            break;
        }
    }
}