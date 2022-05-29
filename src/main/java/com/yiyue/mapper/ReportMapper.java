package com.yiyue.mapper;

import com.yiyue.pojo.*;
import com.yiyue.pojo.Error;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ReportMapper {

    /*查询销售日志*/
    @Select("select username,brandname,goodname,price,specification,num,date from report inner join goods on(report.goodid=goods.id) inner join user on(report.userid=user.id)")
    List<logData> selectAll();

    @Select("select username,brandname,goodname,price,specification,num,date from report inner join goods on(report.goodid=goods.id) inner join user on(report.userid=user.id) where username=#{username}")
    List<logData> selectByName(String username);

    /*增加新条目*/
    @Insert("insert into report values(#{userid},#{goodid},#{num},#{date})")
    void add(Cart good);

    @Select("select username,goods.brandname,goodname,price,specification,num,date from report inner join goods on(report.goodid=goods.id) inner join user on(report.userid=user.id) inner join sell2good on(goods.brandname=sell2good.brandName) where sell2good.userid = #{userid}")
    List<logData> selectAllBySell(int userid);


    /*----------------------大数据-----------------------*/
    /*根据用户信息查它所购买的商品信息*/
    @Select("select goods.id,brandname,goodname,price,specification,sellout,keep,description from report inner join goods on(report.goodid=goods.id) where userid=#{id} and price between #{low} and #{high}")
    List<Good> selectById(@Param("id")Integer id , @Param("low")Double low, @Param("high")Double high);

    @Select("select id,brandname,goodname,price,specification,description from report inner join goods on(report.goodid=goods.id) group by id order by count(*) desc")
    List<Good> selectByHot();

    /*输入的是商品id和今日日期 ， 返回 之前的购买及对应日期*/
    @Select("select sum(num) sellnum,date_format(date, '%Y-%m-%d') date from  report where date > #{date} and goodid = #{id} group by date_format(date, '%Y-%m-%d')")
    List<RecentNum> selectRecent(@Param("id")Integer id, @Param("date")String date);

    /*查看疑似出错用户*/
    @Select("select userid ,username, count(*) num,date_format(date, '%Y-%m-%d') date from  report inner join user on report.userid = user.id group by userid,date_format(date, '%Y-%m-%d') having num>#{errornum}")
    List<Error> selectError(Integer errornum);


}
