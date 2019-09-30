package com.imooc.web.async;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @program: imooc-security
 * @description: 异步线程监听器
 * @author: ouhuixuan
 * @create: 2019-09-09 10:50
 **/
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent>{
    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshEvent) {
       new Thread(()->{
           //模拟监听过程
           while(true){
               if (StringUtils.isNotBlank(mockQueue.getCompleteOrder())){
                   String orderNumber = mockQueue.getCompleteOrder();
                   logger.info("返回订单处理结果："+orderNumber);
                   deferredResultHolder.getMap().get(orderNumber).setResult("place order success");
                   mockQueue.setCompleteOrder(null);
               }else{
                   try {
                       Thread.sleep(1000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }
       }).start();
    }
}
