package com.taikang.test.AopLogger.utilTool;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

public class UtilAop {
    public UtilAop() {
    }

    public static <T extends Annotation> T findMethodAnnotation(Class targetClass, Method method, Class<T> annClass) {
        T a = AnnotationUtils.findAnnotation(method, annClass);
        if (a != null) {
            return a;
        } else {
            Method m = ClassUtils.getMostSpecificMethod(method, targetClass);
            a = AnnotationUtils.findAnnotation(m, annClass);
            return a;
        }
    }

    public static <T extends Annotation> T findAnnotation(Class targetClass, Class<T> annClass) {
        return AnnotationUtils.findAnnotation(targetClass, annClass);
    }

    public static <T extends Annotation> T findAnnotation(Class targetClass, Method method, Class<T> annClass) {
        T a = findMethodAnnotation(targetClass, method, annClass);
        return a != null ? a : findAnnotation(targetClass, annClass);
    }

    public static <T extends Annotation> T findAnnotation(JoinPoint pjp, Class<T> annClass) {
        MethodSignature signature = (MethodSignature)pjp.getSignature();
        Method m = signature.getMethod();
        Class<?> targetClass = pjp.getTarget().getClass();
        return findAnnotation(targetClass, m, annClass);
    }

    public static final Map<String, Object> getMethodArgs(JoinPoint pjp) {
        MethodSignature signature = (MethodSignature)pjp.getSignature();
        Map<String, Object> args = new LinkedHashMap();
        String[] names = signature.getParameterNames();
        int i = 0;

        for(int len = names.length; i < len; ++i) {
            args.put(names[i], pjp.getArgs()[i]);
        }

        return args;
    }

    public static final String getMethodName(ProceedingJoinPoint pjp) {
        StringBuilder methodName = (new StringBuilder(pjp.getSignature().getName())).append("(");
        MethodSignature signature = (MethodSignature)pjp.getSignature();
        String[] names = signature.getParameterNames();
        Class[] args = signature.getParameterTypes();
        int i = 0;

        for(int len = args.length; i < len; ++i) {
            if (i != 0) {
                methodName.append(",");
            }

            methodName.append(args[i].getSimpleName()).append(" ").append(names[i]);
        }

        return methodName.append(")").toString();
    }
}