package com.jeebase.system.webchat.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jeebase.common.annotation.auth.NoAuthentication;
import com.jeebase.common.base.PageResult;
import com.jeebase.common.base.Result;
import com.jeebase.system.common.entity.Dict;
import com.jeebase.system.common.service.IDictService;
import com.jeebase.system.security.entity.User;
import com.jeebase.system.security.service.IUserService;
import com.jeebase.system.webchat.entity.Pdca;
import com.jeebase.system.webchat.entity.PdcaNo;
import com.jeebase.system.webchat.entity.Pmis;
import com.jeebase.system.webchat.mapper.PdcaMapper;
import com.jeebase.system.webchat.service.PdcaService;
import com.jeebase.system.webchat.service.PmisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 小郭
 * @version 1.0
 */
@RestController
@RequestMapping("/pdca")
@Slf4j
@Api(tags = "基础功能维护模块")
public class PdcaController {
    @Autowired
    private IDictService dictService;
    @Autowired
    private IUserService userService;
    @Autowired
    private PdcaService pdcaService;

    @Autowired
    PmisService pmisService;
    @Autowired
    private PdcaMapper pdcaMapper;

//
//    /**
//     * 查询全部
//     * @return
//     */
//    }
    /**
     * 添加数据
     * @param pdca
     * @return
     */
    @PostMapping("/save")
    @ApiOperation("添加")
    public Result save(  @RequestBody Pdca pdca){
        log.info("执行添加===> {}",pdca);
        String pmisName = pdca.getPmisName();// TODO 根据项目名称，从项目表取出项目id
        LambdaQueryWrapper<Pmis> pmislqw = new LambdaQueryWrapper<>();
        pmislqw.eq(Pmis::getName,pmisName);
//    @PostMapping("/test")
////    @ApiOperation("查询全部单独接口")  //
//    public Result<List<Pdca>> getAll (){
//        List<Pdca> list = pdcaService.list();
//        return new Result().put(list).success("成功了");
        Pmis pmis = pmisService.getOne(pmislqw);
        Long pmisId = pmis.getId();  //项目id

        String pdmName = pdca.getPdmName();  //根据产品名称在字典表取出产品id，产品负责人id，再根据产品负责人id在user表中取出产品负责人姓名pmpName
        LambdaQueryWrapper<Dict> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Dict::getDictName,pdmName);
        Dict dict = dictService.getOne(lqw);
        Long pdmId = dict.getId();         //产品id
        Long creator = dict.getCreator();  //产品负责人人id
        LambdaQueryWrapper<User> lqw1 = new LambdaQueryWrapper<>();
        lqw1.eq(User::getId,creator);
        User user = userService.getOne(lqw1);
        String pmpName = user.getUserName();  //产品负责人姓名



        Long pdcaId = creator; //根据计划申请人id 在用户表中取出用户姓名放入pdcaName
//        LambdaQueryWrapper<User> lqw2 = new LambdaQueryWrapper<>();
//        lqw1.eq(User::getId,pdcaId);
//        User user2 = userService.getOne(lqw1);
        String pdcaName = user.getUserName();  //申请人姓名

        String brdName = pdca.getBrdName(); //根据需求名称，从字典表中取出需求id
        LambdaQueryWrapper<Dict> lqw3 = new LambdaQueryWrapper<>();
        lqw3.eq(Dict::getDictName,brdName);
        Dict dict2 = dictService.getOne(lqw);
        Long brdId = dict2.getId();  //需求id

