## java8学习
> 这个项目是我阅读了《java8实战》这本书，根据书籍提供的源码做了一些修改注释,用于以后查阅复习使用  

### lambda表达式
##### basics
* 流的构建与遍历 [BuildingStreams.java](./src/main/java/com/sun/lambda/basics/BuildingStreams.java)
* 筛选与切片 [Filtering.java](./src/main/java/com/sun/lambda/basics/Filtering.java)
* 映射 [Mapping.java](./src/main/java/com/sun/lambda/basics/Mapping.java)
* 查找和匹配 [Finding.java](./src/main/java/com/sun/lambda/basics/Finding.java)
* 归约 [NumericStreams.java](./src/main/java/com/sun/lambda/basics/NumericStreams.java)  [Reducing.java](./src/main/java/com/sun/lambda/basics/Reducing.java)
* 排序 [Sorting.java](./src/main/java/com/sun/lambda/basics/Sorting.java)
* 示例/应用 [PuttingIntoPractice.java](./src/main/java/com/sun/lambda/basics/PuttingIntoPractice.java)
##### collector(用流收集数据)
* 规约与汇总 [Reducing.java](./src/main/java/com/sun/lambda/collector/Reducing.java)  [Summarizing.java](./src/main/java/com/sun/lambda/collector/Summarizing.java)
* 分组 [Grouping.java](./src/main/java/com/sun/lambda/collector/Grouping.java)
* 分区 [Partitioning.java](./src/main/java/com/sun/lambda/collector/Partitioning.java)
##### ParallelStreams(并行流)
* 并行流演示 [ParallelStreams.java](./src/main/java/com/sun/lambda/parallel/ParallelStreams.java)
##### optional(处理null)
* 处理null [optionaling.java](./src/main/java/com/sun/lambda/optional/Optionaling.java)

#### 新的日期时间API
[示例代码](./src/main/java/com/sun/DateTime/DateTimeing.java)
* 新的时间对象 DateTimeing.useLocalDate
* 时间处理接口 DateTimeing.useTemporalAdjuster
* 时间格式化 DateTimeing.useDateFormatter
