package org.rcisoft.base.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GaoLiwei
 * @date 2019/3/20
 */
public class ZhnyUtils {


    /**
     * 自动更新实体类的属性
     *     id为实体类中主键的命名
     *     objects中为其他属性，map的key是属性命名，value是要更新的值
     *
     * @author GaoLiWei
     * @date 15:15 2019/3/20
     **/
    public static List<?> updateEntity(List<?> list, String id, Map<String, Object> map) {
        if (null != list && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                try {
                    Class<?> aClass = list.get(0).getClass();
                    Field[] declaredFields = aClass.getDeclaredFields();
                    if (declaredFields.length > 0) {
                        Type type = null;
                        Method method = null;
                        if (StringUtils.isNotEmpty(id)){
                            for (int l = 0; l<declaredFields.length; l++){
                                if (id.equals(declaredFields[l].getName())){
                                    type = declaredFields[l].getType();
                                    break;
                                }
                            }
                            String idMethodName = "set" + id.substring(0, 1).toUpperCase() + id.substring(1);
                            method = aClass.getMethod(idMethodName,(Class<?>) type);
                            method.invoke(list.get(i), UuidUtil.create32());
                        }
                        //处理其他
                        if (!map.isEmpty()){
                            for (Map.Entry<String, Object> entry : map.entrySet()) {
                                for (int l = 0; l<declaredFields.length; l++){
                                    if (entry.getKey().equals(declaredFields[l].getName())){
                                        type = declaredFields[l].getType();
                                        break;
                                    }
                                }
                                String otherMethodName = "set" + entry.getKey().substring(0, 1).toUpperCase() + entry.getKey().substring(1);
                                method = aClass.getMethod(otherMethodName, (Class<?>) type);
                                method.invoke(list.get(i), entry.getValue());
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return list;
    }


    /**
     * 根据list集合中的类的某个属性将这个list集合进行分类
     *      list要进行分类的集合
     *      name属性名
     * @author GaoLiWei
     * @date 14:47 2019/4/1
     **/
    public static Map<Object, List> groupListByName(List list, String name) {

        Map<Object, List> resultMap = new HashMap<>();
        if (list.size() > 0) {
            try {
                for (int i = 0; i < list.size(); i++) {
                    Class<?> aClass = list.get(0).getClass();
                    Field[] fields = FieldUtils.getAllFields(aClass);
                    Type type = null;
                    Method method = null;
                    for (int f = 0; f < fields.length; f++) {
                        if (name.equals(fields[f].getName())) {
                            type = fields[f].getType();
                            break;
                        }
                    }
                    String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
                    method = aClass.getMethod(methodName);
                    Object key = method.invoke(list.get(i));
                    //如果map中已经存在了key，则直接将这个数据归到这个分组下
                    if (resultMap.containsKey(key)) {
                        resultMap.get(key).add(list.get(i));
                    } else {//如果这个设备ID不再map的key中，则说明没有这个设备id的分组，新建一个分组，将数据放进去
                        List arrayList = new ArrayList<>();
                        arrayList.add(list.get(i));
                        resultMap.put(key, arrayList);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultMap;
    }


}
