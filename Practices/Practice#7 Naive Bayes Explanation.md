# Practice#7 Naive Bayes Explanation

This practice is simple, in this naive bayes theorem you can find more information about the implementation of spark.ml in the section on decision trees.

***In simple terms, a Naive Bayes classifier assumes that the presence or absence of a particular characteristic is not related to the presence or absence of any other characteristic, given the variable class. For example, a fruit can be considered an apple if it is red, round, and about 7 cm in diameter.***


>  A Naive Bayes classifier considers that each of these characteristics
> contributes independently to the probability that this fruit is an
> apple, regardless of the presence or absence of the other
> characteristics. For other probability models, Naive Bayes classifiers
> can be trained very efficiently in a supervised learning environment.
> In many practical applications, parameter estimation for Naive Bayes
> models uses the maximum likelihood method, in other words, you can
> work with the Naive Bayes model without accepting Bayesian probability
> or any of the Bayesian methods.

An advantage of the Naive Bayes classifier is that only a small amount of training data is required to estimate the parameters (the means and variances of the variables) required for classification. 

***As the independent variables are assumed, it is only necessary to determine the variances of the variables of each class and not the entire covariance matrix.***
