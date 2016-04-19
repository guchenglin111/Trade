package com.bs.trade.service.impl; 

import com.bs.trade.model.GoodsType;
import com.bs.trade.service.GoodsTypeService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** 
* GoodsTypeServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 7, 2016</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class GoodsTypeServiceImplTest {

    private static final Logger LOGGER = Logger.getLogger(UserInfoServiceImplTest.class);
    @Autowired
    private GoodsTypeService goodsTypeService;

    @Test
    public void testInsert() throws Exception {

        GoodsType goodsType = new GoodsType();
        goodsType.setName("电器");
        Object  o = goodsTypeService.save(goodsType);
        LOGGER.info(o);
    }
}
