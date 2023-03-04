package com.softeem.utils;

import java.sql.SQLException;
import java.util.List;

public interface BaseDaoInterface <T>{
    /*查询表中所有数据*/
    List<T> findAll() throws SQLException;
    /*添加数据*/
    void save(T t) throws SQLException;
    /*根据主键id进行数据的修改*/
    void updateById(T t) throws SQLException;
    /*根据主键id删除数据*/
    void deleteById(Integer id) throws SQLException;
    /*根据主键id查询数据*/
    T findById(Integer id) throws SQLException;
    /*无条件分页查询*/
    List<T> page(Integer pageNumber) throws SQLException;
    /*求表中的记录数*/
    int pageRecord() throws SQLException;
}