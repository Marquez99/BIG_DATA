

# Evaluation #1

## INSTRUCTIONS

Answer the following questions with Spark DataFrames and Scala using the "CSV" Netflix_2011_2016.csv found in the spark-dataframes folder.

1. Start a simple Spark session.

2. Upload Netflix Stock CSV file, have Spark infer the data types.

3. What are the names of the columns?

4. What is the scheme like?

5. Print the first 5 columns.

6. Use describe () to learn about the DataFrame.

7. Create a new dataframe with a new column called "HV Ratio" which is the ratio that It is between the price in the column "High" versus the column "Volume" of shares traded for a day. Hint is an operation

8. What day had the highest peak in the “Open” column?

9. What is the meaning of the Close column “Close” in the context of financial information,
explain it there is no need to code anything?

10. What is the maximum and minimum in the “Volume” column?

11. With Syntax Scala / Spark $ answer the following:
to. 
a. How many days was the “Close” column less than $ 600?

b. What percentage of the time was the “High” column greater than $ 500?

c. What is the Pearson correlation between column "High" and column "Volume"?

d. What is the maximum in the “High” column per year?
and. What is the “Close” column average for each calendar month?

## EXPLICATION
This evaluation is intended to solve and analyze the instructions
previously established in the evaluation of unit 1, where the
Netflix stock prices and how they change over the course of
the years 2011 to 2015 .

For this evaluative practice, the data provided by the
teacher Jose Christian Romero in the [link](https://github.com/jcromerohdz/BigData/blob/master/Spark_DataFrame/Netflix_2011_2016.csv) , where it reflects all the aforementioned data, within these data can be found the different appreciations of Netflix shares in the period 2011-2016, how do diaramenta actions open and close, so as its maximum and minimum values.

Each of the steps taken by the Engineer are reflected below.
Marquez Kevin, where the comments explaining each
line of code.

It can be concluded that Scala is a very user-friendly programming language. The user who is implementing it, each of its instructions are easy to know and interpret, specifically this CSV helps us to implement various
Scala conditions.

As a conclusion in the CSV it can be said that Netflix shares increased
600% from 2011 to 2016, with its closing of shares in 2011 with 120
Dlls and closing the year 2016 with 722.45 Dlls, this large increase may be due to many factors, such as the type of content on the platform or exclusivities that usually have this company.

The month of all the years that it is more feasible to buy a share is the month of July, its rate of decrease around 10% compared to other months of the year 2016, which can be of great benefit to investors in this content platform.
