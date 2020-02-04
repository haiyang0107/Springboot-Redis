package file.service;

import file.dao.IndustryReportRepository;
import file.entity.IndustryReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author acy
 * @date 2018/6/26
 */
@Service
public class IndustryReportServiceImpl implements IntfIndustryReportService{

    @Autowired
    private IndustryReportRepository reportRepository;
    @Override
    public IndustryReport findByTitle(String title) {

        return reportRepository.findByTitle(title);
    }

    @Override
    public int updateSizeById(String size, Long id) {
        return reportRepository.updateSizeById(size,id);
    }

    @Override
    public int updateById(String url,String size, Long id ) {
        return reportRepository.updateById(url,size, id);
    }
}
