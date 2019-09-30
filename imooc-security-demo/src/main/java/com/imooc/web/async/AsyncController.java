package com.imooc.web.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * @program: imooc-security
 * @description: 异步处理Restful请求
 * @author: ouhuixuan
 * @create: 2019-09-06 10:32
 **/
@RestController
public class AsyncController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /* @RequestMapping("/order")
     public Callable<String> order() throws Exception{
         logger.info("主线程开始");
         Callable<String> result = new Callable<String>() {
             @Override
             public String call() throws Exception {
                 logger.info("副线程开始");
                 logger.info("副线程执行业务");
                 Thread.sleep(3000);
                 logger.info("副线程结束并返回值");
                 return "success";
             }
         };
         logger.info("主线程结束并返回");
         return result;
     }*/


    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;
    @RequestMapping("/order")
    public DeferredResult<String> order() throws Exception {
        logger.info("主线程开始");
        //模拟生成订单号
        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);
        DeferredResult  result = new DeferredResult();
        deferredResultHolder.getMap().put(orderNumber,result);
        logger.info("主线程结束");
        return result;
    }
}
