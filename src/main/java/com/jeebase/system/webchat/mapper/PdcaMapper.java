package com.jeebase.system.webchat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jeebase.system.webchat.entity.Pdca;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 小郭
 * @version 1.0
 */
public interface PdcaMapper extends BaseMapper<Pdca> {

    /**
     * 根据条件查询返回数据
     * @param id
     * @return
     */
    List<Pdca> selectLists(Long id);

    /**
     * 根据id修改
     * @param pdca
     * @return
     */
    boolean updates(Pdca pdca);
}
