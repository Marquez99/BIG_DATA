/*PRACTICA 3
MARQUEZ ENRIQUEZ KEVIN EDWIN 17212923*/

/*1. Crea una lista llamada "lista" con los elementos "rojo", "blanco", "negro"*/
var lista = List("rojo", "blanco", "negro")

/*2. Añadir 5 elementos mas a "lista" "verde" ,"amarillo", "azul", "naranja", "perla"*/
lista = lista :+ "verde"
lista = lista :+ "amarillo"
lista = lista :+ "azul"
lista = lista :+ "naranja"
lista = lista :+ "perla" 

/*3. Traer los elementos de "lista" "verde", "amarillo", "azul"*/
lista(3)
lista(4)
lista(5)

/*4. Crea un arreglo de numero en rango del 1-1000 en pasos de 5 en 5*/
val arr = Array.range(0, 1001, 5)

/*5. Cuales son los elementos unicos de la lista Lista(1,3,3,4,6,7,3,7) utilice conversion a conjuntos*/ 

val Lista = List(1,3,3,4,6,7,3,7)
Lista.toSet

/*6. Crea una mapa mutable llamado nombres que contenga los siguiente
     "Jose", 20, "Luis", 24, "Ana", 23, "Susana", "27"*/
  

   val mutmap = collection.mutable.Map(
       ( "Jose", 20), ("Luis", 24), ("Ana", 23), ("Susana", "27"))

 /*6 a . Imprime todas la llaves del mapa*/
 mutmap.keys


 /*  6 b . Agrega el siguiente valor al mapa("Miguel", 23)*/
 mutmap += ("Miguel" -> 23)
