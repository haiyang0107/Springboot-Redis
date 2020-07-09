package file.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import utils.DateUtil;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Description TODO
 * @Author susaifei
 * @Date 2018/7/4 14:36
 * @Version 1.0
 **/
@Component
public class CommonUtil {
    @Value("${fileserver.repository}")
    String defaultRepository;

    static String repository;

    @PostConstruct
    public void setRepository(){
        this.repository = this.defaultRepository;
    }



    /**
     * 装配文件路径
     *
     * @param request
     * @param path
     * @return
     */
    public static final String getTargetPath(HttpServletRequest request, String path){
        if(request == null)
            throw new IllegalArgumentException();
        if(path == null)
            path = "";
        else
            path = path.trim();

        path = formatPath(path);
        String targetPath = repository + path;
        createDirectoryIfNecessary(targetPath);
        return targetPath;
    }


    /**
     * 获取唯一文件名
     *
     * @return
     */
    public static final String getIdentity(){
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return (df.format(new Date()) + UUID.randomUUID());
    }


    /**
     * 格式化path
     * @param path
     * @return
     */
    private static final String formatPath(String path){
        if(path == null  || path.trim().length() == 0)
            return "";

        for(;;){
            if(path.startsWith("/")){
                path = path.replaceFirst("/","");
                continue;
            } else
                break;
        }

        for(;;)
        {
            if(path.endsWith("/")){
                path = path.substring(0, path.length()-1);
                continue;
            } else
                break;
        }
        return path;
    }

    /**
     * 创建目录
     *
     * @param absPath
     * @return
     */
    public static final void createDirectoryIfNecessary(String absPath){
        File file = new File(absPath);
        if(!file.exists()){
            file.mkdirs();
        }
    }

    public static void main(String[] args) {

//       System.out.println(getIdentity());

        System.out.println(System.getProperty("file.separator"));

//        System.out.println(formatPath("/////ddd/ddd/eee/d////"));
        String s = "D:/zkdj@ssf/work/pd_210_merchantssys/Code/fileservers/target/fileserver.jar!/BOOT-INF/c";
        String ss = s.substring(0, s.indexOf(".jar"));
        System.out.println(ss);
        System.out.println(ss.substring(0, ss.lastIndexOf("/")+1));

//        String filename = "ddddd.jpg";
//        String suffix = filename.substring(filename.lastIndexOf(".")).toLowerCase();
//        System.out.println(suffix);
    }

}
