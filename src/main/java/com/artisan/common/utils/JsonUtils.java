package com.artisan.common.utils;

import com.google.gson.stream.JsonReader;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/**
 * json处理工具类
 * @author Leeyom Wang
 * @date 2017年10月19日 12:05
 */
public class JsonUtils {

    /**
     * 解析json字符串
     * @param json json字符串：{"name":"jack","age":12}
     * @return 转换后的map键值对
     */
    public static Map<String, Object> toParam(String json) {
        Map<String, Object> map = new HashMap<String, Object>();
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true); // 在宽松模式下解析
        try {
            reader.beginObject(); // 开始解析一个新的对象
            while (reader.hasNext()) {
                String key = reader.nextName();
                String value = reader.nextString();
                map.put(key, value);
            }
            reader.endObject(); // 结束对象的解析
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * json拼串操作
     * @param list  集合
     * @param count
     * @return
     */
    public static String list2Json(List<?> list, int count) {
        Map map = new HashMap();
        map.put("rows", list);
        map.put("total", count);
        JSONObject jsonObject = JSONObject.fromObject(map);
        return jsonObject.toString();
    }

    /**
     * 按逗号截取字符串获取整型参数
     * @param string 需要截取的字符串
     * @return 整形数组
     */
    public static List<Integer> strToList(String string) {
        String[] split = string.split(",");
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
        }
        List<String> asList = Arrays.asList(split);
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < asList.size(); i++) {
            list.add(Integer.parseInt(asList.get(i)));
        }
        return list;
    }
}
