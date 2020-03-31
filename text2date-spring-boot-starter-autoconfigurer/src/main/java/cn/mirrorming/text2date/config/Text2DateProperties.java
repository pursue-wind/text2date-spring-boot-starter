package cn.mirrorming.text2date.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Text2DateProperties
 *
 * @author Mireal
 */
@Data
@ConfigurationProperties(prefix = "spring.text2date")
public class Text2DateProperties {
    private String regx;
    private String result;
}
