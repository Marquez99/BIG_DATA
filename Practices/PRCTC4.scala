/*PRACTICA 4
MARQUEZ ENRIQUEZ KEVIN EDWIN 17212923*/

/* Dado el pseudocódigo de la sucesión de Fibonacci en el enlace proporcionado, implementar con Scala el Algoritmo 1, */
Algoritmo 1:
def FIBO1(NUM: Int): Int = {
    if(NUM < 2) return NUM else FIBO1(NUM-1) + FIBO1(NUM-2)
}

/* Algoritmo 2*/
import scala.math.sqrt
import scala.math.pow

def FIBO2(NUM: Int): Int = {
    if(NUM < 2) {
        return NUM
    }
    else {
        var P1 = ((1+sqrt(5))/2)
        var J2 = ( (pow(P1,NUM) - pow(1-P1,NUM) ) / sqrt(5))
        return (J2).toInt
    }
}

/*Algoritmo 3*/

def FIBO3(NUM: Int): Int = {
      var NUM1 = NUM - 1; 
      var P1 = 0;
      var P2 = 1;
      var P3 = 0;
      for ( k <- 0 to NUM1)
      {
        P3 = P2 + P1;
        P1 = P2;
        P2 = P3;
      }
      return P1
    }

/*Algoritmo 4*/ 

def FIBO4(NUM: Int): Int = {
      var NUM1 = NUM -1;
      var P1 = 0;
      var P2 = 1;
      for ( k <- 0 to P1)
      {
        P2 = P2 + P1;
        P1 = P2 - P1;
      }

/*Algoritmo 5*/

def FIBO5(NUM: Int): Int = {
      if ( NUM < 2) return NUM
      else {
        var VR = new Array[Int](NUM + 2);
         VR(0) = 0;
         VR(1) = 1;
        for ( k <- 2 to (NUM + 1))
          {
            VR(k) = VR(k - 1) + VR(k - 2);
          }
        return(VR(NUM))
      }
    }