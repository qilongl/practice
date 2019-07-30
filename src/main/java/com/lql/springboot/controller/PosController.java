package com.lql.springboot.controller;

import com.lql.springboot.service.PosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author lql
 * @date 2018/6/10 19:19
 * @describe pos机后台数据接口提供
 */
@CrossOrigin
@RestController
@RequestMapping("/pos")
public class PosController {
    @Autowired
    PosService posService;


    /**
     * 获取菜单 code子集
     *
     * @param response
     * @return
     */
    @PostMapping("/findFoodMenuList")
    public String findFoodMenuList(@RequestBody Map map, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String result = posService.findFoodMenuList(map);
        return result;
    }

    /**
     * 获取菜单
     *
     * @param req
     * @param response
     * @return
     */
    @RequestMapping("/findFoodMenuList2")
    public String findFoodMenuList2(HttpServletRequest req, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String result = posService.findFoodMenuList2();
        return result;
    }

    /**
     * 获取热销菜单
     * @param req
     * @param response
     * @return
     */
    @RequestMapping("/getOfferFoodlist")

    public String getOfferFoodlist(HttpServletRequest req, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String result = posService.getOfferFoodlist();
        return result;
    }

    /**
     * 获取历史订单
     *
     * @param req
     * @param response
     * @return
     */
    @RequestMapping("/getAllOrderList")
    public String getAllOrderList(@RequestBody Map map, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String result = posService.getAllOrderList(map);
        return result;
    }

    /**
     * 下单
     *
     * @param jsonParams
     * @param req
     * @param response
     * @return
     */
    @RequestMapping(value = "/addUserOrder", method = {RequestMethod.POST, RequestMethod.GET})
    public String addOrder(@RequestParam(value = "jsonParams", required = true) String jsonParams,
                           HttpServletRequest req, HttpServletResponse response) throws SQLException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String result = posService.addOrder(String.valueOf(jsonParams));
        return result;
    }

    /**
     * 查询订单详情
     *
     * @param jsonParams
     * @param req
     * @param response
     * @return
     */
    @RequestMapping(value = "/findOrderInfo", method = {RequestMethod.POST, RequestMethod.GET})
    public String findOrderInfo(@RequestParam(value = "jsonParams", required = true) String jsonParams,
                                HttpServletRequest req, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String result = posService.findOrderInfo(String.valueOf(jsonParams));
        return result;
    }
}
