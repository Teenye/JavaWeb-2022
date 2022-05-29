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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserService();
    private UserPicService userPicService = new UserPicService();
    private OnlineService onlineService = new OnlineService();
    private OperationService operationService = new OperationService();

    /*-------------------获取用户信息--------------------*/
    public void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user==null){
            User nouser = new User();
            nouser.setUserName("未登录");
            String jsonString = JSON.toJSONString(nouser);
            System.out.println(nouser);
            response.getWriter().write(jsonString);
        }
        else{
            String jsonString = JSON.toJSONString(user);
            response.getWriter().write(jsonString);
        }

    }
    public void getType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String jsonString = JSON.toJSONString(user.getType());
        response.getWriter().write(jsonString);
    }

    public void outUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        if(user==null){
            response.getWriter().write("login");
        }
        else {

            Date logouttime = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String logoutstr = sdf.format(logouttime);

            Online online = new Online();
            online.setUserid(user.getId());
            online.setUsername(user.getUserName());
            online.setType(user.getType());
            online.setIP((String) session.getAttribute("loginIP"));
            online.setLogin((String) session.getAttribute("logintime"));
            online.setLogout(logoutstr);
            onlineService.add(online);

            /*断开session*/
            session.invalidate();
            System.out.println("out!!!");
            System.out.println(online);
            response.getWriter().write("success");
        }
    }

    /*获取ip*/
    public String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }


    /*------------------登陆注册------------------*/

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*接收用户名和密码*/
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        User login_user = JSON.parseObject(params,User.class);
        System.out.println(login_user);
        /*调用service*/
        User user = userService.selectByNP(login_user.getUserName(),login_user.getPassword());
        if(user!= null){
            /*同个浏览器同个sessionid*/
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            /*回发*/
            String jsonString = JSON.toJSONString("success");
            response.getWriter().write(jsonString);

            /*获取登录日期和ip地址*/
            Date logintime = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String loginStr = sdf.format(logintime);

            /*存入session*/
            session.setAttribute("loginIP",getRemortIP(request));
            session.setAttribute("logintime",loginStr);
            System.out.println(getRemortIP(request));
            System.out.println(logintime);

        }
        else{
            String jsonString = JSON.toJSONString("fail");
            response.getWriter().write(jsonString);
        }
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*接收用户名和密码*/
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        User register_user = JSON.parseObject(params,User.class);
        User u = userService.selectByName(register_user.getUserName());
        if(u == null){
            /*用户名不存在，加用户*/

            /*创建用户画像*/
            UserPic userPic = new UserPic();
            userPic.setUsername(register_user.getUserName());
            userPic.setBuynum(0);
            userPic.setPay(0.0);
            userPic.setOfftenbuy("");
            userPic.setOfftenbrowse("");
            userPicService.add(userPic);


            userService.add(register_user);
            String jsonString = JSON.toJSONString("success");
            response.getWriter().write(jsonString);
        }
        else{
            /*用户存在，不能添加*/
            String jsonString = JSON.toJSONString("fail");
            response.getWriter().write(jsonString);
        }
    }



    /*-----------------后台管理-------------------------*/
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userService.selectAll();
        String jsonString = JSON.toJSONString(users);
        response.getWriter().write(jsonString);
    }
    public void selectByCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        User getuser = JSON.parseObject(params,User.class);

        /*----------------------记录操作日志--------------------*/
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        /*操作时间*/
        Date otime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String otimestr = sdf.format(otime);
        Operation operation = new Operation();
        operation.setUserid(user.getId());
        operation.setUsername(user.getUserName());
        operation.setIP((String)session.getAttribute("loginIP"));
        operation.setDate(otimestr);
        operation.setOperationname("查询用户");
        operationService.add(operation);

        List<User> users= userService.selectByCondition(getuser.getUserName());
        String jsonString = JSON.toJSONString(users);
        response.getWriter().write(jsonString);
    }

    /*添加用户*/
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //读取请求时参数
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        User getuser = JSON.parseObject(params,User.class);

        /*----------------------记录操作日志--------------------*/
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        /*操作时间*/
        Date otime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String otimestr = sdf.format(otime);
        Operation operation = new Operation();
        operation.setUserid(user.getId());
        operation.setUsername(user.getUserName());
        operation.setIP((String)session.getAttribute("loginIP"));
        operation.setDate(otimestr);
        operation.setOperationname("添加用户");
        operationService.add(operation);

        userService.add(getuser);
        response.getWriter().write("success");
    }
    /*删除用户*/
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //读取请求时参数
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        User getuser = JSON.parseObject(params,User.class);

        /*----------------------记录操作日志--------------------*/
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        /*操作时间*/
        Date otime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String otimestr = sdf.format(otime);
        Operation operation = new Operation();
        operation.setUserid(user.getId());
        operation.setUsername(user.getUserName());
        operation.setIP((String)session.getAttribute("loginIP"));
        operation.setDate(otimestr);
        operation.setOperationname("删除用户");
        operation.setToID(getuser.getId());
        operationService.add(operation);

        userService.delete(getuser.getId());
        response.getWriter().write("success");
    }
    /*更新用户*/
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //读取请求时参数
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        User getuser = JSON.parseObject(params,User.class);

        /*----------------------记录操作日志--------------------*/
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        /*操作时间*/
        Date otime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String otimestr = sdf.format(otime);
        Operation operation = new Operation();
        operation.setUserid(user.getId());
        operation.setUsername(user.getUserName());
        operation.setIP((String)session.getAttribute("loginIP"));
        operation.setDate(otimestr);
        operation.setOperationname("更新用户");
        operation.setToID(getuser.getId());
        operationService.add(operation);

        userService.update(getuser);
        response.getWriter().write("success");
    }



}
