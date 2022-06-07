#include "lois.h"

void delaiUniforme(int temps,int delta){
    int bi, bs ;
    int n, nbSec ;
    bi = temps - delta ;
    if (bi < 0) bi = 0 ;
    bs = temps + delta ;
    n = bs - bi ;
    nbSec = (rand()/ (float)RAND_MAX) * n ;
    nbSec += bi ;
    printf("%d\n", nbSec) ;
}

void delaiGauss(double moyenne, double ecartype){
    double u1 = rand()/ (RAND_MAX+1.0);
    double u2 = rand()/ (RAND_MAX+1.0);
    double temps = sqrt(-(2*log(u1)))*cos(2* M_PI * u2) * ecartype + moyenne;
    printf("%f\n", temps) ;
}

void delaiExponentiel(double lambda){
    double u = rand()/ (RAND_MAX+1.0);
    double temps = -(log(u) / lambda);
    printf("%f\n", temps) ;
}

int main(int argc, char const *argv[]){
    srand(time(NULL));
    for (int i = 0 ; i < 5000 ; i++){
        delaiExponentiel(1.0/20.0);
    }
    return 0;
}
