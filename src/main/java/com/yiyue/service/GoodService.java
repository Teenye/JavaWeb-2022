package com.yiyue.service;

import com.yiyue.mapper.GoodMapper;
import com.yiyue.pojo.Good;
import com.yiyue.pojo.UserPic;
import com.yiyue.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class GoodService {
    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public Good selectById(int id){
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
        //4. 调用方法
        Good good = mapper.selectById(id);
        //5. 释放资源
        sqlSession.close();
        return good;
    }

    public List<String> selectBrand() {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
        //4. 调用方法
        List<String> brands = mapper.selectBrand();
        //5. 释放资源
        sqlSession.close();
        return brands;
    }

    public List<String> selectBrandBySell(int userid) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
        //4. 调用方法
        List<String> brands = mapper.selectBrandBySell(userid);
        //5. 释放资源
        sqlSession.close();
        return brands;
    }

    public List<Good> selectAll() {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
        //4. 调用方法
        List<Good> goods = mapper.selectAll();
        //5. 释放资源
        sqlSession.close();
        return goods;
    }

    public List<Good> selectAllBySell(int userid) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
        //4. 调用方法
        List<Good> goods = mapper.selectAllBySell(userid);
        //5. 释放资源
        sqlSession.close();
        return goods;
    }

    public List<Good> selectByCondition(String brandname,String goodname) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
        //4. 调用方法
        List<Good> goods = mapper.selectByCondition(brandname, goodname);
        //5. 释放资源
        sqlSession.close();
        return goods;
    }

    public List<Good> selectByConditionsBySell(String brandname , String goodname ,int userid) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
        //4. 调用方法
        List<Good> goods = mapper.selectByConditionsBySell(brandname,goodname,userid);
        //5. 释放资源
        sqlSession.close();
        return goods;
    }

    public void add(Good good) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
        //4. 调用方法
        mapper.add(good);
        sqlSession.commit();//提交事务
        //5. 释放资源
        sqlSession.close();
    }
    public void delete(int id) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
        //4. 调用方法
        mapper.delete(id);
        sqlSession.commit();//提交事务
        //5. 释放资源
        sqlSession.close();
    }
    public void update(Good good) {
        SqlSession sqlSession = factory.openSession();
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
        //4. 调用方法
        mapper.update(good);
        sqlSession.commit();//提交事务
        //5. 释放资源
        sqlSession.close();
    }

    /*根据经常购买、浏览的品牌*/
    /*----接受参数：品牌名 和  购买区间*/
    public List<Good> selectByPic(String brandname , Double low , Double high){
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
        //4. 调用方法
        List<Good> goods = mapper.selectByPic(brandname, low, high);
        //5. 释放资源
        sqlSession.close();
        return goods;
    }

    // 根据相似的用户查
    public List<Good> selectBySim(String brandname , Double low , Double high){
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        GoodMapper mapper = sqlSession.getMapper(GoodMapper.class);
        //4. 调用方法
        List<Good> goods = mapper.selectByPic(brandname, low, high);
        //5. 释放资源
        sqlSession.close();
        return goods;
    }



}
