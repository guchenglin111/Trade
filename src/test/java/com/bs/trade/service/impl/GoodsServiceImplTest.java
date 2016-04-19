package com.bs.trade.service.impl; 

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
}
