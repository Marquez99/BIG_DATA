# Practice 1 - Logistic Regression

## 1.- Import SparkSession with Logistic Regression
We simply use this piece of code to import both libraries to our practice.
```r
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.sql.SparkSession
```

## 2.- Error Reporting
This is an optional step that is used to ommit some errors that might show when executing the program, this doesn't affect the outcome.
```r
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)
```

## 3.- Create Spark Session
We call the builder to create a spark session.
```r
val spark = SparkSession.builder().getOrCreate()
```

## 4.- Import the Data Set
We import the data set to be able to be used.
```r
val data  = spark.read.option("header","true").option("inferSchema", "true").format("csv").load("C:/Users/Julio/BIG_DATA/Resources/advertising.csv")
```

## 5.- Print Schema
We then print the Schema so we can see how it is configured.
```r
data.printSchema()
```

## 6.- Print the First Row
There are 2 ways we can do this, the first one is using "data.head(1)", which will print the very first row of data in the data set.
The other one is utilizing the following piece of code: 
```r
//Easy Way
data.head(1)

//Pro MLG Way
val colnames = data.columns
val firstrow = data.head(1)(0)
println("\n")
println("Example data row")
for(ind <- Range(1, colnames.length)){
    println(colnames(ind))
    println(firstrow(ind))
    println("\n")
}
```
## 7.- Rename the column "Clicked on Ad" as "label", use  "Daily Time Spent on Site","Age","Area Income","Daily Internet Usage","Timestamp","Male" as "features" and create a new column named "Hour" with the Timestamp contained in "Hour of the click"
While sounding complicated, it's not that hard to do. First we create the column named "Hour" utilizing the .withColumn method and send the parameters that contain the name of the column and the data that is going to be used in that column. 
Then we rename the column "Clicked on Ad" as "label" using a select query, similar to the SQL code, where we select the column and rename it using .as. After that we add the columns suggested as features of that column so we can get the features that we need.
```r
val timedata = data.withColumn("Hour",hour(data("Timestamp")))

val logregdata = timedata.select(data("Clicked on Ad").as("label"), $"Daily Time Spent on Site", $"Age", $"Area Income", $"Daily Internet Usage", $"Hour", $"Male")
```
## 8.- Import Vector Assembler and Vectors
Simple, we just import the following Libraries:
```r
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
```

## 9.- Create a New Object VectorAssembler Called "assembler" for the Features
In here we make the value assembler and create the object, which then we set the input columns with an array of the columns we already used to create the features. Then we create the output that is going to be named "features".
```r
val assembler = (new VectorAssembler()
                  .setInputCols(Array("Daily Time Spent on Site", "Age","Area Income","Daily Internet Usage","Hour","Male"))
                  .setOutputCol("features"))
```

## 10.- Use randomSplit to Create a 70/30 Distribution Train and Test Sets
we basically split the data into two different sets, one named training which takes the 70% of the data and one named test that takes the 30% of the data.
```r
val Array(training, test) = logregdata.randomSplit(Array(0.7, 0.3), seed = 12345)
```

## 11.- Import Pipeline 
We just import the Pipeline Library
```r
import org.apache.spark.ml.Pipeline
```

## 12.- Create a new object from LogistiRegression named "lr" 
We create the object, pretty simple code:
```r
val lr = new LogisticRegression()
```

## 13.- Create a new pipeline object with the elements: assembler, lr 
We do this by simply adding .setStages(Array(x,y)) to the newly created Pipeline object.
```r
val pipeline = new Pipeline().setStages(Array(assembler, lr))
```

## 14.- Fit the pipeline for the training set
We do this with the following code:
```r
val model = pipeline.fit(training)
```

## 15.- Take the results in the test set with transform
We just create a value named results and transform the test set.
```r
val results = model.transform(test)
```

## 16.- For Metrics and Evaluation import MulticlassMetrics
We just import the following library:
```r
import org.apache.spark.mllib.evaluation.MulticlassMetrics
```
## 17.- Convert the test results in RDD using .as and .rdd
To do this we create a value named predictionAndLabels and select the columns "prediction" and "label" as a double value so we can transform it to .rdd.
```r
val predictionAndLabels = results.select($"prediction",$"label").as[(Double, Double)].rdd
```
## 18.- Initialize a MulticlassMetrics object
We do this by creating a simple object and sending the predictionAndLabels .rdd conversion as a parameter.
```r
val metrics = new MulticlassMetrics(predictionAndLabels)
```
## 19.- Print the Confusion Matrix
We do this by simply printing a small title and calling "metrics.confusionMatrix" and "metrix.accuracy" so we can see the confuson matrix and also the amount of accuracy it has.
```r
println("Confusion matrix:")
println(metrics.confusionMatrix)

metrics.accuracy
```
