package org.anh.cherry

import org.apache.spark.sql.{DataFrame, Row}
import org.scalatest.FunSpec
import org.quocanh.spark.cherry.SparkSessionForTests
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import com.github.mrpowers.spark.fast.tests.DataFrameComparer
import org.apache

class MySpec
  extends FunSpec
    with SparkSessionForTests
    with DataFrameComparer {

  import spark.implicits._

  def teasing(df: DataFrame): DataFrame = {
    df.withColumn("tease", lit("I like Cherry"))
  }

  describe(".cherryData") {

    it("appends a teasing column to a DataFrame") {

      val sourceDF = Seq((1, "Tom"), (2, "Jerry")).toDF("id", "name")

      val actualDF = sourceDF.transform(teasing)

      val expectedData = List(
        Row(1, "Tom", "I like Cherry"),
        Row(2, "Jerry", "I like Cherry")
      )

      val expectedSchema = List(
        StructField("id", IntegerType, false),
        StructField("name", StringType, true),
        StructField("tease", StringType, false)
      )

      val expectedDF = spark.createDataFrame(
        spark.sparkContext.parallelize(expectedData),
        StructType(expectedSchema)
      )

      assertSmallDataFrameEquality(actualDF, expectedDF)
    }
  }

}
