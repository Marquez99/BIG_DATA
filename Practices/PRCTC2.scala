/*PRACTICA 2 
MARQUEZ ENRIQUEZ KEVIN EDWIN 17212923*/

import scala.io.StdIn.readLine

/*1. Desarrollar un algoritmo en scala que calcule el radio de un circulo*/
def Radio(): Unit = {
    println("Diametro de un circulo: ")
    var Diametro = scala.io.StdIn.readInt()
    var Rd = Diametro/2
    println("Radio es: " + Rd)
}

Radio()

/*2. Desarrollar un algoritmo en scala que me diga si un número es primo*/
def Primo(): Unit = {
    println("Pon un numero: ")
    val Num = scala.io.StdIn.readInt()
    if(2 >= Num) {
        println(s"$Num ES PRIMO")
    } 
    
    else if (Num%2!=0)
    {
        println(s"$Num ES PRIMO")
    }
    else {
        println(s"$Num NO ES PRIMO")
    }
}
Primo()

/* 3. Dada la variable  var bird = "tweet", utiliza interpolación de strings para imprimir "Estoy ecribiendo un tweet"*/

var Pjr  = "tweet"
val Mnsj = s"Escribiendo un $Pjr"

/* 4. Dada la variable var mensaje = "Hola Luke yo soy tu padre!" utiliza slice para extraer la ecuencia "Luke"*/
val Mnsj= "Hola Luke yo soy tu padre!"
Mnsj.slice(5,9)

/*5. Cúal es la diferencia entre value (val) y una variable (var) en scala?*/

/* val crea una variable inmutable, ya cual su valor no puede cambiar una vez declarado.
    var crea una variable mutable, está si se puede modificar su valor original.*/

/*6. Dada la tupla (2,4,5,1,2,3,3.1416,23) regresa el número 3.1416*/
val Tupla = (2,4,5,1,2,3,3.1416,23)
Tupla._7