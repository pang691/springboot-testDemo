//package com.taikang.test.autz.util;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.sun.xml.internal.ws.util.UtilException;
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.Collections;
//
//public class UtilFastjson {
//    private static final String TAG = "JSON";
//    private static List<SerializerFeature> jsonFeatures = Collections.synchronizedList(new ArrayList());
//
//    public UtilFastjson() {
//    }
//
//    public static JSONObject parseObject(Object obj) {
//        return parseObject(toJSONString(obj));
//    }
//
//    public static JSONObject parseObject(String json) {
//        int features = JSON.DEFAULT_PARSER_FEATURE;
//        features |= JsonFormat.Feature.OrderedField.getMask();
//        return parseObject(json, features);
//    }
//
//    public static JSONObject parseObject(String json, int features) {
//        try {
//            return (JSONObject)JSON.parseObject(json, JSONObject.class, features, new JsonFormat.Feature[0]);
//        } catch (Exception var3) {
//            throw UtilException.unchecked(var3);
//        }
//    }
//
//    public static <T> T parseObject(JSONObject object, Class<T> clazz) {
//        return parseObject(toJSONString(object), clazz);
//    }
//
//    public static <T> T parseObject(String json, Class<T> clazz) {
//        try {
//            int features = JSON.DEFAULT_PARSER_FEATURE;
//            features |= Feature.OrderedField.getMask();
//            return JSON.parseObject(json, clazz, features, new Feature[0]);
//        } catch (Exception var3) {
//            throw UtilException.unchecked(var3);
//        }
//    }
//
//    public static JSONArray parseArray(String json) {
//        try {
//            return JSON.parseArray(json);
//        } catch (Exception var2) {
//            throw UtilException.unchecked(var2);
//        }
//    }
//
//    public static <T> List<T> parseArray(JSONArray array, Class<T> clazz) {
//        return parseArray(toJSONString(array), clazz);
//    }
//
//    public static <T> List<T> parseArray(String json, Class<T> clazz) {
//        try {
//            return JSON.parseArray(json, clazz);
//        } catch (Exception var3) {
//            throw UtilException.unchecked(var3);
//        }
//    }
//
//    public static String toJSONString(Object obj) {
//        if (obj instanceof String) {
//            return (String)obj;
//        } else {
//            try {
//                String json = JSON.toJSONString(obj, (SerializerFeature[])((SerializerFeature[])jsonFeatures.toArray(new SerializerFeature[0])));
//                return json;
//            } catch (Exception var2) {
//                throw UtilException.unchecked(var2);
//            }
//        }
//    }
//
//    public static String toJSONString(Object obj, SerializerFeature... features) {
//        if (obj instanceof String) {
//            return (String)obj;
//        } else {
//            try {
//                String json = JSON.toJSONString(obj, features);
//                return json;
//            } catch (Exception var3) {
//                throw UtilException.unchecked(var3);
//            }
//        }
//    }
//
//    public static boolean isJSONObject(Object obj) {
//        if (obj instanceof JSONObject) {
//            return true;
//        } else if (!(obj instanceof String)) {
//            return false;
//        } else {
//            try {
//                JSONObject json = parseObject((String)obj);
//                return json != null && !json.isEmpty();
//            } catch (Exception var2) {
//                throw UtilException.unchecked(var2);
//            }
//        }
//    }
//
//    public static boolean isJSONArray(Object obj) {
//        if (obj instanceof JSONArray) {
//            return true;
//        } else if (!(obj instanceof String)) {
//            return false;
//        } else {
//            try {
//                JSONArray json = parseArray((String)obj);
//                return json != null && !json.isEmpty();
//            } catch (Exception var2) {
//                throw UtilException.unchecked(var2);
//            }
//        }
//    }
//
//    static {
//        jsonFeatures.add(SerializerFeature.WriteDateUseDateFormat);
//        jsonFeatures.add(SerializerFeature.WriteMapNullValue);
//        jsonFeatures.add(SerializerFeature.PrettyFormat);
//        jsonFeatures.add(SerializerFeature.DisableCircularReferenceDetect);
//    }
//}