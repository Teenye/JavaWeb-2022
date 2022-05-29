package com.yiyue.web;

import com.alibaba.fastjson.JSON;
import com.yiyue.mapper.ReportMapper;
import com.yiyue.pojo.*;
import com.yiyue.pojo.Error;
import com.yiyue.service.BrowseService;
import com.yiyue.service.GoodService;
import com.yiyue.service.ReportService;
import com.yiyue.service.UserPicService;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/userpic/*")
public class UserPicServlet extends BaseServlet {
    private BrowseService browseService = new BrowseService();
    private ReportService reportService = new ReportService();
    private UserPicService userPicService = new UserPicService();
    private GoodService goodService = new GoodService();
    /*价格区间*/
    private double[][] priceArray = {{0, 300}, {301, 600}, {601, 1000}, {1001, 10000}};


    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<UserPic> operations = userPicService.selectAll();

        String jsonString = JSON.toJSONString(operations);
        response.getWriter().write(jsonString);
    }

    /*------------------推荐系统----------------------*/
    /*热门品牌推荐*/
    public void selectHot(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        /* 热卖商品 */
        List<Good> hotgoods = reportService.selectByHot();
        /* 浏览度高商品*/
        List<Good> fregoods = browseService.selectByFre();
        hotgoods.addAll(fregoods);

        List<Good> goods = randomlist(hotgoods, 8);

        String jsonString = JSON.toJSONString(goods);
        response.getWriter().write(jsonString);
    }

    /*最常买、最常逛、相似用户购买选择的推荐*/
    public void selectLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        UserPic userPic = userPicService.selectByName(user.getUserName());

        /*平均消费*/
        double mean = userPic.getPay() / userPic.getBuynum();
        Double low = priceArray[(int) (mean / 300)][0];
        Double high = priceArray[(int) (mean / 300)][1];


        List<Good> goods = new ArrayList<Good>();
        /*经常买、经常逛*/
        List<Good> goods_buy = goodService.selectByPic(userPic.getOfftenbuy(), low, high);
        List<Good> goods_browse = goodService.selectByPic(userPic.getOfftenbrowse(), low, high);
        goods.addAll(goods_buy);
        goods.addAll(goods_browse);

        /*查找得到相似的ID*/
        List<Integer> simID = userPicService.selectSim(userPic);
        for (Integer x : simID) {
            // 跳过自己
            if (Objects.equals(x, user.getId())) continue;
            List<Good> simGoods = reportService.selectById(x, low, high);
            goods.addAll(simGoods);
        }

        /*打乱 选取随机取几个展示*/
        Collections.shuffle(goods);
        List<Good> resgoods = randomlist(goods, 8);

        String jsonString = JSON.toJSONString(resgoods);
        response.getWriter().write(jsonString);
    }

    /*随机返回定数的商品*/
    private List<Good> randomlist(List<Good> goods, int returnNum) {

        List<Good> res = new ArrayList<Good>();//返回的随机的list

        int listNum = goods.size();
        //如果returnNum大于数据源list，直接返回list数据源全部数据
        if (listNum < returnNum) {
            returnNum = listNum;
        }

        int randomIndex = (int) (Math.random() * (goods.size() - returnNum));

        for (int i = randomIndex; i < randomIndex + returnNum; i++) {
            res.add(goods.get(i));
        }
        return res;
    }


    /*------------------商品最近的购买----------------------*/
    public void selectRecent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //读取请求时参数
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Integer id = JSON.parseObject(params,Integer.class);

        /*获取七天前的日期*/
        Date nowtime = new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date pretime = new Date();
        pretime.setTime(nowtime.getTime() - 6 * 24 * 60 * 60 * 1000);
        String pre = sdf.format(pretime);

        /*存放近七天的商品销售数量*/
        List<Integer> res = Arrays.asList(new Integer[7]);
        for(int i=0 ; i<7 ;++i){
            res.set(i, 0);
        }

        /*得到结果*/
        List<RecentNum> recentNums = reportService.selectRecent(id, pre);

        /*七天前的整数天*/
        Date predate = null;
        try {
            predate = sdf.parse(pre);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for(RecentNum x: recentNums){
            Date date = null;
            try {
                date = sdf.parse(x.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            /*第几天的数组索引*/
            int index =(int) (date.getTime()-predate.getTime())/ (24 * 60 * 60 * 1000);

            res.set(index, x.getSellnum());
        }

        System.out.println(res);

        String jsonString = JSON.toJSONString(res);
        response.getWriter().write(jsonString);
    }

    /*------------------销售异常----------------------*/
    public void selectError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*一天超过n单同样的，即为异常*/
        List<Error> errors = reportService.selectError(10);

        String jsonString = JSON.toJSONString(errors);
        response.getWriter().write(jsonString);
    }


}
