package com.yiyue.web;

import com.alibaba.fastjson.JSON;
import com.yiyue.pojo.*;
import com.yiyue.service.BrowseService;
import com.yiyue.service.GoodService;
import com.yiyue.service.OperationService;
import com.yiyue.service.UserPicService;

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

@WebServlet("/operation/*")
public class OperationServlet extends BaseServlet{

    private OperationService operationService = new OperationService();
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<Operation> operations = operationService.selectAll();

        String jsonString = JSON.toJSONString(operations);
        response.getWriter().write(jsonString);
    }

}
