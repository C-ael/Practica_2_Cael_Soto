#include <stdio.h>

//Por Cael Soto

void duplicar(int w) {
    w = w * 2;
    printf("valor dentro de la función: %d\n", w); //en teoría es 13
}

int main() {
    int num = 13;
    printf("valor antes de la función: %d\n", num);
    duplicar(num); //en teoría debe ser 26

    printf("valor después de la función: %d\n", num);
    return 0; //en teoría debe seguir como 13
}