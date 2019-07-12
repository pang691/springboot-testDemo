package com.taikang.test.annotation.UtilTools;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class UtilReflect {
    private static final ConcurrentMap<String, Map<String, Field>> FIELD_CACHE = new ConcurrentHashMap();

    public static Map<String,Field> getBeanFields(Class cl){
        String className = descClass(cl);
        if(FIELD_CACHE.containsKey(className)){
            return (Map)FIELD_CACHE.get(className);
        }else {
            HashMap properties;
            for(properties = new HashMap(); cl != null; cl = cl.getSuperclass()){
                Field[] fields = cl.getDeclaredFields();
                Field[] var4 = fields;
                int var5 = fields.length;
                for(int var6 = 0; var6 < var5; ++var6){
                    Field field = var4[var6];
                    if(!Modifier.isTransient(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())){
                        field.setAccessible(true);
                        properties.put(field.getName(),field);
                    }
                }
            }
            FIELD_CACHE.put(className,properties);
            return properties;
        }
    }

    public static Object getFieldValue(Object object, String fieldName){
        Object result = null;
        try{
            Field field = getDeclaredField(object,fieldName);
            if(field == null){
                return null;
            }
            makeAccessible(field);
            result = field.get(object);
        }catch (IllegalAccessException e){
            ;
        }
        return result;
    }

    private static Field getDeclaredField(Object object, String fieldName) {
        Class superClass = object.getClass();
        while (superClass != Object.class){
            try {
                return superClass.getDeclaredField(fieldName);
            }catch (NoSuchFieldException e){
                superClass = superClass.getSuperclass();
            }
        }
        return null;
    }

    public static String descClass(Class<?> c){
        if(!c.isArray()){
            return c.getName();
        }else {
            StringBuilder sb = new StringBuilder();
            do{
                sb.append("[]");
                c = c.getComponentType();
            }while (c.isArray());
            return c.getName() + sb.toString();
        }
    }
    public static void makeAccessible(Field field){
        if(!Modifier.isTransient(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())){
            field.setAccessible(true);
        }
    }

    public static void setFieldValue(Object object, String fieldName, Object value) {
        try{
            Field field = getDeclaredField(object,fieldName);
            if(field == null){
                throw new IllegalArgumentException("wewewewe");
            }
            makeAccessible(field);
            field.set(object,value);
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
    }
}
