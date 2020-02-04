package file.controller.file;

import com.huishu.file.FileServer.common.AjaxResult;
import com.huishu.file.FileServer.common.conf.ConfConstant;
import com.huishu.file.FileServer.controller.BaseController;
import com.huishu.file.FileServer.entity.FilePdf;
import com.huishu.file.FileServer.service.PdfServie;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

/**
 * 文件上传类
 * 
 * @author yindq
 * @date 2017年11月8日
 */
@RestController
@RequestMapping("file")
public class FileController extends BaseController {
	
	private static final Logger LOGGER = Logger.getLogger(FileController.class);

	private static final HashMap<String, String> TypeMap = new HashMap<String, String>();
	// 设置文件允许上传的类型
	static {
		TypeMap.put("image", "gif,jpg,jpeg,png,bmp");
		TypeMap.put("pdf", "pdf,tmp");
		TypeMap.put("excel", "xls,xlsx");
		TypeMap.put("word","doc,docx");
		TypeMap.put("PPT","ppt,pptx");
		TypeMap.put("TXT","txt");
	}
	
	// 设置文件上传大小
	public static long fileSize = 10 * 1024 * 1024;
	
	@Autowired
	private PdfServie pdfServie;
	/**
	 * 文件上传
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/PDFUpload.do", method = RequestMethod.POST)
	public AjaxResult pdfUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("file name is :" + file.getOriginalFilename());
		if (!file.isEmpty()) {
			if (file.getSize() > fileSize) {
				return error("文件超过上传大小");
			}
			String OriginalFilename = file.getOriginalFilename();
			String fileSuffix = OriginalFilename.substring(OriginalFilename.lastIndexOf(".") + 1).toLowerCase();
			if (!Arrays.asList(TypeMap.get("pdf").split(",")).contains(fileSuffix)) {
				return error("文件格式错误");
			}
			String url = request.getSession().getServletContext().getRealPath("/");
			String pdfUrl = request.getSession().getServletContext().getRealPath("/") + ConfConstant.DEFAULT_PDF;
			File uploadDir = new File(pdfUrl);
			if (!uploadDir.isDirectory()) {
				if (!uploadDir.mkdir()) {
					return error("上传文件路径非法");
				}
			}
			if (!uploadDir.canWrite()) {
				return error("上传目录没有写权限");
			}
			if (!ServletFileUpload.isMultipartContent(request)) {
				return error("没有文件上传");
			}
			
			String newname = UUID.randomUUID() + "." + fileSuffix;
			try {
				File saveFile = new File(pdfUrl, newname);
				if(!new File(pdfUrl).exists())   {
				    new File(pdfUrl).mkdirs();
				}
				file.transferTo(saveFile);
				FilePdf pdf = pdfServie.savePdf(ConfConstant.IP_PORT+ConfConstant.TOMCAT+ConfConstant.DEFAULT_PDF + "/" + newname);
				System.out.println("-----------pdf---------:"+pdf.getUrl());
				new Thread(new Runnable() {
					@Override
					public void run() {
						String filePath = pdfUrl + "/" + newname;
						Document document = new Document();
						try {
							document.setFile(filePath);
							float scale = 1.0f;// 缩放比例（大图）
							float rotation = 0f;// 旋转角度
							String path=UUID.randomUUID().toString();
							for (int i = 0; i < document.getNumberOfPages(); i++) {
								BufferedImage image = (BufferedImage) document.getPageImage(i,
										GraphicsRenderingHints.SCREEN,
										org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX,
										rotation, scale);
								RenderedImage rendImage = image;
								try {
									File file = new File(url+ConfConstant.DEFAULT_PDF_IMG + "/" +path+"/icepdf_" + i + ".jpg");
									if(!new File(url+ConfConstant.DEFAULT_PDF_IMG + "/" +path).exists())   {
									    new File(url+ConfConstant.DEFAULT_PDF_IMG + "/" +path).mkdirs();
									}
									// 这里png作用是：格式是jpg但有png清晰度
									ImageIO.write(rendImage, "png", file);
									pdfServie.savePdfImg(ConfConstant.IP_PORT+ConfConstant.TOMCAT+ConfConstant.DEFAULT_PDF_IMG + "/" +path+"/icepdf_" + i + ".jpg", pdf.getId(), i);
								} catch (Exception e) {
									e.printStackTrace();
								}
								image.flush();
							}
							document.dispose();
						} catch (Exception e1) {
							LOGGER.error("PDF转图片失败！", e1);
						}
					}
				}).start();
				return success(true).setMessage("上传成功");
			} catch (Exception e) {
				LOGGER.error("上传PDF失败！", e);
				return error("上传失败");
			}
		} else {
			 return error("没有文件上传");
		}
	}
	
	/**
	 * 图片上传
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/imageUpload.do", method = RequestMethod.POST)
	public AjaxResult imageUpload(@RequestParam("image") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("file name is :" + file.getOriginalFilename());
		if (!file.isEmpty()) {
			if (file.getSize() > fileSize) {
				return error("图片超过上传大小");
			}
			String OriginalFilename = file.getOriginalFilename();
			String fileSuffix = OriginalFilename.substring(OriginalFilename.lastIndexOf(".") + 1).toLowerCase();
			if (!Arrays.asList(TypeMap.get("image").split(",")).contains(fileSuffix)) {
				return error("图片格式错误");
			}
			String url = request.getSession().getServletContext().getRealPath("/") + ConfConstant.DEFAULT_USER;
			File uploadDir = new File(url);
			if (!uploadDir.isDirectory()) {
				if (!uploadDir.mkdir()) {
					return error("上传图片路径非法");
				}
			}
			if (!uploadDir.canWrite()) {
				return error("上传目录没有写权限");
			}
			if (!ServletFileUpload.isMultipartContent(request)) {
				return error("没有图片上传");
			}
			String newname = UUID.randomUUID() + "." + fileSuffix;
			try {
				File saveFile = new File(url, newname);
				if(!new File(url).exists())   {
				    new File(url).mkdirs();
				}
				file.transferTo(saveFile);
				return success(ConfConstant.IP_PORT+ConfConstant.TOMCAT+ConfConstant.DEFAULT_USER + "/" + newname).setMessage("上传成功");
			} catch (Exception e) {
				LOGGER.error("上传图片失败！", e);
				return error("上传失败");
			}
		} else {
			 return error("没有图片上传");
		}
	}

	/**
	 * 文件的下载
	 * @param filePath
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/downLoad.do", method = RequestMethod.GET)
	public void downLoad(String filePath, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = request.getSession().getServletContext().getRealPath("/") + ConfConstant.DEFAULT_PDF;
        File f = new File(url,filePath);
        if (!f.exists()) {
            response.sendError(404, "File not found!");
            return;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;

        response.reset(); // 非常重要
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
        out.write(buf, 0, len);
        br.close();
        out.close();
    }
}
