package file.dao;

import file.entity.FilePdfImg;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * PDF文件
 * 
 * @author yindq
 * @date 2017年11月9日
 */
@Repository
public interface FilePdfImgRepository extends CrudRepository<FilePdfImg, Long> {

}
