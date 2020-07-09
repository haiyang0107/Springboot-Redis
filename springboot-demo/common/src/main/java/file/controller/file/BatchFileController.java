package file.controller.file;


import constants.ConfConstant;
import file.controller.BaseController;
import file.entity.IndustryReport;
import file.service.IntfIndustryReportService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.UUID;

/**
 * 文件上传类
 * 
 * @author yindq
 * @date 2017年11月8日
 */
@RestController
@RequestMapping("file/batch")
public class BatchFileController extends BaseController {

    private static final Logger LOGGER = Logger.getLogger(BatchFileController.class);

    private static final HashMap<String, String> TypeMap = new HashMap<String, String>();

    // 设置文件允许上传的类型
    static {
        TypeMap.put("image", "gif,jpg,jpeg,png,bmp");
        TypeMap.put("pdf", "pdf,tmp");
        TypeMap.put("excel", "xls,xlsx");
        TypeMap.put("word", "doc,docx");
        TypeMap.put("PPT", "ppt,pptx");
        TypeMap.put("TXT", "txt");
    }

    // 设置文件上传大小
    public static long fileSize = 10 * 1024 * 1024;

    @Autowired
    IntfIndustryReportService industryReportService;

    /**
     * 文件上传
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/PDFUpload.do", method = RequestMethod.POST)
    public void pdfUpload(HttpServletRequest request, HttpServletResponse response, String title,String size) {
        String path = "W:/694-fujian";
        File f = new File(path);
        File[] tempList = f.listFiles();
        System.out.println("该目录下对象个数：" + tempList.length);
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                File file = tempList[i];

                String OriginalFilename = file.getName();
                Long size2=file.length();
                DecimalFormat df = new DecimalFormat("#");
                String fileSizeString = "";
                if (size2 < 1048576) {
                    fileSizeString = df.format((long) size2 / 1024) + "KB";
                } else if (size2 < 1073741824) {
                    fileSizeString = df.format((double) size2 / 1048576) + "MB";
                }

                String title2 = OriginalFilename.substring(0, OriginalFilename.lastIndexOf("."));
                String fileSuffix = OriginalFilename.substring(OriginalFilename.lastIndexOf(".") + 1).toLowerCase();
                String pdfUrl = request.getSession().getServletContext().getRealPath("/") + ConfConstant.DEFAULT_PDF;

                String newname = UUID.randomUUID() + "." + fileSuffix;
                FileInputStream fis = null;
                FileOutputStream fos = null;
                try {
                    File saveFile = new File(pdfUrl, newname);
                    if (!new File(pdfUrl).exists()) {
                        new File(pdfUrl).mkdirs();
                    }
                    fis = new FileInputStream(file);
                    fos = new FileOutputStream(saveFile);
                    byte[] b = new byte[1024];
                    int n = 0;
                    while ((n = fis.read(b)) != -1) {
                        fos.write(b, 0, n);

                    }
                    System.out.println("=======" + n);
                    fos.flush();
                    String s = ConfConstant.IP_PORT + ConfConstant.TOMCAT + ConfConstant.DEFAULT_PDF + "/" + newname;
                    IndustryReport report = industryReportService.findByTitle(title2);
                    System.out.println("-----------------report---------");
                    if (report == null)
                        continue;
                    industryReportService.updateById(s,fileSizeString, report.getId());

                } catch (Exception e) {
                    LOGGER.error("上传PDF失败！", e);
                } finally {
                    try {
                        if (fis != null)
                            fis.close();
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                    }
                }


            }
        }
        System.out.println("------------执行完成-----------------");

    }


    @RequestMapping(value = "/PDFSizeUpload.do", method = RequestMethod.POST)
    public void pdfSizeUpload(HttpServletRequest request, HttpServletResponse response,String size) {
        String path = "W:/694-fujian";
        File f = new File(path);
        File[] tempList = f.listFiles();
        System.out.println("该目录下对象个数：" + tempList.length);
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                File file = tempList[i];

                String OriginalFilename = file.getName();
                Long size2=file.length();
                DecimalFormat df = new DecimalFormat("#");
                String fileSizeString = "";
                if (size2 < 1048576) {
                    fileSizeString = df.format((long) size2 / 1024) + "KB";
                } else if (size2 < 1073741824) {
                    fileSizeString = df.format((double) size2 / 1048576) + "MB";
                }

                String title2 = OriginalFilename.substring(0, OriginalFilename.lastIndexOf("."));
                     if(i==3)
                         break;
                    IndustryReport report = industryReportService.findByTitle(title2);
                    System.out.println("-----------------report---------");
                    if(report == null)
                        continue;
                    report.setSize(fileSizeString);
                    industryReportService.updateSizeById(fileSizeString, report.getId());

                }
                }


            }
        }



