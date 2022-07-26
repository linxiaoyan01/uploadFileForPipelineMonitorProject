package top.kaluna.uploadfile.biz;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.kaluna.uploadfile.UploadfileApplication;
import top.kaluna.uploadfile.service.FileService;

import javax.annotation.Resource;

/**
 * @author Yuery
 * @date 2022/5/17/0017 - 10:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UploadfileApplication.class)
@Slf4j
public class testFileUpload {

    @Resource
    FileService fileService;

    @Test
    public void testLocalFile() {

        fileService.fileUpload();

        //fileService.deleteFile();
    }
}
