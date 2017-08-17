package com.pokeman.sercice.doll.impl;

import com.pokeman.PokemanConstant;
import com.pokeman.dao.impl.DollGroupMemberDaoImpl;
import com.pokeman.dao.impl.DollInfoDaoImpl;
import com.pokeman.gateway.controller.dto.DollAddDto;
import com.pokeman.gateway.controller.dto.DollQuery;
import com.pokeman.model.PmDollGroupMember;
import com.pokeman.model.PmDollInfo;
import com.pokeman.sercice.doll.IDollService;
import com.pokeman.util.DateUtil;
import com.pokeman.util.UUIDGeneratorUtils;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ycy
 * @since 2017/7/31
 */
@Log4j
@Service("dollService")
public class DollServiceImpl implements IDollService {

    @Resource(name = "dollInfoDaoImpl")
    private DollInfoDaoImpl dollInfoDao;

    @Resource(name = "dollGroupMemberDaoImpl")
    private DollGroupMemberDaoImpl dollGroupMemberDao;

    public Map saveDollInfo(DollAddDto dollAddDto) {
        String time = DateUtil.getCurrentTime();
        log.info("准备创建数据...");
        PmDollInfo dollInfo = new PmDollInfo();
        dollInfo.setDollId(UUIDGeneratorUtils.getUUID());
        dollInfo.setDollName(dollAddDto.getDollName());
        dollInfo.setDollLevel(dollAddDto.getDollLevel());
        dollInfo.setDollType(dollAddDto.getDollType());
        dollInfo.setCreateTime(time);
        log.info("随机生成的玩偶id：" + dollInfo.getDollId());
        String dollId = dollInfo.getDollId();
        int a = dollInfoDao.saveDollInfo(dollInfo);
        log.info("插入数量：" + a);
        //如果是创建机器人玩偶，则需给机器人添加玩偶
        if (dollInfo.getDollType() == PokemanConstant.TYPE_ROBOT) {
            List<DollAddDto> dollChildList = dollAddDto.getDollChildList();
            if (dollChildList != null && dollChildList.size() > 0) {
                for (DollAddDto dollChild : dollChildList) {
                    PmDollGroupMember dollGroupMember = new PmDollGroupMember();
                    dollGroupMember.setDgmId(UUIDGeneratorUtils.getUUID());
                    dollGroupMember.setDollGroupId(dollId);
                    dollGroupMember.setDollId(dollChild.getDollId());
                    dollGroupMember.setType(PokemanConstant.TYPE_ROBOT);
                    dollGroupMember.setCreateTime(time);
                    dollGroupMemberDao.saveDollToDollGroup(dollGroupMember);
                    //如果机器人玩偶下存在紫色机器人，则需要给紫色机器人添加玩偶
                    List<DollAddDto> dollChildPurPleList = dollChild.getDollChildList();
                    if (dollChildPurPleList != null && dollChildPurPleList.size() > 0 &&
                            dollChild.getDollLevel() == PokemanConstant.DOLL_LEVEL_PURPLE &&
                            dollChild.getDollType() == PokemanConstant.DOLL_ROBOT_TYPE) {
                        for (DollAddDto dollChildPurPle : dollChildPurPleList) {
                            PmDollGroupMember dollGroupMemberPurPle = new PmDollGroupMember();
                            dollGroupMemberPurPle.setDgmId(UUIDGeneratorUtils.getUUID());
                            dollGroupMemberPurPle.setDollGroupId(dollChild.getDollId());
                            dollGroupMemberPurPle.setDollId(dollChildPurPle.getDollId());
                            dollGroupMemberPurPle.setType(PokemanConstant.TYPE_ROBOT);
                            dollGroupMemberPurPle.setCreateTime(time);
                            dollGroupMemberDao.saveDollToDollGroup(dollGroupMemberPurPle);
                        }
                    }
                }
            } else {
                throw new RuntimeException("机器人玩偶集合不能为空");
            }
        }
        Map map = new HashMap();
        if (a > 0) {
            map.put("state:", "success");
        } else {
            map.put("state:", "false");
        }
        return map;
    }

    public List<DollAddDto> findPmDollInfoList(DollQuery dollQuery) {
        List<DollAddDto> dollAddDtoList = new ArrayList<DollAddDto>();
        List<PmDollInfo> dollInfoList = dollInfoDao.findDollInfoList(dollQuery);
        if (dollInfoList != null && dollInfoList.size() > 0) {
            for (PmDollInfo dollInfo : dollInfoList) {
                DollAddDto dollAddDto = new DollAddDto();
                dollAddDto.setDollId(dollInfo.getDollId());
                dollAddDto.setDollName(dollInfo.getDollName());
                dollAddDto.setDollLevel(dollInfo.getDollLevel());
                int dollType = dollInfo.getDollType();
                if (dollType == PokemanConstant.DOLL_TYPE) {
                    dollAddDto.setDollType(dollType);
                } else if (dollType == PokemanConstant.DOLL_ROBOT_TYPE) {
                    //获取机器人的玩偶集合
                    List<PmDollGroupMember> dollGroupMemberList = dollGroupMemberDao.findDollGroupMember(dollInfo.getDollId(), PokemanConstant.TYPE_ROBOT);
                    if (dollGroupMemberList != null && dollGroupMemberList.size() > 0) {
                        List<DollAddDto> dollChildList = new ArrayList<DollAddDto>();
                        for (PmDollGroupMember dollGroupMember : dollGroupMemberList) {
                            DollAddDto dollChildDto = new DollAddDto();
                            PmDollInfo pmDollInfo = dollInfoDao.findDollById(dollGroupMember.getDollId());
                            dollChildDto.setDollId(pmDollInfo.getDollId());
                            dollChildDto.setDollName(pmDollInfo.getDollName());
                            int dollChildLevel = pmDollInfo.getDollLevel();
                            int dollChildType = pmDollInfo.getDollType();
                            dollChildDto.setDollLevel(dollChildLevel);
                            dollChildDto.setDollType(dollChildType);
                            //如果橙色机器人下的玩偶是紫色机器人,说明紫色机器人下还有用机器人集合
                            if (dollInfo.getDollLevel() == PokemanConstant.DOLL_LEVEL_ORANGE &&
                                    dollChildLevel == PokemanConstant.DOLL_LEVEL_PURPLE &&
                                    dollChildType == PokemanConstant.DOLL_ROBOT_TYPE) {

                            }
                            dollChildList.add(dollChildDto);
                        }
                        dollAddDto.setDollChildList(dollChildList);
                    } else {
                        throw new RuntimeException("机器人的玩偶集合数据异常");
                    }
                }

                dollAddDtoList.add(dollAddDto);
            }
        }

        return dollAddDtoList;
    }

    public Boolean deleteDollInfoById(String dollId) {
        log.info("准备删除玩偶，id：" + dollId);
        PmDollInfo pmDollInfo = dollInfoDao.findDollById(dollId);
        if (pmDollInfo.getDollType() == PokemanConstant.DOLL_TYPE) {//单玩偶直接删除
            dollInfoDao.deleteDollInfoById(dollId);
        } else if (pmDollInfo.getDollType() == PokemanConstant.DOLL_ROBOT_TYPE) {//机器人要先删除其玩偶集合
            dollGroupMemberDao.deleteDollGroupByDollGroupId(dollId);
            dollInfoDao.deleteDollInfoById(dollId);
        }
        return true;
    }
}
