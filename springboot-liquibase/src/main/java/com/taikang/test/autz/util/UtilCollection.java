//package com.taikang.test.autz.util;
//
//import com.sun.xml.internal.ws.util.UtilException;
//import org.slf4j.Logger;
//
//import java.util.*;
//
//public class UtilCollection {
//    private UtilCollection() {
//    }
//
//    public static boolean isEmpty(Collection<?> collection) {
//        return collection == null || collection.size() == 0;
//    }
//
//    public static boolean isNotEmpty(Collection<?> collection) {
//        return collection != null && collection.size() > 0;
//    }
//
//    public static boolean isEmpty(Map map) {
//        return map == null || map.size() == 0;
//    }
//
//    public static boolean isNotEmpty(Map map) {
//        return map != null && map.size() > 0;
//    }
//
//    public static int sizeOf(Collection<?> collection) {
//        return isEmpty(collection) ? 0 : collection.size();
//    }
//
//    public static int sizeOf(Map<?, ?> map) {
//        return map == null ? 0 : map.size();
//    }
//
//    public static <K extends Comparable, V> Map<K, V> sortMapByKey(Map<K, V> data) {
//        Map<K, V> data_ = new LinkedHashMap();
//        List<K> list = new LinkedList(data.keySet());
//        Collections.sort(list);
//        Iterator var3 = list.iterator();
//
//        while(var3.hasNext()) {
//            K k = (Comparable)var3.next();
//            data_.put(k, data.get(k));
//        }
//
//        return data_;
//    }
//
//    public static <T> List<T> sortList(List<T> list) {
//        if (list != null && list.size() > 0) {
//            Collections.sort(list);
//        }
//
//        return list;
//    }
//
//    public static List set2List(Set itemSet) {
//        if (itemSet == null) {
//            return null;
//        } else {
//            List _list = new ArrayList();
//            if (itemSet.isEmpty()) {
//                return _list;
//            } else {
//                Iterator iter = itemSet.iterator();
//
//                while(iter.hasNext()) {
//                    _list.add(iter.next());
//                }
//
//                return _list;
//            }
//        }
//    }
//
//    public static List<String> array2List(String... items) {
//        if (items == null) {
//            return null;
//        } else {
//            List _list = new ArrayList();
//            if (items.length == 0) {
//                return _list;
//            } else {
//                String[] var2 = items;
//                int var3 = items.length;
//
//                for(int var4 = 0; var4 < var3; ++var4) {
//                    Object obj = var2[var4];
//                    _list.add(obj);
//                }
//
//                return _list;
//            }
//        }
//    }
//
//    public static Set list2Set(List itemList) {
//        if (itemList == null) {
//            return null;
//        } else {
//            Set _set = new HashSet();
//            if (itemList.isEmpty()) {
//                return _set;
//            } else {
//                Iterator var2 = itemList.iterator();
//
//                while(var2.hasNext()) {
//                    Object obj = var2.next();
//                    _set.add(obj);
//                }
//
//                return _set;
//            }
//        }
//    }
//
//    public static Set<String> array2Set(String... items) {
//        if (items == null) {
//            return null;
//        } else {
//            Set _set = new HashSet();
//            if (items.length == 0) {
//                return _set;
//            } else {
//                String[] var2 = items;
//                int var3 = items.length;
//
//                for(int var4 = 0; var4 < var3; ++var4) {
//                    Object obj = var2[var4];
//                    _set.add(obj);
//                }
//
//                return _set;
//            }
//        }
//    }
//
//    public static String joinArray(Object... objs) {
//        Collection collection = Arrays.asList(objs);
//        return joinCollection(collection, ",");
//    }
//
//    public static String joinCollection(Collection objs) {
//        return joinCollection(objs, ",");
//    }
//
//    public static String joinCollection(Collection objs, String spliter) {
//        if (objs != null && !objs.isEmpty()) {
//            StringBuilder buffer = new StringBuilder();
//            int i = 0;
//
//            for(Iterator var4 = objs.iterator(); var4.hasNext(); ++i) {
//                Object obj = var4.next();
//                if (i != 0) {
//                    buffer.append(",");
//                }
//
//                buffer.append(obj);
//            }
//
//            return buffer.toString();
//        } else {
//            return "";
//        }
//    }
//
//public static List<String> splitString2List(String temp) {
//        return splitString2List(temp, ',');
//    }
//
//    public static List<String> splitString2List(String temp, char spliter) {
//        List<String> list = new ArrayList();
//        if (UtilString.isEmpty(temp)) {
//            return list;
//        } else {
//            String[] ary = UtilString.split(temp, spliter);
//            if (ary != null && ary.length > 0) {
//                String[] var4 = ary;
//                int var5 = ary.length;
//
//                for(int var6 = 0; var6 < var5; ++var6) {
//                    String str = var4[var6];
//                    list.add(str);
//                }
//            }
//
//            return list;
//        }
//    }
//
//    public static Integer[] stringArr2IntArr(String[] strArr) {
//        Integer[] inArr = new Integer[strArr.length];
//
//        for(int i = 0; i < strArr.length; ++i) {
//            try {
//                inArr[i] = Integer.parseInt(strArr[i].trim());
//            } catch (NumberFormatException var4) {
//                throw var4;
//            }
//        }
//
//        return inArr;
//    }
//
//    public static String extractToString(Collection collection, String propertyName, String separator) {
//        List list = extractToList(collection, propertyName);
//        return StringUtils.join(list, separator);
//    }
//
//    public static List extractToList(Collection collection, String propertyName) {
//        ArrayList list = new ArrayList(collection.size());
//
//        try {
//            Iterator var3 = collection.iterator();
//
//            while(var3.hasNext()) {
//                Object obj = var3.next();
//                list.add(PropertyUtils.getProperty(obj, propertyName));
//            }
//
//            return list;
//        } catch (Exception var5) {
//            throw UtilException.convertReflectExceptionToUnchecked(var5);
//        }
//    }
//
//    public static Map extractToMap(Collection collection, String keyPropertyName, String valuePropertyName) {
//        HashMap map = new HashMap(collection.size());
//
//        try {
//            Iterator var4 = collection.iterator();
//
//            while(var4.hasNext()) {
//                Object obj = var4.next();
//                map.put(PropertyUtils.getProperty(obj, keyPropertyName), PropertyUtils.getProperty(obj, valuePropertyName));
//            }
//
//            return map;
//        } catch (Exception var6) {
//            throw UtilException.convertReflectExceptionToUnchecked(var6);
//        }
//    }
//
//    public static Map<String, String> toStringMap(String... pairs) {
//        Map<String, String> parameters = new HashMap();
//        if (pairs.length > 0) {
//            if (pairs.length % 2 != 0) {
//                throw new IllegalArgumentException("pairs must be even.");
//            }
//
//            for(int i = 0; i < pairs.length; i += 2) {
//                parameters.put(pairs[i], pairs[i + 1]);
//            }
//        }
//
//        return parameters;
//    }
//
//    public static Map<String, String> toMap(Properties properties) {
//        if (properties == null) {
//            return new HashMap(0);
//        } else {
//            Map<String, String> map = new HashMap(properties.size());
//            Iterator var2 = properties.entrySet().iterator();
//
//            while(var2.hasNext()) {
//                Map.Entry<Object, Object> entry = (Map.Entry)var2.next();
//                map.put(entry.getKey().toString(), entry.getValue().toString());
//            }
//
//            return map;
//        }
//    }
//
//    public static <T> List<T> getLeftDiff(List<T> list1, List<T> list2) {
//        if (isEmpty((Collection)list2)) {
//            return list1;
//        } else {
//            List<T> list = new ArrayList();
//            if (isNotEmpty((Collection)list1)) {
//                Iterator var3 = list1.iterator();
//
//                while(var3.hasNext()) {
//                    T o = var3.next();
//                    if (!list2.contains(o)) {
//                        list.add(o);
//                    }
//                }
//            }
//
//            return list;
//        }
//    }
//
//    public static void infoMap(Logger logger, Map map) {
//        if (map != null && !map.isEmpty()) {
//            int i = 0;
//            Iterator iterator = map.keySet().iterator();
//
//            while(iterator.hasNext()) {
//                ++i;
//                Object key = iterator.next();
//                Object value = map.get(key);
//                if (logger.isInfoEnabled()) {
//                    logger.info("(" + i + ")" + key + " -> " + UtilFastjson.toJSONString(value));
//                }
//            }
//        } else if (logger.isInfoEnabled()) {
//            logger.info("this map is build!");
//        }
//
//    }
//
//    public static void infoArray(Logger logger, Object[] objs) {
//        if (objs != null && objs.length != 0) {
//            int i = 0;
//            Object[] var3 = objs;
//            int var4 = objs.length;
//
//            for(int var5 = 0; var5 < var4; ++var5) {
//                Object obj = var3[var5];
//                ++i;
//                if (obj != null && logger.isInfoEnabled()) {
//                    logger.info("(" + i + ")" + UtilFastjson.toJSONString(obj));
//                }
//            }
//        } else if (logger.isInfoEnabled()) {
//            logger.info("this objs is build!");
//        }
//
//    }
//
//    public static void infoCollection(Logger logger, Collection objs) {
//        if (objs != null && objs.size() != 0) {
//            int i = 0;
//            Iterator var3 = objs.iterator();
//
//            while(var3.hasNext()) {
//                Object obj = var3.next();
//                ++i;
//                if (obj != null && logger.isInfoEnabled()) {
//                    logger.info("(" + i + ")" + UtilFastjson.toJSONString(obj));
//                }
//            }
//        } else if (logger.isInfoEnabled()) {
//            logger.info("this objs is build!");
//        }
//
//    }
//}