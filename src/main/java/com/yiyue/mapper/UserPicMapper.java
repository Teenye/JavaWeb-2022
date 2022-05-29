package com.yiyue.mapper;

import com.yiyue.pojo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserPicMapper {

    @Select("select * from userpic")
    List<UserPic> selectAll();

    /*增加新条目*/
    @Insert("insert into userpic values(#{username},#{buynum},#{pay} , #{offtenbuy} ,#{offtenbrowse})")
    void add(UserPic userPic);

    @Update("update userpic set buynum=#{buynum}, pay=#{pay} , offtenbuy=#{offtenbuy}, offtenbrowse=#{offtenbrowse} where username = #{username}  ")
    void update(UserPic userPic);

    @Select("select * from userpic where username = #{username}")
    UserPic selectByName(String username);

    /*查看相似的用户ID*/
    @Select("select user.id from userpic inner join user on userpic.username = user.username where offtenbrowse = #{offtenbrowse} or offtenbuy=#{offtenbuy}")
    List<Integer> selectSim(UserPic target);

}
