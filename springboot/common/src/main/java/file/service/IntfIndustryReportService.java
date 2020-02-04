package file.service;


import file.entity.IndustryReport;

/**
 * @author acy
 * @date 2018/6/26
 */
public interface IntfIndustryReportService {

    IndustryReport findByTitle(String title);

    int  updateById(String url, String size, Long id);

    int  updateSizeById(String size, Long id);
}
