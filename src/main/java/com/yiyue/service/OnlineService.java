package com.yiyue.service;

import com.yiyue.mapper.BrowseMapper;
import com.yiyue.mapper.OnlineMapper;
import com.yiyue.pojo.Browse;
import com.yiyue.pojo.BrowseGood;
import com.yiyue.pojo.Online;
import com.yiyue.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class OnlineService {

    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public void add(Online online) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        OnlineMapper mapper = sqlSession.getMapper(OnlineMapper.class);
        //4. 调用方法
        mapper.add(online);
        sqlSession.commit();//提交事务
        //5. 释放资源
        sqlSession.close();
    }

    public List<Online> selectAll() {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        OnlineMapper mapper = sqlSession.getMapper(OnlineMapper.class);
        //4. 调用方法
        List<Online> onlines= mapper.selectAll();
        sqlSession.commit();//提交事务
        //5. 释放资源
        sqlSession.close();
        return onlines;
    }



}
