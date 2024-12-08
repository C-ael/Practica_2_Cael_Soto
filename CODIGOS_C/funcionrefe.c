#include <stdio.h>

//Por: Cael Soto

void cuadriplicar(int *v) { //casi mismo ejem con referencia
    *v = *v * 4;
}

int main() {
    int num = 2;
    printf("El valor antes de la función es: %d\n", num);
    cuadriplicar(&num); //en teoría 2

    printf("El valor después de la función es: %d\n", num);
    return 0; //en teoría 8
}