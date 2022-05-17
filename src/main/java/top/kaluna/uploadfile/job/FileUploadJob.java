package top.kaluna.uploadfile.job;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import top.kaluna.uploadfile.config.FilePathConfig;
import top.kaluna.uploadfile.service.FileService;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Yuery
 * @date 2022/5/14/0014 - 22:47
 */
@Component
@Slf4j
public class FileUploadJob {

    @Resource
    private FileService fileService;
    @Resource
    private FilePathConfig filePathConfig;

    private final Logger LOG = LoggerFactory.getLogger(FileUploadJob.class);
    /**
     *
     * 指定每天的中午12点和凌晨00点上传数据到阿里云oss
     *
     */
    @Transactional(rollbackFor = Exception.class)
    @Scheduled(cron = "0 0 0,12 * * ?")
    public void cronPerWeek(){
        LOG.info("upload file start -- week");
        long start = System.currentTimeMillis();
        fileService.fileUpload(filePathConfig.getPath());
        LOG.info("upload file end -- week，耗时：{}毫秒", System.currentTimeMillis() - start);
    }
//    /**
//     *
//     * 从0秒开始,每30分钟执行一次
//     * 从physical_value表查询前半个小时tag！=0的记录到breakpoint_record记录表中
//     */
//    @Transactional(rollbackFor = Exception.class)
//    @Scheduled(cron = "0 0/30 * * * ?" )
//    public void cronPerMonth(){
//        LOG.info("upload file start --- month");
//        long start = System.currentTimeMillis();
//        String path = "D:\\桌面\\册镇海底监测需求\\网页制作测试数据";
//        fileService.fileUpload(path);
//        LOG.info("upload file end --- month，耗时：{}毫秒",System.currentTimeMillis() - start);
//    }
}
