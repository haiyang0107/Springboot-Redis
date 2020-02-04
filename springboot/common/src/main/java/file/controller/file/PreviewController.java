package file.controller.file;

import org.apache.catalina.connector.CoyoteOutputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description TODO
 * @Author susaifei
 * @Date 2018/7/27 14:00
 * @Version 1.0
 **/
@Controller
@RequestMapping("preview")
public class PreviewController {

    @GetMapping(value = "pdf")
    public String pdfView(@RequestParam String file, Model model){
        model.addAttribute("file", file);
        return "pdf";
    }

    @Value("${spring.resources.static-locations}")
    private String filePath;

    @GetMapping(value = "pdf2")
    @ResponseBody
    public void read(@RequestParam String file, HttpServletRequest req, HttpServletResponse response){
//        response.reset();
        CoyoteOutputStream fos = null;
        FileInputStream fis = null;
        try {
            response.setContentType("application/pdf");
            fos = (CoyoteOutputStream) response.getOutputStream();

            File targetFile = ResourceUtils.getFile(filePath + file);
            fis = new FileInputStream(targetFile);
            response.setHeader("Content-length", targetFile.length()+"");
            response.setHeader("Accept-Ranges: ", "bytes");
            //设置缓存区
            ByteBuffer buffer = ByteBuffer.allocate(1);
            //输入通道
            FileChannel readChannel = fis.getChannel();

            long startTime = System.currentTimeMillis();
            while(true){
                buffer.clear();
                int len = readChannel.read(buffer);//读入数据
                if(len < 0){
                    break;//传输结束
                }
                buffer.flip();
                fos.write(buffer);
            }
//            fos.flush();
            long endTime = System.currentTimeMillis();
            System.out.println("花费："+ (endTime-startTime) / 1000 +"秒");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
            }
        }

    }
}
