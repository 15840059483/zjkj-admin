package com.jeebase.system.webchat.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jeebase.system.webchat.entity.Pmis;

import java.util.List;

public interface PmisService extends IService<Pmis> {

    int savePmis(Pmis pmis);

    int deletePmis(Long id);

    int updatePmis(Pmis pmis);

    Page<Pmis> selectPmisList(Page<Pmis> page, Pmis pmis);

    List selectPmis();

}
