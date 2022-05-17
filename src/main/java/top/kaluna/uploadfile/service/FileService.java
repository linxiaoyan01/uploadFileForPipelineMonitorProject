package top.kaluna.uploadfile.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Yuery
 * @date 2022/5/14/0014 - 17:45
 */
public interface FileService {

    void fileUpload(String path);
    void deleteFile(String path);
}
