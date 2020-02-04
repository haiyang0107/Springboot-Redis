package file.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import file.dao.FilePdfImgRepository;
import file.dao.FilePdfRepository;
import file.entity.FilePdf;
import file.entity.FilePdfImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PdfServieImpl implements PdfServie{
	
	@Autowired
	private FilePdfRepository filePdfRepository;
	@Autowired
	private FilePdfImgRepository filePdfImgRepository;
	
	@Override
	public FilePdf savePdf(String url) {
		FilePdf pdf = new FilePdf();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		pdf.setCreateTime(format1.format(date));
		pdf.setData(format2.format(date));
		pdf.setDownloads(0);
		pdf.setUrl(url);
		return filePdfRepository.save(pdf);
	}

	@Override
	public Boolean savePdfImg(String url, Long pdfId, Integer sort) {
		FilePdfImg img = new FilePdfImg();
		img.setImageUrl(url);
		img.setPdfId(pdfId);
		img.setSort(sort);
		FilePdfImg save = filePdfImgRepository.save(img);
		if(save==null){
			return false;
		}
		return true;
	}

}
