package cn.mirrorming.text2date.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Mireal
 */
@Data
@ConfigurationProperties(prefix = "spring.text2date")
public class Text2DateProperties {
    private String prefix;
    private String suffix;
}
