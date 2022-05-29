package com.yiyue.service;

import com.yiyue.mapper.CartMapper;
import com.yiyue.mapper.GoodMapper;
import com.yiyue.mapper.ReportMapper;
import com.yiyue.pojo.*;
import com.yiyue.pojo.Error;
import com.yiyue.util.SqlSessionFactoryUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ReportService {
    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<logData> selectAll() {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        ReportMapper mapper = sqlSession.getMapper(ReportMapper.class);
        //4. 调用方法
        List<logData> goods = mapper.selectAll();
        //5. 释放资源
        sqlSession.close();
        return goods;
    }

    public List<logData> selectAllBySell(int userid) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        ReportMapper mapper = sqlSession.getMapper(ReportMapper.class);
        //4. 调用方法
        List<logData> goods = mapper.selectAllBySell(userid);
        //5. 释放资源
        sqlSession.close();
        return goods;
    }

    public List<logData> selectByName(String username) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        ReportMapper mapper = sqlSession.getMapper(ReportMapper.class);
        //4. 调用方法
        List<logData> goods = mapper.selectByName(username);
        //5. 释放资源
        sqlSession.close();
        return goods;
    }

    public void add(Cart good) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        ReportMapper mapper = sqlSession.getMapper(ReportMapper.class);
        //4. 调用方法
        mapper.add(good);
        sqlSession.commit();//提交事务
        //5. 释放资源
        sqlSession.close();
    }

    /*------------------大数据-----------------*/
    public List<Good> selectById(Integer ID, Double low ,Double high) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        ReportMapper mapper = sqlSession.getMapper(ReportMapper.class);
        //4. 调用方法
        List<Good> goods = mapper.selectById(ID,low,high);
        //5. 释放资源
        sqlSession.close();
        return goods;
    }

    public List<Good> selectByHot() {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        ReportMapper mapper = sqlSession.getMapper(ReportMapper.class);
        //4. 调用方法
        List<Good> goods = mapper.selectByHot();
        //5. 释放资源
        sqlSession.close();
        return goods;
    }

    public  List<RecentNum> selectRecent(Integer id, String date){
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        ReportMapper mapper = sqlSession.getMapper(ReportMapper.class);
        //4. 调用方法
        List<RecentNum> recentNums = mapper.selectRecent(id,date);
        //5. 释放资源
        sqlSession.close();
        return recentNums;
    }

    public  List<Error> selectError(Integer errornum){
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        ReportMapper mapper = sqlSession.getMapper(ReportMapper.class);
        //4. 调用方法
        List<Error> errors = mapper.selectError(errornum);
        //5. 释放资源
        sqlSession.close();
        return errors;
    }

}
