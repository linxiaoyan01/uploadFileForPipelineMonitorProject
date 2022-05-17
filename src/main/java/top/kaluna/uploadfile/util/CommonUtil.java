package top.kaluna.uploadfile.util;

import java.util.UUID;

/**
 * @author Yuery
 * @date 2022/5/14/0014 - 20:14
 */
public class CommonUtil {


    /**
     * 生成uuid
     * @return
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-","").substring(0,32);
    }
}
