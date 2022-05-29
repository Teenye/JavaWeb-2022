package com.yiyue.web;

import com.alibaba.fastjson.JSON;
import com.yiyue.pojo.Browse;
import com.yiyue.pojo.BrowseGood;
import com.yiyue.pojo.Online;
import com.yiyue.pojo.User;
import com.yiyue.service.BrowseService;
import com.yiyue.service.OnlineService;

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

@WebServlet("/online/*")
public class OnlineServlet extends BaseServlet{
    private OnlineService onlineService = new OnlineService();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Online> onlines = onlineService.selectAll();
        String jsonString = JSON.toJSONString(onlines);
        response.getWriter().write(jsonString);
    }

}
