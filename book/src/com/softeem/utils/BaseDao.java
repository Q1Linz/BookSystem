package com.softeem.utils;

import org.apache.commons.dbutils.*;

public class BaseDao {
    public QueryRunner queryRunner ;
    public static Integer pageSize = 4;

    public BaseDao(){
        queryRunner = new QueryRunner(JdbcUtils.dataSource);
    }

    public RowProcessor getProcess(){
        BeanProcessor bean = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bean);
        return processor;
    }
}