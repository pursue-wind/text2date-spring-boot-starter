package cn.mirrorming.text2date.config;

import cn.mirrorming.text2date.time.TimeEntity;
import cn.mirrorming.text2date.time.TimeEntityRecognizer;
import lombok.Setter;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * todo  springboot 配置直接加入正则搜索
 *
 * @author Mireal
 */
public class DatetimeRecognizer {
    @Setter
    Text2DateProperties text2DateProperties;

    private TimeEntityRecognizer timeEntityRecognizer = new TimeEntityRecognizer();

    /**
     * 文本转 {@link TimeEntity}
     *
     * @param text 需要解析的文本
     * @return {@link TimeEntity}
     * @throws IOException IO异常
     */
    public List<TimeEntity> parse(String text) {
        return timeEntityRecognizer.parse(text);
    }

    /**
     * 文本转 {@link Date}
     *
     * @param text 需要解析的文本
     * @return {@link Date}
     * @throws IOException IO异常
     */
    public List<Date> dateParse(String text) {
        return timeEntityRecognizer.parse(text)
                .stream()
                .map(TimeEntity::getValue)
                .collect(Collectors.toList());
    }
}
