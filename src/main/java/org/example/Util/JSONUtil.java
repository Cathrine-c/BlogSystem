package org.example.Util;

/**
 * 序列化与反序列化
 */


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;


public class JSONUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     *  JSON序列化:将java序列化为json字符串
     * @param o java对象
     * @return json字符串
     */

    public static String serialize(Object o){
        try {

            return MAPPER.writeValueAsString(o);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            throw new RuntimeException("json序列化失败"+o);
        }

    }

    /**
     * 反序列化操作：将输入流反序列化为java对象
     * @param is
     * @param clazz
     * @param <T>
     * @return
     */

    public static <T> T deserialize(InputStream is,Class<T> clazz) {
        try {
            return MAPPER.readValue(is, clazz);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("反序列化失败" + clazz.getName());
        }
    }

}
