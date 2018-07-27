package flylvzheng.utils;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @Desc:文件工具类
 * @Author: ZhangYue
 * @Date: create in 2018/1/31
 * @copyright Navi WeCloud
 */
public class FileUtil {
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
