package file.controller.file;

import com.huishu.file.FileServer.common.AjaxResult;
import com.huishu.file.FileServer.common.util.CommonUtil;
import com.huishu.file.FileServer.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Description 文件上传路径
 *
 * @Author susaifei
 * @Date 2018/7/4 13:43
 * @Version 1.0
 **/
@RestController
@RequestMapping("upload")
public class FileUploadController extends BaseController {

    Logger logger = LoggerFactory.getLogger(FileUploadController.class);
    @Value("${fileserver.repository}")
    String defaultRepository;
    /**
     * 通用单文件上传，支持存储路径
     *
     * @param sourceFile 上传文件
     * @param path  文件存储路径
     * @return
     */
    @PostMapping("")
    public AjaxResult upload(@RequestParam(value = "file")MultipartFile sourceFile,
                             @RequestParam(value = "path", required = false) String path){

        System.out.println(sourceFile.getSize());
        // 获取文件存放在服务器上面的绝对路径
        // 路径不存在则会自动创建
        String targetPath = CommonUtil.getTargetPath(request, path);

        String filename = sourceFile.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf(".")).toLowerCase();

        String targetFileName = CommonUtil.getIdentity() + suffix;
        File file = new File(targetPath + System.getProperty("file.separator") + targetFileName);


        try {
            sourceFile.transferTo(file);
            return success(targetFileName);
        } catch (IOException e) {
            logger.error(e + "");
            return error("上传失败：" + e);
        }
    }

}
