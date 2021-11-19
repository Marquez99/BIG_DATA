//MARQUEZ ENRIQUEZ KEVIN EDWIN 17212923
//LOPEZ MEDRANO JULIO ANTONIO 17211533

// Basic Spark Session
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate() 

// Import the following libraries to use the program
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator

//1. Load the data from the csv, do the right cleaning of the data to be processed by the following algorithm 
//   (Important, this cleaning has to be made by a Scala script in Spark)
//      a. Use the Mllib library from Spark and the Machine Learning algorithm multilayer perceptron
// ***Note to self: There is a total of 151 rows of data.***
val data = spark.read.option("header", "true").option("inferSchema","true")csv("C:/Users/Julio/BIG_DATA/Practices/Resources/iris.csv")

//2. What are the column names?
data.columns

//3. What is the schema?
data.printSchema()

//4. Shows the columns and the first 5 values
data.show(5)

//5. Use the describe() method to learn more from the dataframe data
data.describe()

//6. Make the relevant transformation for the categorical data which will be our labels to be classified.
val labelIndexer = new StringIndexer().setInputCol("species").setOutputCol("indexedSpecies").fit(data) 
val indexed = labelIndexer.transform(data).withColumnRenamed("indexedSpecies", "label")
indexed.show(151) //Note: This will show all data so you can see the transformation

val labelIndexer2 = new StringIndexer().setInputCol("label").setOutputCol("indexedSpecies").fit(indexed)
val assembler = new VectorAssembler().setInputCols(Array("sepal_length","sepal_width","petal_length","petal_width")).setOutputCol("features")
val features = assembler.transform(indexed)
features.show(151) //Note: This will show all data so you can see the second transformation

