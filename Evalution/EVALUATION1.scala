//EVALUACION NUMERO #1 DATOS MASIVOS
//MARQUEZ ENRIQUEZ KEVIN EDWIN 17212923

/1.Comienza una simple sesión Spark. 
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()


//2.Cargue el archivo Netflix Stock CSV, haga que Spark infiera los tipos de datos. 
val Dataf = spark.read.option("header","true").option("inferSchema","true").csv("Netflix_2011_2016.csv")

//3. ¿Cuáles son los nombres de las columnas? 
Dataf.columns


//4. ¿Cómo es el esquema? 

Dataf.printSchema()

//5. Imprime las primeras 5 columnas. 

Dataf.head(5)

//6. Usa describe () para aprender sobre el DataFrame. 

Dataf.describe().show()

//7. Crea un nuevo dataframe con una columna nueva llamada “HV Ratio” que es la relación que  esixte entre el precio de la columna “High” frente a la columna “Volumen” de acciones  negociadas por un día. Hint es una operación 

val Dataf2 = Dataf.withColumn("HV Ratio",Dataf("High")/Dataf("Volume"))

//8. ¿Qué día tuvo el pico mas alto en la columna “Open”? 

Dataf.orderBy($"Open".desc).show(1)

//9. ¿Cuál es el significado de la columna Cerrar “Close” en el contexto de información financiera,  
//explíquelo no hay que codificar nada? 

//Esta columna indica el precio en el que se concluyeron las ventas de acciones el día segun sea el caso.


//10. ¿Cuál es el máximo y mínimo de la columna “Volumen”?

//MAXIMO
Dataf.select(max("Volume")).show()

//MINIMO
Dataf.select(min("Volume")).show()


//11. Con Sintaxis Scala/Spark $ conteste los siguiente: 

//a. ¿Cuántos días fue la columna “Close” inferior a $600? 
Dataf.filter($"Close"<600).count()

//b. ¿Qué porcentaje del tiempo fue la columna “High” mayor que $500? 
(Dataf.filter($"High">500).count()*1.0/Dataf.count())*100

//c. ¿Cuál es la correlación de Pearson entre la columna “High” y la columna “Volumen”? 
Dataf.select(corr("High","Volume")).show()


//d. ¿Cuál es el máximo de la columna “High” por año? 
val YDataf = Dataf.withColumn("Year",year(Dataf("Date")))

val Ymaxs = YDataf.select($"Year",$"High").groupBy("Year").max()

Ymaxs.select($"Year",$"max(High)").show()


//e. ¿Cuál es el promedio de columna “Close” para cada mes del calendario? 

val mDataf = Dataf.withColumn("Month",month(Dataf("Date")))
val mavgs = mDataf.select($"Month",$"Close").groupBy("Month").mean()

mavgs.select($"Month",$"avg(Close)").show()