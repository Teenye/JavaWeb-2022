package com.yiyue.service;

import com.yiyue.mapper.BrowseMapper;
import com.yiyue.mapper.CartMapper;
import com.yiyue.mapper.ReportMapper;
import com.yiyue.mapper.SaleMapper;
import com.yiyue.pojo.*;
import com.yiyue.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrowseService {

    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public void add(Browse browse) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        BrowseMapper mapper = sqlSession.getMapper(BrowseMapper.class);
        //4. 调用方法
        mapper.add(browse);
        sqlSession.commit();//提交事务
        //5. 释放资源
        sqlSession.close();
    }

    public List<BrowseGood> selectBySell(int id) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        BrowseMapper mapper = sqlSession.getMapper(BrowseMapper.class);
        //4. 调用方法
        List<BrowseGood> goods= mapper.selectBySell(id);
        sqlSession.commit();//提交事务
        //5. 释放资源
        sqlSession.close();
        return goods;
    }

    /*-------查找浏览最多的--------*/
    public List<Good> selectByFre(){
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        BrowseMapper mapper = sqlSession.getMapper(BrowseMapper.class);
        //4. 调用方法
        List<Good> goods = mapper.selectByFre();
        //5. 释放资源
        sqlSession.close();
        return goods;
    }

}
