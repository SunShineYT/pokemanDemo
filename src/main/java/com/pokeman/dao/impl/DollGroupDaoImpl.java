package com.pokeman.dao.impl;

import com.pokeman.dao.impl.support.ModelSqlSessionDaoSupport;
import com.pokeman.model.PmDollGroup;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ycy
 * @since 2017/7/31
 * 玩偶组Dao 层
 */
@Repository("dollGroupDaoImpl")
public class DollGroupDaoImpl extends ModelSqlSessionDaoSupport {

    /**
     * 保存玩偶组
     * @param dollGroup
     * @return
     */
    public int saveDollGroup(PmDollGroup dollGroup){
        return this.getSqlSession().insert("saveDollGroup",dollGroup);
    }

    /**
     * 查询玩偶组列表
     * @param dollGroupName
     * @return
     */
    public List<PmDollGroup> findDollGroupList(String dollGroupName) {
        Map map=new HashMap();
        map.put("dollName",dollGroupName);
        List<PmDollGroup> dollGroupList = this.getSqlSession().selectList("findDollGroupList",map);
        return dollGroupList;
    }

    /**
     * 删除玩偶组
     * @param dollGroupId
     * @return
     */
    public int deleteDollGroupById(String dollGroupId) {
        Map map=new HashMap();
        map.put("dollGroupId",dollGroupId);
        return this.getSqlSession().delete("deleteDollGroupById",map);
    }

}
