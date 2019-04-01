package flylvzheng.restupload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 以动手实践为荣,以只看不练为耻.
 * 以打印日志为荣,以出错不报为耻.
 * 以局部变量为荣,以全局变量为耻.
 * 以单元测试为荣,以手工测试为耻.
 * 以代码重用为荣,以复制粘贴为耻.
 * 以多态应用为荣,以分支判断为耻.
 * 以定义常量为荣,以魔法数字为耻.
 * 以总结思考为荣,以不求甚解为耻.
 *
 * @author LvZheng
 * 创建时间：2019/3/13 上午11:00
 */
public class RestTemplateUpload {


    @Autowired
    private MadpUploadUtil madpUploadUtil;

    public Object login(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            // return "file is empty.";
        }

        String originalFilename = file.getOriginalFilename();
        String newFileName = originalFilename.substring(originalFilename.lastIndexOf(".") - 1);
        File file1 = null;
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            File upload = new File(path.getAbsolutePath(), "static/tmpupload/");
            if (!upload.exists()) {
                upload.mkdirs();
            }
            String uploadPath = upload + "\\";
            file1 = new File(uploadPath + originalFilename);
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String aaa = madpUploadUtil.upload("MTA5N0FBOENGNUI5NDQ5NjkzRkU3NzBEQ0YzRDBDMTgxNTUxODU4NzI5OTQw", file1);
        return null;
    }
}
