package com.jeebase.system.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jeebase.system.common.dto.DictInfo;
import com.jeebase.system.common.entity.Dict;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 数据字典表 Mapper 接口
 * </p>
 *
 * @author fyy
 * @since  
 */
public interface DictMapper extends BaseMapper<Dict> {
    /**
     * 查询字典列表
     * @param parentId
     * @return
     */
    List<DictInfo> queryDictTreeProc(Integer parentId);
}