        pdca.setPmisId(pmisId); //项目id
        pdca.setPdmId(pdmId);   //产品id
        pdca.setPmpId(String.valueOf(creator));    //产品负责人id
        pdca.setPmpName(pmpName); //产品负责人
        pdca.setPdcaName(pdcaName); //计划申请人
        pdca.setPdcaId(pdcaId);
        pdca.setBrdId(brdId);     //需求id
        boolean save = pdcaService.save(pdca);
        if (save)
        {
            return new Result().success("添加成功了");
        }else
        {
            return new Result().error("添加失败");
        }

    }
    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation("删除数据")
    public Result deleteById(Long id){
        log.info("背删除人id=== {}",id);
        //条件构造器
        LambdaUpdateWrapper<Pdca> luw = new LambdaUpdateWrapper<>();
        luw.eq(Pdca::getId,id);
        luw.set(Pdca::getDelFlag,"1");    //0不删除，1删除
        boolean b = pdcaService.update(luw);
        if (b)
        {
            return new Result().success("删除成功了");
        }else
        {
            return new Result().error("删除失败");
        }

    }

    /**
     * 根据id修改
     * @param pdca
     * @return
     */
    @PutMapping
    @ApiOperation("修改数据")
    public Result update(@RequestBody Pdca pdca){
        log.info("执行修改数据===》 {}",pdca);
       boolean b =  pdcaMapper.updates(pdca);
        return new Result().success(b ? "修改成功":"修改失败");
    }

    /**
     * 根据条件查询数据
     * @param id
     * @return
     */
    @GetMapping("/lists/{id}")
    @ApiOperation("员工查询个人记录")
    public Result<List<Pdca>> selects(@PathVariable("id") Long id){
        log.info("修改人id====》 {}",id);
        LambdaQueryWrapper<Pdca> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Pdca::getCreator,id);
        lqw.eq(Pdca::getDelFlag,"0");
        lqw.orderByAsc(Pdca::getCreateTime);
        List<Pdca> lists =  pdcaMapper.selectList(lqw);
        return new Result<List<Pdca>>().put(lists);
    }

    /**
     * 分页查询
     * @param pdca
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("分页查询")
    public PageResult<Pdca> selectPages(@RequestBody PdcaNo pdca){
        log.info("分页数据===》 {}",pdca);
                                      /** //项目名          产品名        审核状态            创建人
//        int pageNo, int pageSize, String pmisName, String pdmName, String qaCode, String pdcaName
        ,LocalDate beginDate, LocalDate endDate*/
        String pmisName = pdca.getPmisName();
        String qaCode = pdca.getQaCode();
        String pdcaName = pdca.getPdcaName();
        String pdmName = pdca.getPdmName();
        LocalDate beginDate = pdca.getBeginDate();
        LocalDate endDate = pdca.getEndDate();
        //构造分页构造器
        Page<Pdca> pageinfo = new Page<>(pdca.getPageNo(), pdca.getPageSize());
        LambdaQueryWrapper<Pdca> lqw = new LambdaQueryWrapper<>();
        lqw.eq(qaCode != null , Pdca::getQaCode,qaCode);
        lqw.like(pmisName != null,Pdca::getPmisName,pmisName);
        lqw.like(pdcaName != null, Pdca::getPdcaName , pdcaName);
        lqw.like(pdmName != null, Pdca::getPdmName , pdmName);
        lqw.ge(beginDate != null,Pdca::getCreateTime,beginDate); //大于创建时间
        lqw.le(endDate != null,Pdca::getCreateTime,endDate);   //小于创建时间
        lqw.orderByAsc(Pdca::getCreateTime);
        pdcaService.page(pageinfo,lqw);

        PageResult<Pdca> pageResult = new PageResult<Pdca>(pageinfo.getTotal(), pageinfo.getRecords());
        return pageResult;
    }


    /**
     * 查询字典里列表
     *
     * @param
     * @return
     */
    @GetMapping( "/separate")
    @NoAuthentication
    @ApiOperation("立项需求项目")
    public Result<Map<String,List<Dict>>> queryDictList() {
        log.info("执行立项需求项目查询");
        //项目
        List lists = pmisService.selectPmis();

        //查询产品
        LambdaQueryWrapper<Dict> lqw = new LambdaQueryWrapper<>();
        lqw.select(Dict::getId,Dict::getDictName);
        lqw.eq(Dict::getParentId,1726780464144400385L);
        Map<String, List<Dict>> maps = new HashMap<>();
        List<Dict> dictList = dictService.list(lqw);
        maps.put("pdm",dictList);

        //查询需求
        LambdaQueryWrapper<Dict> lqw2 = new LambdaQueryWrapper<>();
        lqw2.select(Dict::getId,Dict::getDictName);
        lqw2.eq(Dict::getParentId,1726780543366414338L);
        List<Dict> dictList2 = dictService.list(lqw2);
        maps.put("brd",dictList2);

        maps.put("pmis",lists);
        return new Result<Map<String,List<Dict>>>().success().put(maps);
    }

    //审核qaCode  0 通过 1未审核
    @GetMapping("/code/{id}")
    @ApiOperation("审核")
    public Result Audit(Long id){
        log.info("背审核人id=== {}",id);
        LambdaUpdateWrapper<Pdca> luw = new LambdaUpdateWrapper<>();
        luw.eq(Pdca::getId,id);
        luw.set(Pdca::getQaCode,"0");
        boolean b = pdcaService.update(luw);
        if (b) {
            return new Result().success("成功");
        }else {
            return new Result().error("失败了");
        }

    }

    @GetMapping("/finish/{id}")
    @ApiOperation("完成")
    public Result finish(Long id,String aaa){
        LambdaUpdateWrapper<Pdca> luw = new LambdaUpdateWrapper<>();
        luw.eq(Pdca::getId,id);
        luw.set(Pdca::getFctDate, LocalDateTime.now());
        boolean b = pdcaService.update(luw);
        if (b) {
            return new Result().success("成功");
        }else {
            return new Result().error("失败了");
        }
    }

//    public static void main(String[] args) {
//        System.out.println(LocalDateTime.now());
//    }
}
