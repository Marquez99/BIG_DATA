/* FINAL PROJECT OF BIG DATA*/

/*PART 1 OF PROJECT*/
/* CODE OF ALGORITHM SVM*/

// We import the necessary libraries with which we are going to work
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.DateType
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.classification.LinearSVC
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.log4j._

// Remove warnings
Logger.getLogger("org").setLevel(Level.ERROR)

// We create a spark session and load the CSV data into a datraframe
val spark = SparkSession.builder().getOrCreate()
val df = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank.csv")

// We change the column "y" for one with binary data.
val change1 = df.withColumn("y",when(col("y").equalTo("yes"),1).otherwise(col("y")))
val change2 = change1.withColumn("y",when(col("y").equalTo("no"),2).otherwise(col("y")))
val newcolumn = change2.withColumn("y",'y.cast("Int"))




