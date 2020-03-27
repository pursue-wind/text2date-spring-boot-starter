package cn.mirrorming.text2date.config;

import cn.mirrorming.text2date.time.TimeEntity;
import cn.mirrorming.text2date.time.TimeEntityRecognizer;
import lombok.Data;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * todo  springboot 配置直接加入正则搜索
 *
 * @author Mireal
 */
@Data
public class DatetimeRecognizer {
    Text2DateProperties text2DateProperties;

    public List<TimeEntity> parse(String text) throws IOException {
        TimeEntityRecognizer timeEntityRecognizer = new TimeEntityRecognizer();
        return timeEntityRecognizer.parse(text);
    }

    public List<Date> dateParse(String text) throws IOException {
        TimeEntityRecognizer timeEntityRecognizer = new TimeEntityRecognizer();
        return timeEntityRecognizer.parse(text).stream().map(TimeEntity::getValue).collect(Collectors.toList());
    }
}
