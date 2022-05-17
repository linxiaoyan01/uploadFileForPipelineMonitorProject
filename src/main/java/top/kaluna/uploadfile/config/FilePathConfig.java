package top.kaluna.uploadfile.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yuery
 * @date 2022/5/14/0014 - 22:42
 */
@ConfigurationProperties(prefix = "file")
@Configuration
@Data
public class FilePathConfig {

    private String path;

}
