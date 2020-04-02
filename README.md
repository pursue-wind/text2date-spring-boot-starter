# 提取中文文本里面的时间
## 使用
### 添加依赖
```xml
<dependency>
    <groupId>io.github.mirrormingzz</groupId>
    <artifactId>text2date-spring-boot-starter</artifactId>
    <version>1.0.3</version>
</dependency>
```
### 注入`DatetimeRecognizer`
```java
@Autowired
private DatetimeRecognizer datetimeRecognizer;
```
### 使用dateParse()/parse()进行文本解析
```java
List<Date> dates = datetimeRecognizer.dateParse("半个小时后提醒我去买票");

List<TimeEntity> timeEntities = datetimeRecognizer.parse("明天下午四点到五点去看电影");
```


- 2020-4-2 修复：半个小时，半个钟头前/后和xx小时前/后 的解析
- 2020-3-27 添加：半小时前/后和xx小时前/后 的解析

### TODO:
- 节假日解析
- 时间短语解析
- 是否倾向未来时间可配置
- 自定义添加解析参数

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
