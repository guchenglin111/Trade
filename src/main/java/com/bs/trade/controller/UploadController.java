package com.bs.trade.controller;

import com.bs.trade.model.GoodsInfo;
import com.bs.trade.model.Picture;
import com.bs.trade.model.UserInfo;
import com.bs.trade.service.GoodsService;
import com.bs.trade.service.PictureService;
import com.bs.trade.service.UserInfoService;
import com.bs.trade.session.SessionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 上传
 * Created by wangyanan on 2016/5/13.
 */
@Controller
public class UploadController {
    private static final Logger LOGGER = Logger.getLogger(UploadController.class);
    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private PictureService pictureService;

    @RequestMapping("user/upload")
    public ModelAndView upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {
        String realPath = request.getSession().getServletContext().getRealPath("/pic");
        File pathFile = new File(realPath);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        UserInfo userInfo = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        userInfo.setPortrait("pic/" + file.getOriginalFilename());
        //将文件copy上传到服务器
        file.transferTo(new File(realPath + "/" + file.getOriginalFilename()));
        userInfoService.updateNotNull(userInfo);
        return new ModelAndView("personal");
    }


    @RequestMapping("goods/upload")
    public ModelAndView uploadGoods(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {
        String realPath = request.getSession().getServletContext().getRealPath("/pic");
        File pathFile = new File(realPath);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        UserInfo userInfo = (UserInfo) sessionService.getAttribute(request, Constants.User_SESSION);
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setOwner(userInfo.getId());
        goodsInfo.setImageUrl("pic/" + file.getOriginalFilename());
        //将文件copy上传到服务器
        file.transferTo(new File(realPath + "/" + file.getOriginalFilename()));
        goodsInfo.setCollectCount(0);
        goodsInfo.setOutCount(0);
        goodsInfo.setSearchCount(0);
        goodsService.save(goodsInfo);
        return new ModelAndView("publish");
    }

    @RequestMapping("goods/upload/group")
    public ModelAndView uploadGoods(@RequestParam("file") MultipartFile[] file, HttpServletRequest request) throws IllegalStateException, IOException {
        String realPath = request.getSession().getServletContext().getRealPath("/pic");
        File pathFile = new File(realPath);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        Picture picture = new Picture();
        Integer goods = goodsService.getListOne();
        picture.setGoods(goods);
        for (MultipartFile f : file) {
            picture.setPrint("pic/" + f.getOriginalFilename());
            f.transferTo(new File(realPath + "/" + f.getOriginalFilename()));
            pictureService.save(picture);
        }
        //将文件copy上传到服务器
        return new ModelAndView("publish");
    }

}

