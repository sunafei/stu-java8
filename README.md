## java8学习
> 这个项目是我阅读了《java8实战》这本书，根据书籍提供的源码做了一些修改注释,用于以后查阅复习使用  
> java8的新特性主要包含了lambda表达式的引入,新的接口定义(接口的默认方法),新的日期时间API  
> 程序运行环境为java9

### 目录结构
#### lambda
###### com.sn.lambda.basics(基本使用)
* 流的构建与遍历 BuildingStreams.java
* 筛选与切片 Filtering.java
* 映射 Mapping.java
* 查找和匹配 Finding.java
* 归约 NumericStreams.java Reducing.java
* 排序 Sorting
* 示例/应用 PuttingIntoPractice.java
######  com.sun.lambda.collector(用流收集数据)
* 规约与汇总 Reducing.java Summarizing.java
* 分组 Grouping.java
* 分区 Partitioning.java
###### com.sun.lambda.parallel.ParallelStreams(并行流)
###### com.sun.lambda.optional(处理null)
#### 新的日期时间API
1. com.sun.DateTime
* 新的时间对象 DateTimeing.useLocalDate
* 时间处理接口TemporalAdjuster DateTimeing.useTemporalAdjuster
* 时间格式化 DateTimeing.useDateFormatter
