package com.softeem.dao.impl;

import com.softeem.bean.Book;
import com.softeem.dao.Bookdao;
import com.softeem.utils.BaseDao;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.channels.NonWritableChannelException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Bookdaoimpl extends BaseDao implements Bookdao {

    public Bookdaoimpl(){}
    @Override
    public List<Book> findAll() throws SQLException {
        BaseDao baseDao = new BaseDao();
        RowProcessor processor = baseDao.getProcess();
        BeanListHandler<Book>beanlist=new BeanListHandler<>(Book.class,processor);
       return queryRunner.query("select * from t_book order by id desc",beanlist);
    }
    @Override
    public void save(Book book) throws SQLException {
        queryRunner.update("insert into t_book values(?,?,?,?,?,?,?)",book.getId(),book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public void updateById(Book book) throws SQLException {
        queryRunner.update("update t_book set name=?,price=?,author=?,sales=?,stock=?,img_path=? where id=?",book.getName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        queryRunner.update("delete from t_book where id=?",id);
    }

    @Override
    public Book findById(Integer id) throws SQLException {
        BaseDao baseDao = new BaseDao();
        RowProcessor processor = baseDao.getProcess();
        BeanHandler<Book>bean=new BeanHandler<>(Book.class,processor);
        return queryRunner.query("select * from t_book where id=?",bean,id);
    }

    @Override
    public List<Book> page(Integer pageNumber) throws SQLException {
        BaseDao baseDao = new BaseDao();
        RowProcessor processor = baseDao.getProcess();
        BeanListHandler<Book>beanlist=new BeanListHandler<>(Book.class,processor);
        return queryRunner.query("select * from t_book limit ?,?",beanlist,(pageNumber-1)*pageSize,pageSize);
    }

    @Override
    public int pageRecord() throws SQLException {
        ScalarHandler<Long> scalarHandler=new ScalarHandler();
        return queryRunner.query("select count(*) from t_book",scalarHandler).intValue();
    }

    @Override
    public Integer queryForPageTotalCount() throws SQLException {
        ScalarHandler<Long> scalarHandler=new ScalarHandler();
        return queryRunner.query("select count(*) from t_book",scalarHandler).intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) throws SQLException {
        BaseDao baseDao = new BaseDao();
        RowProcessor processor = baseDao.getProcess();

        BeanListHandler<Book>beanlist=new BeanListHandler<>(Book.class,processor);
        return queryRunner.query("select * from t_book order by id desc limit ?,?",beanlist,begin,pageSize);
    }

    @Override
    public List<Book> queryByPrice(int begin, int pageSize,int min, int max) throws SQLException {
        BaseDao baseDao = new BaseDao();
        RowProcessor processor = baseDao.getProcess();
        BeanListHandler<Book>beanlist=new BeanListHandler<>(Book.class,processor);
        return queryRunner.query("select * from t_book where price between ? and ? order by id desc limit ?,? ",beanlist,min,max,begin,pageSize);
    }

    @Override
    public Integer queryByPriceTotal(int min, int max) throws SQLException {
        ScalarHandler<Long> scalarHandler=new ScalarHandler();
        return queryRunner.query("select count(*) from t_book where price between ? and ?",scalarHandler,min,max).intValue();
    }

    @Override
    public Integer queryForPageTotalCount(String name, String author, int min, int max) throws SQLException {
        StringBuilder sql = new StringBuilder("select count(*) from t_book where 1 = 1 ");
        ArrayList list = new ArrayList();
        if(name!= null && !"".equals(name)){
            sql.append(" and name like ? ");
            list.add("%" + name + "%");
        }
        if(author!= null && !"".equals(author)){
            sql.append(" and author like ? ");
            list.add("%" + author + "%");
        }
        if(min>0 && max>0){
            if(min > max){
                int t = min;
                min = max;
                max = t;
            }
            sql.append(" and price between ? and ? ");
            list.add(min);
            list.add(max);
        }
        ScalarHandler<Long> scalarHandler=new ScalarHandler();
        return queryRunner.query(sql.toString(),scalarHandler,list.toArray()).intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize, String name, String author, int min, int max) throws SQLException {
            BaseDao baseDao = new BaseDao();
            RowProcessor processor = baseDao.getProcess();
            StringBuilder sql = new StringBuilder("select * from t_book where 1 = 1 ");
            ArrayList list = new ArrayList();
            if(name!= null && !"".equals(name)){
                sql.append(" and name like ? ");
                list.add("%" + name + "%");
            }
            if(author!= null && !"".equals(author)){
                sql.append(" and author like ? ");
                list.add("%" + author + "%");
            }
            if(min>=0 && max>=0){
                if(min > max){
                    int t = min;
                    min = max;
                    max = t;
                }
                sql.append(" and price between ? and ? ");
                list.add(min);
                list.add(max);
            }
            String end = " order by id desc limit ?,? ";
            list.add(begin);
            list.add(pageSize);
            BeanListHandler<Book>beanlist=new BeanListHandler<>(Book.class,processor);
            return queryRunner.query(sql + end,beanlist,list.toArray());
    }
}