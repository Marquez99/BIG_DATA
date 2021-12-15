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



// We generate the features table
val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","pdays","previous")).setOutputCol("features")
val fea = assembler.transform(newcolumn)

// We change the column "y" to the label column
val cambio = fea.withColumnRenamed("y", "label")
val feat = cambio.select("label","features")

// SVM: It is required to change the numerical categorical values to 0 and 1 respectively
val c1 = feat.withColumn("label",when(col("label").equalTo("1"),0).otherwise(col("label")))
val c2 = c1.withColumn("label",when(col("label").equalTo("2"),1).otherwise(col("label")))
val c3 = c2.withColumn("label",'label.cast("Int"))

// c3.show(5)

// The data is prepared for training and the test
val Array(trainingData, testData) = c3.randomSplit(Array(0.7, 0.3))

// Model instance using the label and features as predominant values
val linsvc = new LinearSVC().setLabelCol("label").setFeaturesCol("features")

// Model fit
val linsvcModel = linsvc.fit(trainingData)

// Transformation of the model with the test data
val lnsvc_prediction = linsvcModel.transform(testData)
lnsvc_prediction.select("prediction", "label", "features").show(10)

// Print intercept line
// println(s"Coefficients: ${linsvcModel.coefficients} Intercept: ${linsvcModel.intercept}")

// Show Accuracy
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("label").setPredictionCol("prediction").setMetricName("accuracy")
val lnsvc_accuracy = evaluator.evaluate(lnsvc_prediction)
print("Accuracy of SVM IS = " + (lnsvc_accuracy))








/* PART 2 OF PROJECT*/

/* DECISION TREE CODE*/

/*Import OF LIBRARIES*/


import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.DateType
import org.apache.spark.ml.feature.VectorIndexer
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.feature.IndexToString
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.log4j._

/*DE;ETE WRNING*/
Logger.getLogger("org").setLevel(Level.ERROR)

/*CREATE SPARK SESSION*/
val spark = SparkSession.builder().getOrCreate()
val df = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank.csv")

/*DATA TYPES.*/
// df.printSchema()
// df.show(1)

/*CHANGE THE BINARY DATA*/
val change1 = df.withColumn("y",when(col("y").equalTo("yes"),1).otherwise(col("y")))
val change2 = change1.withColumn("y",when(col("y").equalTo("no"),2).otherwise(col("y")))
val newcolumn = change2.withColumn("y",'y.cast("Int"))

/*NEW CLOUMN*/
// newcolumn.show(1)

/*GENERATE FEATURES LABELS*/
val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","pdays","previous")).setOutputCol("features")
val fea = assembler.transform(newcolumn)
/*Mostramos la nueva columna*/
// fea.show(1)

/*CHANNGE THE COLUMNS*/
val cambio = fea.withColumnRenamed("y", "label")
val feat = cambio.select("label","features")
// feat.show(1)

/*DecisionTree*/
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(feat)

/*FEATURES */
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(4) 

/*DIVISION OF THE DATA 70% AND 30%*/
val Array(trainingData, testData) = feat.randomSplit(Array(0.7, 0.3))

/*CREATE OF AN OBJECT OF PREDECITION TREE*/
val dt = new DecisionTreeClassifier().setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures")

/*PREDECITON VRANCH*/
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)

/*PIELINE CRATE*/
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, dt, labelConverter))

/*Create a model de training*/
val model = pipeline.fit(trainingData)

/*MODEL DATA TRANSFORM*/
val predictions = model.transform(testData)

/*PREDICTIONES*/
// predictions.select("predictedLabel", "label", "features").show(5)

/*ACCURANCY RESULT*/
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(predictions)
println(s"DECISION TREE ACCURACY IS = ${accuracy}")





/* PART 3 OF FINAL PROJECT*/

/* LOGISTIC REGRESION */

import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.feature.{VectorAssembler, StringIndexer, VectorIndexer}
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.sql.SparkSession

import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

val spark = SparkSession.builder().getOrCreate()

/*Se importan los datos del CSV*/
val data  = spark.read.option("header","true").option("inferSchema", "true").option("delimiter",";").format("csv").load("bank.csv")

