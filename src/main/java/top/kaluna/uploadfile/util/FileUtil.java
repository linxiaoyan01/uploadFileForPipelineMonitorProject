package top.kaluna.uploadfile.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Yuery
 * @date 2022/5/17/0017 - 9:46
 */
public class FileUtil {

    /**
     * 列出所有文件名包括该路径下所有文件夹下的文件名
     */
    public static List<String> getFileNames(String path) {
        // get file list where the path has
        File file = new File(path);
        // get the folder list
        File[] array = file.listFiles();
        List<String> names = new ArrayList<>();
        for (int i = 0; i < Objects.requireNonNull(array).length; i++) {
            if (array[i].isFile()) {
                final String name = array[i].getName();
                names.add(name);
                //System.out.println(array[i].getPath());
            } else if (array[i].isDirectory()) {
                getFileNames(array[i].getPath());
            }
        }
        return names;
    }

    /**
     * 列出所有文件名包括该路径下所有文件夹下的文件名
     */
    public static void deleteFile(String path) {
        // get file list where the path has
        File file = new File(path);
        // get the folder list
        File[] array = file.listFiles();

        for (int i = 0; i < Objects.requireNonNull(array).length; i++) {
            if (array[i].isFile()) {
                array[i].delete();
            } else if (array[i].isDirectory()) {
                deleteFile(array[i].getPath());
            }
        }
    }

}
