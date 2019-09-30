package com.imooc.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static java.lang.Thread.*;

/**
 * @program: imooc-security
 * @description: 由于当前没有使用消息队列，所以该对象为模拟消息队列的对象
 * @author: ouhuixuan
 * @create: 2019-09-06 16:06
 **/
@Component
public class MockQueue {
   private Logger logger = LoggerFactory.getLogger(getClass());
    /**代表下单的消息，当该属性不为空时，代表接到了下单的消息*/
    private String placeOrder;
    /**代表订单完成的消息，当该属性不为空时，代表接到了订单完成的消息*/
    private String completeOrder;
    /**
    * @Description: 把下单消息属性的set方法改造，使该类真正成为模拟消息队列的类
    * @Param:
    * @return:
    * @Author: ouhuixuan
    * @Date: 2019/9/6
    */
    public void setPlaceOrder(String placeOrder) throws InterruptedException {
       new Thread(()->{
          logger.info("接到下单请求");
           //模拟业务处理
           try {
               Thread.sleep(3000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           //为订单完成属性设置，代表订单完成了，收到订单完成的消息
           this.completeOrder = placeOrder;
          logger.info("下单请求处理完毕："+placeOrder);
       }).start();
    }

    public String getPlaceOrder() {
        return placeOrder;
    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
