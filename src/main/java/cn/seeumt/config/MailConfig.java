package cn.seeumt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "qqmail")
public class MailConfig {

    private String sender;

    /**
     * 标题
     */
    private String subject;

    /**
     * 内容
     */
    private String text;


}
