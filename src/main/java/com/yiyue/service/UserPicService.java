package com.yiyue.service;

import com.yiyue.mapper.OperationMapper;
import com.yiyue.mapper.UserPicMapper;
import com.yiyue.pojo.Operation;
import com.yiyue.pojo.UserPic;
import com.yiyue.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserPicService {

    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<UserPic> selectAll() {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        UserPicMapper mapper = sqlSession.getMapper(UserPicMapper.class);
        //4. 调用方法
        List<UserPic> userpics= mapper.selectAll();
        //5. 释放资源
        sqlSession.close();
        return userpics;
    }


    public void add(UserPic userPic) {
        SqlSession sqlSession = factory.openSession();
        UserPicMapper mapper = sqlSession.getMapper(UserPicMapper.class);
        mapper.add(userPic);
        sqlSession.commit();
        sqlSession.close();
    }

    public void update(UserPic userPic) {
        SqlSession sqlSession = factory.openSession();
        UserPicMapper mapper = sqlSession.getMapper(UserPicMapper.class);
        mapper.update(userPic);
        sqlSession.commit();
        sqlSession.close();
    }

    public UserPic selectByName(String username) {
        SqlSession sqlSession = factory.openSession();
        UserPicMapper mapper = sqlSession.getMapper(UserPicMapper.class);
        UserPic userpic = mapper.selectByName(username);
        sqlSession.close();
        return userpic;
    }

    public List<Integer> selectSim(UserPic userPic) {
        SqlSession sqlSession = factory.openSession();
        UserPicMapper mapper = sqlSession.getMapper(UserPicMapper.class);
        List<Integer> simID = mapper.selectSim(userPic);
        sqlSession.close();
        return simID;
    }
}
