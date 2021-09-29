//EVALUACION NUMERO #1 DATOS MASIVOS
//MARQUEZ ENRIQUEZ KEVIN EDWIN 17212923

/1.Comienza una simple sesión Spark. 
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()


//2.Cargue el archivo Netflix Stock CSV, haga que Spark infiera los tipos de datos. 
val Dataf = spark.read.option("header","true").option("inferSchema","true").csv("Netflix_2011_2016.csv")

//3. ¿Cuáles son los nombres de las columnas? 
Dataf.columns
