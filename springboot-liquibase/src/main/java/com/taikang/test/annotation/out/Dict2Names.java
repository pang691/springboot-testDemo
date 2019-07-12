package com.taikang.test.annotation.out;

import com.taikang.test.annotation.UtilTools.UtilReflect;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("dict2Names")
public class Dict2Names {


    public void code2Name(Object object){
        if(null != object){
            //TODO 判断这个类是否集成model超类
            Map<String, Field> resultMap = UtilReflect.getBeanFields(object.getClass());
            if(null == resultMap || resultMap.size() == 0){
                return;
            }
            Iterator fieldIter = resultMap.keySet().iterator();
            while (fieldIter.hasNext()){
                String fieldName = (String)fieldIter.next();
                Field field = (Field)resultMap.get(fieldName);

                try {
                    Object fieldObject = UtilReflect.getFieldValue(object,fieldName);
//                    if(fieldObject != null){
//                        this.code2NameBatch(fieldObject);
//                    }else if(this.isValueObject(fieldObject)){
//                        this.code2Name(fieldObject);
//                    }else
                        if(field.isAnnotationPresent(Dict2NameLabel.class)){
                        this.doRealSwitch(object,field,fieldObject);
                    }
                }catch (IllegalArgumentException e){
                    e.printStackTrace();
                }

            }
        }
    }

    private void code2NameBatch(Object fieldObject) {
        List voList = (List)fieldObject;
        for (Object vo : voList){
            if(vo != null){
                code2Name(vo);
            }
        }
    }
    private Boolean isValueObject(Object fieldObject){
        Boolean flag = false;
        if(fieldObject != null){
            flag = (fieldObject instanceof Model);
        }
        return true;
    }

    private void doRealSwitch(Object object, Field field, Object fieldObject) {
        if(fieldObject.getClass().getTypeName().equals("java.lang.String")){
            String source = (String)fieldObject;
            if(StringUtils.isEmpty(source)){
                return;
            }
            Dict2NameLabel dict2NameLabel = (Dict2NameLabel)field.getAnnotation(Dict2NameLabel.class);
            String classified = dict2NameLabel.classified();
            //TODO 数据库查询 根据 classified 和 source 查询数据库
            String targetValue = "赋值进来啦啦啦啦";
            String target = dict2NameLabel.target();
            if(!StringUtils.isEmpty(target)){
                UtilReflect.setFieldValue(object,target,targetValue);
            }
        }
    }

}
