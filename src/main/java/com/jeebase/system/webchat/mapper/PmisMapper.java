package com.jeebase.system.webchat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jeebase.system.common.dto.LogInfo;
import com.jeebase.system.common.dto.QueryLog;
import com.jeebase.system.webchat.entity.Pmis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PmisMapper extends BaseMapper<Pmis> {

    Page<Pmis> selectPmisList(Page<Pmis> page, @Param("pmis") Pmis pmis);

    List selectPmis();

}
