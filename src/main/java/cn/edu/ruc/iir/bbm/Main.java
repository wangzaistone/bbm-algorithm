package cn.edu.ruc.iir.bbm;

import cn.edu.ruc.iir.bbm.domain.Point;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.bbm
 * @ClassName: Main
 * @Description: -Dspark.master=local
 * @author: taoyouxian
 * @date: Create in 2017-11-12 10:37
 **/
public class Main {
    public static void main(String[] args) {
        SparkSession spark = SparkSession
                .builder()
//                .master("spark://10.77.40.27:7077")
                .appName("1M-clean-data-index")
                .getOrCreate();
        JavaRDD<Point> point = spark.read().textFile(args[0]).javaRDD().map(new Function<String, Point>() {
            public Point call(String line) throws Exception {
                String[] fields = line.split(args[1]);
                Point p = new Point(Integer.valueOf(fields[0]), Float.valueOf(fields[1]), Float.valueOf(fields[2]));
                return p;
            }
        });
        Dataset<Row> dataset = spark.createDataFrame(point, Point.class);
        dataset.createOrReplaceTempView(args[2]);
        try {
            Dataset<Row> pointDF = spark.sql(args[3]);
            pointDF.show();
        } catch (Exception e) {
            System.out.print("Error Info: " + e.getMessage());
        }
    }
}
