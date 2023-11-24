package com.jeebase.system.webchat.service.imp;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jeebase.system.webchat.entity.Pmis;
import com.jeebase.system.webchat.mapper.PmisMapper;
import com.jeebase.system.webchat.service.PmisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PmisServiceImpl extends ServiceImpl<PmisMapper, Pmis> implements PmisService {

    @Autowired
    PmisMapper pmisMapper;
    @Override
    public int savePmis(Pmis pmis) {
        return pmisMapper.insert(pmis);
    }

    @Override
    public int deletePmis(Long id) {
        return pmisMapper.deleteById(id);
    }

    @Override
    public int updatePmis(Pmis pmis) {
        return pmisMapper.updateById(pmis);
    }

    @Override
    public Page<Pmis> selectPmisList(Page<Pmis> page, Pmis pmis) {
        Page<Pmis> pagePmisInfo = pmisMapper.selectPmisList(page, pmis);
        return pagePmisInfo;
    }

    @Override
    public List selectPmis() {
        return pmisMapper.selectPmis();
    }
}