/*Se categoriza las variables de string a valor numérico*/
val yes = data.withColumn("y",when(col("y").equalTo("yes"),1).otherwise(col("y")))
val clean = yes.withColumn("y",when(col("y").equalTo("no"),2).otherwise(col("y")))
val cleanData = clean.withColumn("y",'y.cast("Int"))

/*Creación del Array con los datos seleccionados*/
val featureCols = Array("age","previous","balance","duration")

/*Creación del Vector en base a los features*/
val assembler = new VectorAssembler().setInputCols(featureCols).setOutputCol("features")

/*Transformación a un nuevo DF*/
val df2 = assembler.transform(cleanData)

/*Rename de columnas*/
val featuresLabel = df2.withColumnRenamed("y", "label")

/*Selección de index*/
val dataI = featuresLabel.select("label","features")

/*Creación del Array con los datos de entrenamiento y test*/
val Array(training, test) = dataI.randomSplit(Array(0.7, 0.3), seed = 12345)

/*Modelo de Regresion*/
val lr = new LogisticRegression().setMaxIter(10).setRegParam(0.3).setElasticNetParam(0.8)

val lrModel = lr.fit(training)

var results = lrModel.transform(test)

/*Impresión de los coeficientes e intercepciones*/
// println(s"Coefficients: \n${lrModel.coefficientMatrix}")
// println(s"Intercepts: \n${lrModel.interceptVector}")

import org.apache.spark.mllib.evaluation.MulticlassMetrics

// Convert test results to RDD using .as and .rdd
val predictionAndLabels = results.select($"prediction",$"label").as[(Double, Double)].rdd

// Initialize a MulticlassMetrics object
val metrics = new MulticlassMetrics(predictionAndLabels)

// metrics.accuracy

println(s"LOGISTIC REGRESION ACUURANCY IS= ${metrics.accuracy}")





/* final part of project*/ 
/*MultilayerPerceptron*/


// We import the necessary libraries with which we are going to work
import org.apache.spark.sql.types.DateType
import org.apache.spark.sql.SparkSession
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.classification.MultilayerPerceptronClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.feature.VectorIndexer
import org.apache.spark.ml.feature.IndexToString
import org.apache.spark.ml.Pipeline
import org.apache.log4j._

// Remove warnings
Logger.getLogger("org").setLevel(Level.ERROR)

// We create a spark session and load the CSV data into a datraframe
val spark = SparkSession.builder().getOrCreate()
val df = spark.read.option("header","true").option("inferSchema","true").option("delimiter",";").format("csv").load("bank.csv")

// // We display the data types
// df.printSchema()
// // We show first line
// df.show(1)

// We change the column "y" for one with binary data
val df1 = df.withColumn("y",when(col("y").equalTo("yes"),1).otherwise(col("y")))
val df2 = df1.withColumn("y",when(col("y").equalTo("no"),0).otherwise(col("y")))
val newcolumn = df2.withColumn("y",'y.cast("Int"))

// // We display the new column
// newcolumn.show(1)

// We generate the features field with VectorAssembler
val assembler = new VectorAssembler().setInputCols(Array("balance","day","duration","pdays","previous")).setOutputCol("features")
val newDF = assembler.transform(newcolumn)

// // We show the features field
// newDF.show(1)

// We modify the field "y" by label
val cambio = newDF.withColumnRenamed("y", "label")
// We select a new df with the fields of 'label' and 'features'
val finalDF = cambio.select("label","features")
// finalDF.show(1)

// We change the main label with categorical data from string to an Index
val labelIndexer = new StringIndexer().setInputCol("label").setOutputCol("indexedLabel").fit(finalDF)

// // We show the category of the data
// println(s"Found labels: ${labelIndexer.labels.mkString("[", ", ", "]")}")

// New variables to define an index to the vectors of the "features" field
val featureIndexer = new VectorIndexer().setInputCol("features").setOutputCol("indexedFeatures").setMaxCategories(2).fit(finalDF)

// Split the data into train and test
val splits = finalDF.randomSplit(Array(0.7, 0.3), seed = 1234L)
val trainingData = splits(0)
val testData = splits(1)

// Specify layers for the neural network:
// Input layer of size 5 (features), two intermediate of size 6 and 5 and output of size 2 (classes)
val layers = Array[Int](5, 6, 5, 2)

// Create an instance of the classification library method with the input field "indexedLabel" and the characteristics of the field "indexedFeatures"
val trainer = new MultilayerPerceptronClassifier().setLayers(layers).setLabelCol("indexedLabel").setFeaturesCol("indexedFeatures").setBlockSize(128).setSeed(1234L).setMaxIter(100)

// For demonstration purposes, the prediction is inverted to the string type of the label.
val labelConverter = new IndexToString().setInputCol("prediction").setOutputCol("predictedLabel").setLabels(labelIndexer.labels)

// We join the data created to have a new df with the new fields
val pipeline = new Pipeline().setStages(Array(labelIndexer, featureIndexer, trainer, labelConverter))

// Let's create a model with the training data
val model = pipeline.fit(trainingData)

// We generate the prediction with the test data
val prediction = model.transform(testData)
prediction.select("prediction", "label", "features").show(5)

// We finished a test to know the accuracy of the model and its efficiency.
val evaluator = new MulticlassClassificationEvaluator().setLabelCol("indexedLabel").setPredictionCol("prediction").setMetricName("accuracy")
val accuracy = evaluator.evaluate(prediction)

// Result
print("Accuracy of Support Vector Machine is = " + (accuracy))
