package com.mao.test.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mao.test.entity.PPProDocPathDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author Mr mao
 * @Date 11:19
 * @Version 1.0
 * @Description
 */
@Component
@Mapper
public interface PPProDocPathMapper extends BaseMapper<PPProDocPathDTO> {

    void insertOne(PPProDocPathDTO t);

    void updateByName(PPProDocPathDTO t);

    void deleteProdocInfo(Object id);

    PPProDocPathDTO queryById(Object id);

    PPProDocPathDTO queryByName(String file_Name);

}
