package com.yiyue.mapper;

import com.yiyue.pojo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BrowseMapper {

    /*增加新条目*/
    @Insert("insert into browse values(#{userid},#{goodid},#{date},#{duration})")
    void add(Browse browse);

    /*查询销售日志*/
    @Select("select username, goodname, date,duration from browse inner join goods on(browse.goodid = goods.id) inner join user on(browse.userid = user.id) inner join sell2good on(goods.brandname = sell2good.brandName) where sell2good.userid = #{id}")
    List<BrowseGood> selectBySell(int id);

    @Select("select username, goodname, date,duration from browse inner join goods on(browse.goodid = goods.id) inner join user on(browse.userid = user.id)")
    List<BrowseGood> selectByRoot();


    /*-----------浏览最多------------*/
    @Select("select id,brandname,goodname,price,specification,description from browse inner join goods on(browse.goodid=goods.id) group by id order by count(*) desc\n")
    List<Good> selectByFre();
}
