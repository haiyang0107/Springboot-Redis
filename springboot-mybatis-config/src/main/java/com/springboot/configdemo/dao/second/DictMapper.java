package com.springboot.configdemo.dao.second;

import com.springboot.configdemo.entity.second.Dict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DictMapper {

    public List<Dict> findList();

    /**
     * mybatis insert 返回结果只有-1，0和1
     * @param dict
     * @return
     */
    public  int insertDict(Dict dict);

    public  int deleteDict(int id);
}
