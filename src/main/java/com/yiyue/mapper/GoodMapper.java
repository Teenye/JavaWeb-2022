package com.yiyue.mapper;

import com.yiyue.pojo.Good;
import com.yiyue.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GoodMapper {

    @Select("select * from goods")
    List<Good> selectAll();

    @Select("select * from goods where id = #{id}")
    Good selectById(int id);

    @Select("select goods.brandname,goodname,price,specification,sellout,keep,description from sell2good inner join goods on(sell2good.brandname =goods.brandname) where userid = #{userid}")
    List<Good> selectAllBySell(int userid);

    @Insert("insert into goods values(null,#{brandName},#{goodName},#{price},#{specification},#{description},#{sellout},#{keep})")
    void add(Good good);

    @Delete("delete from goods where id=#{id}")
    void delete(int id);

    @Update("update goods set brandname=#{brandName},goodname=#{goodName},price=#{price},specification=#{specification},description=#{description},sellout=#{sellout},keep=#{keep} where id = #{id}")
    void update(Good good);

    @Select("select * from goods where brandname like CONCAT('%',#{brandname},'%') and goodname like CONCAT('%',#{goodname},'%')")
    List<Good> selectByCondition(@Param("brandname")String brandname,@Param("goodname") String goodname);

    @Select("select distinct brandname from goods")
    List<String> selectBrand();

    @Select("select distinct brandName from sell2good where userid = #{userid}")
    List<String> selectBrandBySell(int userid);

    @Select("select goods.brandname,goodname,price,specification,sellout,keep,description from sell2good inner join goods on(sell2good.brandName =goods.brandname) where userid = #{userid} and goods.brandname like CONCAT('%',#{brandname},'%') and  goodname like CONCAT('%',#{goodname},'%')")
    List<Good> selectByConditionsBySell(@Param("brandname") String brandname,@Param("goodname") String goodname, @Param("userid") int userid);

    /*大数据推荐*/
    @Select("select * from goods where brandname = #{brandname} and price between #{low} and #{high}")
    List<Good> selectByPic(@Param("brandname") String brandname,@Param("low") Double low,@Param("high") Double high);

    /*查看相似用户的购买物品*/
    @Select("select goods.id, brandname,goodname,price,specification,sellout,keep,description from report inner join goods on report.goodid = goods.id where userid = #{id}")
    List<Good> selectBySim(User user);

}
