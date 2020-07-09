package file.dao;

import file.entity.IndustryReport;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author acy
 * @date 2018/6/26
 */
public interface IndustryReportRepository extends CrudRepository<IndustryReport, Long> {

    @Query(value="select * from t_industry_report WHERE title = ?",nativeQuery=true)
    IndustryReport findByTitle(String title);

    @Modifying
    @Transactional
    @Query(value = "update t_industry_report as c set c.url = ?, c.size = ? where id = ? ",nativeQuery=true)
    int  updateById(String url, String size, Long id);

    @Modifying
    @Transactional
    @Query(value = "update t_industry_report as c set c.size = ? where id = ? ",nativeQuery=true)
    int  updateSizeById(String size, Long id);
}
