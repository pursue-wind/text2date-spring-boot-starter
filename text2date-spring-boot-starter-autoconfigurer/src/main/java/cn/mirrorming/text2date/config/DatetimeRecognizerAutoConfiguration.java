package cn.mirrorming.text2date.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mireal
 */
@Configuration
//@ConditionalOnWebApplication
@EnableConfigurationProperties(Text2DateProperties.class)
public class DatetimeRecognizerAutoConfiguration {

    @Autowired
    Text2DateProperties text2DateProperties;

    /**
     * datetimeRecognizer
     *
     * @return DatetimeRecognizer
     */
    @Bean
    public DatetimeRecognizer datetimeRecognizer() {
        DatetimeRecognizer recognizer = new DatetimeRecognizer();
        recognizer.setText2DateProperties(text2DateProperties);
        return recognizer;
    }
}
