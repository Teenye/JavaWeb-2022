package com.yiyue.mapper;

import com.yiyue.pojo.Browse;
import com.yiyue.pojo.BrowseGood;
import com.yiyue.pojo.Operation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OperationMapper {

    /*增加新条目*/
    @Insert("insert into operation values(#{userid},#{username}, #{IP}, #{date}, #{operationname}, #{toID})")
    void add(Operation operation);

    @Select("select * from operation")
    List<Operation> selectAll();

}
