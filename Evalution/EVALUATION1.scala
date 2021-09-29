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

