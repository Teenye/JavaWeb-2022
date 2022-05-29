package com.yiyue.mapper;


import com.yiyue.pojo.Online;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OnlineMapper {

    /*增加新条目*/
    @Insert("insert into online values(#{userid} , #{username} , #{type} , #{IP},#{login},#{logout})")
    void add(Online online);

    /*查询销售日志*/
    @Select("select * from online")
    List<Online> selectAll();

}
