package com.pokeman.gateway.controller;

import com.pokeman.dao.impl.DollInfoDaoImpl;
import com.pokeman.gateway.controller.dto.DollAddDto;
import com.pokeman.gateway.controller.dto.DollGroupDto;
import com.pokeman.gateway.controller.dto.DollQuery;
import com.pokeman.model.PmDollGroup;
import com.pokeman.model.PmDollInfo;
import com.pokeman.sercice.doll.IDollService;
import com.pokeman.sercice.dollGroup.IDollGroupService;
import com.pokeman.util.UUIDGeneratorUtils;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ycy
 * @since 2017/7/19
 * http://localhost:8090/controller/testAction/saveDollInfo
 */
@Log4j
@Controller
@RequestMapping("testAction")
public class TestAction {

    @Resource(name = "dollService")
    private IDollService dollService;

    @Resource(name = "dollGroupService")
    private IDollGroupService dollGroupService;
    @ResponseBody
    @RequestMapping(value = "viewAll", method = RequestMethod.POST)//方法级别Mapping
    public void viewAll(String name, String pwd) {

        log.info("name=" + name);

        log.info("pwd=" + pwd);

    }

    /**
     * 创建玩偶
     */
    @ResponseBody
    @RequestMapping(value = "saveDollInfo", method = RequestMethod.POST)//方法级别Mapping
    public Map saveDollInfo(@RequestBody DollAddDto dollAddDto) {
        return dollService.saveDollInfo(dollAddDto);
    }

    /**
     * 查询玩偶列表
     */
    @ResponseBody
    @RequestMapping(value = "findDollInfoList", method = RequestMethod.GET)//方法级别Mapping
    public List<DollAddDto> findDollInfoList(@ModelAttribute DollQuery dollQuery) {
        return dollService.findPmDollInfoList(dollQuery);
    }

    /**
     * 删除玩偶
     */
    @ResponseBody
    @RequestMapping(value = "deleteDollInfoById/{dollId}", method = RequestMethod.POST)//方法级别Mapping
    public Boolean deleteDollInfoById(@PathVariable String dollId) {
        return dollService.deleteDollInfoById(dollId);
    }



    /**
     * 创建玩偶组
     */
    @ResponseBody
    @RequestMapping(value = "saveDollGroup", method = RequestMethod.POST)//方法级别Mapping
    public String saveDollGroup(@RequestBody DollGroupDto dollGroupDto) {
        return dollGroupService.saveDollGroup(dollGroupDto);
    }

    /**
     * 查询玩偶列表
     */
    @ResponseBody
    @RequestMapping(value = "findDollGroupList", method = RequestMethod.GET)//方法级别Mapping
    public List<PmDollGroup> findDollGroupList(String dollGroupName) {
        return dollGroupService.findDollGroupList(dollGroupName);
    }


}
