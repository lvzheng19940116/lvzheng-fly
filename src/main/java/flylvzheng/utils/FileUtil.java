package flylvzheng.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;


public class FileUtil {


    /**
     * 根据key读取value
     *
     * @param filePath
     * @param keyWord
     * @return String
     * getProperties("com/test/config/config.properties");
     * @Description: 第一种方式：根据文件名使用spring中的工具类进行解析
     * filePath是相对路劲，文件需在classpath目录下
     * application.yml   key:value  port:9999
     */
    public static String getProperties(String filePath, String keyWord) {
        Properties prop = null;
        String value = null;
        try {
            // 通过Spring中的PropertiesLoaderUtils工具类进行获取
            prop = PropertiesLoaderUtils.loadAllProperties(filePath);
            // 根据关键字查询相应的值
            value = prop.getProperty(keyWord);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }


    /**
     * @Desc:文件工具类
     * @Author: ZhangYue
     * @Date: create in 2018/1/31
     * @copyright Navi WeCloud
     */
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    /**
     * JSON文件转对象
     *
     * @param filePath 文件路径
     * @param clz      对象类型
     * @param <T>      目标类型
     * @return T
     * @throws Exception
     */
    public static <T> T readJsonFile(String filePath, Class<T> clz) throws Exception {
        File file = ResourceUtils.getFile(filePath);
        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(inputStreamReader)) {
            String line;
            StringBuilder str = new StringBuilder();
            while ((line = br.readLine()) != null) {
                str.append(line);
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(str.toString(), clz);
        }
    }


}
