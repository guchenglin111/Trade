package com.bs.trade.service.impl; 

import com.bs.trade.controller.UploadController;
import com.bs.trade.mapper.ForcusMapper;
import com.bs.trade.mapper.GoodsMapper;
import com.bs.trade.model.Focus;
import com.bs.trade.model.GoodsInfo;
import com.bs.trade.service.GoodsService;
import org.apache.log4j.Logger;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
* GoodsServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 7, 2016</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class GoodsServiceImplTest {

    private static final Logger LOGGER = Logger.getLogger(UserInfoServiceImplTest.class);
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private ForcusMapper forcusMapper;



    @Test
    public void testinsrt() throws Exception {
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setName("漫画");
        Object o = goodsService.save(goodsInfo);
        LOGGER.info(o);
    }

    @Test
    public void testgetByGoods() throws Exception {
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setRegion("石景山");
        Object  o = goodsService.selectByGoods(goodsInfo,1,10);
        LOGGER.info(o);

    }

   /* @Test
    public void testbatchsave() throws Exception {
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setName("夏目友人帐");
        goodsInfo.setRegion("石景山");
        GoodsInfo goodsInfo2 = new GoodsInfo();
        goodsInfo2.setName("野良神");
        goodsInfo2.setRegion("敦煌");
        List<GoodsInfo> goods = Lists.newArrayList(goodsInfo,goodsInfo2);
        Object o = goodsService.batchsave(goods);
        LOGGER.info(o);

    }*/

    @Test
    public void testShow() throws Exception {

       // Object o = goodsMapper.showGoodsByOut(null,null,null);
        GoodsInfo goodsInfo = new GoodsInfo();
        Object o = goodsService.selectGoodsInfo(goodsInfo,1,10,"out_count");
        LOGGER.info(o);
    }

    @Test
    public void testFriend() throws Exception {
        Integer id = 1;
        Object o = forcusMapper.firendList(id);
        LOGGER.info(o);

    }

    @Test
    public void testFocus() throws Exception {
        Focus focus = new Focus();
        focus.setThisId(1);
        focus.setFriendId(2);
        Object o = forcusMapper.delete(focus);
        LOGGER.info(o);

    }

    @Test
    public void testselect() throws Exception {
           String secrch = "海淀";
          Object o = goodsService.selectAny(secrch,1,10);
        LOGGER.info(o);
    }
}
