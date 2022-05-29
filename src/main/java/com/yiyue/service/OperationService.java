package com.yiyue.service;

import com.yiyue.mapper.BrowseMapper;
import com.yiyue.mapper.OperationMapper;
import com.yiyue.pojo.Browse;
import com.yiyue.pojo.BrowseGood;
import com.yiyue.pojo.Operation;
import com.yiyue.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class OperationService {

    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public void add(Operation operation) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        OperationMapper mapper = sqlSession.getMapper(OperationMapper.class);
        //4. 调用方法
        mapper.add(operation);
        sqlSession.commit();//提交事务
        //5. 释放资源
        sqlSession.close();
    }

    public List<Operation> selectAll() {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        OperationMapper mapper = sqlSession.getMapper(OperationMapper.class);
        //4. 调用方法
        List<Operation> operations= mapper.selectAll();
        sqlSession.commit();//提交事务
        //5. 释放资源
        sqlSession.close();
        return operations;
    }



}
