package com.yiyue.service;

import com.yiyue.mapper.CartMapper;
import com.yiyue.mapper.SaleMapper;
import com.yiyue.pojo.Cart;
import com.yiyue.pojo.CartGood;
import com.yiyue.pojo.Sale;
import com.yiyue.pojo.Seller;
import com.yiyue.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class SaleService {

    //1. 创建SqlSessionFactory 工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /*联合查询，商品信息*/
    public List<Sale> selectAll() {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        SaleMapper mapper = sqlSession.getMapper(SaleMapper.class);
        //4. 调用方法
        List<Sale> sales = mapper.selectAll();
        //5. 释放资源
        sqlSession.close();
        return sales;
    }

    /*联合查询，商品信息*/
    public Sale selectByBrand(String brandname) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        SaleMapper mapper = sqlSession.getMapper(SaleMapper.class);
        //4. 调用方法
        Sale sale = mapper.selectByBrand(brandname);
        //5. 释放资源
        sqlSession.close();
        return sale;
    }

    /*更新加一减一*/
    public void update(Sale sale) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        SaleMapper mapper = sqlSession.getMapper(SaleMapper.class);
        //4. 调用方法
        mapper.update(sale);
        sqlSession.commit();
        sqlSession.close();
    }

    /*关于管理销售人员*/
    public List<Seller> selectSell() {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        SaleMapper mapper = sqlSession.getMapper(SaleMapper.class);
        //4. 调用方法
        List<Seller> sellers = mapper.selectSell();
        //5. 释放资源
        sqlSession.close();
        return sellers;
    }
    public void updateSell(Seller seller) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取GoodMapper
        SaleMapper mapper = sqlSession.getMapper(SaleMapper.class);
        //4. 调用方法
        mapper.updateSell(seller);
        sqlSession.commit();
        sqlSession.close();
    }
}
