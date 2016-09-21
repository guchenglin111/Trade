package com.bs.trade.controller;

import com.alibaba.fastjson.JSON;
import com.bs.trade.model.GoodsInfo;
import com.bs.trade.model.GoodsType;
import com.bs.trade.model.GoodsVo;
import com.bs.trade.model.UserInfo;
import com.bs.trade.service.GoodsService;
import com.bs.trade.service.GoodsTypeService;
import com.bs.trade.service.UserInfoService;
import com.bs.trade.session.SessionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * 物品相关控制类
 * Created by wangyanan on 2016/4/7.
 */
@Controller
public class GoodsController {
    private String page_list = "index";


    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserInfoService userInfoService;


    /**
     * 获取物品列表
     *
     * @param goodInfo 检索字段
     * @param page  分页信息
     * @param rows  分页信息
     * @param sort  分页信息
     * @return 检索物品列表
     */
    @RequestMapping(value = {"list", "index", "index.html", ""})
    public ModelAndView getList(GoodsInfo goods,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "8") int rows, String sort) {
        ModelAndView result = new ModelAndView(page_list);
        PageInfo<GoodsVo> goodsInfoList = goodsService.selectGoodsInfo(goods, page, rows, sort);
        result.addObject("pageInfo", goodsInfoList);
        result.addObject("queryParam", goods);
        result.addObject("page", page);
        result.addObject("rows", rows);
        return result;
    }


    /**
     * 列表页
     */
    @RequestMapping(value = {"goods/goodsList"})
    public ModelAndView goodsList(@RequestParam(required = false, defaultValue = "1") int page,
                                  @RequestParam(required = false, defaultValue = "5") int rows) {
        ModelAndView result = new ModelAndView("goodsList");
        // GoodsInfo goodsInfo = JSON.parseObject(goods, GoodsInfo.class);
        GoodsInfo goodsInfo = new GoodsInfo();
        PageInfo<GoodsVo> goodsInfoList = goodsService.selectGoodsInfo(goodsInfo, page, rows);
        result.addObject("pageInfo", goodsInfoList);
        result.addObject("page", page);
        result.addObject("rows", rows);
        return result;
    }


    /**
     * 列表页
     * @param goods
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = {"goods/selectList"})
    public ModelAndView goodsList(@RequestBody String goods,
                                  @RequestParam(required = false, defaultValue = "1") int page,
                                  @RequestParam(required = false, defaultValue = "5") int rows) {
        ModelAndView result = new ModelAndView("goodsList");
        GoodsInfo goodsInfo = JSON.parseObject(goods, GoodsInfo.class);
        PageInfo<GoodsVo> goodsInfoList = goodsService.selectGoodsInfo(goodsInfo, page, rows);
        result.addObject("pageInfo", goodsInfoList);
        result.addObject("page", page);
        result.addObject("rows", rows);
        return result;
    }

    /**
     * 列表页(搜索)
     */
    @RequestMapping(value = {"goods/searchList"})
    public ModelAndView searchList( String search,
                                  @RequestParam(required = false, defaultValue = "1") int page,
                                  @RequestParam(required = false, defaultValue = "5") int rows) {
        ModelAndView result = new ModelAndView("goodsList");
        PageInfo<GoodsVo> goodsInfoList = goodsService.selectAny(search, page, rows);
        result.addObject("pageInfo", goodsInfoList);
        result.addObject("page", page);
        result.addObject("rows", rows);
        return result;
    }



    /**
     * 管理员页
     */
    @RequestMapping(value = {"admin/goods"})
    public ModelAndView adminList(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "5") int rows) {
        ModelAndView result = new ModelAndView("adminItem");
        PageInfo<GoodsVo> goodsInfoList = goodsService.adminGoodsInfo(page, rows);
        result.addObject("pageInfo", goodsInfoList);
        result.addObject("page", page);
        result.addObject("rows", rows);
        return result;
    }

    /**
     * 资金列表
     */
    @RequestMapping("money")
    public ModelAndView getMList(@RequestBody String goods,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "5") int rows, @RequestParam(required = false, defaultValue = "id") String sort) {
        ModelAndView result = new ModelAndView(page_list);
        GoodsInfo goodsInfo = JSON.parseObject(goods, GoodsInfo.class);
        PageInfo<GoodsVo> goodsInfoList = goodsService.selectMoney(goodsInfo, page, rows, sort);
        result.addObject("pageInfo", goodsInfoList);
        result.addObject("page", page);
        result.addObject("rows", rows);
        return result;
    }


    /**
     * 根据物品主键查找物品
     *
     * @param goods 主键
     * @return 物品详细信息
     */
    @RequestMapping(value = "view")
    public ModelAndView view(@RequestBody String goods,HttpServletRequest request) {
        UserInfo curure = (UserInfo)sessionService.getAttribute(request,Constants.User_SESSION);
        GoodsVo goodsVo = JSON.parseObject(goods, GoodsVo.class);
        GoodsVo goodsInfo = new GoodsVo();
        if (curure != null){
            if (curure.getEmail().equals("admin@trade.com")){
                goodsInfo = goodsService.adminGetGoodsInfo(goodsVo);
            }
            else{
                goodsInfo  = goodsService.getGoodsInfo(goodsVo);
            }
        }

        else
        { goodsInfo  = goodsService.getGoodsInfo(goodsVo);}
        return new ModelAndView("shop", "goods", goodsInfo);
    }

    /**
     * 添加物品
     *
     * @param goods 添加对象
     * @return 操作记录
     */
    @RequestMapping(value = "user/addGoods")
    public ModelAndView save(@RequestBody String goods, HttpServletRequest request) {
        GoodsInfo goodsInfo = JSON.parseObject(goods, GoodsInfo.class);
        UserInfo userInfo = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        UserInfo user = userInfoService.getByEmail(userInfo);
        Integer id = goodsService.getListOne();
        goodsInfo.setId(id);
        goodsInfo.setStatus(0);
        goodsInfo.setOwner(userInfo.getId());
        goodsService.updateNotNull(goodsInfo);

        //修改发布商品数
        user.setGoodsCount(user.getGoodsCount()+1);
        userInfoService.updateNotNull(user);
        return new ModelAndView("index");
    }

    /**
     * 添加物品(资金周转)
     *
     * @param goods 添加对象
     * @return 操作记录
     */
    @RequestMapping(value = "user/addGoodsMoney")
    public ModelAndView saveMoney(@RequestBody String goods, HttpServletRequest request) {
        GoodsInfo goodsInfo = JSON.parseObject(goods, GoodsInfo.class);
        UserInfo userInfo = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        goodsInfo.setStatus(0);
        goodsInfo.setType(6);
        goodsInfo.setOwner(userInfo.getId());
        goodsInfo.setImageUrl("img/money.jpg");
        goodsService.save(goodsInfo);
        //修改发布商品数
        userInfo.setGoodsCount(userInfo.getGoodsCount()+1);
        userInfoService.updateNotNull(userInfo);
        return new ModelAndView("index");
    }

    /**
     * 删除物品
     *
     * @return 操作记录
     */
    @RequestMapping("admin/delete")
    public ModelAndView delete(@RequestBody String goods) {
        GoodsInfo goodsInfo = JSON.parseObject(goods, GoodsInfo.class);
        goodsService.delete(goodsInfo);
        return new ModelAndView("adminItem");
    }


    /**
     * 个人主页发布商品列表
     */
    @RequestMapping(value = {"user/add/list"})
    public ModelAndView addList(HttpServletRequest request, @RequestBody String user,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false, defaultValue = "5") int rows) {
        UserInfo curuser = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        UserInfo userInfo = JSON.parseObject(user, UserInfo.class);
        Integer id = userInfo.getId();
        Integer sessionId = curuser.getId();
        if (id == -1 || id.equals(sessionId)) {
            PageInfo<GoodsVo> addList = goodsService.showAdd(curuser, page, rows);
            return new ModelAndView("personal", "list", addList);
        } else {
            PageInfo<GoodsVo> addList = goodsService.showAdd(userInfo, page, rows);
            return new ModelAndView("personal", "list", addList);
        }
    }
}
