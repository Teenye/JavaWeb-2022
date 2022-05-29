package com.yiyue.web;

import com.alibaba.fastjson.JSON;
import com.yiyue.pojo.*;
import com.yiyue.service.*;
import com.yiyue.util.SendMail;

import javax.mail.MessagingException;
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

@WebServlet("/cart/*")
public class CartServlet extends BaseServlet{
    private CartService cartService = new CartService();
    private ReportService reportService = new ReportService();
    private GoodService goodService = new GoodService();
    private SaleService saleService = new SaleService();
    private UserPicService userPicService = new UserPicService();

    /*---------------------------购物车------------------------------*/
    /*查询购物车*/
    public void selectCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //读取请求时参数
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        /*查询购物车商品*/
        List<CartGood> goods = cartService.selectCart(user.getId());
        String jsonString = JSON.toJSONString(goods);
        response.getWriter().write(jsonString);
    }
    public void selectCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*获取section用户*/
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        /*查询购物车的商品数量*/
        Integer count = cartService.selectCount(user.getId());
        String jsonString = JSON.toJSONString(count);
        response.getWriter().write(jsonString);
    }

    /*添加购物车*/
    public void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*获取session用户*/
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        /*获取选择的商品信息(只携带goodid）*/
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Cart cart = JSON.parseObject(params,Cart.class);

        /*查看当前用户是否有该商品*/
        Cart c = cartService.selectBy2Id(user.getId(), cart.getGoodid());

        if(c!=null)
        {
            /*更新购物车表加一*/
            Integer num = c.getNum();
            c.setNum(num+1);
            cartService.update(c);
        }
        else
        {
            cart.setNum(1);
            cart.setUserid(user.getId());
            cartService.add(cart);
        }
        response.getWriter().write("success");
    }
    /*删减购物车*/
    public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*获取session用户*/
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        /*获取选择的商品信息(只携带goodid）*/
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Cart cart = JSON.parseObject(params,Cart.class);

        /*查看当前用户是否有该商品*/
        Cart c = cartService.selectBy2Id(user.getId(), cart.getGoodid());

        if(c.getNum()>1)
        {
            /*更新购物车表加一*/
            Integer num = c.getNum();
            c.setNum(num-1);
            cartService.update(c);
        }
        else
        {
            cart.setUserid(user.getId());
            cartService.delete(cart);
        }
        response.getWriter().write("success");
    }

    public void buy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*获取session用户*/
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        /*获取选择的商品信息(只携带goodid）*/
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Cart cart = JSON.parseObject(params,Cart.class);

        /*1.查找id的商品*/
        Good good = goodService.selectById(cart.getGoodid());

        /*如果不够买返回失败*/
        if(good.getKeep()-cart.getNum()<0){
            response.getWriter().write("false");
        }
        else {
            /*添加进用户信息，做表的删除*/
            cart.setUserid(user.getId());
            /*购买成功，购物车删除*/
            cartService.delete(cart);

            /*添加日期信息，并设置*/
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateNowStr = sdf.format(d);
            cart.setDate(dateNowStr);

            /*更改庫存*/
            good.setSellout(good.getSellout() + cart.getNum());
            good.setKeep(good.getKeep() - cart.getNum());
            goodService.update(good);

            /*4.更改销售额度表*/
            Sale sale = saleService.selectByBrand(good.getBrandName());
            sale.setMoney(sale.getMoney() + good.getPrice() * cart.getNum());
            saleService.update(sale);

            /*插入报告表*/
            reportService.add(cart);


            /*更新用户画像*/
            UserPic userPic = userPicService.selectByName(user.getUserName());
            userPic.setBuynum(userPic.getBuynum()+ cart.getNum());
            userPic.setPay(userPic.getPay()+ good.getPrice() * cart.getNum());
            userPic.setOfftenbuy(good.getBrandName());
            userPicService.update(userPic);


            /**/
            response.getWriter().write("success");
            SendMail sendMail = new SendMail("谢谢惠顾~商品已发货，具体请看消息通知", user.getEmail());
            sendMail.start();
        }
    }

    // root看到的销售日志
    public void showtable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        /*插入表*/
        List<logData> cartGoods = reportService.selectAll();

        String jsonString = JSON.toJSONString(cartGoods);
        response.getWriter().write(jsonString);
    }

    public void statistics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        /*插入表*/
        List<logData> cartGoods = reportService.selectAll();

        String jsonString = JSON.toJSONString(cartGoods);
        response.getWriter().write(jsonString);
    }

    // sellman看到的销售日志
    public void showtableBySell(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        /*插入表*/
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<logData> cartGoods = reportService.selectAllBySell(user.getId());

        String jsonString = JSON.toJSONString(cartGoods);
        response.getWriter().write(jsonString);
    }

    // 用戶看到的購買記錄
    public void showrecords(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        /*获取session用户*/
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        /*插入表*/
        List<logData> cartGoods = reportService.selectByName(user.getUserName());

        String jsonString = JSON.toJSONString(cartGoods);
        response.getWriter().write(jsonString);
    }



}
