package com.imooc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @program: imooc-security
 * @description:
 * @author: ouhuixuan
 * @create: 2019-09-03 09:52
 **/
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {
    @Override
    public void initialize(MyConstraint constraint) {
        System.out.println("校验器运行前的准备工作");
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        System.out.println("校验器实现逻辑");
        return false;
    }
}
