package com.pokeman.dao.impl;

import com.pokeman.dao.impl.support.ModelSqlSessionDaoSupport;
import com.pokeman.gateway.controller.dto.DollQuery;
import com.pokeman.model.PmDollInfo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author ycy
 * @since 2017/7/19
 * 玩偶Dao 层
 */
@Repository("dollInfoDaoImpl")
public class DollInfoDaoImpl extends ModelSqlSessionDaoSupport {


    /**
     * 保存玩偶
     *
     * @param dollInfo
     * @return
     */
    public int saveDollInfo(PmDollInfo dollInfo) {
        return this.getSqlSession().insert("saveDollInfo", dollInfo);
    }

    public List<PmDollInfo> findDollInfoList(DollQuery dollQuery) {
        Map map=new HashMap();
        map.put("dollName",dollQuery.getDollName());
        map.put("dollLevel",dollQuery.getDollLevel());
        map.put("dollType",dollQuery.getDollType());
        List<PmDollInfo> pmDollInfoList = this.getSqlSession().selectList("findDollInfoList",map);
        return pmDollInfoList;
    }

    /**
     * 根据id获取玩偶
     * @param dollId
     * @return
     */
    public PmDollInfo findDollById(String dollId) {
        Map map=new HashMap();
        map.put("dollId",dollId);
        return this.getSqlSession().selectOne("findDollById",map);
    }

    public int deleteDollInfoById(String dollId) {
        Map map=new HashMap();
        map.put("dollId",dollId);
        return this.getSqlSession().delete("deleteDollById",map);
    }
}
