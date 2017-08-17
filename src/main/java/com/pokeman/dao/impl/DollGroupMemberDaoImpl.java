package com.pokeman.dao.impl;

import com.pokeman.dao.impl.support.ModelSqlSessionDaoSupport;
import com.pokeman.gateway.controller.dto.DollGroupDto;
import com.pokeman.model.PmDollGroupMember;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ycy
 * @since 2017/7/31
 * 玩偶组-玩偶 Dao层
 */
@Repository("dollGroupMemberDaoImpl")
public class DollGroupMemberDaoImpl extends ModelSqlSessionDaoSupport {


    /**
     * 玩偶添加到玩偶组
     * @param dollGroupMember
     * @return
     */
    public int saveDollToDollGroup(PmDollGroupMember dollGroupMember){
        return this.getSqlSession().insert("saveDollToDollGroup",dollGroupMember);
    }


    /**
     * 获取玩偶组列表
     * @param dollGroupId
     * @param type  1:组 2 ：机器人
     * @return
     */
    public List<PmDollGroupMember> findDollGroupMember(String dollGroupId,int type){
        Map map=new HashMap();
        map.put("dollGroupId",dollGroupId);
        map.put("type",type);
        return this.getSqlSession().selectList("findDollGroupMember",map);
    }

    /**
     * 根据 dollGroupId 删除
     * @param dollGroupId
     * @param type  1:组 2 ：机器人
     * @return
     */
    public int deleteDollGroupByDollGroupId(String dollGroupId){
        Map map=new HashMap();
        map.put("dollGroupId",dollGroupId);
        return this.getSqlSession().delete("deleteDollGroupByDollGroupId",map);
    }
}
