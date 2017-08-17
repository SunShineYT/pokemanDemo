package com.pokeman.sercice.dollGroup.impl;

import com.pokeman.PokemanConstant;
import com.pokeman.dao.impl.DollGroupDaoImpl;
import com.pokeman.dao.impl.DollGroupMemberDaoImpl;
import com.pokeman.gateway.controller.dto.DollGroupDto;
import com.pokeman.model.PmDollGroup;
import com.pokeman.model.PmDollGroupMember;
import com.pokeman.sercice.dollGroup.IDollGroupService;
import com.pokeman.util.DateUtil;
import com.pokeman.util.UUIDGeneratorUtils;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author ycy
 * @since 2017/7/31
 */
@Log4j
@Service("dollGroupService")
public class DollGroupServiceImpl implements IDollGroupService {

    @Resource(name = "dollGroupDaoImpl")
    private DollGroupDaoImpl dollGroupDao;

    @Resource(name = "dollGroupMemberDaoImpl")
    private DollGroupMemberDaoImpl dollGroupMemberDao;

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public String saveDollGroup(DollGroupDto dollGroupDto) {
        if(dollGroupDto!=null){
            if(StringUtils.isBlank(dollGroupDto.getDollGroupName())){
                throw new RuntimeException("玩偶组名不能为空");
            }
            if(dollGroupDto.getDollIdList()==null||dollGroupDto.getDollIdList().size()==0){
                throw new RuntimeException("玩偶集合不能为空");
            }
        }
        String currentTime=DateUtil.getCurrentTime();
        //先创建玩偶组，再把玩偶加入玩偶组
        PmDollGroup dollGroup=new PmDollGroup();
        dollGroup.setDollGroupId(UUIDGeneratorUtils.getUUID());
        log.info("随机生成的玩偶组id：" + dollGroup.getDollGroupId());
        dollGroup.setDollGroupName(dollGroupDto.getDollGroupName());
        dollGroup.setCreateTime(currentTime);
        int result=dollGroupDao.saveDollGroup(dollGroup);
        String dollGroupId="";
        if(result==1){
            dollGroupId=dollGroup.getDollGroupId();
        }
        List<String> dollIdList=dollGroupDto.getDollIdList();
        for(String dollId:dollIdList){
            PmDollGroupMember dollGroupMember=new PmDollGroupMember();
            dollGroupMember.setDgmId(UUIDGeneratorUtils.getUUID());
            dollGroupMember.setDollGroupId(dollGroupId);
            dollGroupMember.setDollId(dollId);
            dollGroupMember.setType(PokemanConstant.TYPE_GROUP);
            dollGroupMember.setCreateTime(currentTime);
            dollGroupMemberDao.saveDollToDollGroup(dollGroupMember);
        }

        return "创建玩偶组成功";
    }

    public List<PmDollGroup> findDollGroupList(String dollGroupName) {
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Boolean deleteDollGroupById(String dollGroupId) {
        return null;
    }
}
