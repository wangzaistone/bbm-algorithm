## Intro
基于分布式计算框架的算法实现

## Additional Readings
- 问题
```
R和S是二维数据（ID，横坐标、纵坐标）的集合
现要求使用分布式计算框架（Hadoop或Spark）计算：
Select R.ID, S.ID from R, S where L2(R,S) <= q
Select R.ID, S.ID from R left outer join S where L2(R,S) <= q
Select R.ID, S.ID from R right outer join S where L2(R,S) <= q
```
- 评分
```
功能分：60分（正确即为满分）
性能分：40分（性能最快的组员成绩均为满分，其他组的成绩为40*T/S,其中T为第一名的成绩，S为当前组的成绩）
数据集的产生：均匀分布（ Zipf分布）
```

## Command
| args    | description | info   |
|---------|--------|-------------|
| args[0] | Path | /rainbow-manage/1M-clean-data-index ("/rainbow-manage/Points.txt" for test) |
| args[1] | Split | " " ("," for test) |
| args[2] | TableName | Point |
| args[3] | Sql | select a.* from (  select p1.ID, p1.PointX, p1.PointY, p2.ID as P2_ID, p2.PointX as P1_PointX, p2.PointY as P2_PointY, round((cast(p1.PointX as float) - cast(p2.PointX as float)), 4) * round((cast(p1.PointX as float) - cast(p2.PointX as float)), 4) as num from Point p1, Point p2) a where a.num = 4 and a.PointY = 2 |

## Required Software
- Java Platform (JDK) >= 8; once installed, update JAVA_HOME to your installed java folder.
- Apache Maven 3.0.5; many ways to install this, including brew install maven on Mac OSX.

## Usage
For configuration of the demo, you can:
>
> * mvn clean
> * mvn install
> * ./sbin/start-all.sh(start Spark Cluster)
> * ./bin/spark-submit --master spark://presto00:7077 /home/presto/opt/bbm-algorithm.jar "/rainbow-manage/Points.txt" "," "Point" "select a.* from (  select p1.ID, p1.PointX, p1.PointY, p2.ID as P2_ID, p2.PointX as P1_PointX, p2.PointY as P2_PointY, round((cast(p1.PointX as float) - cast(p2.PointX as float)), 4) * round((cast(p1.PointX as float) - cast(p2.PointX as float)), 4) as num from Point p1, Point p2) a where a.num = 4 and a.PointY = 2"
>

## Note
- [HADOOP](http://10.77.40.236:50070/explorer.html#/rainbow-manage).
- [Spark](http://10.77.40.236:4044/).
- in `cn.edu.ruc.iir.bbm.Main`, you can see the implementation.

## Contact
For feedback and questions with IntelliJ IDEA building the project, feel free to email us:
* Youxian Tao taoyouxian@aliyun.com

## More
See [LastVersion](https://github.com/taoyouxian/master-candidate/blob/master/mc-bbm/src/main/java/cn.edu.ruc.iir.mc.bbm/Main.java)
- Test
```sql
./bin/spark-submit --master spark://presto00:7077 /home/presto/opt/bbm-algorithm.jar "/rainbow-manage/Points.txt" "," "Point" "select a.* from (  select p1.ID, p1.PointX, p1.PointY, p2.ID as P2_ID, p2.PointX as P1_PointX, p2.PointY as P2_PointY, round((cast(p1.PointX as float) - cast(p2.PointX as float)), 4) * round((cast(p1.PointX as float) - cast(p2.PointX as float)), 4) as num from Point p1, Point p2) a where a.num = 4 and a.PointY = 2"
```
- Lib
you can see `bbm-algorithm.jar` in `docs`


