package com.yiyue.web;

import com.alibaba.fastjson.JSON;
import com.yiyue.pojo.*;
import com.yiyue.service.CartService;
import com.yiyue.service.GoodService;
import com.yiyue.service.ReportService;
import com.yiyue.service.SaleService;
import com.yiyue.util.SendMail;

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

@WebServlet("/sale/*")
public class SaleServlet extends BaseServlet{
    private SaleService saleService = new SaleService();

    /*查询所有品牌的销售额*/
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Sale> goods = saleService.selectAll();
        System.out.println(goods);;
        String jsonString = JSON.toJSONString(goods);
        response.getWriter().write(jsonString);
    }


    /*关于销售人员管理*/
    public void selectSell(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Seller> sellers = saleService.selectSell();
        String jsonString = JSON.toJSONString(sellers);
        response.getWriter().write(jsonString);
    }

    public void updateSell(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Seller seller = JSON.parseObject(params,Seller.class);
        saleService.updateSell(seller);
        response.getWriter().write("success");
    }

}
