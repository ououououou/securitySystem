package com.imooc.web.async;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: imooc-security
 * @description: 用于处理传递结果到主线程
 * @author: ouhuixuan
 * @create: 2019-09-06 16:27
 **/
@Component
public class DeferredResultHolder {
   /**Key是订单号，每个订单号对应一个订单的处理结果，DeferredResult存放的就是订单的处理结果，最终会返回一个结果给前台*/
    private Map<String,DeferredResult<String>> map = new HashMap<String,DeferredResult<String>>();

    public Map<String, DeferredResult<String>> getMap() {
        return map;
    }

    public void setMap(Map<String, DeferredResult<String>> map) {
        this.map = map;
    }
}
