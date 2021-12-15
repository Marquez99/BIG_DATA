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
