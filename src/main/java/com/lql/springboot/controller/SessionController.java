package com.lql.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.lql.springboot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@RestController
public class SessionController {
    @Autowired
    BookService bookService;

    @RequestMapping(value = "session")
    public String session(HttpServletRequest req,HttpServletResponse response){
        String result="";
        HttpSession session =req.getSession();
        if (session.isNew()){
            result="session创建成功，session的ID为："+session.getId();
        }else {
            result="session已经存在，session的ID为："+session.getId();
        }
        session.setAttribute("data",req.getRequestURI());
        return result+"                     "+session.getAttribute("data");
    }

    @RequestMapping(value = "book/list")
    public String bookList(HttpServletRequest req,HttpServletResponse response){
        HttpSession session =req.getSession();
        session.setAttribute("list",bookService.getAll());
        return bookService.getAll()+" "+session.getId();
    }

    @RequestMapping(value = "book")
    public String book(HttpServletRequest req,HttpServletResponse response){
        return bookService.getAll();
    }

    @RequestMapping(value = {"/hello","/hi" })
    private String hello(HttpServletRequest req, HttpServletResponse response) throws UnsupportedEncodingException {
        List<Map> list = new ArrayList<Map>();
        Map<String,Object> params=new HashMap<>();
        Map<String,Object> params1=new HashMap<>();
        Enumeration<String> names=req.getParameterNames();
        while (names.hasMoreElements()){
            String key = names.nextElement();
            String value = req.getParameter(key);
            params.put(key,value);
//            params1.put("##"+key+"##","%%"+value+"%%");
        }
        list.add(params);
        list.add(params1);

        Cookie[] it=req.getCookies();
        if (it!=null && it.length>0){
            for (Cookie cookie:it){
                System.out.println(cookie.getName()+":"+URLEncoder.encode(cookie.getValue(),"utf-8"));
            }
        }

//        for (int i=0;i<it.length;i++){
//            String name=it[i].getName();
//            String value=it[i].getValue();
//            params1.put(name,it[i]);
//        }
//        System.out.println(it.toString());
        Cookie cookie = new Cookie("account", URLEncoder.encode("刘奇龙","utf-8"));
        Cookie cookie1 = new Cookie("password","123456");
//        cookie.setMaxAge(60);
//        cookie.setPath("/");
        response.addCookie(cookie);
        response.addCookie(cookie1);
        String sb=JSON.toJSONString(list);
        return JSON.toJSONString(req.getSession().getAttribute("list"));

    }
}
