package com.yiyue.mapper;

import com.yiyue.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SaleMapper {

    @Select("select userid, username,sale.brandname,money from sale left outer join sell2good on(sale.brandname = sell2good.brandName) left outer join user on (user.id= userid) ")
    List<Sale> selectAll();

    @Select("select * from sale where brandname = #{brandname}")
    Sale selectByBrand(String brandname);

    /*更新*/
    @Update("update sale set money=#{money} where brandname=#{brandname}")
    void update(Sale sale);


    /*关于销售员管理*/
    @Select("select sell2good.userid, username,brandname from sell2good left outer join user on (user.id= userid) ")
    List<Seller> selectSell();


    @Update("update sell2good set userid=#{userid} where brandname=#{brandname}")
    void updateSell(Seller seller);

}
