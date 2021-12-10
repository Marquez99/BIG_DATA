
# Evaluation 3 - Unit 3

## 1.- Import a simple spark session

```r
import org.apache.spark.sql.SparkSession
```
## 2.- Minimize the errors with code

```r
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)
```
## 3.- Create an instance for the spark session
```r
val spark = SparkSession.builder().getOrCreate()
```
## 4.- Import the Kmeans library
```r
import org.apache.spark.ml.clustering.KMeans
```
## 5.- Load the dataset from the csv file
```r
val data = spark.read.option("header", "true").option("inferSchema","true")csv("C:/Users/Julio/BIG_DATA/Resources/Wholesale customers data.csv")
```
## 6.- Select the following columns: Fresh, Milk, Grocery, Frozen, Detergents_Paper, Delicassen and call this set as feature_data
```r
val feature_data = data.select("Fresh", "Milk", "Grocery", "Frozen", "Detergents_Paper", "Delicassen")
```
## 7.- Import Vector Assembler y Vector
```r
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors
```
## 8.- Create a new object Vector Assembler for the features columns as an input set
```r
val assembler = new VectorAssembler().setInputCols(Array("Fresh", "Milk", "Grocery", "Frozen", "Detergents_Paper", "Delicassen")).setOutputCol("features")
```
## 9.- Use the object assembler to transform feature_data
```r
val features = assembler.transform(feature_data)
```
## 10.- Create the kmean model with k = 3
```r
val kmeans = new KMeans().setK(3).setSeed(1L)
val model = kmeans.fit(features)
```
## 11.- Evaluate the groups using Within Set Sum of Squared Errors WSSSE and print the centroids.
```r
val WSSE = model.computeCost(features)
println(s"\n\nWithin set sum of Squared Errors = $WSSE\n\n")

println("Cluster Centers: ")
model.clusterCenters.foreach(println)
```
![imagen](https://github.com/Marquez99/BIG_DATA/blob/Unit_3/Resources/Centroids.png)

### 
