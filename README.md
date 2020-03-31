# 提取中文文本里面的时间 text2date-spring-boot-starter
## 使用
### 添加依赖
```xml
<dependency>
    <groupId>io.github.mirrormingzz</groupId>
    <artifactId>text2date-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```
### 注入`DatetimeRecognizer`, 使用dateParse()/parse()进行文本解析
```java
@Autowired
private DatetimeRecognizer datetimeRecognizer;

List<Date> dates = datetimeRecognizer.dateParse("明天下午四点到五点去看电影");

List<TimeEntity> timeEntities = datetimeRecognizer.parse("明天下午四点到五点去看电影");
```


- 2020-3-27 添加：半小时前/后和xx小时前/后 的解析

### 参考项目：
> mandarintools：https://github.com/luoxuefeng01/mandarintools-1

> 复旦大学fnlp：https://github.com/FudanNLP/fnlp
时间处理模块见：package org.fnlp.nlp.cn.ner;

> Microsoft/Recognizers-Text：https://github.com/microsoft/Recognizers-Text
> **微软的 Java - datetime 目前未完成**

> Time-NLP：https://github.com/shinyke/Time-NLP

> TimeRecognition：https://github.com/senlinxuefeng/TimeRecognition

> TimeRec：https://github.com/wbq813/TimeRec

> ALBERT_4_Time_Recognition：https://github.com/percent4/ALBERT_4_Time_Recognition

> Chinese_Time_Recogniztion：https://github.com/percent4/Chinese_Time_Recogniztion
