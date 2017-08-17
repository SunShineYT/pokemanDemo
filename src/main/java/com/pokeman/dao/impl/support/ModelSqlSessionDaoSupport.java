package com.pokeman.dao.impl.support;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ycy
 * @since 2017/7/31
 */

public class ModelSqlSessionDaoSupport  extends SqlSessionDaoSupport {
    /**
     * Autowired 必须要有
     */
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}
