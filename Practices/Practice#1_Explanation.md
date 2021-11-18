# Practice #1 Decision tree Explanation

This practice is straightforward. Decision trees are a popular family of classification and regression methods. You can find more information about the spark.ml implementation in the section on decision trees.
The following examples load a dataset in LibSVM format, divide it into test and training sets, train on the first dataset, and then evaluate on the retained test set. 

*We use two feature transformers to prepare the data; These index categories help the label and categorical features, adding metadata to the DataFrame that the decision tree algorithm can recognize.
It uses a decision tree as a predictive model that maps observations about an item to conclusions about the target value of the item. It is one of the predictive modeling approaches used in statistics, data mining, and machine learning.*

**Tree models, where the target variable can take a finite set of values, are called classification trees. In these tree structures, the leaves represent class labels and the branches represent the conjunctions of features that lead to those class labels.**

Decision trees, where the target variable can take continuous values ​​(usually real numbers) are called regression trees. Decision trees are among the most popular algorithms due to their simplicity.

> The goal is to create a model that predicts the value of a target
> variable based on various input variables. A decision tree or
> classification tree is a tree in which each internal node (not leaf)
> is labeled with an input function.

The arcs from a node labeled with a feature are labeled with each of the possible values ​​of the feature. Each leaf in the tree is marked with a class or probability distribution over the classes.

