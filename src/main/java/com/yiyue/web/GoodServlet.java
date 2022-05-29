package com.yiyue.web;

import com.alibaba.fastjson.JSON;
import com.yiyue.mapper.OperationMapper;
import com.yiyue.pojo.Good;
import com.yiyue.pojo.Operation;
import com.yiyue.pojo.User;
import com.yiyue.service.GoodService;
import com.yiyue.service.OperationService;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;


@WebServlet("/good/*")
public class GoodServlet extends BaseServlet {
    private GoodService goodService = new GoodService();
    private OperationService operationService = new OperationService();

    /*设置图片*/
    boolean setImage(String image, String filePath) {
        int index = image.indexOf("base64,");
        if (index == -1) {
            return false;
        }
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(image.substring(index + 7));
        OutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        try {
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /*条件查询*/
    public void selectByConditions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //读取请求时参数
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        //将json对象转换为Good类对象
        Good good = JSON.parseObject(params,Good.class);
        //调用goodService类的方法做条件查询
        List<Good> goods = goodService.selectByCondition(good.getBrandName(),good.getGoodName());
        //将List<Good>转换为json对象
        String jsonString = JSON.toJSONString(goods);
        response.getWriter().write(jsonString);
    }
    /*查询所有商品*/
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Good> goods = goodService.selectAll();
        String jsonString = JSON.toJSONString(goods);
        response.getWriter().write(jsonString);
    }
    /*查询所有品牌*/
    public void selectBrands(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> goods = goodService.selectBrand();
        String jsonString = JSON.toJSONString(goods);
        response.getWriter().write(jsonString);
    }


    /*------------------------------root操作------------------------------*/
    public void selectByConditionsByRoot(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //读取请求时参数
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        //将json对象转换为Good类对象
        Good good = JSON.parseObject(params,Good.class);

        /*--------------operation-----------------*/
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
        operation.setOperationname("查询商品");
        operation.setToID(good.getId());
        operationService.add(operation);


        //调用goodService类的方法做条件查询
        List<Good> goods = goodService.selectByCondition(good.getBrandName(),good.getGoodName());
        //将List<Good>转换为json对象
        String jsonString = JSON.toJSONString(goods);
        response.getWriter().write(jsonString);
    }



    /*------------------------------销售员操作------------------------------*/
    public void selectAllBySell(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Good> goods = goodService.selectAllBySell(user.getId());
        String jsonString = JSON.toJSONString(goods);
        response.getWriter().write(jsonString);
    }


    public void selectBrandsBySell(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<String> goods = goodService.selectBrandBySell(user.getId());
        String jsonString = JSON.toJSONString(goods);
        response.getWriter().write(jsonString);
    }


    public void selectByConditionsBySell(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        /*获取条件查询的商品条件属性*/
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Good good = JSON.parseObject(params,Good.class);

        /*操作时间*/
        Date otime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String otimestr = sdf.format(otime);

        Operation operation = new Operation();
        operation.setUserid(user.getId());
        operation.setUsername(user.getUserName());
        operation.setIP((String)session.getAttribute("loginIP"));
        operation.setDate(otimestr);
        operation.setOperationname("查询商品");
        operationService.add(operation);


        /*查询*/
        List<Good> goods = goodService.selectByConditionsBySell(good.getBrandName(),good.getGoodName(), user.getId());
        String jsonString = JSON.toJSONString(goods);
        response.getWriter().write(jsonString);
    }

    /*添加商品*/
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Good good = JSON.parseObject(params,Good.class);

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
        operation.setOperationname("添加商品");
        operationService.add(operation);

        goodService.add(good);
        response.getWriter().write("success");
    }
    /*删除商品*/
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Good good = JSON.parseObject(params,Good.class);

        /*------------------------记录操作日志---------------------*/
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
        operation.setOperationname("删除商品");
        operation.setToID(good.getId());
        operationService.add(operation);


        goodService.delete(good.getId());
        response.getWriter().write("success");
    }
    /*更新商品*/
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //读取请求时参数
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Good good = JSON.parseObject(params,Good.class);

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
        operation.setOperationname("更新商品");
        operation.setToID(good.getId());
        operationService.add(operation);


        goodService.update(good);

        /*保存图片*/
        String image = good.getImg_src();

        //将上传的图片保存到本地
        boolean is_successful = true;
        if (image != null) {
            String fileName = String.valueOf(good.getId());
            String parePath = request.getSession().getServletContext().getRealPath("/imgs/");
            setImage(image, "imgs/" + fileName + ".jpg");
            is_successful = setImage(image, parePath + fileName + ".jpg");
        }

        response.getWriter().write("success");
    }
}
