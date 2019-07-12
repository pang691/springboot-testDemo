package com.taikang.test.annotation.in;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

/**
 * 通过注解前端数据校验
 */
public class ValidateByKu implements ConstraintValidator<AnnotationCustom,String> {
    private AnnotationCustom annotationCustom;
    @Override
    public void initialize(AnnotationCustom constraintAnnotation) {
        this.annotationCustom = constraintAnnotation;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return doCheck(value);
    }

    private boolean doCheck(Object value){
        String sourceValue = (String) value;
        if(StringUtils.isEmpty(sourceValue)){
            return true;
        }
        //TODO 查询数据库
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("dict");
        if(!list.contains(sourceValue)){
            System.out.println("非法的数值：{}"+sourceValue);
            return false;
        }
        return true;
    }
}
