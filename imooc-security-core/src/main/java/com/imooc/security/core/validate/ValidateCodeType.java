package com.imooc.security.core.validate;

import com.imooc.security.core.properties.SecurityConstants;

/**
 * @program: imooc-security
 * @description: 验证码类型枚举
 * @author: ouhuixuan
 * @create: 2019-09-27 15:29
 **/
public enum ValidateCodeType {
    SMS{
        @Override
        public String getParamNameOnValidate(){
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },
    /**
     * 图片验证码
     */
    IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };

    /**
     * 校验时从请求中获取的参数的名字
     * @return
     */
    public abstract String getParamNameOnValidate();
}
