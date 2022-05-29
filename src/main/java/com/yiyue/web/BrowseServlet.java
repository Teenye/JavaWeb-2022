package com.yiyue.web;

import com.alibaba.fastjson.JSON;
import com.yiyue.pojo.*;
import com.yiyue.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/browse/*")
public class BrowseServlet extends BaseServlet{
    private BrowseService browseService = new BrowseService();
    private UserPicService userPicService = new UserPicService();
    private GoodService goodService = new GoodService();

    /*查询所有品牌的销售额*/
    public void addBrowse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*获取浏览信息*/
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Browse browse = JSON.parseObject(params, Browse.class);

        /*添加用戶信息*/
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        browse.setUserid(user.getId());
        /*添加最后浏览日期信息，并设置*/
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);
        browse.setDate(dateNowStr);
        browseService.add(browse);

        /*更新用户画像*/
        Good good = goodService.selectById(browse.getGoodid());
        UserPic userPic = userPicService.selectByName(user.getUserName());
        userPic.setOfftenbrowse(good.getBrandName());
        userPicService.update(userPic);

        response.getWriter().write("success");
    }

    public void selectBySell(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<BrowseGood> goods = browseService.selectBySell(user.getId());
        System.out.println(goods);

        String jsonString = JSON.toJSONString(goods);
        response.getWriter().write(jsonString);
    }

}
