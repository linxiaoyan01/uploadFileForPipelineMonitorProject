package top.kaluna.uploadfile;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 86158
 */
@SpringBootApplication
@MapperScan("top.kaluna.uploadfile.mapper")
@EnableScheduling
public class UploadfileApplication {
    public static void main(String[] args) {
            SpringApplication.run(UploadfileApplication.class, args);
    }
}
